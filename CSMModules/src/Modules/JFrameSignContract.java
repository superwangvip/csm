/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * JFrameSignContract.java
 *
 * Created on 2010-8-20, 15:25:54
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
 * @author Youquan Jiang
 */
public class JFrameSignContract extends javax.swing.JFrame {

    private JDBTableModel dm = null;
    boolean readAll = true;

    /** Creates new form JFrameSignContract */
    public JFrameSignContract() {
        initComponents();
        int iThisWidth = 1010;
        int iThisHight = 630;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y - 10, iThisWidth, iThisHight);
        fetchUsersToCombox();
        jComboBoxUserList.setSelectedItem(JFrameLogin.user_name);

        jTextFieldFundAccount.setText("");
        jTextFieldName.setText("");
        jTextFieldName.setFocusable(false);
        BasicInfo bsi = BasicInfo.getBaseicInfo();
        ContractItems = bsi.getContractItems();
        Enumeration enum1 = ContractItems.elements();
        jComboBoxContractItems.removeAllItems();
        while (enum1.hasMoreElements()) {
            String item = enum1.nextElement().toString();
            jComboBoxContractItems.addItem(item);
        }

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
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldFundAccount = new javax.swing.JTextField();
        jComboBoxContract = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldFlag = new javax.swing.JTextField();
        jComboBoxUserList = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxContractItems = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldCommRate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldContractDate = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldDeadline = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldRemark = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldSeller = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldMsgTel = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("客户签约管理");

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
        jTable1.setToolTipText("用鼠标点击要签约的客户");
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 952, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jButton2.setForeground(new java.awt.Color(153, 0, 204));
        jButton2.setText("按帐号查询客户");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("保存");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("取消");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("资金帐号");

        jTextFieldFundAccount.setFont(new java.awt.Font("宋体", 0, 14));

