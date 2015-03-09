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
//导入回购交易资料
public class DBFImporter26 extends Thread {

    String filename = "";
    JPanel msgPanel = null;
    JLabel msgLabel = null;

    public DBFImporter26(String filename, JPanel msgPanel, JLabel msgLabel) {
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
            //导入数据
            importDBF(filename, msgPanel, msgLabel);

        } catch (IOException ex) {
            Main.logger.getLogger(DBFImporter26.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("IO异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (SQLException ex) {
            Main.logger.getLogger(DBFImporter26.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (JDBFException ex) {
            Main.logger.getLogger(DBFImporter26.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("dbf异常_1: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (ParseException ex) {
           Main.logger.getLogger(DBFImporter26.class.getName()).log(Level.SEVERE, null, ex);
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
        JDBF dbf = new JDBF(filename,null);
        Connection conn = Main.getConnection();
        Statement SQL = conn.createStatement();
        int maxRec = dbf.header.getNumberOfRecords();
        ProgressValues pv = new ProgressValues(0, maxRec);
        ProgressBar pb = new ProgressBar("导入回购交易资料dbf", pv);
        for (int i = 0; i < maxRec; i++) {
            Object aobj[] = dbf.readRecord(i);//读取记录
            //取各字段
            String fund_account = aobj[dbf.getFieldNumber("fundid")].toString();
            String client_name = aobj[dbf.getFieldNumber("custname")].toString();
            String stock_code = aobj[dbf.getFieldNumber("stkcode")].toString();
            String stock_name = aobj[dbf.getFieldNumber("stkname")].toString();
            String order_price = aobj[dbf.getFieldNumber("orderprice")].toString();
            String order_quantity = aobj[dbf.getFieldNumber("orderqty")].toString();
            String match_amount = aobj[dbf.getFieldNumber("matchamt")].toString();
            String bsflag = aobj[dbf.getFieldNumber("bsflagname")].toString();
            String stock_type = aobj[dbf.getFieldNumber("stktype014")].toString();
            String order_date=aobj[dbf.getFieldNumber("orderdate")].toString();
            String match_date=aobj[dbf.getFieldNumber("matchdate")].toString();
            //构造SQL插入语句
            String SqlStatement = "insert into repo_trading values(" + fund_account + ",'" + client_name + "','";
            SqlStatement = SqlStatement + stock_code + "','" + stock_name + "',";
            SqlStatement = SqlStatement + order_price + "," + order_quantity + "," + match_amount + ",'";
            SqlStatement = SqlStatement + bsflag + "','" + stock_type +"','"+order_date+"','"+match_date+ "')";
            System.out.println(SqlStatement);
            SQL.execute(SqlStatement);//执行SQL插入语句
            pv.setCurrentValue(i);
        }
        dbf.close();
        conn.close();
        pv.setCurrentValue(pv.getEndtValue());
    }
}
