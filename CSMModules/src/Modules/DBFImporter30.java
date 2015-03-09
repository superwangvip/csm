package Modules;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jiang Youquan
 */
//导入理财产品信息dbf
public class DBFImporter30 extends Thread {

    String filename = "";
    JPanel msgPanel = null;
    JLabel msgLabel = null;

    public DBFImporter30(String filename, JPanel msgPanel, JLabel msgLabel) {
        this.filename = filename;
        this.msgPanel = msgPanel;
        this.msgLabel = msgLabel;
    }

    @Override
    public void run() {
        MainMenu.TIMER_STEP = 0;
        Date beginTime = new Date();
        System.out.println("正在导入数据...");
        try {
            //清理股票池数据
            Connection conn = Main.conn;
            Statement SQL = conn.createStatement();
            SQL.execute("truncate table finance_product_info");
            //导入dbf
            importDBF(filename, msgPanel, msgLabel);
        } catch (IOException ex) {
            Main.logger.getLogger(DBFImporter1.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("IO异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (SQLException ex) {
            Main.logger.getLogger(DBFImporter1.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (JDBFException ex) {
            Main.logger.getLogger(DBFImporter1.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("dbf异常_1: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (ParseException ex) {
            Main.logger.getLogger(DBFImporter1.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("dbf异常_2: " + ex.getLocalizedMessage()).setVisible(true);
        }
        msgPanel.setVisible(false);
        Date endTime = new Date();
        long elapse = (endTime.getTime() - beginTime.getTime());
        String elapseStr = "";
        if (elapse >= 1000) {
            elapseStr = elapse / 1000 + "秒";
        } else {
            elapseStr = elapse + "毫秒";
        }
        System.out.println("数据导入完毕,耗时:" + elapseStr);
        MainMenu.TIMER_STEP = 1;
    }

    //dbf数据导入:分笔导入
    public static void importDBF(String filename, JPanel msgPanel, JLabel msgLabel) throws FileNotFoundException, IOException, SQLException, JDBFException, ParseException, SQLException {
        JDBF dbf = new JDBF(filename, null);
        Connection conn = Main.conn;
        Statement SQL = conn.createStatement();
        int maxRec = dbf.header.getNumberOfRecords();
        ProgressValues pv = new ProgressValues(0, maxRec);
        ProgressBar pb = new ProgressBar("导入理财产品dbf", pv);
        for (int i = 0; i < maxRec; i++) {
            Object aobj[] = dbf.readRecord(i);//读取记录
            //取出各字段
            String product_code = aobj[0].toString();
            String product_name = aobj[1].toString();
            String sale_time1 = aobj[2].toString();
            String sale_time2 = aobj[3].toString();
            String set_time = aobj[4].toString();
            String due_time = aobj[5].toString();
            String bonus_time = aobj[6].toString();
            String mean_deal = aobj[7].toString();
            String commision_rate = aobj[8].toString();
            String anticipated_return_rate = aobj[9].toString();
            String liquidity = aobj[10].toString();
            String risk_level = aobj[11].toString();
            String product_group = aobj[12].toString();
            String product_type = aobj[13].toString();
            String product_strategy = aobj[14].toString();
            String doc_id = aobj[15].toString();
            String memo = aobj[16].toString();
            //构造SQ插入L语句
            String sql = "insert finance_product_info values('";
            sql = sql + product_code + "','" + product_name+"'";
            if (sale_time1.equals("")) {
                sql = sql + ",null";
            } else {
                sql = sql + ",'" + sale_time1+"'";
            }

            if (sale_time2.equals("")) {
                sql = sql + ",null";
            } else {
                sql = sql + ",'" + sale_time2+"'";
            }

            if (set_time.equals("")) {
                sql = sql + ",null";
            } else {
                sql = sql + ",'" + set_time+"'";
            }

            if (due_time.equals("")) {
                sql = sql + ",null";
            } else {
                sql = sql + ",'" + due_time+"'";
            }

            if (bonus_time.equals("")) {
                sql = sql + ",null";
            } else {
                sql = sql + ",'"+ bonus_time+"'";
            }
            sql = sql + ",'"+mean_deal + "','" + commision_rate + "','" + anticipated_return_rate + "'," + liquidity + ",'" + risk_level + "','" + product_group + "','" + product_type + "','" + product_strategy + "','" + doc_id + "','" + memo + "')";
            //执行SQL语句
            System.out.println(sql);
            SQL.execute(sql);
            pv.setCurrentValue(i);
        }
        dbf.close();
        pv.setCurrentValue(pv.getEndtValue());
        msgPanel.setVisible(false);
    }
}
