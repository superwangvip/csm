package Modules;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameStockPoolControl.java
 *
 * Created on 2010-8-19, 20:46:15
 */
/**
 *
 * @author Administrator
 */
public class JFrameStockPoolControl extends javax.swing.JFrame {

    private int operationType = 0;
    private final int ADD_STOCK = 1;
    private final int DELETE_STOCK = 2;
    private final int REVISE_STOCK = 3;
    private final int NO_ACTION = 0;
    private boolean GO_ON = true;

    /** Creates new form JFrameStockPoolControl */
    public JFrameStockPoolControl() {
        initComponents();
        int iThisWidth = 812;
        int iThisHight = 580;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y - 10, iThisWidth, iThisHight);
        this.setPreferredSize(screen);
        String sql = "execute get_stock_pool ";
        JDBTableModel dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        boolean addSerial = true;
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        jTextFieldStockCode.setText("");
        jTextFieldStockCode.setEditable(false);
        jTextFieldStockName.setText("");
        jTextFieldStockName.setEditable(false);
        jTextFieldAdvisedPrece.setText("");
        jTextFieldAdvisedPrece.setEditable(false);
        jTextFieldObjectPrice.setText("");
        jTextFieldObjectPrice.setEditable(false);
        jTextFieldAdvisedSellingPrice.setText("");
        jTextFieldAdvisedSellingPrice.setEditable(false);
        jTextFieldStopRate.setText("7");
        jTextFieldStopRate.setEditable(false);
        jTextFieldMemo.setText("");
        jTextFieldMemo.setEditable(false);
        //jLabel7.setText(JFrameLogin.user_name + " 推荐的股票");
        operationType = NO_ACTION;

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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextFieldStockCode = new javax.swing.JTextField();
        jTextFieldStockName = new javax.swing.JTextField();
        jTextFieldAdvisedPrece = new javax.swing.JTextField();
        jTextFieldObjectPrice = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldAdvisedSellingPrice = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldMemo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldStopRate = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("股票池管理");

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
        jTable1.setCellSelectionEnabled(true);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jButton1.setText("添加");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("删除");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("取消");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("保存");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextFieldStockCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldStockCodeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldStockCodeKeyTyped(evt);
            }
        });

        jLabel1.setText("证券代码");

        jLabel2.setText("证券名称");

        jLabel3.setText("建议买入价格");

        jLabel4.setText("建议目标价格");

        jLabel5.setText("建议卖出价格");

        jButton5.setText("修改");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel6.setText("推荐理由");

        jLabel7.setText("股票池管理");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "上海", "深圳" }));

        jLabel8.setText("交易所");

        jLabel9.setText("建议止损比率(%)");

        jTextFieldStopRate.setText("7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(246, 246, 246)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(122, 122, 122)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addGap(19, 19, 19)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(163, 163, 163)
                                        .addComponent(jLabel6)
                                        .addGap(13, 13, 13)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldAdvisedPrece)
                                    .addComponent(jTextFieldObjectPrice)
                                    .addComponent(jTextFieldAdvisedSellingPrice)
                                    .addComponent(jComboBox1, 0, 60, Short.MAX_VALUE)
                                    .addComponent(jTextFieldStockName)
                                    .addComponent(jTextFieldStockCode, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldStopRate, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextFieldMemo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5)
                                    .addComponent(jButton1))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton3))
                                    .addComponent(jButton2))))
                        .addGap(184, 184, 184))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldStockCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldStockName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldAdvisedPrece, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldObjectPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldAdvisedSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jTextFieldStopRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jTextFieldMemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTextFieldStockCode.setEditable(true);
        jTextFieldStockCode.setText("");

        jTextFieldStockName.setEditable(true);
        jTextFieldStockName.setText("");

        jTextFieldAdvisedPrece.setEditable(true);
        jTextFieldAdvisedPrece.setText("");

        jTextFieldObjectPrice.setEditable(true);
        jTextFieldObjectPrice.setText("");

        jTextFieldAdvisedSellingPrice.setEditable(true);
        jTextFieldAdvisedSellingPrice.setText("");

        jTextFieldStopRate.setEditable(true);
        jTextFieldMemo.setEditable(true);
        jTextFieldMemo.setText("");

        operationType = ADD_STOCK;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int r = jTable1.getSelectedRow();
        if (r == -1) {
            JOptionPane.showMessageDialog(this, "请先选择要删除的记录（用鼠标点击记录)");
            return;
        }

        TableModel m = jTable1.getModel();
        String market = m.getValueAt(r, 1).toString();
        jComboBox1.setSelectedItem(market);
        String stock_code = (String) m.getValueAt(r, 2);
        String stock_name = (String) m.getValueAt(r, 3);

        jTextFieldStockCode.setEditable(false);
        jTextFieldStockCode.setText(stock_code);

        jTextFieldStockName.setEditable(false);
        jTextFieldStockName.setText("");

        jTextFieldAdvisedPrece.setEditable(false);
        jTextFieldAdvisedPrece.setText("");

        jTextFieldObjectPrice.setEditable(false);
        jTextFieldObjectPrice.setText("");

        jTextFieldAdvisedSellingPrice.setEditable(false);
        jTextFieldAdvisedSellingPrice.setText("");

        if (JOptionPane.showConfirmDialog(this, "确信删除:" + stock_code + stock_name + "?") == JOptionPane.YES_OPTION) {
            operationType = DELETE_STOCK;
            save_update_info();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int r = jTable1.getSelectedRow();
        if (r == -1) {
            JOptionPane.showMessageDialog(this, "请先选择要修改的记录（用鼠标点击记录)");
            return;
        }
        TableModel m = jTable1.getModel();
        int mr = jTable1.convertRowIndexToModel(r);
        String market = m.getValueAt(mr, 1).toString();
        jComboBox1.setSelectedItem(market);
        String stock_code = m.getValueAt(mr, 2).toString();
        jTextFieldStockCode.setEditable(false);
        jTextFieldStockCode.setText(stock_code);

        String stock_name = m.getValueAt(mr, 3).toString();
        jTextFieldStockName.setEditable(true);
        jTextFieldStockName.setText(stock_name);

        String advised_buying_price = m.getValueAt(mr, 6).toString();
        jTextFieldAdvisedPrece.setEditable(true);
        jTextFieldAdvisedPrece.setText(advised_buying_price);

        String advised_object_price = m.getValueAt(mr, 7).toString();
        jTextFieldObjectPrice.setEditable(true);
        jTextFieldObjectPrice.setText(advised_object_price);

        String advised_selling_price = m.getValueAt(mr, 8).toString();
        jTextFieldAdvisedSellingPrice.setEditable(true);
        jTextFieldAdvisedSellingPrice.setText(advised_selling_price);

        String stop_rate = m.getValueAt(mr, 9).toString();
        jTextFieldStopRate.setEditable(true);
        jTextFieldStopRate.setText(stop_rate);

        String memo = m.getValueAt(mr, 11).toString();
        jTextFieldMemo.setEditable(true);
        jTextFieldMemo.setText(memo);
        operationType = REVISE_STOCK;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (operationType == NO_ACTION) {
            JOptionPane.showMessageDialog(this, "当前没有操作需要保存");
            return;
        }
        if (!isInputValid()) {
            JOptionPane.showMessageDialog(this, "数据格式错 !");
            return;
        }
        save_update_info();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int r = jTable1.getSelectedRow();
        TableModel m = jTable1.getModel();
        String market = m.getValueAt(r, 1).toString();
        jComboBox1.setSelectedItem(market);
        String stock_code = m.getValueAt(r, 2).toString();
        jTextFieldStockCode.setEditable(false);
        jTextFieldStockCode.setText(stock_code);

        String stock_name = m.getValueAt(r, 3).toString();
        jTextFieldStockName.setEditable(false);
        jTextFieldStockName.setText(stock_name);
        jTextFieldStopRate.setEditable(false);

}//GEN-LAST:event_jTable1MouseClicked

    private void jTextFieldStockCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldStockCodeKeyTyped
        String market = jComboBox1.getSelectedItem().toString().trim();
        String inputCode = jTextFieldStockCode.getText().toString().trim();
        int ret_status = 0;
        String stock_name = "";
        if (inputCode.length() == 6) {

            String sql = "execute check_stock_code '" + market + "','" + inputCode + "'";
            PreparedStatement SqlStatement;
            try {
                SqlStatement = Main.conn.prepareStatement(sql);
                Boolean HasResult = SqlStatement.execute();
                while (!HasResult) {
                    HasResult = SqlStatement.getMoreResults();
                }
                if (HasResult) {
                    ResultSet SqlResult = SqlStatement.getResultSet();
                    while (SqlResult.next()) {
                        ret_status = SqlResult.getInt(1);
                        stock_name = SqlResult.getString(2);
                    }
                    if (ret_status < 0) {
                        JOptionPane.showMessageDialog(this, stock_name);
                    } else {
                        jTextFieldStockName.setText(stock_name);
                        jTextFieldStockName.setEditable(false);
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(JFrameStockPoolControl.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
    }//GEN-LAST:event_jTextFieldStockCodeKeyTyped

    private void jTextFieldStockCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldStockCodeKeyPressed
    }//GEN-LAST:event_jTextFieldStockCodeKeyPressed

    public boolean isInputValid() {
        boolean yes = true;
        String advised_price = jTextFieldAdvisedPrece.getText();
        String advised_object_price = jTextFieldObjectPrice.getText();
        String advised_selling_price = jTextFieldAdvisedSellingPrice.getText();
        try {
            double d = Double.valueOf(advised_price);
            d = Double.valueOf(advised_object_price);
            d = Double.valueOf(advised_selling_price);
        } catch (NumberFormatException ex) {
            yes = false;
        }
        return yes;
    }

    public int save_update_info() {
        int ret_status = 0;
        String ret_message = "保存成功";
        PreparedStatement SqlStatement;
        if (operationType == ADD_STOCK) {
            try {
                // TODO add your handling code here:
                int adviser_id = JFrameLogin.user_id;
                String adviser = JFrameLogin.user_name;
                String market = jComboBox1.getSelectedItem().toString();
                String stock_code = jTextFieldStockCode.getText();
                String stock_name = jTextFieldStockName.getText();
                String advised_price = jTextFieldAdvisedPrece.getText();
                String advised_object_price = jTextFieldObjectPrice.getText();
                String advised_selling_price = jTextFieldAdvisedSellingPrice.getText();
                String stop_rate = jTextFieldStopRate.getText();
                String memo = jTextFieldMemo.getText();
                if (memo == null) {
                    memo = " ";
                }

                String sql = "execute add_stock_pool '";
                sql = sql + market + "','" + stock_code + "','" + stock_name + "'," + advised_price + "," + advised_object_price + ",";
                sql = sql + advised_selling_price + "," + adviser_id + ",'" + adviser + "'," + stop_rate + ",'" + memo + "'";
                //System.out.println(sql);
                SqlStatement = Main.conn.prepareStatement(sql);
                Boolean HasResult = SqlStatement.execute();

                while (!HasResult) {
                    HasResult = SqlStatement.getMoreResults();
                }
                if (HasResult) {

                    ResultSet SqlResult = SqlStatement.getResultSet();
                    while (SqlResult.next()) {
                        ret_status = SqlResult.getInt("ret_status");
                        ret_message = SqlResult.getString("ret_msg");//????
                    }
                }
                jTextFieldStockCode.setText("");
                jTextFieldStockCode.setEditable(false);
                jTextFieldStockName.setText("");
                jTextFieldStockName.setEditable(false);
                jTextFieldAdvisedPrece.setText("");
                jTextFieldAdvisedPrece.setEditable(false);
                jTextFieldObjectPrice.setText("");
                jTextFieldObjectPrice.setEditable(false);
                jTextFieldAdvisedSellingPrice.setText("");
                jTextFieldAdvisedSellingPrice.setEditable(false);

                jTextFieldMemo.setText("");
                jTextFieldMemo.setEditable(false);
                operationType = NO_ACTION;
            } catch (SQLException ex) {
                Logger.getLogger(JFrameServiceCallback.class.getName()).log(Level.SEVERE, null, ex);
                Main.logger.warning(ex.getLocalizedMessage());
                JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());

            }
        } else if (operationType == REVISE_STOCK) {
            int operator_id = JFrameLogin.user_id;
            String market = jComboBox1.getSelectedItem().toString();
            String stock_code = jTextFieldStockCode.getText();
            String advised_buying_price = jTextFieldAdvisedPrece.getText();
            String advised_object_price = jTextFieldObjectPrice.getText();
            String advised_selling_price = jTextFieldAdvisedSellingPrice.getText();
            String stop_rate = jTextFieldStopRate.getText();
            String memo = jTextFieldMemo.getText();
            String sql = "execute revise_stock_pool '" + market + "','" + stock_code + "',";
            sql = sql + advised_buying_price + "," + advised_object_price + "," + advised_selling_price + ",";
            sql = sql + stop_rate + ",'" + memo + "'," + operator_id;
            try {
                SqlStatement = Main.conn.prepareStatement(sql);
                Boolean HasResult = SqlStatement.execute();
                while (!HasResult) {
                    HasResult = SqlStatement.getMoreResults();
                }
                if (HasResult) {
                    ResultSet SqlResult = SqlStatement.getResultSet();
                    while (SqlResult.next()) {
                        ret_status = SqlResult.getInt("ret_status");
                        ret_message = SqlResult.getString("ret_msg");//?????
                    }
                }
                jTextFieldStockCode.setText("");
                jTextFieldStockCode.setEditable(false);
                jTextFieldStockName.setText("");
                jTextFieldStockName.setEditable(false);
                jTextFieldAdvisedPrece.setText("");
                jTextFieldAdvisedPrece.setEditable(false);
                jTextFieldObjectPrice.setText("");
                jTextFieldObjectPrice.setEditable(false);
                jTextFieldAdvisedSellingPrice.setText("");
                jTextFieldAdvisedSellingPrice.setEditable(false);
                jTextFieldStopRate.setEditable(false);
                jTextFieldMemo.setText("");
                jTextFieldMemo.setEditable(false);
                operationType = NO_ACTION;
            } catch (SQLException ex) {
                Logger.getLogger(JFrameServiceCallback.class.getName()).log(Level.SEVERE, null, ex);
                Main.logger.warning(ex.getMessage());
                JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
            }
        } else if (operationType == DELETE_STOCK) {
            try {
                int operator = JFrameLogin.user_id;
                int r = jTable1.getSelectedRow();
                TableModel m = jTable1.getModel();
                String market = m.getValueAt(r, 1).toString();
                String stock_code = jTextFieldStockCode.getText();
                SqlStatement = Main.conn.prepareStatement("execute delete_stock_pool ?,?,?");
                SqlStatement.setString(1, market);
                SqlStatement.setString(2, stock_code);
                SqlStatement.setInt(3, operator);


                Boolean HasResult = SqlStatement.execute();
                while (!HasResult) {
                    HasResult = SqlStatement.getMoreResults();
                }
                if (HasResult) {
                    ResultSet SqlResult = SqlStatement.getResultSet();

                    while (SqlResult.next()) {
                        ret_status = SqlResult.getInt("ret_status");
                        ret_message = SqlResult.getString("ret_msg");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(JFrameServiceCallback.class.getName()).log(Level.SEVERE, null, ex);
                Main.logger.warning(ex.getMessage());
                JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
            }
            jTextFieldStockCode.setText("");
            jTextFieldStockCode.setEditable(false);
            jTextFieldStockName.setText("");
            jTextFieldStockName.setEditable(false);
            jTextFieldAdvisedPrece.setText("");
            jTextFieldAdvisedPrece.setEditable(false);
            jTextFieldObjectPrice.setText("");
            jTextFieldObjectPrice.setEditable(false);
            jTextFieldAdvisedSellingPrice.setText("");
            jTextFieldAdvisedSellingPrice.setEditable(false);
            jTextFieldMemo.setText("");
            jTextFieldMemo.setEditable(false);
            operationType = NO_ACTION;
        } else {
            ret_message = "你没有操作或者最近修改已经保存";
        }
        JOptionPane.showMessageDialog(this, ret_message);

        String sql = "execute get_stock_pool ";
        JDBTableModel dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        boolean addSerial = true;
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        return ret_status;
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldAdvisedPrece;
    private javax.swing.JTextField jTextFieldAdvisedSellingPrice;
    private javax.swing.JTextField jTextFieldMemo;
    private javax.swing.JTextField jTextFieldObjectPrice;
    private javax.swing.JTextField jTextFieldStockCode;
    private javax.swing.JTextField jTextFieldStockName;
    private javax.swing.JTextField jTextFieldStopRate;
    // End of variables declaration//GEN-END:variables
}
