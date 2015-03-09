/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Administrator
 */
public class BackupClient {

    JPanel jpl;
    JLabel jbl;

    public BackupClient(JPanel jpl, JLabel jbl) {
        this.jpl = jpl;
        this.jbl = jbl;
    }

    public void backup() {
        MainMenu.TIMER_STEP=0;
        try {
            java.sql.Statement stat = Main.conn.createStatement();
            String sql = "select name,crdate from sysobjects where name like '%client_backup%' order by crdate";
            boolean hasResultSet = stat.execute(sql);
            if (!hasResultSet) {
                stat.getMoreResults();
            }
            if (hasResultSet) {
                ResultSet SqlResult = stat.getResultSet();
                String tableName = "client_backup";
                while (SqlResult.next()) {
                    tableName = SqlResult.getString(1);
                }
                tableName = getNextName(tableName);
                BackupRestoreClient myThread = new BackupRestoreClient("client", tableName, 1,jpl);
                SwingUtilities.invokeLater(myThread);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BackupClient.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getMessage());
                        new JFrameWarning(ex.getLocalizedMessage()).setVisible(true);
        }
        MainMenu.TIMER_STEP=1;
    }

    private String getNextName(String name) {
        String nextName = "";
        if (name.equals("client_backup")) {
            nextName = "client_backup_1";
        } else {
            int n = name.lastIndexOf("_");
            String numStr = name.substring(n + 1);
            n = Integer.valueOf(numStr).intValue() + 1;
            nextName = "client_backup_" + n;
        }
        return nextName;
    }
}


