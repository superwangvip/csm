package Modules;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jiang Youquan
 */
//导入股票池dbf
public class DBFImporter12 extends Thread {

    String filename = "";
    JPanel msgPanel = null;
    JLabel msgLabel = null;

    public DBFImporter12(String filename, JPanel msgPanel, JLabel msgLabel) {
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
            SQL.execute("truncate table stock_pool");
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
        ProgressBar pb = new ProgressBar("导入股票池dbf", pv);
        for (int i = 0; i < maxRec; i++) {
            Object aobj[] = dbf.readRecord(i);//读取记录
            //取出各字段
            String market = aobj[0].toString();
            String stock_code = aobj[1].toString();
            String stock_name = aobj[2].toString();
            String last_close_price = aobj[3].toString();
            String open_price = aobj[4].toString();
            String current_price = aobj[5].toString();
            String fluctuation_rate = aobj[6].toString();
            String advised_buying_price = aobj[7].toString();
            String advised_time = aobj[8].toString();
            String buying_time = aobj[9].toString();
            String buying_count = aobj[10].toString();
            String advised_object_price = aobj[11].toString();
            String object_count = aobj[12].toString();
            String advised_selling_price = aobj[13].toString();
            String selling_count = aobj[14].toString();
            String profit_rate = aobj[15].toString();
            String investment_adviser_id = aobj[16].toString();
            String investment_adviser = aobj[17].toString();
            String memo = aobj[18].toString();
            String client_informed_count = aobj[19].toString();
            String client_buying_count = aobj[20].toString();
            String status = aobj[21].toString();
            String suspension_flag = aobj[22].toString();
            String buying_price = aobj[23].toString();
            String stop_rate = aobj[24].toString();
            String stop_price = aobj[25].toString();
            //构造SQ插入L语句
            String SqlStatement = "insert stock_pool values('";
            SqlStatement = SqlStatement + market + "','" + stock_code + "','" + stock_name + "',";
            SqlStatement = SqlStatement + last_close_price + "," + open_price + "," + current_price + ",";
            SqlStatement = SqlStatement + fluctuation_rate + "," + advised_buying_price + ",'" + advised_time + "','";
            SqlStatement = SqlStatement + buying_time + "'," + buying_count + ",";
            SqlStatement = SqlStatement + advised_object_price + "," + object_count + "," + advised_selling_price + ",";
            SqlStatement = SqlStatement + selling_count + "," + profit_rate + "," + investment_adviser_id + ",'";
            SqlStatement = SqlStatement + investment_adviser + "','" + memo + "'," + client_informed_count + ",";
            SqlStatement = SqlStatement + client_buying_count + ",'" + status + "','" + suspension_flag + "',";
            SqlStatement = SqlStatement + buying_price + "," + stop_rate + "," + stop_price + ")";
            //执行SQL语句
            SQL.execute(SqlStatement);
            pv.setCurrentValue(i);
        }
        dbf.close();
        pv.setCurrentValue(pv.getEndtValue());
        msgPanel.setVisible(false);
    }
}
