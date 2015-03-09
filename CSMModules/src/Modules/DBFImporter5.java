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
//导入客户-经纪人信息
public class DBFImporter5 extends Thread {

    String filename = "";
    JPanel msgPanel = null;
    JLabel msgLabel = null;

    public DBFImporter5(String filename, JPanel msgPanel, JLabel msgLabel) {
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
            //先清理client_agent表数据
            Connection conn = Main.conn;
            Statement SQL = conn.createStatement();
            SQL.execute("truncate table client_agent");
            SQL.execute("update backup_log set agent_import_time=getdate()");
            //在批量导入数据
            bulkImportDBF(filename, msgPanel, msgLabel);
            //更新client表里的经纪人
            String SqlStatement = "update_client_agent";
            SQL.execute(SqlStatement);
        } catch (IOException ex) {
            Main.logger.getLogger(DBFImporter5.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("IO异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (SQLException ex) {
            Main.logger.getLogger(DBFImporter5.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (JDBFException ex) {
            Main.logger.getLogger(DBFImporter5.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("dbf异常_1: " + ex.getLocalizedMessage()).setVisible(true);
        } catch (ParseException ex) {
           Main.logger.getLogger(DBFImporter5.class.getName()).log(Level.SEVERE, null, ex);
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
        ProgressBar pb = new ProgressBar("导入客户-经纪人信息dbf", pv);
        System.out.println("\n 读取记录块：记录总数：" + maxRec + ",块大小:" + size + ",块个数：" + m + ",零头：" + m1);
        //1.=================先处理整块数据===================
        for (int i = 0; i < m; i++) {
            Object records[][] = dbf.readBlock(i * size, size);
            SQL.clearBatch();
            for (int j = 0; j < size; j++) {
                Object aobj[] = records[j];//读取记录
                //取出各字段
                String fundid = aobj[dbf.getFieldNumber("fundid")].toString();
                String clientname = aobj[dbf.getFieldNumber("clientname")].toString();
                String agentno = aobj[dbf.getFieldNumber("agentno")].toString();
                String agentname = aobj[dbf.getFieldNumber("agentname")].toString();
                String agent_status = aobj[dbf.getFieldNumber("brokerstat")].toString();
                String open_date = aobj[dbf.getFieldNumber("opendate")].toString();
                //构造SQL插入语句
                String SqlStatement = "insert into client_agent (Fundid,clientname,agentno,agentname,agent_status,opendate) ";
                SqlStatement = SqlStatement + "values(" + fundid + ",'" + clientname + "','" + agentno + "','" +
                        agentname + "','" + agent_status + "','" + open_date + "')";

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
            String fundid = aobj[dbf.getFieldNumber("fundid")].toString();
            String clientname = aobj[dbf.getFieldNumber("clientname")].toString();
            String agentno = aobj[dbf.getFieldNumber("agentno")].toString();
            String agentname = aobj[dbf.getFieldNumber("agentname")].toString();
            String agent_status = aobj[dbf.getFieldNumber("brokerstat")].toString();
            String open_date = aobj[dbf.getFieldNumber("opendate")].toString();
            //构造SQL插入语句
            String SqlStatement = "insert into client_agent (Fundid,clientname,agentno,agentname,agent_status,opendate) ";
            SqlStatement = SqlStatement + "values(" + fundid + ",'" + clientname + "','" + agentno + "','" +
                    agentname + "','" + agent_status + "','" + open_date + "')";

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
}
