package Modules;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author Administrator
 */
public class RestoreClient extends Thread {

    JPanel mp = null;
    JLabel m = null;

    public RestoreClient(JPanel p, JLabel l) {
        mp = p;
        m = l;
    }

    @Override
    public void run() {
        try {
            Main.conn.setAutoCommit(false);
            java.sql.Statement SQL = Main.conn.createStatement();
            SQL.execute("exec restore_client");
            Main.conn.commit();
            Main.conn.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(RestoreClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        m.setText("恢复完毕！");
        mp.setVisible(false);
    }
}


