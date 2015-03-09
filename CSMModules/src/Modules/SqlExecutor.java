package Modules;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jiang Youquan
 */
public class SqlExecutor {

    //单线程执行SQL，无提示窗口
    @SuppressWarnings("static-access")
    public synchronized void execute(Connection conn, String sql) {
        try {
            PreparedStatement SqlStatement;
            int returnStatus = 0;
            
            SqlStatement = conn.prepareStatement(sql);
            Boolean HasResult = SqlStatement.execute();
            if (!HasResult) {
                HasResult = SqlStatement.getMoreResults();
            }
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    returnStatus = SqlResult.getInt("ret_status");
                    SqlResult.getString("ret_msg"); //?????
                }
            }
        } catch (SQLException ex) {
            Main.logger.getLogger(SqlExecutor.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        }
    }

    //单线程执行SQL，无提示窗口,但可弹出显示执行返回信息
    @SuppressWarnings("static-access")
    public synchronized void execute1(Connection conn, String sql) {
        String ret_msg = "";
        try {
            PreparedStatement SqlStatement;
            int returnStatus = 0;
            SqlStatement = conn.prepareStatement(sql);
            Boolean HasResult = SqlStatement.execute();
            while (!HasResult) {
                HasResult = SqlStatement.getMoreResults();
            }
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    returnStatus = SqlResult.getInt("ret_status");
                    ret_msg = SqlResult.getString("ret_msg"); //?????
                }
            }
        } catch (SQLException ex) {
            Main.logger.getLogger(SqlExecutor.class.getName()).log(Level.SEVERE, null, ex);
            ret_msg = "sql异常: " + ex.getLocalizedMessage();
        }
        JOptionPane.showMessageDialog(null, ret_msg);
    }

    //单线程执行SQL，无提示窗口, 有返回信息（返回String类对象）
    @SuppressWarnings("static-access")
    public synchronized String execute2(Connection conn, String sql) {
        String ret_msg = "";
        try {
            PreparedStatement SqlStatement;
            int returnStatus = 0;
            SqlStatement = conn.prepareStatement(sql);
            Boolean HasResult = SqlStatement.execute();
            while (!HasResult) {
                HasResult = SqlStatement.getMoreResults();
            }
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    returnStatus = SqlResult.getInt("ret_status");
                    ret_msg = SqlResult.getString("ret_msg"); //?????
                }
            }
        } catch (SQLException ex) {
            ret_msg = "sql异常: " + ex.getMessage();
            Main.logger.getLogger(SqlExecutor.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning(ret_msg).setVisible(true);
        }
        return ret_msg;
    }

    //多线程执行SQL，有提示窗口,且有返回信息弹出
    public synchronized void execute(Connection conn, String sql, String msg) {
        SQLStatus st = new SQLStatus();
        SysMessage sm = new SysMessage(msg, st);
        Thread t1 = new Thread(sm);
        //SqlThread r2 = new SqlThread(conn, sql, st);
        SqlThread t2 = new SqlThread(conn, sql, st);
        //Thread t2 = new Thread(r2);
        SwingUtilities.invokeLater(t1);
        t2.start();
    }
}

class SqlThread extends Thread {

    Connection conn;
    String sql;
    SQLStatus st;

    public SqlThread(Connection conn, String sql, SQLStatus st) {
        this.conn = conn;
        this.sql = sql;
        this.st = st;
    }

    @Override
    public void run() {
        int ret_status = -1;
        String ret_msg = "执行完毕!";
        this.st.setSTatus(false);
        System.out.println("SQL execution started!");
        try {
            java.sql.Statement SQL = conn.createStatement();
            boolean HasResult = SQL.execute(sql);
            while (!HasResult) {
                HasResult = SQL.getMoreResults();
            }
            if (HasResult) {
                ResultSet SqlResult = SQL.getResultSet();
                while (SqlResult.next()) {
                    ret_status = SqlResult.getInt(1);
                    ret_msg = SqlResult.getString(2);
                }
            }
        } catch (SQLException ex) {
            ret_msg = "SQL异常：" + ex.getMessage();
            Main.logger.warning(ret_msg);
        }
        this.st.setSTatus(true);
        System.out.println("SQL execution finished!");
        JOptionPane.showMessageDialog(null, ret_msg);
    }
}
