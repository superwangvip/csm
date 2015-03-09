package Modules;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jiang Youquan
 */
//当日委托dbf批量导入
public class DBFImporter15 extends Thread {

    String filename = "";
    JPanel msgPanel = null;
    JLabel msgLabel = null;

    public DBFImporter15(String filename, JPanel msgPanel, JLabel msgLabel) {
        this.filename = filename;
        this.msgPanel = msgPanel;
        this.msgLabel = msgLabel;
    }

    @Override
    public void run() {
                MainMenu.TIMER_STEP=0;
        Date beginTime = new Date();
        System.out.println("正在批量导入数据...");
        try {
            //批量导入数据
            bulkImportDBF(filename, msgPanel, msgLabel);
        }  catch (IOException ex) {
            Main.logger.getLogger(DBFImporter15.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("IO异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (SQLException ex) {
            Main.logger.getLogger(DBFImporter15.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (JDBFException ex) {
            Main.logger.getLogger(DBFImporter15.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("dbf异常_1: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (ParseException ex) {
           Main.logger.getLogger(DBFImporter15.class.getName()).log(Level.SEVERE, null, ex);
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
        MainMenu.TIMER_STEP=1;
    }

    //dbf数据导入:批量导入
    public static void bulkImportDBF(String filename, JPanel msgPanel, JLabel msgLabel)
            throws IOException, SQLException, JDBFException, ParseException, FileNotFoundException, SQLException {

        JDBF dbf = new JDBF(filename,null);
        Connection conn = Main.conn;
        Statement SQL = conn.createStatement();
        conn.setAutoCommit(false);
        int size = 200;
        int maxRec = dbf.header.getNumberOfRecords();
        int m = maxRec / size;
        int m1 = maxRec % size;
        ProgressValues pv = new ProgressValues(0, maxRec);
        ProgressBar pb = new ProgressBar("导入当日委托dbf", pv);
        System.out.println("\n 读取记录块：记录总数：" + maxRec + ",块大小:" + size + ",块个数：" + m + ",零头：" + m1);
        //1.=================先处理整块数据===================
        for (int i = 0; i < m; i++) {
            Object records[][] = dbf.readBlock(i * size, size);
            SQL.clearBatch();
            for (int j = 0; j < size; j++) {
                Object aobj[] = records[j];//读取记录
                //取出各字段
                String fund_account = aobj[0].toString();
                String client_name = aobj[1].toString();
                String order_time = aobj[2].toString();
                String stock_code = aobj[3].toString();
                String stock_name = aobj[4].toString();
                String bs_flag = aobj[5].toString();
                String order_price = aobj[6].toString();
                String order_quantity = aobj[7].toString();
                String match_quantity = aobj[8].toString();
                String cancel_quantity = aobj[9].toString();
                String match_amount = aobj[10].toString();
                String order_date = getDateString();
                //检查数据是否被重复导入
                if (i == 0) {
                    if (isDataRepeated("order_table", order_date)) {
                        //new JFrameWarning("数据重复转入!,请重新选择要转入的dbf").setVisible(true);
                        JOptionPane.showMessageDialog(null, "数据重复转入!,请重新选择要转入的dbf");
                        msgPanel.setVisible(false);
                        pv.setCurrentValue(pv.getEndtValue());//进度结束
                        return;
                    }
                }
                //构造SQL插入语句
                String SqlStatement = "insert order_table values(" + fund_account + ",'";
                SqlStatement = SqlStatement + client_name + "','" + order_time + "','" + stock_code + "','" + stock_name + "','";
                SqlStatement = SqlStatement + bs_flag + "'," + order_price + "," + order_quantity + "," + cancel_quantity + "," +
                        match_quantity + "," + match_amount + ",'" + order_date + "')";
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
            String fund_account = aobj[0].toString();
            String client_name = aobj[1].toString();
            String order_time = aobj[2].toString();
            String stock_code = aobj[3].toString();
            String stock_name = aobj[4].toString();
            String bs_flag = aobj[5].toString();
            String order_price = aobj[6].toString();
            String order_quantity = aobj[7].toString();
            String match_quantity = aobj[8].toString();
            String cancel_quantity = aobj[9].toString();
            String match_amount = aobj[10].toString();
            String order_date = getDateString();
            //构造SQL插入语句
            String SqlStatement = "insert order_table values(" + fund_account + ",'";
            SqlStatement = SqlStatement + client_name + "','" + order_time + "','" + stock_code + "','" + stock_name + "','";
            SqlStatement = SqlStatement + bs_flag + "'," + order_price + "," + order_quantity + "," + cancel_quantity + "," +
                    match_quantity + "," + match_amount + ",'" + order_date + "')";
            //将SQL语句加入到SQL批
            SQL.addBatch(SqlStatement);
            pv.setCurrentValue(pv.getCurrentValue() + 1);
        }
        SQL.executeBatch();//执行SQL批
        conn.commit();//提交
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

    static boolean isDataRepeated(String table, String year_month) {
        boolean repeated = false;
        Integer recn = 0;
        String sql = "";
        if (table.equalsIgnoreCase("month_end_asset")) {
            sql = "select count(*) from month_end_asset where convert(char(6),start_date,112)='" + year_month + "'";
        } else if (table.equalsIgnoreCase("transfer")) {
            sql = "select count(*) from transfer where convert(char(6),operate_date,112)='" + year_month + "'";
        } else if (table.equalsIgnoreCase("open_cancel_log")) {
            sql = "select count(*) from open_cancel_log where convert(char(6),operate_date,112)='" + year_month + "'";
        } else if (table.equalsIgnoreCase("match_details")) {
            sql = "select count(*) from match_details where convert(char(8),match_date,112)='" + year_month + "'";
        } else if (table.equalsIgnoreCase("order_table")) {
            sql = "select count(*) from order_table where convert(char(8),order_date,112)='" + year_month + "'";
        }
        try {
            PreparedStatement SqlStatement = Main.conn.prepareStatement(sql);
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    recn = Integer.valueOf(SqlResult.getInt(1));
                }
            }
            if (recn > 0) {
                repeated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBFImporter15.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repeated;
    }
}
