/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameUserControl.java
 *
 * Created on 2010-4-23, 21:02:40
 */
package Modules;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author Jiang Youquan
 */
public class JFrameUserControl extends javax.swing.JFrame {

    private String user_id = "0";
    private String group_id = "0";
    private int operationType = 0;
    private final int ADD_USER = 1;
    private final int DELETE_USER = 2;
    private final int RESET_PASSWORD = 3;
    private final int CHANGE_USER_ROLE = 4;
    private final int NO_ACTION = 0;
    private boolean GO_ON = true;

    /** Creates new form JFrameUserControl */
    public JFrameUserControl() {
        initComponents();
        int iThisWidth = 450;
        int iThisHight = 440;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y - 10, iThisWidth, iThisHight);
        this.setPreferredSize(screen);
        String sql = "get_user_list";
        JDBTableModel dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        boolean add_serial = false;
        dm.fetchDataToTable(Main.conn, sql, columnType, add_serial);

        jTextFieldName.setEditable(false);
        jTextFieldName.setText("");
        jPasswordField1.setEditable(false);
        jPasswordField1.setText("");
        jPasswordField2.setEditable(false);
        jPasswordField2.setText("");
        jComboBoxRole.setEnabled(false);
        BasicInfo bsi = BasicInfo.getBaseicInfo();
        groupID = bsi.getGroupID();
        groupName = bsi.getGroupName();
        Enumeration enumR = groupName.elements();
        while (enumR.hasMoreElements()) {
            jComboBoxRole.addItem(enumR.nextElement().toString());
        }
        jComboBoxRole.setSelectedIndex(0);

        //注册键盘监听器，监听键盘动作，把系统无操作等待计时器置0;对非管理员屏蔽批量数据复制
        MainMenu.registerKeyListener(jTable1);
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
        jTextFieldName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxRole = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonAddUser = new javax.swing.JButton();
        jButtonResetPassword = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButtonDeleteUser = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("用户管理");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setFont(new java.awt.Font("宋体", 0, 14));
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
        jTable1.setRowHeight(20);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("姓名");

        jLabel2.setText("密码");

        jLabel3.setText("确认密码");

        jLabel4.setText("角色");

