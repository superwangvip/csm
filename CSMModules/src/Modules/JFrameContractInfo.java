/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameContractInfo.java
 *
 * Created on 2012-6-20, 12:32:16
 */
package Modules;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Administrator
 */
public class JFrameContractInfo extends javax.swing.JFrame {

    /** Creates new form JFrameContractInfo */
    public JFrameContractInfo() {
        initComponents();
        int iThisWidth = 1010;
        int iThisHight = 660;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y - 10, iThisWidth, iThisHight);
        getContractItems();

        //注册键盘监听器，监听键盘动作，把系统无操作等待计时器置0;对非管理员屏蔽批量数据复制
        MainMenu.registerKeyListener(jTable1);
        //注册鼠标动作监听器，监听鼠标动作，把系统无操作等待计时器置0
        MainMenu.registerMouseListener(this);
        MainMenu.registerMouseListener(jTable1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextFieldFundAccount = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxContractItem = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jButtonAccount = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("查询客户签约信息");

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
        jTable1.setDragEnabled(true);
        jTable1.setRowHeight(24);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setFont(new java.awt.Font("宋体", 0, 14));
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
        jTable2.setDragEnabled(true);
        jTable2.setRowHeight(24);
        jScrollPane2.setViewportView(jTable2);

        jTextFieldFundAccount.setFont(new java.awt.Font("宋体", 0, 18));
        jTextFieldFundAccount.setForeground(new java.awt.Color(0, 0, 153));

        jLabel2.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel2.setText("资金帐号");

        jComboBoxContractItem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("批量查询");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtonAccount.setText("按帐号查询");
        jButtonAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAccountActionPerformed(evt);
            }
        });

        jButton1.setText("取消");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("签约客户清单(点击记录可列出签约记录）");

        jLabel3.setText("签约记录");

        jLabel6.setForeground(new java.awt.Color(51, 0, 255));
        jLabel6.setText("批量查询时，可选签约项目，如果不选即选第一条空白，则批量查询不会把签约项目做为查询条件");

        jLabel5.setForeground(new java.awt.Color(255, 0, 51));
        jLabel5.setText("注意：");

        jLabel4.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel4.setText("签约项目");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE))
                .addGap(17, 17, 17))
            .addGroup(layout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(jLabel1)
                .addContainerGap(445, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(349, 349, 349)
                .addComponent(jLabel3)
                .addContainerGap(584, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldFundAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel4)
                        .addGap(30, 30, 30)
                        .addComponent(jComboBoxContractItem, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButtonAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton1)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFundAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxContractItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButtonAccount)
                    .addComponent(jButton1)
                    .addComponent(jLabel4))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        TableModel m = jTable1.getModel();
        int r = jTable1.getSelectedRow();
        int mr = jTable1.convertRowIndexToModel(r);
        String fundAccount = m.getValueAt(mr, 1).toString();
        String sql = "get_contract_log " + JFrameLogin.user_id + "," + fundAccount;
        System.out.println(sql);
        JDBTableModel dm2 = new JDBTableModel(jTable2);
        Vector columnType = new Vector();
        boolean addSerial = true;
        dm2.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        if (jTable1.getRowCount() > 0) {
            hasData = true;
        }
}//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int user_id = JFrameLogin.user_id;
        String contract_item = jComboBoxContractItem.getSelectedItem().toString();
        String sql = "execute get_client_by_contract_item  ";
        if (contract_item.equals("")) {
            sql = sql + user_id;
        } else {
            sql = sql + user_id + ",'" + contract_item + "'";
        }
        System.out.println(sql);
        JDBTableModel dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        boolean addSerial = true;
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        MainMenu.makeFace(jTable1);
        //--------------------------
        int r = jTable2.getRowCount();
        int c = jTable2.getColumnCount();
        DefaultTableModel dm2 = new DefaultTableModel(r, c);
        jTable2.setModel(dm2);
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAccountActionPerformed
        long base = 0L;
        long fund_account = 0L;
        String FundAccount = jTextFieldFundAccount.getText().trim();
        String WholeAccount = null;

        if (Main.branchID >= 10) {
            base = Main.branchID * 10000000000L;
            try {
                fund_account = Long.valueOf(FundAccount).longValue();
                if (fund_account < 1000000000L) {
                    fund_account = base + fund_account;
                    WholeAccount = Long.valueOf(fund_account).toString();
                } else {
                    WholeAccount = FundAccount;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "帐号数据格式错！");
                return;
            }
        } else {
            base = Main.branchID * 10000000000L;
            try {
                fund_account = Long.valueOf(FundAccount).longValue();
                if (fund_account < 10000000000L) {
                    fund_account = base + fund_account;
                    WholeAccount = "0" + Long.valueOf(fund_account).toString();
                } else {
                    WholeAccount = FundAccount;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "帐号数据格式错！");
                return;
            }
        }
        //查询客户资料
        jTextFieldFundAccount.setText(WholeAccount);
        int user_id = JFrameLogin.user_id;
        String sql = "execute get_client_info ";
        sql = sql + user_id + "," + fund_account;
        System.out.println(sql);
        JDBTableModel dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        boolean addSerial = true;
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        //查询客户理签约信息
        sql = "get_contract_log " + JFrameLogin.user_id + "," + WholeAccount;
        System.out.println(sql);
        JDBTableModel dm2 = new JDBTableModel(jTable2);
        dm2.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        if (jTable1.getRowCount() > 0) {
            hasData = true;
        }
}//GEN-LAST:event_jButtonAccountActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
}//GEN-LAST:event_jButton1ActionPerformed
    private void getContractItems() {
        jComboBoxContractItem.removeAllItems();
        jComboBoxContractItem.addItem("");
        try {
            PreparedStatement SqlStatement = Main.conn.prepareStatement("execute get_contract_items");
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();

                while (SqlResult.next()) {
                    String contract_item = SqlResult.getString(2);
                    jComboBoxContractItem.addItem(contract_item);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(BasicInfo.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getMessage());
        }
        jComboBoxContractItem.setSelectedIndex(0);
    }

    boolean hasData = false;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAccount;
    private javax.swing.JComboBox jComboBoxContractItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldFundAccount;
    // End of variables declaration//GEN-END:variables
}
