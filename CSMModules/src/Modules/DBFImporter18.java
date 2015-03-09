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
//月末资产dbf数据批量导入
public class DBFImporter18 extends Thread {

    String filename = "";
    JPanel msgPanel = null;
    JLabel msgLabel = null;

    public DBFImporter18(String filename, JPanel msgPanel, JLabel msgLabel) {
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
            Connection conn = Main.conn;
            Statement SQL = conn.createStatement();
            //导入数据
            bulkImportDBF(filename, msgPanel, msgLabel);
            //接口转换
            SQL.execute("data_transform");
        } catch (IOException ex) {
            Main.logger.getLogger(DBFImporter18.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("IO异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (SQLException ex) {
            Main.logger.getLogger(DBFImporter18.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (JDBFException ex) {
            Main.logger.getLogger(DBFImporter18.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("dbf异常_1: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (ParseException ex) {
            Main.logger.getLogger(DBFImporter18.class.getName()).log(Level.SEVERE, null, ex);
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
        ProgressBar pb = new ProgressBar("导入月末资产数据dbf", pv);
        System.out.println("\n 读取记录块：记录总数：" + maxRec + ",块大小:" + size + ",块个数：" + m + ",零头：" + m1);
        //1.=================先处理整块数据===================
        for (int i = 0; i < m; i++) {
            Object records[][] = dbf.readBlock(i * size, size);
            SQL.clearBatch();
            for (int j = 0; j < size; j++) {
                Object aobj[] = records[j];//读取记录
                //取出各字段
                String start_date = aobj[dbf.getFieldNumber("startdate")].toString().trim();
                String end_date = aobj[dbf.getFieldNumber("enddate")].toString();
                String branch = aobj[dbf.getFieldNumber("branch")].toString();
                String fund_id = aobj[dbf.getFieldNumber("fundid")].toString();
                String cust_name = aobj[dbf.getFieldNumber("name")].toString();
                String fund_status = aobj[dbf.getFieldNumber("statusname")].toString();
                String kind_name = aobj[dbf.getFieldNumber("kindname")].toString();
                String top_asset = aobj[dbf.getFieldNumber("topasset")].toString();
                String end_asset = aobj[dbf.getFieldNumber("endasset")].toString();
                String balance = aobj[dbf.getFieldNumber("balance")].toString();
                String end_stockval = aobj[dbf.getFieldNumber("endstockva")].toString();
                String input_money = aobj[dbf.getFieldNumber("inputmoney")].toString();
                String output_money = aobj[dbf.getFieldNumber("outputmone")].toString();
                String input_stock = aobj[dbf.getFieldNumber("inputstock")].toString();
                String output_stock = aobj[dbf.getFieldNumber("outputstoc")].toString();
                String match_amount = aobj[dbf.getFieldNumber("matchamt")].toString();
                String top_stkval = aobj[dbf.getFieldNumber("topmarketv")].toString();
                String sxf = aobj[dbf.getFieldNumber("sxf")].toString();
                String j_sxf = aobj[dbf.getFieldNumber("j_sxf")].toString();
                String commision_rate = aobj[dbf.getFieldNumber("commrate")].toString();
                String open_date = aobj[dbf.getFieldNumber("opendate")].toString();
                String agent_no = aobj[dbf.getFieldNumber("agentno")].toString();
                String agent_name = aobj[dbf.getFieldNumber("agentname")].toString();
                String id_no = " ";
                String year_month = start_date.substring(0, 6);
                //检查是否重复导入相同数据
                if (i == 0 && j == 0) {
                    if (isDataRepeated("month_end_asset", year_month)) {
                        //new JFrameWarning("数据重复转入!,请重新选择要转入的dbf").setVisible(true);
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
                String SqlStatement = "insert month_end_asset values('" + start_date + "','" + end_date + "','" + branch + "'," + fund_id + ",'" + cust_name + "','";
                SqlStatement = SqlStatement + fund_status + "','" + kind_name + "'," + top_asset + "," + end_asset + "," + balance + "," + end_stockval + ",";
                SqlStatement = SqlStatement + input_money + "," + output_money + "," + input_stock + "," + output_stock + "," + match_amount + ",";
                SqlStatement = SqlStatement + sxf + "," + j_sxf + "," + commision_rate + ",'" + open_date + "','" + agent_no + "','" + agent_name + "','" + id_no + "'," + 0 + "," + top_stkval + ")";
                //将SQL语句加入到SQL批
                SQL.addBatch(SqlStatement);
                pv.setCurrentValue(i * size + j);
                System.out.println(i * size + j);
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
            String start_date = aobj[dbf.getFieldNumber("startdate")].toString().trim();
            String end_date = aobj[dbf.getFieldNumber("enddate")].toString();
            String branch = aobj[dbf.getFieldNumber("branch")].toString();
            String fund_id = aobj[dbf.getFieldNumber("fundid")].toString();
            String cust_name = aobj[dbf.getFieldNumber("name")].toString();
            String fund_status = aobj[dbf.getFieldNumber("statusname")].toString();
            String kind_name = aobj[dbf.getFieldNumber("kindname")].toString();
            String top_asset = aobj[dbf.getFieldNumber("topasset")].toString();
            String end_asset = aobj[dbf.getFieldNumber("endasset")].toString();
            String balance = aobj[dbf.getFieldNumber("balance")].toString();
            String end_stockval = aobj[dbf.getFieldNumber("endstockva")].toString();
            String input_money = aobj[dbf.getFieldNumber("inputmoney")].toString();
            String output_money = aobj[dbf.getFieldNumber("outputmone")].toString();
            String input_stock = aobj[dbf.getFieldNumber("inputstock")].toString();
            String output_stock = aobj[dbf.getFieldNumber("outputstoc")].toString();
            String match_amount = aobj[dbf.getFieldNumber("matchamt")].toString();
            String top_stkval = aobj[dbf.getFieldNumber("topmarketv")].toString();
            String sxf = aobj[dbf.getFieldNumber("sxf")].toString();
            String j_sxf = aobj[dbf.getFieldNumber("j_sxf")].toString();
            String commision_rate = aobj[dbf.getFieldNumber("commrate")].toString();
            String open_date = aobj[dbf.getFieldNumber("opendate")].toString();
            String agent_no = aobj[dbf.getFieldNumber("agentno")].toString();
            String agent_name = aobj[dbf.getFieldNumber("agentname")].toString();
            String id_no = " ";
            String year_month = start_date.substring(0, 6);
            //构造SQ插入L语句
            String SqlStatement = "insert month_end_asset values('" + start_date + "','" + end_date + "','" + branch + "'," + fund_id + ",'" + cust_name + "','";
            SqlStatement = SqlStatement + fund_status + "','" + kind_name + "'," + top_asset + "," + end_asset + "," + balance + "," + end_stockval + ",";
            SqlStatement = SqlStatement + input_money + "," + output_money + "," + input_stock + "," + output_stock + "," + match_amount + ",";
            SqlStatement = SqlStatement + sxf + "," + j_sxf + "," + commision_rate + ",'" + open_date + "','" + agent_no + "','" + agent_name + "','" + id_no + "'," + 0 + "," + top_stkval + ")";
            //将SQL语句加入到SQL批
            SQL.addBatch(SqlStatement);
            pv.setCurrentValue(pv.getCurrentValue() + 1);
            System.out.println(pv.getCurrentValue() + 1);
        }
        SQL.executeBatch();//执行SQL批
        conn.commit();
        conn.setAutoCommit(true);
        //最后，月末资产数据到月均资产的结构转换
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
            Logger.getLogger(DBFImporter18.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repeated;
    }
}