        jComboBoxContract.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "签约", "解约" }));

        jLabel2.setText("客户姓名");

        jTextFieldName.setEditable(false);
        jTextFieldName.setFont(new java.awt.Font("宋体", 0, 14));

        jLabel3.setText("指定服务人员");

        jLabel4.setText("签约标志");

        jTextFieldFlag.setEditable(false);
        jTextFieldFlag.setFont(new java.awt.Font("宋体", 0, 14));
        jTextFieldFlag.setText(" ");

        jLabel5.setText("操作选项");

        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setText("注意事项：");

        jLabel7.setForeground(new java.awt.Color(51, 0, 255));
        jLabel7.setText("若管理员操作签约功能，选择指定服务人员签约就立刻把该签约客户的服务关系分配给他（她），也可以在“服务安排”中指定或改变服务员，但解约操作不会取消已有服务关系");

        jComboBoxContractItems.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("签约项目");

        jLabel9.setText("签约佣金标准‰");

        jLabel10.setText("签约/解约日期YYYYMMDD");

        jLabel11.setText("合同到期日");

        jLabel12.setText("原佣金标准‰");

        jLabel15.setText("备注");

        jLabel16.setText("短信电话");

        jLabel17.setText("销售人员");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextFieldFlag, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(155, 155, 155))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextFieldFundAccount, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxContract, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxContractItems, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel17))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldSeller, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                            .addComponent(jTextFieldContractDate, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel11)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextFieldCommRate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextFieldDeadline, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldMsgTel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextFieldRemark, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3)
                                .addGap(7, 7, 7)
                                .addComponent(jComboBoxUserList, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4)))
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jTextFieldFundAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldFlag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jTextFieldContractDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldDeadline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(jTextFieldMsgTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldSeller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxContract, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBoxContractItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(jTextFieldCommRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldRemark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxUserList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jButton4)
                            .addComponent(jButton3))))
                .addGap(14, 14, 14)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

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
        jTable2.setToolTipText("用鼠标点击要签约的客户");
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setColumnSelectionAllowed(true);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel13.setText("客户资料");

        jLabel14.setText("签约记录(操作日志)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(447, 447, 447)
                        .addComponent(jLabel13))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(427, 427, 427)
                        .addComponent(jLabel14))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(2, 2, 2)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        findByAccount();
        search = true;
        if (jTable1.getRowCount() > 0) {
            setData();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void findByAccount() {
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
        jTextFieldFundAccount.setText(WholeAccount);
        readAll = false;
        fetchData(readAll, fund_account);
    }
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        setData();
    }//GEN-LAST:event_jTable1MouseClicked
    private void setData() {
        jTextFieldCommRate.setText("");
        jTextField1.setText("");
        jTextFieldContractDate.setText("");
        jTextFieldDeadline.setText("");
        jTextFieldMsgTel.setText("");
        jTextFieldSeller.setText("");
        jTextFieldRemark.setText("");
        jComboBoxContract.setSelectedIndex(0);
        jComboBoxUserList.setSelectedIndex(0);
        String fund_account = "";
        String name = "";
        int selectedRow = 0; //jTable1.getSelectedRow();
        int mr = jTable1.convertRowIndexToModel(selectedRow);

       // jTextFieldFundAccount.setText(fund_account);
         jTextFieldFundAccount.setText(dm.getValueByColumnName(mr, "资金帐号"));
        jTextFieldName.setText(dm.getValueByColumnName(mr, "姓名"));
        jTextFieldFlag.setText(dm.getValueByColumnName(mr, "签约标志"));
        String service_person = "";

        jComboBoxUserList.setSelectedItem(dm.getValueByColumnName(mr, "服务员"));

        jTextField1.setText(dm.getValueByColumnName(mr, "佣金率‰"));
    // jTextFieldCommRate.setText(comm_rate);
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int r = 0;
        if (search) {
            r = jTable1.getRowCount();
        }
        if (r == 0) {
            JOptionPane.showMessageDialog(this, "没有客户资料，先按帐号查询");
            return;
        }
        int returnStatus = 0;
        String returnMessage = "保存成功";
        String fund_account = jTextFieldFundAccount.getText();
        int mr = jTable1.convertRowIndexToModel(0);
        TableModel md = jTable1.getModel();
        String contract_flag = jComboBoxContract.getSelectedItem().toString();
        int n = jComboBoxUserList.getSelectedIndex();
        Integer servicePersonID = (Integer) userID.get(n);
        int investment_adviser_id = servicePersonID.intValue();
        String contract_time = jTextFieldContractDate.getText();
        String item = jComboBoxContractItems.getSelectedItem().toString();
        String CommRate_S = jTextFieldCommRate.getText();
        String deadline = jTextFieldDeadline.getText();
        String old_comm_rate = jTextField1.getText();
        String remark = jTextFieldRemark.getText();
        String seller = jTextFieldSeller.getText();
        String msg_tel = jTextFieldMsgTel.getText();
        double CommRate = 0.0;
        //检查数据合法性
        if (contract_flag.equals("签约")) {
            try {
                CommRate = Double.valueOf(CommRate_S);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "签约佣金数据格式错！");
                return;
            }
        }
        if (!isValidDateString(contract_time)) {
            JOptionPane.showMessageDialog(this, "签约日期数据格式错！");
            return;
        }
        //调用存储过程写入签约资料
        String sql = "sign_contract " + fund_account + "," + investment_adviser_id;
        sql = sql + ",'" + contract_flag + "','" + item + "','" + contract_time + "','";
        sql = sql + deadline + "'," + CommRate + "," + old_comm_rate + ",'" + remark + "'," + JFrameLogin.user_id + ",'";
        sql = sql + seller + "','" + msg_tel + "'";
        System.out.println(sql);
        SqlExecutor sqlx = new SqlExecutor();
        sqlx.execute1(Main.conn, sql);
        //刷新客户清单
        if (readAll) {
            fetchData(readAll, 0);
        } else {
            findByAccount();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked
    private void fetchUsersToCombox() {
        BasicInfo bsi = BasicInfo.getBaseicInfo();
        userID =
                bsi.getUserID();
        userName =
                bsi.getUserName();
        Enumeration enum1 = userName.elements();
        while (enum1.hasMoreElements()) {
            String name = enum1.nextElement().toString();
            jComboBoxUserList.addItem(name);
        }

    }

    public void fetchData(boolean readAll, long fund_account) {
        String sql = "";
        int user_id = JFrameLogin.user_id;

        int n = jComboBoxUserList.getSelectedIndex();
        Integer servicePersonID = (Integer) userID.get(n);
        int service_person_id = servicePersonID.intValue();
        //读出客户资料
        sql = "get_client_info " + user_id + "," + fund_account;
        System.out.println(sql);
        dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        boolean addSerial = true;
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);

        //读出签约日志
        JDBTableModel dm2 = new JDBTableModel(jTable2);
        sql = "get_contract_log " + JFrameLogin.user_id + "," + fund_account;
        System.out.println(sql);
        dm2.fetchDataToTable(Main.conn, sql, columnType, addSerial);
    }

    public boolean isValidDateString(String dateStr) {
        boolean yes = true;
        if (dateStr.trim().length() != 8) {
            yes = false;
            return yes;
        }
        String yearStr = dateStr.substring(0, 4);
        String monthStr = dateStr.substring(4, 6);
        String dayStr = dateStr.substring(6);
        try {
            int year = Integer.valueOf(yearStr);
            int month = Integer.valueOf(monthStr);
            int day = Integer.valueOf(dayStr);
            if (year < 2010 || year > 2020 || month < 1 || month > 12 || day < 1 || day > 31) {
                yes = false;
            }
        } catch (NumberFormatException e) {
            yes = false;
        }
        return yes;
    }
    Vector userID;
    Vector userName;
    Vector ContractItems;
    boolean search = false;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBoxContract;
    private javax.swing.JComboBox jComboBoxContractItems;
    private javax.swing.JComboBox jComboBoxUserList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldCommRate;
    private javax.swing.JTextField jTextFieldContractDate;
    private javax.swing.JTextField jTextFieldDeadline;
    private javax.swing.JTextField jTextFieldFlag;
    private javax.swing.JTextField jTextFieldFundAccount;
    private javax.swing.JTextField jTextFieldMsgTel;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldRemark;
    private javax.swing.JTextField jTextFieldSeller;
    // End of variables declaration//GEN-END:variables
}
