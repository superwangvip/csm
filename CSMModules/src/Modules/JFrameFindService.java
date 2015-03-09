/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameFindService.java
 *
 * Created on 2010-5-27, 0:17:16
 */
package Modules;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author Administrator
 */
public class JFrameFindService extends javax.swing.JFrame {

    boolean anotherPersonSelected = false;

    /** Creates new form JFrameFindService */
    public JFrameFindService() {
        initComponents();

        int iThisWidth = 1000;
        int iThisHight = 700;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y - 10, iThisWidth, iThisHight);
        String sql = "execute get_service_report";
        JDBTableModel dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        boolean addSerial = false;
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);


        jComboBoxUser.removeAllItems();
        jComboBoxUser1.removeAllItems();
        BasicInfo bsi = BasicInfo.getBaseicInfo();
        userName = bsi.getServicePerson();
        userID = bsi.getServicePersonID();

        Enumeration enum1 = userName.elements();
        while (enum1.hasMoreElements()) {
            String userName = enum1.nextElement().toString();
            jComboBoxUser.addItem(userName);
            jComboBoxUser1.addItem(userName);
        }

        //注册键盘监听器，监听键盘动作，把系统无操作等待计时器置0;对非管理员屏蔽批量数据复制
        MainMenu.registerKeyListener(jTable1);
        MainMenu.registerKeyListener(jTable2);
        MainMenu.registerKeyListener(jTable3);
        //注册鼠标动作监听器，监听鼠标动作，把系统无操作等待计时器置0
        MainMenu.registerMouseListener(this);
        MainMenu.registerMouseListener(jTable1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldDate = new javax.swing.JTextField();
        jComboBoxUser = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxUser1 = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("查询服务安排情况，并重新安排");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("服务安排情况");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.setToolTipText("按下并保持住鼠标左键，上下拖动，可以一次选择多条记录(ctrl^A 全选)");
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(jTable2);

        jLabel2.setText("已安排服务客户清单");

        jLabel3.setText("服务员/回访员");

        jComboBoxUser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBoxUserFocusGained(evt);
            }
        });

        jLabel6.setText("安排日期");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jLabel2)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxUser, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable3.setColumnSelectionAllowed(true);
        jScrollPane3.setViewportView(jTable3);

        jLabel4.setText("再安排候选客户清单");

        jLabel5.setText("再安排服务员/回访员");

        jComboBoxUser1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxUser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxUser1MouseClicked(evt);
            }
        });
        jComboBoxUser1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBoxUser1FocusGained(evt);
            }
        });
        jComboBoxUser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBoxUser1PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(284, 284, 284)
                        .addComponent(jLabel4)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel5)
                        .addGap(39, 39, 39)
                        .addComponent(jComboBoxUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxUser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jButton4.setText("清除再安排候选清单");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("宋体", 0, 14));
        jLabel7.setForeground(new java.awt.Color(0, 0, 153));
        jLabel7.setText("先以鼠标点击服务情况任意记录");

        jButton3.setText("退出");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setText("保存再安排数据");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("选择客户到候选清单");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton1)
                        .addGap(30, 30, 30)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(26, 26, 26)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int selectedRow = jTable1.getSelectedRow();
        TableModel md = jTable1.getModel();
        int mr = jTable1.convertRowIndexToModel(selectedRow);
        // System.out.print("row="+selectedRow);
        Object date_O = md.getValueAt(mr, 0);
        String date = " ";
        if (date_O != null) {
            date = date_O.toString().trim();
        }
        if (date.equals("")) {
            return;
        }
        selectedDate = date;
        jTextFieldDate.setText(selectedDate);
        Object name_O = md.getValueAt(mr, 1);
        String name = " ";
        if (name_O != null) {
            name = name_O.toString();
        }
        selectedName = name;
        int user_id = getUserId(selectedName);
        System.out.println(user_id + ":" + selectedName);
        int query_way = 1;//查询所有
        int order_by = 2;
        String sql = "execute get_client_list3_1 ";

        sql = sql + user_id + "," + query_way + ",'" + selectedDate + "'";
        System.out.println(sql);
        JDBTableModel dm = new JDBTableModel(jTable2);
        Vector columnType = new Vector();
        boolean addSerial = true;
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        MainMenu.makeFace(jTable2);
}//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int r = jTable3.getRowCount();
        int c = jTable3.getColumnCount();
        DefaultTableModel dm = new DefaultTableModel(r, c);
        jTable3.setModel(dm);
        hasSelected = false;
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!hasSelected) {
            JOptionPane.showMessageDialog(this, "你没有选择记录到候选清单!");
            return;
        }
        if (!anotherPersonSelected) {
            JOptionPane.showMessageDialog(this, "你没有选择再安排的服务员 !");
            return;
        }

        int n = jComboBoxUser1.getSelectedIndex();
        int user_id = ((Integer) userID.elementAt(n)).intValue();
        Thread myThread = new ServiceAssign(user_id, jTable3);
        SwingUtilities.invokeLater(myThread);
        JOptionPane.showMessageDialog(this, "数据已保存");
        //刷新统计表
        String sql = "execute get_service_report";
        JDBTableModel dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        boolean addSerial = false;

        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        //刷新客户清单
        user_id = getUserId(selectedName);
        int query_way = 1;//查询所有
        int order_by = 2;
        sql = "execute get_client_list3_1 ";
        sql = sql + user_id + "," + query_way + ",'" + selectedDate + "'";
        System.out.println(sql);
        JDBTableModel dm1 = new JDBTableModel(jTable2);
        boolean addSerial1 = true;
        dm1.fetchDataToTable(Main.conn, sql, columnType, addSerial1);

        //清除候选清单
        int r = jTable3.getRowCount();
        int c = jTable3.getColumnCount();
        DefaultTableModel dm3 = new DefaultTableModel(r, c);
        jTable3.setModel(dm3);
        hasSelected = false;
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable2.getSelectedRowCount();
        if (selectedRow == 0) {
            JOptionPane.showMessageDialog(this, "请先用鼠标点击客户");
            return;
        }
        String s = String.valueOf(selectedRow);
        int[] selectedRowNo = jTable2.getSelectedRows();

        Vector title = new Vector();
        Vector content = new Vector();
        DefaultTableModel tableModel2;

        TableModel m1 = jTable2.getModel();
        String t1 = m1.getColumnName(1);
        int columnCount = m1.getColumnCount();

        for (int i = 0; i < columnCount; i++) {
            String columnName = m1.getColumnName(i);
            title.add(columnName);
        }

        for (int r = 0; r < selectedRow; r++) {
            Vector row = new Vector();
            int mr = jTable2.convertRowIndexToModel(selectedRowNo[r]);
            for (int c = 0; c < columnCount; c++) {
                row.add(m1.getValueAt(mr, c));
            }
            content.add(row);
        }

        tableModel2 = new DefaultTableModel(content, title);
        jTable3.setModel(tableModel2);
        fitTableColumns(jTable3);
        hasSelected = true;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxUser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxUser1MouseClicked
        anotherPersonSelected = true;
    }//GEN-LAST:event_jComboBoxUser1MouseClicked

    private void jComboBoxUser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBoxUser1PropertyChange
    }//GEN-LAST:event_jComboBoxUser1PropertyChange

    private void jComboBoxUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxUserFocusGained
    }//GEN-LAST:event_jComboBoxUserFocusGained

    private void jComboBoxUser1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxUser1FocusGained
        anotherPersonSelected = true;
    }//GEN-LAST:event_jComboBoxUser1FocusGained

    //表格列宽自适应方法
    private void fitTableColumns(JTable myTable) {
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();

        Enumeration columns = myTable.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) myTable.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
                        myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column); // 此行很重要
            column.setWidth(width + myTable.getIntercellSpacing().width);
        }
    }

    int getUserId(String name) {
        int user_id = -1;
        name = name.trim();//Very important,can not be ommited.
        Enumeration enum1 = userName.elements();
        int i = 0;
        int index = 0;

        while (enum1.hasMoreElements()) {
            String userName1 = enum1.nextElement().toString().trim();
            if (userName1.equals(name.trim())) {
                index = i;
                jComboBoxUser.setSelectedIndex(index);
                break;
            }
            i++;
        }
        int n = jComboBoxUser.getSelectedIndex();
        user_id = ((Integer) userID.elementAt(n)).intValue();
        //System.out.println("found:  " + name + ":" + user_id);
        return user_id;
    }
    Vector userName;
    Vector userID;
    DefaultTableModel tableModel1;
    boolean hasSelected = false;
    String selectedName = "";
    String selectedDate = "";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBoxUser;
    private javax.swing.JComboBox jComboBoxUser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextFieldDate;
    // End of variables declaration//GEN-END:variables
}

