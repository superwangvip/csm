/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameMasterControl.java
 *
 * Created on 2011-4-1, 11:45:42
 */
package Modules;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class JFrameMasterControl extends javax.swing.JFrame {

    Vector columnType = new Vector();
    boolean hasSelected_1 = false;
    boolean hasSelected_2 = false;

    /** Creates new form JFrameMasterControl */
    public JFrameMasterControl() {
        initComponents();
        int iThisWidth = 1010;
        int iThisHight = 700;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y, iThisWidth, iThisHight);
        String sql = "get_user_list";
        JDBTableModel dm1 = new JDBTableModel(jTable1);
        dm1.fetchDataToTable(Main.conn, sql, columnType, false);

        sql = "get_master_list";
        JDBTableModel dm4 = new JDBTableModel(jTable4);
        dm4.fetchDataToTable(Main.conn, sql, columnType, true);
        jButtonDelete.setEnabled(false);

        //注册键盘监听器，监听键盘动作，把系统无操作等待计时器置0;对非管理员屏蔽批量数据复制
        MainMenu.registerKeyListener(jTable1);
        MainMenu.registerKeyListener(jTable2);
        //注册鼠标动作监听器，监听鼠标动作，把系统无操作等待计时器置0
        MainMenu.registerMouseListener(this);
        MainMenu.registerMouseListener(jTable1);
        MainMenu.registerMouseListener(jTable2);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButtonAddMaster = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButtonDelete = new javax.swing.JButton();
        jButtonListAll = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("主管设置");

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

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
        jScrollPane1.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

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
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable4.setColumnSelectionAllowed(true);
        jScrollPane3.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButtonAddMaster.setText("选择到主管列表");
        jButtonAddMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddMasterActionPerformed(evt);
            }
        });

        jButtonSave.setText("建立主管-成员关系");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        Save.setText("选择到成员列表");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        Cancel.setText("取消");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

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
        jScrollPane4.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButtonDelete.setText("删除主管-成员关系");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonListAll.setText("列出全部主管-成员关系");
        jButtonListAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListAllActionPerformed(evt);
            }
        });

        jLabel1.setText("用户列表");

        jLabel2.setText("主管列表");

        jLabel3.setText("成员列表");

        jLabel4.setText("主管-成员关系列表");

        jButton1.setText("统计");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jTextArea1.setText("主管设置就是建立或删除主管与成员的关系，一旦建立了用户甲与用户乙之间的\n“主管-成员关系”，用户甲就有权访问用户乙的所有客户，一个主管用户可以有\n多个成员，但一个成员用户只能属于一个主管。\n\n建立主管-成员关系的方法：\n1、在用户列表中点击选中要设置的主管，并点击“选择到主管列表”按钮；\n2、在用户列表中点击选中要设置为该主管的成员，并点击“选择到成员列表”按钮；\n3、点击“建立主管-成员关系”按钮，保存关系。\n\n删除主管-成员关系的方法：\n1、点击“列出全部主管-成员关系”；\n2、点击选择主管-成员关系列表中要删除的关系记录；\n3、点击“删除主管-成员关系”；\n注意：“主管”必须是“服务员（也叫投资顾问)”或“分析师”，成员必须是“服务员”");
        jScrollPane5.setViewportView(jTextArea1);

        jLabel5.setText("操作说明");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAddMaster)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Save)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSave)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDelete)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonListAll)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cancel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jLabel2)
                .addGap(161, 161, 161)
                .addComponent(jLabel3)
                .addGap(215, 215, 215)
                .addComponent(jLabel4)
                .addContainerGap(296, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 433, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(318, 318, 318))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(3, 3, 3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Save)
                    .addComponent(jButtonAddMaster)
                    .addComponent(Cancel)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonListAll)
                    .addComponent(jButton1))
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_CancelActionPerformed

    private void jButtonAddMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddMasterActionPerformed
        int selectedRowCount = jTable1.getSelectedRowCount();
        if (selectedRowCount == 0) {
            JOptionPane.showMessageDialog(this, "请先用鼠标点击用户记录");
            return;
        }
        int selectedRow = jTable1.getSelectedRow();
        int mr = jTable1.convertRowIndexToModel(selectedRow);
        TableModel md = jTable1.getModel();

        Object master_id_O = md.getValueAt(mr, 0);
        String master_id = " ";
        if (master_id_O != null) {
            master_id = master_id_O.toString().trim();
        }
        Object master_O = md.getValueAt(mr, 1);
        String master = " ";
        if (master_O != null) {
            master = master_O.toString().trim();
        }
        String sql = "select " + master_id + " as 主管用户户号,'" + master + "' as 主管姓名";
        // System.out.println(sql);
        JDBTableModel dm = new JDBTableModel(jTable2);
        dm.fetchDataToTable(Main.conn, sql, columnType, false);
        hasSelected_1 = true;
    }//GEN-LAST:event_jButtonAddMasterActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        int selectedRowCount = jTable1.getSelectedRowCount();
        if (selectedRowCount == 0) {
            JOptionPane.showMessageDialog(this, "请先点击用户记录");
            return;
        }
        int selectedRow = jTable1.getSelectedRow();
        int mr = jTable1.convertRowIndexToModel(selectedRow);
        TableModel md = jTable1.getModel();

        Object member_id_O = md.getValueAt(mr, 0);
        String member_id = " ";
        if (member_id_O != null) {
            member_id = member_id_O.toString().trim();
        }
        Object member_O = md.getValueAt(mr, 1);
        String member = " ";
        if (member_O != null) {
            member = member_O.toString().trim();
        }
        //System.out.println(member_id + "," + member + "mr=" + mr);
        String sql = "select " + member_id + " as 成员用户户号,'" + member + "' as 成员姓名";
        // System.out.println(sql);
        JDBTableModel dm = new JDBTableModel(jTable3);
        dm.fetchDataToTable(Main.conn, sql, columnType, false);
        hasSelected_2 = true;
    }//GEN-LAST:event_SaveActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        if (!hasSelected_1 || !hasSelected_2) {
            JOptionPane.showMessageDialog(this, "请选择用户到主管列表与成员列表");
            return;
        }

        TableModel md2 = jTable2.getModel();
        Object master_id_O = md2.getValueAt(0, 0);
        String master_id_s = " ";
        if (master_id_O != null) {
            master_id_s = master_id_O.toString().trim();
        }
        int master_id = Integer.valueOf(master_id_s).intValue();

        TableModel md3 = jTable3.getModel();
        Object member_id_O = md3.getValueAt(0, 0);
        String member_id_s = " ";
        if (member_id_O != null) {
            member_id_s = member_id_O.toString().trim();
        }
        int member_id = Integer.valueOf(member_id_s).intValue();
        String sql = "add_master_member " + master_id + "," + member_id;
        System.out.println(sql);
        SqlExecutor sqlx = new SqlExecutor();
        sqlx.execute1(Main.conn, sql);

        sql = "get_member_list " + master_id;
        JDBTableModel dm4 = new JDBTableModel(jTable4);
        dm4.fetchDataToTable(Main.conn, sql, columnType, true);
        jButtonDelete.setEnabled(true);
}//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        int selectedRowCount = jTable4.getSelectedRowCount();
        if (selectedRowCount == 0) {
            JOptionPane.showMessageDialog(this, "请先点击主管-成员关系记录");
            return;
        }
        int selectedRow = jTable4.getSelectedRow();
        int mr = jTable4.convertRowIndexToModel(selectedRow);
        TableModel md = jTable4.getModel();
        String master = md.getValueAt(mr, 2).toString().trim();
        String member = md.getValueAt(mr, 4).toString().trim();
        if (JOptionPane.showConfirmDialog(this, "确信删除:" + master + "-->" + member + "的关系吗 ?") != JOptionPane.YES_OPTION) {
            return;
        }
        Object master_id_O = md.getValueAt(mr, 1);
        String master_id_s = " ";
        if (master_id_O != null) {
            master_id_s = master_id_O.toString().trim();
        }
        int master_id = Integer.valueOf(master_id_s).intValue();

        Object member_id_O = md.getValueAt(mr, 3);
        String member_id_s = " ";
        if (member_id_O != null) {
            member_id_s = member_id_O.toString().trim();
        }
        int member_id = Integer.valueOf(member_id_s).intValue();

        String sql = "delete_master_member " + master_id + "," + member_id;
        System.out.println(sql);
        SqlExecutor sqlx = new SqlExecutor();
        sqlx.execute1(Main.conn, sql);

        sql = "get_member_list_all ";
        JDBTableModel dm4 = new JDBTableModel(jTable4);
        dm4.fetchDataToTable(Main.conn, sql, columnType, true);
        jButtonDelete.setEnabled(true);
}//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonListAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListAllActionPerformed
        String sql = "get_member_list_all";
        JDBTableModel dm4 = new JDBTableModel(jTable4);
        dm4.fetchDataToTable(Main.conn, sql, columnType, true);
        jButtonDelete.setEnabled(true);
}//GEN-LAST:event_jButtonListAllActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String sql = "get_master_list";
        JDBTableModel dm4 = new JDBTableModel(jTable4);
        dm4.fetchDataToTable(Main.conn, sql, columnType, true);
        jButtonDelete.setEnabled(false);
    }//GEN-LAST:event_jButton1ActionPerformed
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JButton Save;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAddMaster;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonListAll;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}