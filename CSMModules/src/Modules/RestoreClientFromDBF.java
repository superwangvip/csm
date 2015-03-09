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
public class RestoreClientFromDBF extends Thread {

    JPanel mp = null;
    JLabel m = null;

    public RestoreClientFromDBF(JPanel p, JLabel l) {
        mp = p;
        m = l;
    }

    @Override
    public void run() {
        try {
            Main.conn.setAutoCommit(false);
            java.sql.Statement SQL = Main.conn.createStatement();
            
            Main.conn.commit();
            Main.conn.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(RestoreClientFromDBF.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getLocalizedMessage());
        }
        m.setText("恢复完毕！");
        mp.setVisible(false);
    }
}