class ServiceAssign extends Thread {

    JTable tb2;
    int service_person_id = 0;

    public ServiceAssign(int servicePersonID, JTable tb) {
        service_person_id = servicePersonID;
        tb2 = tb;
    }

    @Override
    public void run() {

        int r = tb2.getRowCount();
        DefaultTableModel dm = (DefaultTableModel) tb2.getModel();
        for (int i = 0; i < r; i++) {
            String fundAccountStr = dm.getValueAt(i, 1).toString().trim();
            long fundAccount = Long.valueOf(fundAccountStr).longValue();
            assignServicePerson(service_person_id, fundAccount);
        }

    }

    public void assignServicePerson(int service_person_id, long fund_account) {

        int returnStatus = 0;
        String returnMessage = "保存成功";
        PreparedStatement SqlStatement = null;
        try {
            //安排回访员
            SqlStatement = Main.conn.prepareStatement("execute assign_service_person ?,?,?,?");

            SqlStatement.setInt(1, service_person_id);
            SqlStatement.setLong(2, fund_account);
            SqlStatement.setNull(3, java.sql.Types.INTEGER);
            SqlStatement.setInt(4, JFrameLogin.user_id);
            Boolean HasResult = SqlStatement.execute();

            if (!HasResult) {
                HasResult = SqlStatement.getMoreResults();
            }
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    returnStatus = SqlResult.getInt("ret_status");
                    returnMessage = SqlResult.getString("ret_msg");//?????
                }
                SqlStatement.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFrameFindService.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getLocalizedMessage());
        }
    }
}