        jButtonAddUser.setText("添加");
        jButtonAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddUserActionPerformed(evt);
            }
        });

        jButtonResetPassword.setText("重置密码");
        jButtonResetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetPasswordActionPerformed(evt);
            }
        });

        jButtonSave.setText("保存");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButton5.setText("取消");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPasswordField1.setText("jPasswordField1");

        jPasswordField2.setText("jPasswordField1");
        jPasswordField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField2FocusLost(evt);
            }
        });

        jButtonDeleteUser.setText("删除");
        jButtonDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteUserActionPerformed(evt);
            }
        });

        jButton1.setText("变更角色");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1)))
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxRole, 0, 160, Short.MAX_VALUE)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jPasswordField2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButtonAddUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonResetPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                            .addComponent(jButton1))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonDeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(279, 279, 279)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(30, 30, 30))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonAddUser)
                                .addComponent(jButtonDeleteUser)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonSave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonResetPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)))))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddUserActionPerformed
        jTextFieldName.setEditable(true);
        jTextFieldName.setText("");
        jPasswordField1.setEditable(true);
        jPasswordField1.setText("");
        jPasswordField2.setEditable(true);
        jPasswordField2.setText("");
        jComboBoxRole.setEnabled(true);
        jComboBoxRole.setSelectedIndex(0);
        operationType = ADD_USER;
    }//GEN-LAST:event_jButtonAddUserActionPerformed

    private void jButtonDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteUserActionPerformed
        int r = jTable1.getSelectedRow();
        if (r == -1) {
            JOptionPane.showMessageDialog(this, "请先选择要删除的记录（用鼠标点击记录)");
            return;
        }

        TableModel m = jTable1.getModel();
        int mr = jTable1.convertRowIndexToModel(r);
        String user_name = (String) m.getValueAt(mr, 1);

        jTextFieldName.setEditable(false);
        jTextFieldName.setText("");
        jPasswordField1.setEditable(false);
        jPasswordField1.setText("");
        jPasswordField2.setEditable(false);
        jPasswordField2.setText("");
        jComboBoxRole.setEnabled(false);

        if (JOptionPane.showConfirmDialog(this, "确信删除:" + user_name + "?") == JOptionPane.YES_OPTION) {
            operationType = DELETE_USER;
            save_update_info();

            String sql = "get_user_list";
            JDBTableModel dm = new JDBTableModel(jTable1);
            Vector columnType = new Vector();
            boolean add_serial = false;
            dm.fetchDataToTable(Main.conn, sql, columnType, add_serial);

            jTextFieldName.setText("");
            jTextFieldName.setEditable(false);
            jPasswordField1.setText("");
            jPasswordField1.setEditable(false);
            jPasswordField2.setText("");
            jPasswordField2.setEditable(false);
        }
    }//GEN-LAST:event_jButtonDeleteUserActionPerformed

    private void jButtonResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetPasswordActionPerformed
        int r = jTable1.getSelectedRow();
        if (r == -1) {
            JOptionPane.showMessageDialog(this, "请先选择要修改的记录（用鼠标点击记录)");
            return;
        }

        TableModel m = jTable1.getModel();
        String userName = (String) m.getValueAt(r, 1);
        jTextFieldName.setEditable(false);
        jTextFieldName.setText(userName);
        jPasswordField1.setEditable(true);
        jPasswordField1.setText("");
        jPasswordField2.setEditable(true);
        jPasswordField2.setText("");
        jComboBoxRole.setEnabled(false);
        jComboBoxRole.setSelectedIndex(0);
        operationType = RESET_PASSWORD;
    }//GEN-LAST:event_jButtonResetPasswordActionPerformed

    private void jPasswordField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField2FocusLost
        char[] pwd1_1 = jPasswordField1.getPassword();
        String pwd1 = String.valueOf(pwd1_1);
        char[] pwd2_1 = jPasswordField2.getPassword();
        String pwd2 = String.valueOf(pwd2_1);
        if (pwd1.compareTo(pwd2) != 0) {
            JOptionPane.showMessageDialog(this, "密码两次输入不符！");
            jPasswordField1.setText("");
            jPasswordField2.setText("");

            GO_ON = false;
        } else {
            GO_ON = true;
        }

    }//GEN-LAST:event_jPasswordField2FocusLost

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        if (operationType == NO_ACTION) {
            JOptionPane.showMessageDialog(this, "你没有操作或者最近操作已经保存");
            return;
        }
        save_update_info();

        String sql = "get_user_list";
        JDBTableModel dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        boolean add_serial = false;
        dm.fetchDataToTable(Main.conn, sql, columnType, add_serial);

        jTextFieldName.setText("");
        jTextFieldName.setEditable(false);
        jPasswordField1.setText("");
        jPasswordField1.setEditable(false);
        jPasswordField2.setText("");
        jPasswordField2.setEditable(false);
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int r = jTable1.getSelectedRow();
        if (r == -1) {
            JOptionPane.showMessageDialog(this, "请先选择要修改的记录（用鼠标点击记录)");
            return;
        }

        TableModel m = jTable1.getModel();
        user_id = (String) m.getValueAt(r, 0);
        String userName = (String) m.getValueAt(r, 1);
        String group_name = (String) m.getValueAt(r, 2);
        group_id = (String) m.getValueAt(r, 3);
        jTextFieldName.setEditable(false);
        jTextFieldName.setText(userName);
        jPasswordField1.setEditable(false);
        jPasswordField1.setText("");
        jPasswordField2.setEditable(false);
        jPasswordField2.setText("");
        jComboBoxRole.setEnabled(true);
        jComboBoxRole.setSelectedItem(group_name);
        operationType = CHANGE_USER_ROLE;
    }//GEN-LAST:event_jButton1ActionPerformed

    public int save_update_info() {
        int ret_status = 0;
        String ret_message = "保存成功";
        if ((operationType == ADD_USER) && GO_ON) {//添加用户
            int operator = JFrameLogin.user_id;
            String user_name = jTextFieldName.getText();
            int role = jComboBoxRole.getSelectedIndex();
            Integer group_ID = (Integer) groupID.elementAt(role);
            int group_id = group_ID.intValue();
            char[] password1 = jPasswordField1.getPassword();
            String password = String.valueOf(password1);

            String sql = "add_user " + operator + ",'" + user_name + "'," + group_ID + ",'" + password + "'";
            System.out.println(sql);
            SqlExecutor sqlx = new SqlExecutor();
            sqlx.execute1(Main.conn, sql);

        } else if ((operationType == RESET_PASSWORD) && GO_ON) {//重置密码

            char[] pwd1_1 = jPasswordField1.getPassword();
            String pwd1 = String.valueOf(pwd1_1);
            char[] pwd2_1 = jPasswordField2.getPassword();
            String pwd2 = String.valueOf(pwd2_1);
            if (pwd1.compareTo(pwd2) != 0) {
                JOptionPane.showMessageDialog(this, "密码两次输入不符！");
                jPasswordField1.setText("");
                jPasswordField2.setText("");
                GO_ON = false;
                return 0;
            } else {
                GO_ON = true;
            }

            int operator = JFrameLogin.user_id;
            int r = jTable1.getSelectedRow();
            TableModel m = jTable1.getModel();
            String user_idStr = ((String) m.getValueAt(r, 0));
            int user_id = Integer.valueOf(user_idStr).intValue();
            char[] new_password1 = jPasswordField1.getPassword();
            String new_password = String.copyValueOf(new_password1);


            String sql = "reset_password " + operator + "," + user_id + ",'" + new_password + "'";
            System.out.println(sql);
            SqlExecutor sqlx = new SqlExecutor();
            sqlx.execute1(Main.conn, sql);
        } else if (operationType == DELETE_USER) {//删除用户
            int operator = JFrameLogin.user_id;
            int r = jTable1.getSelectedRow();
            TableModel m = jTable1.getModel();
            String user_idStr = ((String) m.getValueAt(r, 0));
            int user_id = Integer.valueOf(user_idStr).intValue();

            String sql = "delete_user " + operator + "," + user_id;
            System.out.println(sql);
            SqlExecutor sqlx = new SqlExecutor();
            sqlx.execute1(Main.conn, sql);
        } else if (operationType == CHANGE_USER_ROLE) {//变更用户角色
            int r = jTable1.getSelectedRow();
            TableModel m = jTable1.getModel();
            String user_idStr = ((String) m.getValueAt(r, 0));
            int user_id = Integer.valueOf(user_idStr).intValue();

            int role = jComboBoxRole.getSelectedIndex();
            Integer group_ID = (Integer) groupID.elementAt(role);
            int group_id = group_ID.intValue();

            String sql = "change_user_role " + user_id + "," + group_id;
            System.out.println(sql);
            SqlExecutor sqlx = new SqlExecutor();
            sqlx.execute1(Main.conn, sql);
        } else {
            ret_message = "你没有操作或者最近修改已经保存";
            JOptionPane.showMessageDialog(this, ret_message);
        }

        operationType = NO_ACTION;

        return ret_status;
    }
    Vector groupID;
    Vector groupName;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonAddUser;
    private javax.swing.JButton jButtonDeleteUser;
    private javax.swing.JButton jButtonResetPassword;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
}

