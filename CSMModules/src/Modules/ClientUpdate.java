/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

/**
 *
 * @author Administrator
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class ClientUpdate extends Thread {

    JPanel mp = null;
    JLabel m = null;

    //String update_time="";
    public ClientUpdate(JPanel p, JLabel l) {
        mp = p;
        m = l;

    //  update_time=update_yearmonth;
    }

    public void run() {
        MainMenu.TIMER_STEP=0;
        boolean error = false;
        int ret_status = -1;
        String ret_msg = "";
        try {
            java.sql.Statement SQL = Main.conn.createStatement();
            boolean HasResult = SQL.execute("exec update_client_info");
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
            Main.logger.getLogger(ClientUpdate.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);;
            error = true;
        }

        if (error) {
            m.setText("sql异常!,详情见运行日志。");
        } else {
            m.setText("更新完毕！");
            JOptionPane.showMessageDialog(null, ret_msg);
            mp.setVisible(false);
        }
        MainMenu.TIMER_STEP=1;
    }
}

