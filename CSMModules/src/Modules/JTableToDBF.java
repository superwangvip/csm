/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

/**
 *
 * @author Administrator
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Administrator
 */
public class JTableToDBF {

    public JTableToDBF() {
    }

    public int saveToDBF(JTable table, Vector columnType, String outFile)
            throws JDBFException, UnsupportedEncodingException {
        TableModel md = table.getModel();
        int recordCount = 0;
        //create dbf header
        int c = table.getColumnCount();
        JDBFField ajdbfield[] = new JDBFField[c];
        String sqlInt = "tinyint,smallint,int";
        String sqlLong = "bigint";
        String sqlFloat = "float,numeric,real";
        String sqlMoney = "money";
        for (int i = 0; i < c; i++) {
            String columnName = table.getColumnName(i).trim();
            JDBColumn dbcol = (JDBColumn) columnType.elementAt(i);
            String type = dbcol.getTypeName();
            int width = dbcol.getcolumnWidth();
            System.out.println("columnName(" + i + "):" + columnName);
            System.out.println("columnType(" + i + "):" + dbcol.getTypeName());
            System.out.println("width:"+width);
            if (columnName.length() > 10) {
                columnName = "field" + i;
            }

            if (sqlInt.contains(type)) {
                ajdbfield[i] = new JDBFField(columnName, 'N', 8, 0);
            } else if (sqlLong.contains(type)) {
                ajdbfield[i] = new JDBFField(columnName, 'N', 12, 0);

            } else if (sqlFloat.contains(type) || sqlMoney.contains(type)) {
                ajdbfield[i] = new JDBFField(columnName, 'N', 26, 7);
            } else {
                ajdbfield[i] = new JDBFField(columnName, 'C', width, 0);
            }
        }
        // write dbf header and append records
        JDBF dbf = null;
        try {//write dbf header
            dbf = new JDBF(outFile, ajdbfield);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JTableToDBF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JTableToDBF.class.getName()).log(Level.SEVERE, null, ex);
        }
        int n = table.getRowCount();
        // append records
        for (int i = 0; i < n; i++) {
            Object[] dbfFieldValue = new Object[c];
            int m = table.getColumnCount();
            //set filed values for dbf records
            for (int j = 0; j < m; j++) {
                JDBColumn dbcol = (JDBColumn) columnType.elementAt(j);
                String type = dbcol.getTypeName();
                int width = dbcol.getcolumnWidth();
                Object cellData = md.getValueAt(i, j);
                // System.out.println("columnValue(" + i + "," + j + "):" + cellData);
                String s = "";
                if (cellData == null) {
                    s = "";
                } else {
                    s = cellData.toString().trim();
                }
      

                if (cellData == null) {
                    cellData = "";
                }

                if (sqlInt.contains(type) || sqlLong.contains(type)) {
                    if (s.trim().equals("")) {
                        s = "0";
                    }
                    Long d = Long.valueOf(s);
                    dbfFieldValue[j] = d;
                } else if (sqlFloat.contains(type) || sqlMoney.contains(type)) {
                    if (s.trim().equals("")) {
                        s = "0.00";
                    }

                    Double d = Double.valueOf(s);
                    dbfFieldValue[j] = d;

                } else {
                    dbfFieldValue[j] = s;
                }
            }
            dbf.addRecord(dbfFieldValue);
            recordCount++;
        }
        try {
            dbf.close();
        } catch (IOException ex) {
            Logger.getLogger(JTableToDBF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recordCount;
    }
}