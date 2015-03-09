/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * JFrameGrantServiceRelation.java
 *
 * Created on 2010-11-26, 12:05:42
 */
package Modules;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author Administrator
 */
public class JFrameGrantServiceRelation extends javax.swing.JFrame {

    boolean forService = true;
    Vector columnType = new Vector();
    boolean addSerial = true;
    boolean hasSelected = false;
    int searchByAgent = 1;
    int searchByServicePerson = 2;
    int searchByAnalyst = 3;
    int serchMethod = 0;
    Vector servicePerson;
    Vector servicePersonID;
    Vector analyst;
    Vector analystID;

    /** Creates new form JFrameGrantServiceRelation */
    public JFrameGrantServiceRelation() {
        initComponents();
        int iThisWidth = 1010;
        int iThisHight = 720;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y - 10, iThisWidth, iThisHight);
        jComboBoxUser.removeAllItems();
        BasicInfo bsi = BasicInfo.getBaseicInfo();
        servicePerson = bsi.getServicePerson();
        servicePersonID = bsi.getServicePersonID();

        Enumeration enum1 = servicePerson.elements();
        jComboBoxUser.addItem("无指定服务员");
        while (enum1.hasMoreElements()) {
            String servicePerson1 = enum1.nextElement().toString();
            jComboBoxUser.addItem(servicePerson1);
        }
        jComboBoxUser.addItem("取消服务员");
        jComboBoxUser1.removeAllItems();
        analyst = bsi.getAnalyst();
        analystID = bsi.getAnalystID();
        Enumeration enum2 = analyst.elements();
        jComboBoxUser1.addItem("无指定分析师");
        while (enum2.hasMoreElements()) {
            String servicePerson2 = enum2.nextElement().toString();
            jComboBoxUser1.addItem(servicePerson2);
        }
        jComboBoxUser1.addItem("取消分析师");
        jRadioButton1.setSelected(true);
        jComboBoxUser.setEnabled(true);
        jComboBoxUser1.setEnabled(false);

        //注册键盘监听器，监听键盘动作，把系统无操作等待计时器置0;对非管理员屏蔽批量数据复制
        MainMenu.registerKeyListener(jTable1);
        MainMenu.registerKeyListener(jTable2);
        MainMenu.registerKeyListener(jTable3);
        //注册鼠标动作监听器，监听鼠标动作，把系统无操作等待计时器置0
        MainMenu.registerMouseListener(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jComboBoxUser = new javax.swing.JComboBox();
        jComboBoxUser1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("转授服务关系");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 956, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );

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
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(jTable2);

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

