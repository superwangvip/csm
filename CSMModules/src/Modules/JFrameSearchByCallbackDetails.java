/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameSearchByCallbackDetails.java
 *

 */
package Modules;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author Administrator
 */
public class JFrameSearchByCallbackDetails extends javax.swing.JFrame {
    private boolean havingData = false;
    boolean addSerial = true;
    Vector columnType1 = new Vector();
    Vector columnType2 = new Vector();
    Vector servicePersonID;
    Vector servicePerson;

    /** Creates new form JFrameSearchByCallbackDetails */
    public JFrameSearchByCallbackDetails() {
        initComponents();
        int iThisWidth = 1010;
        int iThisHight = 710;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y - 10, iThisWidth, iThisHight);
        iniControls();
        jLabelPrompt.setText("");

        //注册键盘监听器，监听键盘动作，把系统无操作等待计时器置0;对非管理员屏蔽批量数据复制
        MainMenu.registerKeyListener(jTable1);
        //注册鼠标动作监听器，监听鼠标动作，把系统无操作等待计时器置0
        MainMenu.registerMouseListener(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxOperationFavourite = new javax.swing.JComboBox();
        jComboBoxProfession = new javax.swing.JComboBox();
        jComboBoxReligion = new javax.swing.JComboBox();
        jComboBoxResidence = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldFundAccount = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxTrading_way = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBoxNation = new javax.swing.JComboBox();
        jComboBoxServicePlatform = new javax.swing.JComboBox();
        jComboBox1ServiceFrequence = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListInvestmentEver = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxSoftware = new javax.swing.JComboBox();
        jButtonAccount = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxIsDiy = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jComboBox1ServicePerson = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxRiskFavourite = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxDecisionType = new javax.swing.JComboBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListHobby = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabelPrompt = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jComboBoxSatisfaction = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jComboBoxUserList = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("按详细回访信息查询客户");

        jLabel1.setText("职业");

        jLabel11.setText("宗教信仰");

        jLabel14.setText("居住区县");

        jLabel6.setText("操作偏好");

        jComboBoxOperationFavourite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxProfession.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxReligion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxResidence.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel16.setText("资金帐号");

        jLabel17.setText("客户姓名");

        jTextFieldName.setForeground(new java.awt.Color(0, 0, 255));

        jTextFieldFundAccount.setForeground(new java.awt.Color(0, 0, 153));

        jLabel2.setText("交易方式");

        jComboBoxTrading_way.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("喜欢的服务平台");

        jLabel12.setText("民族");

        jLabel15.setText("服务频度");

        jComboBoxNation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxServicePlatform.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox1ServiceFrequence.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jListInvestmentEver.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListInvestmentEver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListInvestmentEverMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jListInvestmentEver);

        jLabel3.setText("使用交易软件");

        jLabel8.setText("以往投资品种");

