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
//导入理财产品库存(基金资产)dbf
public class DBFImporter23 extends Thread {

    String filename = "";
    JPanel msgPanel = null;
    JLabel msgLabel = null;

    public DBFImporter23(String filename, JPanel msgPanel, JLabel msgLabel) {
        this.filename = filename;
        this.msgPanel = msgPanel;
        this.msgLabel = msgLabel;
    }

    @Override
    public void run() {
        MainMenu.TIMER_STEP=0;
        Date beginTime = new Date();
        System.out.println("正在导入数据...");
        try {
            //先清理financing表数据
            Connection conn = Main.conn;
            Statement SQL = conn.createStatement();
            SQL.execute("truncate table financing");
            //导入数据
            importDBF(filename, msgPanel, msgLabel);
            //用导入数据更新client表
            String SqlStatement = "execute update_financing ";
            //SQL.execute(SqlStatement);
        }  catch (IOException ex) {
            Main.logger.getLogger(DBFImporter23.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("IO异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (SQLException ex) {
            Main.logger.getLogger(DBFImporter23.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (JDBFException ex) {
            Main.logger.getLogger(DBFImporter23.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("dbf异常_1: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (ParseException ex) {
           Main.logger.getLogger(DBFImporter23.class.getName()).log(Level.SEVERE, null, ex);
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
        MainMenu.TIMER_STEP=0;
    }

    //dbf数据导入:分笔导入
    public static void importDBF(String filename, JPanel msgPanel, JLabel msgLabel) throws FileNotFoundException, IOException, SQLException, JDBFException, ParseException, SQLException {
        JDBF dbf = new JDBF(filename,null);
        Connection conn = Main.conn;
        Statement SQL = conn.createStatement();
        int maxRec = dbf.header.getNumberOfRecords();
        ProgressValues pv = new ProgressValues(0, maxRec);
        ProgressBar pb = new ProgressBar("导入客户基金资产数据dbf", pv);
        for (int i = 0; i < maxRec; i++) {
            Object aobj[] = dbf.readRecord(i);//读取记录
            //取出各字段
            String fund_account = aobj[dbf.getFieldNumber("fundid")].toString().trim();
            String cust_name = aobj[dbf.getFieldNumber("custname")].toString().trim();
            String of_code = aobj[dbf.getFieldNumber("ofcode")].toString().trim();
            String of_name = aobj[dbf.getFieldNumber("ofname")].toString().trim();
            String of_type = aobj[dbf.getFieldNumber("openfun008")].toString().trim();
            String of_avl = aobj[dbf.getFieldNumber("ofavl")].toString().trim();
            String market_value = aobj[dbf.getFieldNumber("marketv012")].toString().trim();
            String balance = aobj[dbf.getFieldNumber("ofbal")].toString().trim();
            String current015 = aobj[dbf.getFieldNumber("current015")].toString().trim();
            //构造SQ插入L语句
            String SqlStatement = "insert financing values(" + fund_account+",'"+cust_name+"','";
            SqlStatement=SqlStatement+ of_code+"','"+of_name+"','"+of_type+"',"+of_avl+",";
            SqlStatement=SqlStatement+market_value+","+balance+","+current015+")";
            //执行SQL语句
            //System.out.println(SqlStatement);
            SQL.execute(SqlStatement);
            pv.setCurrentValue(i);
        }
        dbf.close();
        pv.setCurrentValue(pv.getEndtValue());
        msgPanel.setVisible(false);
    }
}