        jButton1.setText("按经纪人统计");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("按服务员统计");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("按分析师统计");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("选择客户到服务转授清单");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("清除服务转移清单");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("转授服务关系给其他服务员");
        jRadioButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton1StateChanged(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("转授服务关系给其他分析师");
        jRadioButton2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButton2StateChanged(evt);
            }
        });

        jComboBoxUser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxUser1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("被授权的服务员");

        jLabel2.setText("被授权的分析师");

        jButton6.setText("保存转授服务关系");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("取消");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel3.setText("服务人员统计表");

        jLabel4.setForeground(new java.awt.Color(51, 0, 255));
        jLabel4.setText("统计内容出现后，点击服务人员统计表记录可列出相关客户清单于表二");

        jLabel5.setText("(表二)客户清单");

        jLabel6.setForeground(new java.awt.Color(0, 0, 204));
        jLabel6.setText("按住鼠标左键上下拖拽表二中的记录可选择记录，鼠标停于表二并ctrl+A可以全选，然后点击选择按钮");

        jLabel7.setText("(表三)转授服务清单");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1)
                            .addComponent(jButton6))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jButton7))
                            .addComponent(jRadioButton2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(jComboBoxUser, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(jComboBoxUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(363, 363, 363)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(32, 32, 32)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(36, 36, 36)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 984, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 982, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(jButton2)
                        .addComponent(jButton1)
                        .addComponent(jLabel4))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton7)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxUser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (serchMethod == searchByAnalyst) {
            int selectedRow = jTable1.getSelectedRow();
            TableModel md = jTable1.getModel();
            int mr = jTable1.convertRowIndexToModel(selectedRow);
            Object id = md.getValueAt(mr, 1);
            String analyst_id = id.toString().trim();
            if (!analyst_id.equals("0")) {
                String sql = "execute get_client_info8 " + JFrameLogin.user_id + "," + analyst_id;
                JDBTableModel dm = new JDBTableModel(jTable2);
                dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
                makeFace(jTable2);
            }
        }
        if (serchMethod == searchByServicePerson) {
            int selectedRow = jTable1.getSelectedRow();
            TableModel md = jTable1.getModel();
            int mr = jTable1.convertRowIndexToModel(selectedRow);
            Object id = md.getValueAt(mr, 1);
            String service_person_id = id.toString().trim();
            if (!service_person_id.equals("0")) {
                String sql = "execute get_client_info3 " + JFrameLogin.user_id + "," + service_person_id;
                JDBTableModel dm = new JDBTableModel(jTable2);
                dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
                makeFace(jTable2);
            }
        }
        if (serchMethod == searchByAgent) {
            int selectedRow = jTable1.getSelectedRow();
            TableModel md = jTable1.getModel();
            int mr = jTable1.convertRowIndexToModel(selectedRow);
            Object agent_O = md.getValueAt(mr, 1);
            if (agent_O == null) {
                agent_O = " ";
            }
            String agent = agent_O.toString().trim();
            if (!agent.equals("")) {
                String sql = "execute get_client_info9 " + JFrameLogin.user_id + ",'" + agent + "'";
                JDBTableModel dm = new JDBTableModel(jTable2);
                dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
                makeFace(jTable2);
            }
        }
}//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String sql = "analyst_report";
        JDBTableModel dm = new JDBTableModel(jTable1);
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        serchMethod = searchByAnalyst;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String sql = "get_agent_report";
        JDBTableModel dm = new JDBTableModel(jTable1);
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        serchMethod = searchByAgent;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String sql = "service_person_report";
        JDBTableModel dm = new JDBTableModel(jTable1);
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        serchMethod = searchByServicePerson;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int selectedRow = jTable2.getSelectedRowCount();
        if (selectedRow == 0) {
            JOptionPane.showMessageDialog(this, "请先用鼠标点击记录");
            return;
        }
        int[] selectedRowNo = jTable2.getSelectedRows();

        Vector title = new Vector();
        Vector content = new Vector();
        DefaultTableModel tableModel2;

        TableModel m1 = jTable2.getModel();
        int columnCount = m1.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            String columnName = m1.getColumnName(i);
            title.add(columnName);
        }

        for (int r = 0; r < selectedRow; r++) {
            int mr = jTable2.convertRowIndexToModel(selectedRowNo[r]);
            Vector row = new Vector();
            for (int c = 0; c < columnCount; c++) {
                row.add(m1.getValueAt(mr, c));
            }
            content.add(row);
        }

        tableModel2 = new DefaultTableModel(content, title);
        jTable3.setModel(tableModel2);
        fitTableColumns(jTable3);//表格列宽自适应
        hasSelected = true;
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int r = jTable3.getRowCount();
        int c = jTable3.getColumnCount();
        DefaultTableModel dm = new DefaultTableModel(r, c);
        jTable3.setModel(dm);
        hasSelected = false;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jRadioButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton1StateChanged
        if (jRadioButton1.isSelected()) {
            jComboBoxUser.setEnabled(true);
            jComboBoxUser1.setEnabled(false);
        }
    }//GEN-LAST:event_jRadioButton1StateChanged

    private void jRadioButton2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton2StateChanged
        // TODO add your handling code here:
        if (jRadioButton2.isSelected()) {
            jComboBoxUser.setEnabled(false);
            jComboBoxUser1.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioButton2StateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (!hasSelected) {
            JOptionPane.showMessageDialog(this, "你没有选择记录到候选清单!");
            return;
        }

        int n = jComboBoxUser.getSelectedIndex();
        int m = jComboBoxUser1.getSelectedIndex();

        int user_id = 0;
        int analyst_id = 0;
        if (n > 0 && n < jComboBoxUser.getItemCount() - 1) {
            user_id = ((Integer) servicePersonID.elementAt(n - 1)).intValue();
        }
        if (m > 0 && m < jComboBoxUser1.getItemCount() - 1) {
            analyst_id = ((Integer) analystID.elementAt(m - 1)).intValue();
        }
        if (n == jComboBoxUser.getItemCount() - 1) {
            user_id = -1;
        }
        if (m == jComboBoxUser1.getItemCount() - 1) {
            analyst_id = -1;
        }
        saveData(user_id, analyst_id);

        if (serchMethod == searchByAgent) {
            String sql = "get_agent_report";
            JDBTableModel dm = new JDBTableModel(jTable1);
            dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
            serchMethod = searchByAgent;
        }
        if (serchMethod == searchByServicePerson) {
            String sql = "service_person_report";
            JDBTableModel dm = new JDBTableModel(jTable1);
            dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
            serchMethod = searchByServicePerson;
        }
        if (serchMethod == searchByAnalyst) {
            String sql = "analyst_report";
            JDBTableModel dm = new JDBTableModel(jTable1);
            dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
            serchMethod = searchByAnalyst;
        }
        JOptionPane.showMessageDialog(this, "数据已保存");
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
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

    public void saveData(int service_person_id, int analyst_id) {
        int r = jTable3.getRowCount();
        DefaultTableModel dm = (DefaultTableModel) jTable3.getModel();
        for (int i = 0; i < r; i++) {
            long fundAccount = (Long) dm.getValueAt(i, 1);

            String msg = assignServicePerson(service_person_id, fundAccount, analyst_id);
            if (!msg.trim().equals("安排成功!") && !msg.trim().equals("取消成功!")) {
                msg = msg + ",帐号:" + fundAccount;
                JOptionPane.showMessageDialog(this, msg);
            }
        }
    }

    public String assignServicePerson(int service_person_id, long fund_account, int analyst_id) {

        int returnStatus = 0;
        String returnMessage = "保存成功";
        PreparedStatement SqlStatement = null;
        try {
            SqlStatement = Main.conn.prepareStatement("execute assign_service_person ?, ?,?,?");
            //tring sql = "executeassign_service_person " + service_person_id + "," + fund_account + "," + analyst_id;
            //ystem.out.println(sql);
            SqlStatement.setInt(1, service_person_id);
            SqlStatement.setLong(2, fund_account);
            SqlStatement.setInt(3, analyst_id);
            SqlStatement.setInt(4, JFrameLogin.user_id);  //操作员,2011-09-09 补充
            Boolean HasResult = SqlStatement.execute();

            while (!HasResult) {
                HasResult = SqlStatement.getMoreResults();
            }
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    returnStatus = SqlResult.getInt("ret_status");
                    returnMessage = SqlResult.getString("ret_msg").trim();
                }
                SqlStatement.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFrameGrantServiceRelation.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getMessage());
        }
        return returnMessage;
    }

    private synchronized void makeFace(JTable table) {
        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {

                @Override
                public Component getTableCellRendererComponent(JTable table,
                        Object value, boolean isSelected, boolean hasFocus,
                        int row, int column) {
                    TableModel m = table.getModel();

                    for (int i = 0; i < row + 1; i++) {

                        String contract = "";
                        String credit = "";
                        int mr = table.convertRowIndexToModel(i);
                        Object credit_O = m.getValueAt(mr, credit_column);

                        if (credit_O != null) {
                            credit = credit_O.toString().trim();
                        }
                        Object contract_O = m.getValueAt(mr, contract_column);
                        if (contract_O != null) {
                            contract = contract_O.toString().trim();
                        }

                        if (credit.equals("已开通")) {//开通融资融券者以特殊颜色显示
                            setBackground(new Color(204, 255, 255));
                            setForeground(Color.RED);
                        } else if (contract.equals("签约")) {
                            setBackground(new Color(204, 255, 255));  //签约客户特殊颜色
                            setForeground(Color.blue);
                        } else {
                            setForeground(Color.BLACK);
                            setBackground(Color.WHITE);
                        }
                    }
                    return super.getTableCellRendererComponent(table, value,
                            isSelected, hasFocus, row, column);
                }
            };
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
            }
        } catch (Exception ex) {
            Logger.getLogger(JFrameRealtimeMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    int credit_column = 16;
    int contract_column = 14;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}


