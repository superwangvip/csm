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
//for khxh.dbf 开户销户日志dbf数据导入
public class DBFImporter8 extends Thread {

    String filename = "";
    JPanel msgPanel = null;
    JLabel msgLabel = null;

    public DBFImporter8(String filename, JPanel msgPanel, JLabel msgLabel) {
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
            importDBF(filename, msgPanel, msgLabel);
        } catch (IOException ex) {
            Main.logger.getLogger(DBFImporter8.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("IO异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (SQLException ex) {
            Main.logger.getLogger(DBFImporter8.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (JDBFException ex) {
            Main.logger.getLogger(DBFImporter8.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("dbf异常_1: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (ParseException ex) {
           Main.logger.getLogger(DBFImporter8.class.getName()).log(Level.SEVERE, null, ex);
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

    //dbf数据导入:分笔导入
    public static void importDBF(String filename, JPanel msgPanel, JLabel msgLabel) throws FileNotFoundException, IOException, SQLException, JDBFException, ParseException, SQLException {
        JDBF dbf = new JDBF(filename,null);
        Connection conn = Main.conn;
        Statement SQL = conn.createStatement();
        int maxRec = dbf.header.getNumberOfRecords();
        ProgressValues pv = new ProgressValues(0, maxRec);
        ProgressBar pb = new ProgressBar("导入开户销户日志dbf", pv);
        for (int i = 0; i < maxRec; i++) {
            Object aobj[] = dbf.readRecord(i);//读取记录
            //取出各字段
            String fundid = aobj[dbf.getFieldNumber("fundid")].toString();
            String name = aobj[dbf.getFieldNumber("name")].toString();
            String summary = aobj[dbf.getFieldNumber("digesti008")].toString();
            String operate_date = aobj[dbf.getFieldNumber("operdate")].toString().trim();
            String year_month = operate_date.substring(0, 6);
            //检查是否重复导入相同数据
            if (i == 0) {
                if (isDataRepeated("open_cancel_log", year_month)) {
                    JOptionPane.showMessageDialog(null, "数据重复转入!,请重新选择要转入的dbf");
                    msgPanel.setVisible(false);
                    SQL.close();
                    dbf.close();
                    pv.setCurrentValue(pv.getEndtValue());//结束进度条
                    return;
                }
            }
            //构造SQ插入L语句
            String SqlStatement = "insert into open_cancel_log (fund_account,name,open_cancel_summary,operate_date) ";
            SqlStatement = SqlStatement + "values (" + fundid + ",'" + name + "','" + summary + "','" + operate_date + "')";
            //执行SQL语句
            SQL.execute(SqlStatement);
            pv.setCurrentValue(i);
        }
        dbf.close();
        pv.setCurrentValue(pv.getEndtValue());
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
            Logger.getLogger(DBFImporter8.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repeated;
    }
}
