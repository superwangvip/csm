/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameCallbackInDetail.java
 *
 * Created on 2011-3-25, 18:05:51
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
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Administrator
 */
public class JFrameCallbackInDetail extends javax.swing.JFrame {

    private boolean havingData = false;
    private final int UPDTAE_SERVICE_INFO = 1;
    private final int CALL_BACK = 2;
    private final int NO_ACTION = 0;
    private int fetch_way = 0;
    boolean addSerial = true;
    private final int FETCH_ALL = 3;
    private final int FETCH_NOT_CALLBACKED = 4;
    private final int FETCH_ONLY_ONE = 5;
    /** Creates new form JFrameCallbackInDetail */
    public JFrameCallbackInDetail() {
        initComponents();
        int iThisWidth = 1010;
        int iThisHight = 720;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y - 10, iThisWidth, iThisHight);
        iniControls();
        setControlsEnable(false);

        //注册键盘监听器，监听键盘动作，把系统无操作等待计时器置0;对非管理员屏蔽批量数据复制
        MainMenu.registerKeyListener(jTable1);
        MainMenu.registerKeyListener(jTable2);
        //注册鼠标动作监听器，监听鼠标动作，把系统无操作等待计时器置0
        MainMenu.registerMouseListener(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextFieldFundAccount = new javax.swing.JTextField();
        jButtonReadData = new javax.swing.JButton();
        jButtonAccount = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxProfession = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxTrading_way = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxSoftware = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxRiskFavourite = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxDecisionType = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxOperationFavourite = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxServicePlatform = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListInvestmentEver = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxIsDiy = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListHobby = new javax.swing.JList();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxReligion = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jComboBoxNation = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jComboBox1ServicePerson = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jComboBoxResidence = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jComboBox1ServiceFrequence = new javax.swing.JComboBox();
        jButtonCallback = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextFieldName = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jComboBoxSex = new javax.swing.JComboBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaMemo = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("客户详细回访");

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

        jTextFieldFundAccount.setForeground(new java.awt.Color(0, 0, 153));

        jButtonReadData.setText("批量读取客户资料");
        jButtonReadData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReadDataActionPerformed(evt);
            }
        });

        jButtonAccount.setText("按帐号读取客户资料");
        jButtonAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAccountActionPerformed(evt);
            }
        });

        jLabel1.setText("职业");

        jComboBoxProfession.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("交易方式");

        jComboBoxTrading_way.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("使用交易软件");

        jComboBoxSoftware.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("风险偏好");

        jComboBoxRiskFavourite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("投资决策类型");

        jComboBoxDecisionType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("操作偏好");

        jComboBoxOperationFavourite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("喜欢的服务平台");

        jComboBoxServicePlatform.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        jLabel8.setText("以往投资品种");

        jComboBoxIsDiy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("是否本人操作");

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

        jLabel10.setText("客户爱好");

        jLabel11.setText("宗教信仰");

        jComboBoxReligion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setText("民族");

        jComboBoxNation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setText("为其服务人员");

        jComboBox1ServicePerson.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel14.setText("居住区县");

        jComboBoxResidence.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel15.setText("服务频度");

        jComboBox1ServiceFrequence.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonCallback.setText("详细回访");
        jButtonCallback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCallbackActionPerformed(evt);
            }
        });

        jButtonSave.setText("保存数据");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButton3.setText("取    消");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextFieldName.setForeground(new java.awt.Color(0, 0, 255));

        jLabel16.setText("资金帐号");

        jLabel17.setText("客户姓名");

        jLabel18.setText("客户基本信息");

        jLabel19.setForeground(new java.awt.Color(0, 0, 153));
        jLabel19.setText("（点击基本信息记录可列出详细回访信息）");

        jLabel20.setText("详细回访信息");

        jLabel21.setForeground(new java.awt.Color(0, 0, 153));
        jLabel21.setText("（要输入或修改详细回访信息，请点击详细回访按钮）");

        jButton4.setText("只读取未回访者");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel22.setText("性别");

        jComboBoxSex.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextAreaMemo.setColumns(20);
        jTextAreaMemo.setRows(5);
        jScrollPane5.setViewportView(jTextAreaMemo);

        jLabel23.setText("备注(128字以内)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(307, 307, 307)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16)
                                .addComponent(jLabel17))
                            .addComponent(jLabel22)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(jLabel11)
                                .addComponent(jLabel6)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jTextFieldFundAccount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxSex, javax.swing.GroupLayout.Alignment.TRAILING, 0, 106, Short.MAX_VALUE)
                                    .addComponent(jComboBoxReligion, 0, 106, Short.MAX_VALUE)
                                    .addComponent(jComboBoxResidence, 0, 106, Short.MAX_VALUE)
                                    .addComponent(jComboBoxOperationFavourite, 0, 106, Short.MAX_VALUE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAccount)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonReadData)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxNation, 0, 93, Short.MAX_VALUE)
                                    .addComponent(jComboBox1ServiceFrequence, 0, 93, Short.MAX_VALUE)
                                    .addComponent(jComboBoxTrading_way, 0, 93, Short.MAX_VALUE)
                                    .addComponent(jComboBoxServicePlatform, 0, 93, Short.MAX_VALUE)
                                    .addComponent(jComboBoxProfession, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCallback)
                                .addGap(12, 12, 12)
                                .addComponent(jButtonSave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jComboBoxSoftware, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel4))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel13))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jComboBoxRiskFavourite, javax.swing.GroupLayout.Alignment.LEADING, 0, 90, Short.MAX_VALUE)
                                            .addComponent(jComboBoxIsDiy, 0, 90, Short.MAX_VALUE))
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxDecisionType, 0, 110, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jLabel10))
                                            .addComponent(jComboBox1ServicePerson, javax.swing.GroupLayout.Alignment.TRAILING, 0, 102, Short.MAX_VALUE)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(42, 42, 42)
                                                .addComponent(jLabel23))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(10, 10, 10)))))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(287, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addGap(353, 353, 353))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAccount)
                    .addComponent(jTextFieldFundAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jButtonReadData)
                    .addComponent(jButton4)
                    .addComponent(jButtonCallback)
                    .addComponent(jButtonSave)
                    .addComponent(jButton3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxOperationFavourite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxReligion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxResidence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBoxProfession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBoxTrading_way, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jComboBoxServicePlatform, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBoxNation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox1ServiceFrequence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jComboBoxSoftware, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jComboBoxRiskFavourite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(jComboBoxDecisionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jComboBoxIsDiy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBox1ServicePerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel23))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane4, 0, 0, Short.MAX_VALUE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonReadDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReadDataActionPerformed
        // TODO add your handling code here:

        int user_id = JFrameLogin.user_id;
        int query_way = 1;//查询所有
        String sql = "get_client_list4 ";

        sql = sql + user_id + "," + query_way ;
        JDBTableModel dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        boolean addSerial = true;

        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        fetch_way = FETCH_ALL;
        TableModel m = jTable1.getModel();
        if (m.getRowCount() > 0) {
            havingData = true;
        }

}//GEN-LAST:event_jButtonReadDataActionPerformed

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
            boolean addSerial = true;

            dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
            TableModel m = jTable1.getModel();
            if (m.getRowCount() > 0) {
                havingData = true;
            }
            fetch_way = FETCH_ONLY_ONE;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "你没有输入帐号或数据格式错！");
            return;
        }
}//GEN-LAST:event_jButtonAccountActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (!havingData) {
            JOptionPane.showMessageDialog(this, "请先查询出客户基本资料!");
            return;
        }
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
        Vector  columnType=new Vector();
        boolean addSerial = false;
        dm1.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        jTextFieldFundAccount.setText(fund_account);
        jTextFieldName.setText(name);

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButtonCallbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCallbackActionPerformed
        iniControls();
        jTextFieldName.setEditable(false);
        jTextFieldFundAccount.setEditable(false);
        int selectedRow = jTable1.getSelectedRowCount();
        if (selectedRow == 0) {
            JOptionPane.showMessageDialog(this, "请先点击要回访的客户");
            return;
        }
        int[] selectedRowNo = jTable1.getSelectedRows();
        if (jTable2.getRowCount() < 1) {
            setControlsEnable(true);
        } else {
            setControlsEnable(true);
            initEditMsg();
        }
        jButtonSave.setEnabled(true);
}//GEN-LAST:event_jButtonCallbackActionPerformed
    private void setControlsEnable(boolean flag) {
        jComboBoxProfession.setEnabled(flag);
        jComboBoxSex.setEnabled(flag);
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
        jTextAreaMemo.setEditable(flag);
    }

    private void iniControls() {
        InitComboxItems(jComboBoxProfession, "get_callback_message '职业'");
        InitComboxItems(jComboBoxSex, "get_callback_message '性别'");
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
        jTextAreaMemo.setText("");
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

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        save_update_info();

        String fund_account = jTextFieldFundAccount.getText().toString().trim();
        String sql = "";
        int user_id = JFrameLogin.user_id;
        if (fetch_way == FETCH_ALL) {
            int query_way = 1;//查询所有
            int order_by = 2;
            sql = "get_client_list4 ";

            sql = sql + user_id + "," + query_way + "," + order_by;
        } else if (fetch_way == FETCH_NOT_CALLBACKED) {
            int query_way = 2;
            sql = "get_client_list5 " + user_id + "," + query_way;
        } else {
            sql = "get_client_info ";
            sql = sql + user_id + "," + fund_account;

        }
        /*
        JDBTableModel dm1 = new JDBTableModel();

        boolean addSerial = true;

        dm1.fetchDataToTable(Main.conn, sql, jTable1, columnType, addSerial);*/

        sql = "get_callback_details " + fund_account;
        JDBTableModel dm2 = new JDBTableModel(jTable2);
        Vector  columnType=new Vector();
        boolean addSerial = false;
        dm2.fetchDataToTable(Main.conn, sql, columnType, addSerial);

        iniControls();
        setControlsEnable(false);
        jTextFieldFundAccount.setEditable(true);

}//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int user_id = JFrameLogin.user_id;
        int query_way = 2;//查询未回访者

        String sql = "get_client_list5 ";

        sql = sql + user_id + "," + query_way;
        JDBTableModel dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        addSerial = true;

        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        fetch_way = FETCH_NOT_CALLBACKED;
        TableModel m = jTable1.getModel();
        if (m.getRowCount() > 0) {
            havingData = true;
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public void initEditMsg() {
        TableModel m = jTable2.getModel();
        int mr = 0;
        //sex
        Object sex_O = m.getValueAt(mr, 2);
        String sex = "";
        if (sex_O != null) {
            sex = sex_O.toString().trim();
        }
        //profession
        Object profession_O = m.getValueAt(mr, 3);
        String profession = "";
        if (profession_O != null) {
            profession = profession_O.toString().trim();
        }
        int n = jComboBoxProfession.getItemCount();
        for (int i = 0; i < n; i++) {
            String s = jComboBoxProfession.getItemAt(i).toString().trim();
            if (s.compareTo(profession) == 0) {
                jComboBoxProfession.setSelectedIndex(i);
            }
        }
        //trading_way
        Object trading_way_O = m.getValueAt(mr, 4);
        String trading_way = "";
        if (trading_way_O != null) {
            trading_way = trading_way_O.toString().trim();
        }
        n = jComboBoxTrading_way.getItemCount();
        for (int i = 0; i < n; i++) {
            String s = jComboBoxTrading_way.getItemAt(i).toString().trim();
            if (s.compareTo(trading_way) == 0) {
                jComboBoxTrading_way.setSelectedIndex(i);
            }
        }
        //software
        Object software_O = m.getValueAt(mr, 5);
        String software = "";
        if (trading_way_O != null) {
            software = software_O.toString().trim();
        }
        n = jComboBoxSoftware.getItemCount();
        for (int i = 0; i < n; i++) {
            String s = jComboBoxSoftware.getItemAt(i).toString().trim();
            if (s.compareTo(software) == 0) {
                jComboBoxSoftware.setSelectedIndex(i);
            }
        }
        //risk_favourite
        Object risk_favourite_O = m.getValueAt(mr, 6);
        String risk_favourite = "";
        if (risk_favourite_O != null) {
            risk_favourite = risk_favourite_O.toString().trim();
        }
        n = jComboBoxRiskFavourite.getItemCount();
        for (int i = 0; i < n; i++) {
            String s = jComboBoxRiskFavourite.getItemAt(i).toString().trim();
            if (s.compareTo(risk_favourite) == 0) {
                jComboBoxRiskFavourite.setSelectedIndex(i);
            }
        }
        //decision_type
        Object decision_type_O = m.getValueAt(mr, 7);
        String decision_type = "";
        if (decision_type_O != null) {
            decision_type = decision_type_O.toString().trim();
        }
        n = jComboBoxDecisionType.getItemCount();
        for (int i = 0; i < n; i++) {
            String s = jComboBoxDecisionType.getItemAt(i).toString().trim();
            if (s.compareTo(decision_type) == 0) {
                jComboBoxDecisionType.setSelectedIndex(i);
            }
        }
        //operation_favourite
        Object operation_favourite_O = m.getValueAt(mr, 8);
        String operation_favourite = "";
        if (operation_favourite_O != null) {
            operation_favourite = operation_favourite_O.toString().trim();
        }
        n = jComboBoxOperationFavourite.getItemCount();
        for (int i = 0; i < n; i++) {
            String s = jComboBoxOperationFavourite.getItemAt(i).toString().trim();
            if (s.compareTo(operation_favourite) == 0) {
                jComboBoxOperationFavourite.setSelectedIndex(i);
            }
        }
        //service_platflorm
        Object service_platflorm_O = m.getValueAt(mr, 9);
        String service_platflorm = "";
        if (service_platflorm_O != null) {
            service_platflorm = service_platflorm_O.toString().trim();
        }
        n = jComboBoxServicePlatform.getItemCount();
        for (int i = 0; i < n; i++) {
            String s = jComboBoxServicePlatform.getItemAt(i).toString().trim();
            if (s.compareTo(service_platflorm) == 0) {
                jComboBoxServicePlatform.setSelectedIndex(i);
            }
        }
        //investment_ever
        Object investment_ever_O = m.getValueAt(mr, 10);
        String investment_ever = "";
        if (investment_ever_O != null) {
            investment_ever = investment_ever_O.toString().trim();
        }
        stringTolist(investment_ever, jListInvestmentEver);
        //is_diy
        Object is_diy_O = m.getValueAt(mr, 11);
        String is_diy = "";
        if (is_diy_O != null) {
            is_diy = is_diy_O.toString().trim();
        }
        n = jComboBoxIsDiy.getItemCount();
        for (int i = 0; i < n; i++) {
            String s = jComboBoxIsDiy.getItemAt(i).toString().trim();
            if (s.compareTo(is_diy) == 0) {
                jComboBoxIsDiy.setSelectedIndex(i);
            }
        }
        //hobby
        Object hobby_O = m.getValueAt(mr, 12);
        String hobby = "";
        if (hobby_O != null) {
            hobby = hobby_O.toString().trim();
        }
        stringTolist(hobby, jListHobby);
        //religion
        Object religion_O = m.getValueAt(mr, 13);
        String religion = "";
        if (religion_O != null) {
            religion = religion_O.toString().trim();
        }
        n = jComboBoxReligion.getItemCount();
        for (int i = 0; i < n; i++) {
            String s = jComboBoxReligion.getItemAt(i).toString().trim();
            if (s.compareTo(religion) == 0) {
                jComboBoxReligion.setSelectedIndex(i);
            }
        }
        //nation
        Object nation_O = m.getValueAt(mr, 14);
        String nation = "";
        if (nation_O != null) {
            nation = nation_O.toString().trim();
        }
        n = jComboBoxNation.getItemCount();
        for (int i = 0; i < n; i++) {
            String s = jComboBoxNation.getItemAt(i).toString().trim();
            if (s.compareTo(nation) == 0) {
                jComboBoxNation.setSelectedIndex(i);
            }
        }
        //service_person
        Object service_person_O = m.getValueAt(mr, 15);
        String service_person = "";
        if (service_person_O != null) {
            service_person = service_person_O.toString().trim();
        }
        n = jComboBox1ServicePerson.getItemCount();
        for (int i = 0; i < n; i++) {
            String s = jComboBox1ServicePerson.getItemAt(i).toString().trim();
            if (s.compareTo(service_person) == 0) {
                jComboBox1ServicePerson.setSelectedIndex(i);
            }
        }
        //residence
        Object residence_O = m.getValueAt(mr, 16);
        String residence = "";
        if (residence_O != null) {
            residence = residence_O.toString().trim();
        }
        n = jComboBoxResidence.getItemCount();
        for (int i = 0; i < n; i++) {
            String s = jComboBoxResidence.getItemAt(i).toString().trim();
            if (s.compareTo(residence) == 0) {
                jComboBoxResidence.setSelectedIndex(i);
            }
        }
        //service_frequence
        Object service_frequence_O = m.getValueAt(mr, 17);
        String service_frequence = "";
        if (service_frequence_O != null) {
            service_frequence = service_frequence_O.toString().trim();
        }
        n = jComboBox1ServiceFrequence.getItemCount();
        for (int i = 0; i < n; i++) {
            String s = jComboBox1ServiceFrequence.getItemAt(i).toString().trim();
            if (s.compareTo(service_frequence) == 0) {
                jComboBox1ServiceFrequence.setSelectedIndex(i);
            }
        }
        Object memo_O = m.getValueAt(mr, 18);
        String memo = "";
        if (memo_O != null) {
            memo = memo_O.toString();
        }
        jTextAreaMemo.setText(memo);
    }

    public void stringTolist(String s, JList list) {

        ListModel md = list.getModel();
        int m = md.getSize();
        String[] ns = new String[m];

        for (int i = 0; i < m; i++) {
            ns[i] = (String) md.getElementAt(i);
            if (ns[i].startsWith("√")) {
                ns[i] = ns[i].substring(1);
            }

            if (s.contains(ns[i])) {
                ns[i] = "√" + ns[i];
            }
        }
        list.setListData(ns);
    }

    public String listToString(JList jl) {
        ListModel lm = jl.getModel();
        int m = lm.getSize();
        String s = lm.getElementAt(0).toString();
        if (s.startsWith("√")) {
            s = s.substring(1);
        } else {
            s = "";
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

    public int save_update_info() {
        int returnStatus = 0;
        String returnMessage = "保存成功";
        try {
            PreparedStatement SqlStatement = Main.conn.prepareStatement("execute callback_in_details ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");

            String fund_account = jTextFieldFundAccount.getText().trim();
            long fundAccount = Long.valueOf(fund_account);
            String sex = jComboBoxSex.getSelectedItem().toString().trim();
            String profession = jComboBoxProfession.getSelectedItem().toString();
            String trading_way = jComboBoxTrading_way.getSelectedItem().toString();
            String software = jComboBoxSoftware.getSelectedItem().toString();
            String risk_favourite = jComboBoxRiskFavourite.getSelectedItem().toString();
            String decision_type = jComboBoxDecisionType.getSelectedItem().toString();
            String operation_favourite = jComboBoxOperationFavourite.getSelectedItem().toString();
            String service_platform = jComboBoxServicePlatform.getSelectedItem().toString();
            String investment_ever = listToString(jListInvestmentEver);
            String is_diy = jComboBoxIsDiy.getSelectedItem().toString();
            String hobby = listToString(jListHobby);
            String religion = jComboBoxReligion.getSelectedItem().toString();
            String nation = jComboBoxNation.getSelectedItem().toString();
            String service_person = jComboBox1ServicePerson.getSelectedItem().toString();
            String residence = jComboBoxResidence.getSelectedItem().toString();
            String service_frequence = jComboBox1ServiceFrequence.getSelectedItem().toString();
            String memo = jTextAreaMemo.getText().toString();

            SqlStatement.setLong(1, fundAccount);
            SqlStatement.setString(2, sex);
            SqlStatement.setString(3, profession);
            SqlStatement.setString(4, trading_way);
            SqlStatement.setString(5, software);
            SqlStatement.setString(6, risk_favourite);
            SqlStatement.setString(7, decision_type);
            SqlStatement.setString(8, operation_favourite);
            SqlStatement.setString(9, service_platform);
            SqlStatement.setString(10, investment_ever);
            SqlStatement.setString(11, is_diy);
            SqlStatement.setString(12, hobby);
            SqlStatement.setString(13, religion);
            SqlStatement.setString(14, nation);
            SqlStatement.setString(15, service_person);
            SqlStatement.setString(16, residence);
            SqlStatement.setString(17, service_frequence);
            SqlStatement.setString(18, memo);

            Boolean HasResult = SqlStatement.execute();
            Main.conn.commit();
            Main.conn.setAutoCommit(true);

            while (!HasResult) {
                HasResult = SqlStatement.getMoreResults();
                int n = SqlStatement.getUpdateCount();
            }
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    returnStatus = SqlResult.getInt("return_status");
                    returnMessage = SqlResult.getString("return_message");
                }
                JOptionPane.showMessageDialog(this, returnMessage);
                SqlResult.close();
                SqlStatement.close();
                Main.conn.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFrameServiceCallback.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());

        }
        return returnStatus;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonAccount;
    private javax.swing.JButton jButtonCallback;
    private javax.swing.JButton jButtonReadData;
    private javax.swing.JButton jButtonSave;
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
    private javax.swing.JComboBox jComboBoxServicePlatform;
    private javax.swing.JComboBox jComboBoxSex;
    private javax.swing.JComboBox jComboBoxSoftware;
    private javax.swing.JComboBox jComboBoxTrading_way;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jListHobby;
    private javax.swing.JList jListInvestmentEver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextAreaMemo;
    private javax.swing.JTextField jTextFieldFundAccount;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
}
