/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameLostClient.java
 *
 * Created on 2012-6-18, 17:44:35
 */
package Modules;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author Administrator
 */
public class JFrameLostClient extends javax.swing.JFrame {

    Vector columnType = new Vector();
    boolean addSerial = false;
    boolean hasData = false;
    String outPath = "out";//文件输出目录

    /** Creates new form JFrameLostClient */
    public JFrameLostClient() {
        initComponents();
        int iThisWidth = 1010;
        int iThisHight = 690;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y, iThisWidth, iThisHight);

        //注册键盘监听器，监听键盘动作，把系统无操作等待计时器置0;对非管理员屏蔽批量数据复制
        MainMenu.registerKeyListener(jTable1);
        //注册鼠标动作监听器，监听鼠标动作，把系统无操作等待计时器置0
        MainMenu.registerMouseListener(this);
        MainMenu.registerMouseListener(jTable1);
        //初始化服务员选择框
        BasicInfo bsi = BasicInfo.getBaseicInfo();
        Vector servicePerson = bsi.getServicePerson();
        Enumeration enum1 = servicePerson.elements();
        jComboBoxUser.removeAllItems();
        jComboBoxUser.addItem("全部");
        while (enum1.hasMoreElements()) {
            String servicePerson1 = enum1.nextElement().toString();
            jComboBoxUser.addItem(servicePerson1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextFieldTimeLimit = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldBottom = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldBottom1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxUser = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("查询隐性流失客户");

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
        jTable1.setToolTipText("单击列头可按列排序");
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setText("查询客户资料");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("取消");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextFieldTimeLimit.setText("3");

        jLabel1.setText("交易量连续低于下限月份数");

        jTextFieldBottom.setText("10000");

        jLabel2.setText("交易量低于");

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
        jTable2.setToolTipText("单击列头可按列排序");
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setText("可能的隐性流失客户");

        jLabel4.setText("资产历史");

        jLabel6.setForeground(new java.awt.Color(51, 0, 255));
        jLabel6.setText("点击客户记录可列出其资产历史");

        jTextFieldBottom1.setText("10000");

        jLabel5.setText("资产低于");

        jComboBoxUser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxUserMouseClicked(evt);
            }
        });
        jComboBoxUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBoxUserFocusGained(evt);
            }
        });
        jComboBoxUser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBoxUserPropertyChange(evt);
            }
        });

        jLabel7.setText("服务员");

        jButton5.setText("保存数据到Excel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("保存数据到DBF");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(390, 390, 390)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(422, 422, 422)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldTimeLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldBottom1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel7)
                                .addGap(350, 350, 350)
                                .addComponent(jButton2)
                                .addGap(33, 33, 33)))))
                .addGap(57, 57, 57))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(552, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jComboBoxUser, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton1)))
                .addGap(218, 218, 218))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jComboBoxUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBottom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTimeLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton5))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int user_id = JFrameLogin.user_id;
        double match_amt_bottom = 0.0;
        double asset_bottom = 0.0;

        String s = jTextFieldBottom1.getText().trim();
        if (!s.isEmpty()) {
            try {
                asset_bottom = Double.valueOf(s);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "资产下限数据格式错！");
                return;
            }
        }
        String s1 = jTextFieldBottom.getText().trim();
        if (!s1.isEmpty()) {
            try {
                match_amt_bottom = Double.valueOf(s1);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "交易量下限数据格式错！");
                return;
            }
        }
        String t = jTextFieldTimeLimit.getText().trim();
        int n = 0;
        if (!t.isEmpty()) {
            try {
                n = Integer.valueOf(t);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "无交易月份数据格式错！");
                return;
            }
        }
        String sql = "execute get_client_lost ";
        sql = sql + user_id + "," + n + "," + asset_bottom + "," + match_amt_bottom;
        String service_person = jComboBoxUser.getSelectedItem().toString().trim();
        if (!service_person.equals("全部")) {
            sql = sql + ",'" + service_person + "'";
            System.out.println(sql);
        }

        JDBTableModel dm = new JDBTableModel(jTable1);
        System.out.println(sql);
        String msg = "正在统计......";
        addSerial = true;
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial, msg);
        if (jTable1.getRowCount() > 1) {
            hasData = true;
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        TableModel m = jTable1.getModel();
        int r = jTable1.getSelectedRow();
        int mr = jTable1.convertRowIndexToModel(r);
        String fundAccount = m.getValueAt(mr, 1).toString();

        String sql = "get_client_hist " + fundAccount;
        JDBTableModel dm2 = new JDBTableModel(jTable2);
        Vector columnType = new Vector();
        boolean addSerial = true;

        dm2.fetchDataToTable(Main.conn, sql, columnType, addSerial);
}//GEN-LAST:event_jTable1MouseClicked

    private void jComboBoxUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxUserMouseClicked
}//GEN-LAST:event_jComboBoxUserMouseClicked

    private void jComboBoxUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxUserFocusGained
}//GEN-LAST:event_jComboBoxUserFocusGained

    private void jComboBoxUserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBoxUserPropertyChange
}//GEN-LAST:event_jComboBoxUserPropertyChange

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "没有数据需要保存!");
            return;
        }
        String service_pderson = jComboBoxUser.getSelectedItem().toString().trim();
        String outputFile = "隐性流失客户清单--" + service_pderson + ".xls";
        pathExist(outPath);//检查并创建文件输出目录
        String outputFile1 = outPath + "\\" + outputFile;
        JTableToExcel tbexcel = new JTableToExcel();

        try {
            tbexcel.saveToExcel(jTable1, columnType, outputFile1, addSerial);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JFrameSql.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(JFrameSql.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getMessage());
        }
        String msg = outputFile1 + "已保存,记录总数:" + jTable1.getRowCount();
        JOptionPane.showMessageDialog(this, msg);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "没有数据需要保存!");
            return;
        }
        String service_pderson = jComboBoxUser.getSelectedItem().toString().trim();
        TableModel md = jTable1.getModel();
        String outputFile = "隐性流失客户_" + service_pderson + ".dbf";
        pathExist(outPath);//检查并创建文件输出目录
        String outputFile1 = outPath + "\\" + outputFile;
        JTableToDBF toDBF = new JTableToDBF();
        try {
            int n = toDBF.saveToDBF(jTable1, columnType, outputFile1);
            String msg = outputFile1 + " 已保存" + ",记录总数：" + n;
            JOptionPane.showMessageDialog(this, msg);
        } catch (JDBFException ex) {
            Main.logger.warning(ex.getMessage());
            new JFrameWarning(ex.getLocalizedMessage()).setVisible(true);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(JFrameSql.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getMessage());
            new JFrameWarning(ex.getLocalizedMessage()).setVisible(true);
        }
}//GEN-LAST:event_jButton6ActionPerformed

    //检查并创建目录
    public void pathExist(String path) {
        File file = new File(path);
        //判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdir();
            System.out.println(path + "--目录已经建立");
        } else {
            System.out.println(path + "--目录已经存在");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBoxUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldBottom;
    private javax.swing.JTextField jTextFieldBottom1;
    private javax.swing.JTextField jTextFieldTimeLimit;
    // End of variables declaration//GEN-END:variables
}
