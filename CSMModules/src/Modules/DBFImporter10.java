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
//导入juser.dbf
public class DBFImporter10 extends Thread {

    String filename = "";
    JPanel msgPanel = null;
    JLabel msgLabel = null;

    public DBFImporter10(String filename, JPanel msgPanel, JLabel msgLabel) {
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
            SQL.execute("truncate table juser");
            //导入dbf
            importDBF(filename, msgPanel, msgLabel);
        } catch (IOException ex) {
            Logger.getLogger(DBFImporter1.class.getName()).log(Level.SEVERE, null, ex);
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
        JDBF dbf = new JDBF(filename,null);
        Connection conn = Main.conn;
        Statement SQL = conn.createStatement();
        int maxRec = dbf.header.getNumberOfRecords();
        ProgressValues pv = new ProgressValues(0, maxRec);
        ProgressBar pb = new ProgressBar("导入juser.dbf", pv);
        for (int i = 0; i < maxRec; i++) {
            Object aobj[] = dbf.readRecord(i);//读取记录
            //取出各字段
            String user_id = aobj[0].toString();
            String user_name = aobj[1].toString();
            String group_id = aobj[2].toString();
            String password = aobj[3].toString();
            //构造SQ插入L语句
            String SqlStatement = "insert into juser values(";
            SqlStatement = SqlStatement + user_id + ",'" + user_name + "',";
            SqlStatement = SqlStatement + group_id + ",'" + password + "')";
            //执行SQL语句
            SQL.execute(SqlStatement);
            pv.setCurrentValue(i);
        }
        dbf.close();
        pv.setCurrentValue(pv.getEndtValue());
        msgPanel.setVisible(false);
    }
}