        jComboBoxSoftware.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonAccount.setText("按帐号读取客户资料");
        jButtonAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAccountActionPerformed(evt);
            }
        });

        jLabel9.setText("是否本人操作");

        jComboBoxIsDiy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setText("为其服务人员");

        jComboBox1ServicePerson.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("风险偏好");

        jComboBoxRiskFavourite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("投资决策类型");

        jLabel10.setText("客户爱好");

        jComboBoxDecisionType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jListHobby.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListHobby.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListHobbyMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jListHobby);

        jButton3.setText("取    消");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

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
        jScrollPane2.setViewportView(jTable2);

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel18.setText("客户基本信息");

        jLabelPrompt.setForeground(new java.awt.Color(0, 0, 153));
        jLabelPrompt.setText("（点击基本信息记录可列出详细回访信息）");

        jButton1.setText("按组合条件查询客户资料");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel20.setText("详细回访信息");

        jLabel19.setText("回访满意度");

        jLabel21.setText("服务人员");

        jButton2.setText("取消");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel22.setForeground(new java.awt.Color(51, 0, 255));
        jLabel22.setText("如果某单选项为空白或多选项未选，则该选项不作为查询条件");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelPrompt)
                                .addGap(463, 463, 463))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 978, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(346, 346, 346)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 579, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldName, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldFundAccount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jButtonAccount)
                                        .addGap(97, 97, 97)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBoxOperationFavourite, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxProfession, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxReligion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxResidence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBoxServicePlatform, 0, 95, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel15)
                                                    .addComponent(jLabel12))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jComboBox1ServiceFrequence, 0, 79, Short.MAX_VALUE)
                                                    .addComponent(jComboBoxNation, 0, 79, Short.MAX_VALUE)))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel19))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jComboBoxSatisfaction, 0, 96, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addComponent(jComboBoxTrading_way, 0, 93, Short.MAX_VALUE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(69, 69, 69)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel21))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBoxUserList, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBoxSoftware, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(166, 166, 166)
                                        .addComponent(jLabel8)))))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBoxIsDiy, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxRiskFavourite, javax.swing.GroupLayout.Alignment.LEADING, 0, 77, Short.MAX_VALUE)
                                    .addComponent(jComboBox1ServicePerson, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(104, 104, 104)
                                        .addComponent(jLabel10))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBoxDecisionType, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jButton2)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPrompt)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAccount)
                    .addComponent(jTextFieldFundAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBoxSatisfaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBoxUserList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel22)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxProfession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTrading_way, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxSoftware, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxRiskFavourite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxDecisionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxOperationFavourite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jComboBoxServicePlatform, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxReligion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jComboBoxNation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxResidence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jComboBox1ServiceFrequence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jComboBoxIsDiy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jComboBox1ServicePerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jListInvestmentEverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListInvestmentEverMouseClicked
        String s = jListInvestmentEver.getSelectedValue().toString();
        int i = jListInvestmentEver.getSelectedIndex();
        if (s.startsWith("√")) {
            s = s.substring(1);
        } else {
            s = "√" + s;
        }

        ListModel md = jListInvestmentEver.getModel();
        int m = md.getSize();
        String[] ns = new String[m];
        for (int j = 0; j <
                m; j++) {
            if (j == i) {
                ns[j] = s;
            } else {
                ns[j] = (String) md.getElementAt(j);
            }

        }
        jListInvestmentEver.setListData(ns);

    }//GEN-LAST:event_jListInvestmentEverMouseClicked

    private void jButtonAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAccountActionPerformed
        long base = Main.branchID * 10000000000L;
        long fund_account = 0L;
        String FundAccount = jTextFieldFundAccount.getText().trim();
        String WholeAccount = null;
        try {
            fund_account = Long.valueOf(FundAccount).longValue();
            if (fund_account < 1000000000L) {
                fund_account = base + fund_account;
                WholeAccount = Long.valueOf(fund_account).toString();
            } else {
                WholeAccount = FundAccount;
            }
            jTextFieldFundAccount.setText(WholeAccount);
            int user_id = JFrameLogin.user_id;
            String sql = "execute get_client_info ";
            sql = sql + user_id + "," + fund_account;
            JDBTableModel dm = new JDBTableModel(jTable1);
            Vector columnType = new Vector();
            addSerial = true;

            dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
            TableModel m = jTable1.getModel();
            if (m.getRowCount() > 0) {
                havingData = true;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "没有输入资金帐号或数据格式错！");
            return;
        }
        int selectedRow = 0;
        int mr = jTable1.convertRowIndexToModel(selectedRow);
        TableModel md = jTable1.getModel();
        // System.out.print("row="+selectedRow);

        String sql = "get_callback_details " + WholeAccount;
        JDBTableModel dm1 = new JDBTableModel(jTable2);
        addSerial = false;
        dm1.fetchDataToTable(Main.conn, sql, columnType2, addSerial);
        jLabelPrompt.setText("");
}//GEN-LAST:event_jButtonAccountActionPerformed

    private void setControlsEnable(boolean flag) {
        jComboBoxProfession.setEnabled(flag);
        jComboBoxTrading_way.setEnabled(flag);
        jComboBoxSoftware.setEnabled(flag);
        jComboBoxRiskFavourite.setEnabled(flag);
        jComboBoxDecisionType.setEnabled(flag);
        jComboBoxOperationFavourite.setEnabled(flag);
        jComboBoxServicePlatform.setEnabled(flag);
        jComboBoxReligion.setEnabled(flag);
        jComboBoxNation.setEnabled(flag);
        jComboBoxResidence.setEnabled(flag);
        jComboBox1ServiceFrequence.setEnabled(flag);
        jComboBoxIsDiy.setEnabled(flag);
        jComboBox1ServicePerson.setEnabled(flag);
        jListHobby.setEnabled(flag);
        jListInvestmentEver.setEnabled(flag);
    }

    private void iniControls() {
        InitComboxItems(jComboBoxProfession, "get_callback_message '职业'");
        InitComboxItems(jComboBoxTrading_way, "get_callback_message '交易方式'");
        InitComboxItems(jComboBoxSoftware, "get_callback_message '使用交易软件'");
        InitComboxItems(jComboBoxRiskFavourite, "get_callback_message '风险偏好'");
        InitComboxItems(jComboBoxDecisionType, "get_callback_message '投资决策类型'");
        InitComboxItems(jComboBoxOperationFavourite, "get_callback_message '操作偏好'");
        InitComboxItems(jComboBoxServicePlatform, "get_callback_message '喜欢的服务平台'");
        InitComboxItems(jComboBoxIsDiy, "get_callback_message '是否本人操作'");
        InitComboxItems(jComboBoxReligion, "get_callback_message '宗教信仰'");
        InitComboxItems(jComboBoxNation, "get_callback_message '民族'");
        InitComboxItems(jComboBox1ServicePerson, "get_callback_message '为其服务人员'");
        InitComboxItems(jComboBoxResidence, "get_callback_message '居住区县'");
        InitComboxItems(jComboBox1ServiceFrequence, "get_callback_message '服务频度'");
        InitListItems(jListInvestmentEver, "get_callback_message '以往投资品种'");
        InitListItems(jListHobby, "get_callback_message '客户爱好'");
        fetchUsersToCombox();
        fetchSatisfactionToComBox();
    }

    private void InitComboxItems(JComboBox cmb, String sql) {
        cmb.removeAllItems();
        cmb.addItem(" ");
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
                    String item = SqlResult.getString(1);
                    cmb.addItem(item);
                }
            }
            SqlStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(JFrameLogin.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
        }

    }

    private void InitListItems(JList list, String sql) {
        list.removeAll();
        PreparedStatement SqlStatement;
        try {
            SqlStatement = Main.conn.prepareStatement(sql);
            Boolean HasResult = SqlStatement.execute();
            while (!HasResult) {
                HasResult = SqlStatement.getMoreResults();
            }
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                int i = 0;
                Vector items = new Vector();
                while (SqlResult.next()) {
                    String item = SqlResult.getString(1);
                    items.add(item);
                }
                list.setListData(items);
            }
            SqlStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(JFrameLogin.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
        }

    }

    private void fetchUsersToCombox() {
        BasicInfo bsi = BasicInfo.getBaseicInfo();
        servicePersonID = bsi.getUserID();
        servicePerson = bsi.getUserName();
        Enumeration enum1 = servicePerson.elements();
        jComboBoxUserList.addItem(" ");
        Integer n = Integer.valueOf(0);
        servicePersonID.insertElementAt(n, 0);
        while (enum1.hasMoreElements()) {
            String name = enum1.nextElement().toString();
            jComboBoxUserList.addItem(name);
        }
    }

    public void fetchSatisfactionToComBox() {
        jComboBoxSatisfaction.removeAllItems();
        BasicInfo bsi = BasicInfo.getBaseicInfo();
        satisfaction = bsi.getSatisfactionDegree();
        Enumeration enum1 = satisfaction.elements();
        jComboBoxSatisfaction.addItem(" ");
        while (enum1.hasMoreElements()) {
            String s = enum1.nextElement().toString();
            jComboBoxSatisfaction.addItem(s);
        }
    }
    Vector satisfaction;
    private void jListHobbyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListHobbyMouseClicked
        String s = jListHobby.getSelectedValue().toString();
        int i = jListHobby.getSelectedIndex();
        if (s.startsWith("√")) {
            s = s.substring(1);
        } else {
            s = "√" + s;
        }

        ListModel md = jListHobby.getModel();
        int m = md.getSize();
        String[] ns = new String[m];
        for (int j = 0; j <
                m; j++) {
            if (j == i) {
                ns[j] = s;
            } else {
                ns[j] = (String) md.getElementAt(j);
            }

        }
        jListHobby.setListData(ns);
}//GEN-LAST:event_jListHobbyMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
}//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int selectedRow = jTable1.getSelectedRow();
        int mr = jTable1.convertRowIndexToModel(selectedRow);
        TableModel md = jTable1.getModel();
        // System.out.print("row="+selectedRow);
        Object fund_account_O = md.getValueAt(mr, 1);
        String fund_account = " ";
        Object name_O = md.getValueAt(mr, 2);
        String name = "";
        if (name_O != null) {
            name = name_O.toString().trim();
        }
        if (fund_account_O != null) {
            fund_account = fund_account_O.toString().trim();
        }
        String sql = "get_callback_details " + fund_account;
        JDBTableModel dm1 = new JDBTableModel(jTable2);
        addSerial = false;
        dm1.fetchDataToTable(Main.conn, sql, columnType2, addSerial);
        jTextFieldFundAccount.setText(fund_account);
        jTextFieldName.setText(name);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String sql = " select fund_account as '资金帐号',name as '姓名',id_number as '身份证号',fund_class as '资金类别',account_status as '账户状态',total_assets as '资产总值',\n";
        sql = sql + "stock_market_value as '股票市值',stock_position_ratio as '持股比例',commision_contribution as '佣金贡献',fee_jsxf as '净佣金',agent as '经纪人',telephone as '电话号码',\n";
        sql = sql + "mobile as '手机',email as '电子邮箱',QQ as 'QQ号',investment_style as '投资风格',  financing_product as '是否购买理财产品',\n";
        sql = sql + "service_type1 as '服务类型-1',service_type2 as '服务类型-2',service_type3 as '服务类型-3',service_type4 as '服务类型-4',\n";
        sql = sql + "service_type5 as '服务类型-5',service_person as '服务员',satisfaction_degree as '满意度',back_caller as '回访者',\n";
        sql = sql + "Convert(char(8),back_calling_time,112) as '回访时间',satisfaction_degree1 as '回访满意度',\n";
        sql = sql + "isnull(call_back_flag,'未回访') as '回访标志',summary as '回访摘要',memo as '回访备注',memoS as '服务备注',convert(char(8),update_time,112) as '数据更新日期',\n";
        sql = sql + "convert(char(8),assign_time,112) as '回访安排日期',convert(char(8),assign_time1,112) as '服务安排日期',convert(char(8),opendate,112) as' 开户日期',\n";
        sql = sql + "isnull(contract_flag,'未签约') as '签约标志',convert(char(8),contract_time,112) as '签约时间',isnull(contract_analyst,'') as '指定分析师'\n";
        sql = sql + "from client\n";
        sql = sql + conditionCombined();
        System.out.println(sql);
        JDBTableModel dm1 = new JDBTableModel(jTable1);
        addSerial = true;
        dm1.fetchDataToTable(Main.conn, sql, columnType1, addSerial);
        makeFace(jTable1);
        if (jTable1.getRowCount() >= 1) {
            jLabelPrompt.setText("（点击基本信息记录可列出详细回访信息）");
        } else {
            sql = "get_callback_details -99999";
            JDBTableModel dm2 = new JDBTableModel(jTable2);
            addSerial = false;
            dm2.fetchDataToTable(Main.conn, sql, columnType2, addSerial);
            jLabelPrompt.setText("");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private String conditionCombined() {
        int user_id = JFrameLogin.user_id;
        String condition = " ";
        boolean combined = false;
        boolean combined1 = false;
        int n = jComboBoxUserList.getSelectedIndex();
        Integer servicePerson_id = (Integer) servicePersonID.get(n);
        int service_person_id = servicePerson_id.intValue();
        String service_person = jComboBoxUserList.getSelectedItem().toString();

        String satisfactionDegree1 = jComboBoxSatisfaction.getSelectedItem().toString();

        if (!satisfactionDegree1.equals(" ")) {
            condition = "  where  satisfaction_degree1='" + satisfactionDegree1 + " ' \n";
            combined = true;
        //System.out.println("AAAAAAAAA");
        //          System.out.println( condition );
        }
        if (!service_person.equals(" ")) {
            if (combined) {
                System.out.println(condition);
                condition = condition.substring(0, condition.length() - 2) +
                        "  and (service_person_id=" + service_person_id + "\n " +
                        "or  service_person_id in (select member_id\n from master_member\n" +
                        " where master_id=service_person_id" + " ))\n";
            //        System.out.println("BBBBBBBB");
            } else {
                condition = " where (service_person_id=" + service_person_id + "\n " +
                        "or  service_person_id in (select member_id\n from master_member\n" +
                        " where master_id=service_person_id" + " ))\n";
                //      System.out.println("AAAAAAAAA-1");
                //             System.out.println( condition );
                combined = true;
            }
        }
        if (JFrameLogin.group_id <= 1) {
            if (combined) {
                condition = condition + "  and  fund_account in (select fund_account from callback_details )";
            //   System.out.println("CCCCCCC");
            } else {
                condition = condition + "  where fund_account in (select fund_account from callback_details )";
            // System.out.println("DDDDDDDD");
            }
        } else {
            if (combined) {
                //System.out.println("EEEEEEEE");
                condition = condition + " and ( service_person_id=" + user_id + " or back_caller_id=" + user_id + " or contract_analyst_id=" + user_id + " )\n";
                condition = condition + " and fund_account in (select fund_account from callback_details )";
            } else {
                condition = " where  ( service_person_id=" + user_id + " or back_caller_id=" + user_id + " or contract_analyst_id=" + user_id + " )\n";
                condition = condition + " and fund_account in (select fund_account from callback_details )";
            }
        // combined1 = true;
        }

        String profession = jComboBoxProfession.getSelectedItem().toString();
        if (!profession.equals(" ")) {
            if (combined1) {
                condition = condition.substring(0, condition.length() - 2) + "  and  profession='" + profession + "')\n";
            } else {
                condition = condition.substring(0, condition.length() - 2) + "  where  profession='" + profession + "')\n";
            }
            combined1 = true;
        }
        String trading_way = jComboBoxTrading_way.getSelectedItem().toString();
        if (!trading_way.equals(" ")) {
            if (combined1) {
                condition = condition.substring(0, condition.length() - 2) + "  and trading_way='" + trading_way + "')\n";
            } else {
                condition = condition.substring(0, condition.length() - 2) + "  where  trading_way='" + trading_way + "')\n";
            }
            combined1 = true;
        }
        String software = jComboBoxSoftware.getSelectedItem().toString();
        if (!software.equals(" ")) {
            if (combined1) {
                condition = condition = condition.substring(0, condition.length() - 2) + "  and software='" + software + "')\n";
            } else {
                condition = condition.substring(0, condition.length() - 2) + "  where software='" + software + "')\n";
            }
            combined1 = true;
        }
        String risk_favourite = jComboBoxRiskFavourite.getSelectedItem().toString();
        if (!risk_favourite.equals(" ")) {
            if (combined1) {
                condition = condition.substring(0, condition.length() - 2) + "  and risk_favourite='" + risk_favourite + "')\n";
            } else {
                condition = condition.substring(0, condition.length() - 2) + "  where  risk_favourite='" + risk_favourite + "')\n";
            }
            combined1 = true;
        }
        String decision_type = jComboBoxDecisionType.getSelectedItem().toString();
        if (!decision_type.equals(" ")) {
            if (combined1) {
                condition = condition.substring(0, condition.length() - 2) + "  and decision_type='" + decision_type + "')\n";
            } else {
                condition = condition.substring(0, condition.length() - 2) + "  where decision_type='" + decision_type + "')\n";

            }
            combined1 = true;
        }
        String operation_favourite = jComboBoxOperationFavourite.getSelectedItem().toString();
        if (!operation_favourite.equals(" ")) {
            if (combined1) {
                condition = condition = condition.substring(0, condition.length() - 2) + "  and operation_favourite='" + operation_favourite + "')\n";
            } else {
                condition = condition = condition.substring(0, condition.length() - 2) + "  where operation_favourite='" + operation_favourite + "')\n";
            }
            combined1 = true;
        }
        String service_platform = jComboBoxServicePlatform.getSelectedItem().toString();
        if (!service_platform.equals(" ")) {
            if (combined1) {
                condition = condition.substring(0, condition.length() - 2) + "  and service_platform='" + service_platform + "')\n";
            } else {
                condition = condition.substring(0, condition.length() - 2) + "  where service_platform='" + service_platform + "')\n";
            }
            combined1 = true;
        }
        String investment_ever = listToString1(jListInvestmentEver, "investment_ever");
        if (!investment_ever.equals("()")) {
            if (combined1) {
                condition = condition.substring(0, condition.length() - 2) + "  and  " + investment_ever + " )\n";
            } else {
                condition = condition.substring(0, condition.length() - 2) + "  where  " + investment_ever + " )\n";

            }
            combined1 = true;
        }
        String is_diy = jComboBoxIsDiy.getSelectedItem().toString();
        if (!is_diy.equals(" ")) {
            if (combined1) {
                condition = condition.substring(0, condition.length() - 2) + "  and is_diy='" + is_diy + "')\n";
            } else {
                condition = condition.substring(0, condition.length() - 2) + "  where is_diy='" + is_diy + "')\n";

            }
            combined1 = true;
        }
        String hobby = listToString1(jListHobby, "hobby");
        if (!hobby.equals("()")) {
            if (combined1) {
                condition = condition.substring(0, condition.length() - 2) + "  and " + hobby + " )\n";
            } else {
                condition = condition.substring(0, condition.length() - 2) + "  where  " + hobby + " )\n";

            }
            combined1 = true;
        }
        String religion = jComboBoxReligion.getSelectedItem().toString();
        if (!religion.equals(" ")) {
            if (combined1) {
                condition = condition.substring(0, condition.length() - 2) + "  and religion='" + religion + "')\n";
            } else {
                condition = condition.substring(0, condition.length() - 2) + "  where religion='" + religion + "')\n";

            }
            combined1 = true;
        }
        String nation = jComboBoxNation.getSelectedItem().toString();
        if (!nation.equals(" ")) {
            if (combined1) {
                condition = condition.substring(0, condition.length() - 2) + "  and nation='" + nation + "')\n";
            } else {
                condition = condition.substring(0, condition.length() - 2) + "  where nation='" + nation + "')\n";
            }
            combined1 = true;
        }
        String service_person_for = jComboBox1ServicePerson.getSelectedItem().toString();
        if (!service_person_for.equals(" ")) {
            if (combined1) {
                condition = condition.substring(0, condition.length() - 2) + "  and service_person='" + service_person + "')\n";
            } else {
                condition = condition.substring(0, condition.length() - 2) + "  where service_person='" + service_person + "')\n";

            }
            combined1 = true;
        }
        String residence = jComboBoxResidence.getSelectedItem().toString();
        if (!residence.equals(" ")) {
            if (combined1) {
                condition = condition.substring(0, condition.length() - 2) + "  and residence='" + residence + "')\n";
            } else {
                condition = condition.substring(0, condition.length() - 2) + "  where residence='" + residence + "')\n";
            }
            combined1 = true;
        }
        String service_frequence = jComboBox1ServiceFrequence.getSelectedItem().toString();
        if (!service_frequence.equals(" ")) {
            if (combined1) {
                condition = condition.substring(0, condition.length() - 2) + "  and service_frequence='" + service_frequence + "')\n";
            } else {
                condition = condition.substring(0, condition.length() - 2) + "  where service_frequence='" + service_frequence + "')\n";

            }
            combined1 = true;
        }

        return condition;
    }

    /**
     * @param args the command line arguments
     */
    public String listToString(JList jl) {
        ListModel lm = jl.getModel();
        int m = lm.getSize();
        String s = lm.getElementAt(0).toString();
        if (s.startsWith("√")) {
            s = s.substring(1);
        } else {
            s = " ";
        }

        for (int i = 1; i < m; i++) {
            String s1 = lm.getElementAt(i).toString();
            if (s1.startsWith("√")) {
                s1 = s1.substring(1);
                s = s + "," + s1;
            }
        }
        return s;
    }

    public String listToString1(JList jl, String in_str) {
        ListModel lm = jl.getModel();
        int m = lm.getSize();
        String s = lm.getElementAt(0).toString();
        boolean combined = false;
        if (s.startsWith("√")) {
            s = "(  charindex( '" + s.substring(1) + "'," + in_str + " )>0  )";
            combined = true;
        } else {
            s = "()";
        }

        for (int i = 1; i < m; i++) {
            String s1 = lm.getElementAt(i).toString();
            if (s1.startsWith("√")) {
                s1 = s1.substring(1);
                if (combined) {
                    s = s.substring(0, s.length() - 2) + "\n or charindex( '" + s1 + "'," + in_str + " )>0 )";
                } else {
                    s = "(  charindex( '" + s1 + "'," + in_str + " )>0  )";
                }
                combined = true;
            }
        }
        return s;
    }
        private synchronized void makeFace(JTable table) {
        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {

                @Override
                public Component getTableCellRendererComponent(JTable table,
                        Object value, boolean isSelected, boolean hasFocus,
                        int row, int column) {

                    for (int i = 0; i < row; i++) {
                        String s = "";
                        Object s_O = table.getValueAt(row, credit_colum);
                        if (s_O != null) {
                            s = s_O.toString().trim();
                        }
                        if (s.endsWith("已开通")) {//开通融资融券者以特殊颜色显示
                            setBackground(new Color(204, 255, 255));
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
    int credit_colum = 16;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAccount;
    private javax.swing.JComboBox jComboBox1ServiceFrequence;
    private javax.swing.JComboBox jComboBox1ServicePerson;
    private javax.swing.JComboBox jComboBoxDecisionType;
    private javax.swing.JComboBox jComboBoxIsDiy;
    private javax.swing.JComboBox jComboBoxNation;
    private javax.swing.JComboBox jComboBoxOperationFavourite;
    private javax.swing.JComboBox jComboBoxProfession;
    private javax.swing.JComboBox jComboBoxReligion;
    private javax.swing.JComboBox jComboBoxResidence;
    private javax.swing.JComboBox jComboBoxRiskFavourite;
    private javax.swing.JComboBox jComboBoxSatisfaction;
    private javax.swing.JComboBox jComboBoxServicePlatform;
    private javax.swing.JComboBox jComboBoxSoftware;
    private javax.swing.JComboBox jComboBoxTrading_way;
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
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelPrompt;
    private javax.swing.JList jListHobby;
    private javax.swing.JList jListInvestmentEver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldFundAccount;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
}
