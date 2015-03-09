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
//基金交易数据dbf数据批量导入
public class DBFImporter29 extends Thread {

    String filename = "";
    JPanel msgPanel = null;
    JLabel msgLabel = null;

    public DBFImporter29(String filename, JPanel msgPanel, JLabel msgLabel) {
        this.filename = filename;
        this.msgPanel = msgPanel;
        this.msgLabel = msgLabel;
    }

    @Override
    @SuppressWarnings("static-access")
    public void run() {
        MainMenu.TIMER_STEP = 0;
        Date beginTime = new Date();
        System.out.println("正在批量导入数据...");
        try {
            /*先清理stock_balance_table表数据
            Connection conn = Main.conn;
            Statement SQL = conn.createStatement();
           // SQL.execute("truncate table finance_product_trading");*/
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
        ProgressBar pb = new ProgressBar("导入基金交易信息dbf", pv);
        System.out.println("\n 读取记录块：记录总数：" + maxRec + ",块大小:" + size + ",块个数：" + m + ",零头：" + m1);
        //1.=================先处理整块数据===================
        for (int i = 0; i < m; i++) {
            Object records[][] = dbf.readBlock(i * size, size);
            SQL.clearBatch();
            for (int j = 0; j < size; j++) {
                Object aobj[] = records[j];//读取记录
                //取出各字段
                String fund_account = aobj[dbf.getFieldNumber("fundid")].toString();
                String client_name = aobj[dbf.getFieldNumber("custname")].toString();
                String product_code = aobj[dbf.getFieldNumber("ofcode")].toString();
                String product_name = aobj[dbf.getFieldNumber("ofname")].toString();
                String trd_id_name = aobj[dbf.getFieldNumber("trdidname")].toString();

                String nav = aobj[dbf.getFieldNumber("nav")].toString();
                String match_amt = aobj[dbf.getFieldNumber("matchamt")].toString();
                String confirmed_amt = aobj[dbf.getFieldNumber("confirm011")].toString();
                String match_qty = aobj[dbf.getFieldNumber("matchqty")].toString();
                String match_date = aobj[dbf.getFieldNumber("matchdate")].toString();
                //构造SQL插入语句
                String SqlStatement = "insert finance_product_trading values(" + fund_account + ",'";
                SqlStatement = SqlStatement + client_name + "','" + product_code + "','";
                SqlStatement = SqlStatement + product_name + "','" + trd_id_name + "'," + nav + ",";
                SqlStatement = SqlStatement + match_amt + "," + confirmed_amt + "," + match_qty + ",'" + match_date + "')";
                System.out.println(SqlStatement);
                //将SQL语句加入到SQL批
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
            String fund_account = aobj[dbf.getFieldNumber("fundid")].toString();
            String client_name = aobj[dbf.getFieldNumber("custname")].toString();
            String product_code = aobj[dbf.getFieldNumber("ofcode")].toString();
            String product_name = aobj[dbf.getFieldNumber("ofname")].toString();
            String trd_id_name = aobj[dbf.getFieldNumber("trdidname")].toString();

            String nav = aobj[dbf.getFieldNumber("nav")].toString();
            String match_amt = aobj[dbf.getFieldNumber("matchamt")].toString();
            String confirmed_amt = aobj[dbf.getFieldNumber("confirm011")].toString();
            String match_qty = aobj[dbf.getFieldNumber("matchqty")].toString();
            String match_date = aobj[dbf.getFieldNumber("matchdate")].toString();
            //构造SQL插入语句
            String SqlStatement = "insert finance_product_trading values(" + fund_account + ",'";
            SqlStatement = SqlStatement + client_name + "','" + product_code + "','";
            SqlStatement = SqlStatement + product_name + "','" + trd_id_name + "'," + nav + ",";
            SqlStatement = SqlStatement + match_amt + "," + confirmed_amt + "," + match_qty + ",'" + match_date + "')";
            System.out.println(SqlStatement);
            //将SQL语句加入到SQL批
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
