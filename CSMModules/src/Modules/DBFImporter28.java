package Modules;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jiang Youquan
 */
//两融客户证券资产dbf数据批量导入
public class DBFImporter28 extends Thread {

    String filename = "";
    JPanel msgPanel = null;
    JLabel msgLabel = null;

    public DBFImporter28(String filename, JPanel msgPanel, JLabel msgLabel) {
        this.filename = filename;
        this.msgPanel = msgPanel;
        this.msgLabel = msgLabel;
    }

    @Override
    public void run() {
        MainMenu.TIMER_STEP = 0;
        Date beginTime = new Date();
        System.out.println("正在批量导入数据...");
        try {
            //先清理stock_balance_table表数据
            Connection conn = Main.conn;
            Statement SQL = conn.createStatement();
            SQL.execute("truncate table margin_trading_stockasset");
            //在批量导入数据
            bulkImportDBF(filename, msgPanel, msgLabel);
        //分笔导入数据
        //importDBF(filename, msgPanel, msgLabel);
        } catch (IOException ex) {
            Main.logger.getLogger(DBFImporter14.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("IO异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (SQLException ex) {
            Main.logger.getLogger(DBFImporter14.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (JDBFException ex) {
            Main.logger.getLogger(DBFImporter14.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("dbf异常_1: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (ParseException ex) {
            Main.logger.getLogger(DBFImporter14.class.getName()).log(Level.SEVERE, null, ex);
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

    //dbf数据导入:批量导入
    public static void bulkImportDBF(String filename, JPanel msgPanel, JLabel msgLabel)
            throws IOException, SQLException, JDBFException, ParseException, FileNotFoundException, SQLException {

        JDBF dbf = new JDBF(filename, null);
        Connection conn = Main.conn;
        Statement SQL = conn.createStatement();
        conn.setAutoCommit(false);
        int size = 200;
        int maxRec = dbf.header.getNumberOfRecords();
        int m = maxRec / size;
        int m1 = maxRec % size;
        ProgressValues pv = new ProgressValues(0, maxRec);
        ProgressBar pb = new ProgressBar("导入两融客户证券资产dbf", pv);
        System.out.println("\n 读取记录块：记录总数：" + maxRec + ",块大小:" + size + ",块个数：" + m + ",零头：" + m1);
        //1.=================先处理整块数据===================
        for (int i = 0; i < m; i++) {
            Object records[][] = dbf.readBlock(i * size, size);
            SQL.clearBatch();
            for (int j = 0; j < size; j++) {
                Object aobj[] = records[j];//读取记录
                //取出各字段
                String fund_account = aobj[dbf.getFieldNumber("custid")].toString();
                String client_name = aobj[dbf.getFieldNumber("custname")].toString();
                String stock_code = aobj[dbf.getFieldNumber("stkcode")].toString();
                String stock_name = aobj[dbf.getFieldNumber("stkcode008")].toString();
                String stock_asset = aobj[dbf.getFieldNumber("stkasset")].toString();
                String stock_balance = aobj[dbf.getFieldNumber("stkbal")].toString();
                String stock_available = aobj[dbf.getFieldNumber("stkavl")].toString();
                String market_value = aobj[dbf.getFieldNumber("mktval")].toString();
                String cost_price = aobj[dbf.getFieldNumber("profitp019")].toString();
                String profit = aobj[dbf.getFieldNumber("profitamt")].toString();
                String fetch_date = getDateString();
                //构造SQL插入语句
                String SqlStatement = "insert margin_trading_stockasset values(" + fund_account + ",'";
                SqlStatement = SqlStatement + client_name + "','" + stock_code + "','" + stock_name + "',";
                SqlStatement = SqlStatement + stock_asset + "," + stock_balance + "," + stock_available + ",";
                SqlStatement = SqlStatement + market_value + "," + profit + "," + cost_price + ",getdate())";
                //将SQL语句加入到SQL批
                //System.out.println(SqlStatement);
                SQL.addBatch(SqlStatement);
                pv.setCurrentValue(i * size + j);
            }
            SQL.executeBatch();//执行SQL批
            conn.commit();//提交
        }
        //2.=================再处理补足一块的剩余数据===================
        Object records[][] = dbf.readBlock(m * size, m1);
        SQL.clearBatch();
        for (int j = 0; j < m1; j++) {
            Object aobj[] = records[j];//读取记录
            //取出各字段
            String fund_account = aobj[dbf.getFieldNumber("custid")].toString();
            String client_name = aobj[dbf.getFieldNumber("custname")].toString();
            String stock_code = aobj[dbf.getFieldNumber("stkcode")].toString();
            String stock_name = aobj[dbf.getFieldNumber("stkcode008")].toString();
            String stock_asset = aobj[dbf.getFieldNumber("stkasset")].toString();
            String stock_balance = aobj[dbf.getFieldNumber("stkbal")].toString();
            String stock_available = aobj[dbf.getFieldNumber("stkavl")].toString();
            String market_value = aobj[dbf.getFieldNumber("mktval")].toString();
            String cost_price = aobj[dbf.getFieldNumber("profitp019")].toString();
            String profit = aobj[dbf.getFieldNumber("profitamt")].toString();
            String fetch_date = getDateString();
            //构造SQL插入语句
            String SqlStatement = "insert margin_trading_stockasset values(" + fund_account + ",'";
            SqlStatement = SqlStatement + client_name + "','" + stock_code + "','" + stock_name + "',";
            SqlStatement = SqlStatement + stock_asset + "," + stock_balance + "," + stock_available + ",";
            SqlStatement = SqlStatement + market_value + "," + profit + "," + cost_price + ",getdate())";
            //将SQL语句加入到SQL批
           // System.out.println(SqlStatement);
            SQL.addBatch(SqlStatement);
            pv.setCurrentValue(pv.getCurrentValue() + 1);
        }
        SQL.executeBatch();//执行SQL批
        conn.commit();
        conn.setAutoCommit(true);
        SQL.close();
        dbf.close();
        pv.setCurrentValue(pv.getEndtValue());//进度结束
        msgPanel.setVisible(false);
    }

    private static String getDateString() {
        String date = "1900";
        GregorianCalendar d = new GregorianCalendar();
        int thisYear = d.get(Calendar.YEAR);
        int thisMonth = d.get(Calendar.MONTH) + 1;
        int thisDay = d.get(Calendar.DAY_OF_MONTH);

        date = String.format("%4d%02d%02d", thisYear, thisMonth, thisDay);
        return date;
    }
}
