package Modules;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jiang Youquan
 */
//历史成交明细dbf数据批量导入
public class DBFImporter19 extends Thread {

    String filename = "";
    JPanel msgPanel = null;
    JLabel msgLabel = null;

    public DBFImporter19(String filename, JPanel msgPanel, JLabel msgLabel) {
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
            bulkImportDBF(filename, msgPanel, msgLabel);
        } catch (IOException ex) {
            Main.logger.getLogger(DBFImporter19.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("IO异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (SQLException ex) {
            Main.logger.getLogger(DBFImporter19.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (JDBFException ex) {
            Main.logger.getLogger(DBFImporter19.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("dbf异常_1: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (ParseException ex) {
           Main.logger.getLogger(DBFImporter19.class.getName()).log(Level.SEVERE, null, ex);
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
        ProgressBar pb = new ProgressBar("导入历史成交明细dbf", pv);
        System.out.println("\n 读取记录块：记录总数：" + maxRec + ",块大小:" + size + ",块个数：" + m + ",零头：" + m1);
        //1.=================先处理整块数据===================
        for (int i = 0; i < m; i++) {
            Object records[][] = dbf.readBlock(i * size, size);
            SQL.clearBatch();
            for (int j = 0; j < size; j++) {
                Object aobj[] = records[j];//读取记录
                //取出各字段
                String fund_id = aobj[dbf.getFieldNumber("fundid")].toString();
                String cust_name = aobj[dbf.getFieldNumber("custname")].toString();
                String market_name = aobj[dbf.getFieldNumber("marketname")].toString();
                String market = "";
                if (market_name != null && market_name.contains("沪")) {
                    market = "上海";
                }
                if (market_name != null && market_name.contains("深")) {
                    market = "深圳";
                }
                String stock_code = aobj[dbf.getFieldNumber("stkcode")].toString();
                String stock_name = aobj[dbf.getFieldNumber("stkname")].toString();
                String bs_flag = aobj[dbf.getFieldNumber("bsflagname")].toString();
                String match_price = aobj[dbf.getFieldNumber("matchprice")].toString();
                String match_quantity = aobj[dbf.getFieldNumber("matchqty")].toString();
                String match_amount = aobj[dbf.getFieldNumber("matchamt")].toString();
                String commision = aobj[dbf.getFieldNumber("fee_sxf")].toString();
                String stamp_duty = aobj[dbf.getFieldNumber("fee_yhs")].toString();
                String clear_amount = aobj[dbf.getFieldNumber("fundeffect")].toString();
                String match_date = aobj[dbf.getFieldNumber("bizdate")].toString().trim();
                //检查是否重复导入相同数据
                if (i == 0 && j == 0) {
                    if (isDataRepeated("match_details", match_date)) {
                        JOptionPane.showMessageDialog(null, "数据重复转入!,请重新选择要转入的dbf");
                        msgPanel.setVisible(false);
                        SQL.close();
                        dbf.close();
                        conn.setAutoCommit(true);
                        pv.setCurrentValue(pv.getEndtValue());//进度结束
                        return;
                    }
                }
                //构造SQ插入L语句
                String SqlStatement = "insert into match_details values(";
                SqlStatement = SqlStatement + fund_id + ",'" + cust_name + "','" + stock_code + "','" + stock_name + "','";
                SqlStatement = SqlStatement + bs_flag + "'," + match_price + "," + match_quantity + "," + match_amount + ",";
                SqlStatement = SqlStatement + commision + "," + stamp_duty + "," + clear_amount + ",'" + match_date + "','" + "未清算" + "','" + market + "')";
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
            Object aobj[] = records[j];
            //取出各字段
            String fund_id = aobj[dbf.getFieldNumber("fundid")].toString();
            String cust_name = aobj[dbf.getFieldNumber("custname")].toString();
            String market_name = aobj[dbf.getFieldNumber("marketname")].toString();
            String market = "";
            if (market_name != null && market_name.contains("沪")) {
                market = "上海";
            }
            if (market_name != null && market_name.contains("深")) {
                market = "深圳";
            }
            String stock_code = aobj[dbf.getFieldNumber("stkcode")].toString();
            String stock_name = aobj[dbf.getFieldNumber("stkname")].toString();
            String bs_flag = aobj[dbf.getFieldNumber("bsflagname")].toString();
            String match_price = aobj[dbf.getFieldNumber("matchprice")].toString();
            String match_quantity = aobj[dbf.getFieldNumber("matchqty")].toString();
            String match_amount = aobj[dbf.getFieldNumber("matchamt")].toString();
            String commision = aobj[dbf.getFieldNumber("fee_sxf")].toString();
            String stamp_duty = aobj[dbf.getFieldNumber("fee_yhs")].toString();
            String clear_amount = aobj[dbf.getFieldNumber("fundeffect")].toString();
            String match_date = aobj[dbf.getFieldNumber("bizdate")].toString().trim();
            //构造SQ插入L语句
            if (!fund_id.equals("0")) {//排除特殊记录
                String SqlStatement = "insert into match_details values(";
                SqlStatement = SqlStatement + fund_id + ",'" + cust_name + "','" + stock_code + "','" + stock_name + "','";
                SqlStatement = SqlStatement + bs_flag + "'," + match_price + "," + match_quantity + "," + match_amount + ",";
                SqlStatement = SqlStatement + commision + "," + stamp_duty + "," + clear_amount + ",'" + match_date + "','" + "未清算" + "','" + market + "')";
                //将SQL语句加入到SQL批
                SQL.addBatch(SqlStatement);
            }
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

    //检查是否重复导入相同数据
    public static boolean isDataRepeated(String table, String year_month) {
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
            Logger.getLogger(DBFImporter19.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repeated;
    }
}
