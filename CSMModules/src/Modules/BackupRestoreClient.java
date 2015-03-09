/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class BackupRestoreClient extends Thread {

    String source = "";
    String backup = "";
    int flag = 0;//1=backup,2=restore
    JPanel jpl;

    public BackupRestoreClient(String source, String backup, int flag,JPanel jpl) {
        this.source = source;
        this.backup = backup;
        this.flag = flag;
        this.jpl=jpl;
    }

    @Override
    public void run() {
        MainMenu.TIMER_STEP=0;
        try {
            java.sql.Statement SQL = Main.conn.createStatement();
            if (flag == 1) {
                SQL.execute("select * into " + backup + " from " + source);
            } else {
                SQL.execute("truncate table " + source);
                SQL.execute("insert " + source + " select * from " + backup);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BackupClient.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getMessage());
            new JFrameWarning(ex.getLocalizedMessage()).setVisible(true);
        }
        jpl.setVisible(false);
        MainMenu.TIMER_STEP=1;
    }
}

