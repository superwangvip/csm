/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Administrator
 */
//导入客户QQ号码dbf
public class DBFImporter6 extends Thread {

    String filename = "";
    JPanel msgPanel = null;
    JLabel msgLabel = null;

    public DBFImporter6(String filename, JPanel msgPanel, JLabel msgLabel) {
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
            //先清理client_QQ表数据
            Connection conn = Main.conn;
            Statement SQL = conn.createStatement();
            SQL.execute("truncate table client_QQ");
            SQL.execute("update backup_log set QQ_import_time=getdate()");
            //导入数据
            importDBF(filename, msgPanel, msgLabel);
            //更新client表里的QQ号码
            String SqlStatement = "update client set QQ=b.QQ from client a,client_QQ b" +
                    " where a.fund_account=b.fundid";
            SQL.execute(SqlStatement);
        } catch (IOException ex) {
            Main.logger.getLogger(DBFImporter6.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("IO异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (SQLException ex) {
            Main.logger.getLogger(DBFImporter6.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (JDBFException ex) {
            Main.logger.getLogger(DBFImporter6.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("dbf异常_1: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (ParseException ex) {
           Main.logger.getLogger(DBFImporter6.class.getName()).log(Level.SEVERE, null, ex);
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
    public static void importDBF(String filename, JPanel msgPanel, JLabel msgLabel)
            throws IOException, SQLException, JDBFException, ParseException, FileNotFoundException, SQLException {
        JDBF dbf = new JDBF(filename, null);
        Connection conn = Main.getConnection();
        Statement SQL = conn.createStatement();
        int maxRec = dbf.header.getNumberOfRecords();
        ProgressValues pv = new ProgressValues(0, maxRec);
        ProgressBar pb = new ProgressBar("导入客户QQ号码dbf", pv);
        for (int i = 0; i < maxRec; i++) {
            Object aobj[] = dbf.readRecord(i);//读取记录
            //取各字段
            String fundid = aobj[0].toString();
            String name = aobj[1].toString();
            String QQ = aobj[2].toString();
            //构造SQL插入语句
            String SqlStatement = "insert into client_QQ (fundid,name,QQ) ";
            SqlStatement = SqlStatement + "values(" + fundid + ",'" + name + "','" + QQ + "')";
            //执行SQL语句
            SQL.execute(SqlStatement);
            pv.setCurrentValue(i);
        }
        dbf.close();
        conn.close();
        pv.setCurrentValue(pv.getEndtValue());
    }
}
