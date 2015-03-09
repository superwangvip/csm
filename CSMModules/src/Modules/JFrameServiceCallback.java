package Modules;

import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * JFrameTableTest.java
 *
 * Created on 2010-4-11, 15:25:44
 */

/**
 *
 * @author Administrator
 */
public class JFrameServiceCallback extends javax.swing.JFrame {

    private int source = 0;
    boolean forService = true;
    private int operationType = 0;
    private boolean havingData = false;
    private final int UPDTAE_SERVICE_INFO = 1;
    private final int CALL_BACK = 2;
    private final int NO_ACTION = 0;
    private int fetch_way = 0;
    private final int FETCH_BY_FUND_ACCOUNT = 1;
    private final int FETCH_BY_FILTER = 2;
    private final int FETCH_ALL = 3;
    private JDBTableModel dm = null;

    /** Creates new form JFrameTableTest */
    @SuppressWarnings("empty-statement")
    public JFrameServiceCallback(int s) {

        source = s;  //1=服务,2=回访
        initComponents();
        jLabelTitle.setText(Main.branchName);
        if (JFrameLogin.group_id == 4) {// 柜员只能修改电话
        }
        if (source == 1) {
            jTextFieldService.setText("客户服务");
            jButtonUpdateCallback.setEnabled(false);
            //jComboBoxSummmary.setEnabled(false);
            //jTextFieldMemo.setEditable(false);
            forService = true;
        } else {
            jTextFieldService.setText("客户回访");
            jButtonUpdate.setEnabled(false);
            forService = false;
        }
        if (JFrameLogin.group_id <= 1) {
            jTextFieldDate.setEditable(false);
        }

        //注册键盘监听器，监听键盘动作，把系统无操作等待计时器置0,并控制表格内容复制
        MainMenu.registerKeyListener(jTable1);
        //注册鼠标动作监听器，监听鼠标动作，把系统无操作等待计时器置0
        MainMenu.registerMouseListener(this);

        BasicInfo b = BasicInfo.getBaseicInfo();
        Vector v1 = b.getFinancingProduct();
        jListFinancingProduct.setListData(v1);

        Vector v2 = b.getSatisfactionDegree();
        jComboBoxSatisfaction.removeAllItems();
        jComboBoxSatisfaction1.removeAllItems();
        Enumeration enum1 = v2.elements();
        while (enum1.hasMoreElements()) {
            String degree = enum1.nextElement().toString();
            jComboBoxSatisfaction.addItem(degree);
            jComboBoxSatisfaction1.addItem(degree);
        }

        Vector v3 = b.getInvestmentStyle();
        jComboBoxInvestmentStyle.removeAllItems();
        Enumeration enum2 = v3.elements();
        while (enum2.hasMoreElements()) {
            jComboBoxInvestmentStyle.addItem(enum2.nextElement());
        }

        Vector v4 = b.get_callback_summary();
        jComboBoxSummmary.removeAllItems();
        Enumeration enumS = v4.elements();
        while (enumS.hasMoreElements()) {
            jComboBoxSummmary.addItem(enumS.nextElement());
        }

        Vector v5 = b.getServiceType();
        Enumeration enum3 = v5.elements();
        if (enum3.hasMoreElements()) {
            String t1 = enum3.nextElement().toString();
            jLabelT1.setText(t1);
        }


        if (enum3.hasMoreElements()) {
            String t2 = enum3.nextElement().toString();
            jLabelT2.setText(t2);
        }
        if (enum3.hasMoreElements()) {
            String t3 = enum3.nextElement().toString();
            jLabelT3.setText(t3);
        }
        if (enum3.hasMoreElements()) {
            String t4 = enum3.nextElement().toString();
            jLabelT4.setText(t4);
        }
        if (enum3.hasMoreElements()) {
            String t5 = enum3.nextElement().toString();
            jLabelT5.setText(t5);
        }

        int iThisWidth = 1020;
        int iThisHight = 700;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y - 10, iThisWidth, iThisHight);
        //注册键盘监听器，监听键盘动作，把系统无操作等待计时器置0
        MainMenu.registerKeyListener(jTable1);
        //注册鼠标动作监听器，监听鼠标动作，把系统无操作等待计时器置0
        MainMenu.registerMouseListener(this);
        MainMenu.registerMouseListener(jTable1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldFundAccount = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxSatisfaction = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxInvestmentStyle = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListFinancingProduct = new javax.swing.JList();
        jTextFieldTel = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldMobile = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxSatisfaction1 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldQQ = new javax.swing.JTextField();
        jLabelT1 = new javax.swing.JLabel();
        jLabelT2 = new javax.swing.JLabel();
        jLabelT3 = new javax.swing.JLabel();
        jLabelT4 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldMemo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox5 = new javax.swing.JCheckBox();
        jLabelT5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldAgent = new javax.swing.JTextField();
        jComboBoxSummmary = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldDate = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldMemoS = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButtonCancel = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonReadData1 = new javax.swing.JButton();
        jButtonReadData = new javax.swing.JButton();
        jButtonAccount = new javax.swing.JButton();
        jButtonUpdateCallback = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTextFieldService = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("客户服务管理");
        setBackground(java.awt.Color.white);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelTitle.setFont(new java.awt.Font("宋体", 1, 24));
        jLabelTitle.setForeground(new java.awt.Color(255, 0, 0));
        jLabelTitle.setText("华西证券成都东一环路营业部");

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
        jScrollPane1.setViewportView(jTable1);

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTextFieldName.setEditable(false);
        jTextFieldName.setFont(new java.awt.Font("宋体", 0, 18));
        jTextFieldName.setForeground(new java.awt.Color(0, 0, 153));
        jTextFieldName.setText(" ");

        jTextFieldFundAccount.setFont(new java.awt.Font("宋体", 0, 18));
        jTextFieldFundAccount.setForeground(new java.awt.Color(0, 0, 153));

        jLabel2.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel2.setText("帐号");

        jLabel3.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel3.setText("姓名");

        jCheckBox1.setText("服务类别1");

        jCheckBox2.setText("服务类型2");

        jCheckBox3.setText("服务类型3");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox4.setText("服务类型4");

        jLabel8.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel8.setText("满意度");

        jComboBoxSatisfaction.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "非常满意", "满意", "比较满意", "不太满意", "不满意", "很不满意", "打算转户" }));

        jLabel9.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel9.setText("投资风格");

        jComboBoxInvestmentStyle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "激进", "稳健", "购买理财产品" }));

        jLabel10.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel10.setText("购买何种理财产品");

        jListFinancingProduct.setFont(new java.awt.Font("宋体", 0, 14));
        jListFinancingProduct.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "华西理财1号", "华西理财2号", "华西理财3号", "华西理财4号", "没有购买理财产品" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListFinancingProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListFinancingProductMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jListFinancingProduct);

        jTextFieldTel.setFont(new java.awt.Font("宋体", 0, 18));
        jTextFieldTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTelActionPerformed(evt);
            }
        });

        jTextFieldEmail.setFont(new java.awt.Font("宋体", 0, 18));

        jTextFieldMobile.setFont(new java.awt.Font("宋体", 0, 18));

        jLabel11.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel11.setText("电话");

        jLabel12.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel12.setText("手机");

        jLabel13.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel13.setText("邮箱");

        jComboBoxSatisfaction1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "非常满意", "满意", "比较满意", "不太满意", "不满意", "很不满意", "打算转户" }));

        jLabel14.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel14.setText("回访满意度");

        jLabel15.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel15.setText("QQ");

        jTextFieldQQ.setFont(new java.awt.Font("宋体", 0, 18));

        jLabelT1.setText("jLabelT1");

        jLabelT2.setText("jLabelT1");

        jLabelT3.setText("jLabelT1");

        jLabelT4.setText("jLabelT1");

        jLabel4.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel4.setText("回访摘要");

        jLabel5.setText("回访备注");

        jCheckBox5.setText(" 服务类型5");

        jLabelT5.setText("jLabel6");

        jLabel6.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel6.setText("经纪人");

        jTextFieldAgent.setFont(new java.awt.Font("宋体", 0, 18));

        jComboBoxSummmary.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "需要服务", "不需要服务", "电话空号", "无人接听", " ", " " }));

        jLabel1.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel1.setText("安排日期(yyyymmdd)");

        jTextFieldDate.setFont(new java.awt.Font("宋体", 0, 18));

        jLabel7.setText("服务备注");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldFundAccount)
                                    .addComponent(jTextFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                                .addGap(40, 40, 40))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jTextFieldAgent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(7, 7, 7)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldMemoS)
                                    .addComponent(jTextFieldDate, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldTel, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldMobile, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldQQ, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelT1)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxSatisfaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabelT2))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(25, 25, 25))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jComboBoxInvestmentStyle, 0, 73, Short.MAX_VALUE)
                                        .addGap(14, 14, 14))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addGap(6, 6, 6)
                                .addComponent(jCheckBox2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabelT3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                    .addComponent(jLabelT4)
                                    .addGap(23, 23, 23))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel10)
                                        .addComponent(jCheckBox4))))
                            .addComponent(jCheckBox3))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxSummmary, javax.swing.GroupLayout.Alignment.TRAILING, 0, 103, Short.MAX_VALUE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBoxSatisfaction1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 103, Short.MAX_VALUE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldMemo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox5)
                                    .addComponent(jLabelT5))
                                .addGap(54, 54, 54)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(43, 43, 43))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldFundAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxSatisfaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jComboBoxInvestmentStyle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox1)
                            .addComponent(jCheckBox3)
                            .addComponent(jCheckBox4)
                            .addComponent(jCheckBox5)
                            .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelT5)
                            .addComponent(jLabelT4)
                            .addComponent(jLabelT3)
                            .addComponent(jLabelT2)
                            .addComponent(jLabelT1))
                        .addGap(45, 45, 45)
                        .addComponent(jLabel14)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(jLabel6)
                                .addComponent(jTextFieldAgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldQQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jTextFieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldMemoS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jComboBoxSatisfaction1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxSummmary, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldMemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonCancel.setText("取  消");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonUpdate.setText("修改服务信息");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonReadData1.setText("过滤客户资料");
        jButtonReadData1.setToolTipText("过滤基本信息已经完善的客户");
        jButtonReadData1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReadData1ActionPerformed(evt);
            }
        });

        jButtonReadData.setText("读取客户资料");
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

        jButtonUpdateCallback.setText("回访客户");
        jButtonUpdateCallback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateCallbackActionPerformed(evt);
            }
        });

        jButtonSave.setForeground(new java.awt.Color(0, 0, 102));
        jButtonSave.setText("保 存");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButton1.setForeground(new java.awt.Color(51, 0, 255));
        jButton1.setText("备注管理");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonReadData, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jButtonUpdateCallback, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jButtonReadData1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jButtonAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonReadData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonUpdateCallback)
                .addGap(18, 18, 18)
                .addComponent(jButtonSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonReadData1)
                .addGap(14, 14, 14)
                .addComponent(jButtonAccount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTextFieldService.setFont(new java.awt.Font("宋体", 1, 18));
        jTextFieldService.setForeground(new java.awt.Color(0, 0, 102));
        jTextFieldService.setText("客户服务");
        jTextFieldService.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(jLabelTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldService, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jTextFieldService, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.dispose();

    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonReadDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReadDataActionPerformed
        String dateStr = jTextFieldDate.getText().trim();

        if (!dateStr.equals("") && !isValidDateString(dateStr)) {
            JOptionPane.showMessageDialog(this, "日期输入错!");
            return;
        }
        int user_id = JFrameLogin.user_id;
        int query_way = 1;//查询所有

        String sql = "";
        if (forService) {
            if (dateStr == null || dateStr.equals("")) {
                sql = "execute get_client_list3 ";
            } else {
                sql = "execute get_client_list3_1 ";
            }
        } else {
            if (dateStr == null || dateStr.equals("")) {
                sql = "execute get_client_list4 ";
            } else {
                sql = "execute get_client_list4_1 ";
            }
        }
        sql = sql + user_id + "," + query_way;
        if (dateStr != null && !dateStr.equals("")) {
            sql = sql + ",'" + dateStr + "'";
        }
        System.out.println(sql);
        dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        boolean addSerial = true;

        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        MainMenu.makeFace(jTable1);
        fetch_way = FETCH_ALL;
        havingData = true;
}//GEN-LAST:event_jButtonReadDataActionPerformed
    public boolean isValidDateString(String dateStr) {
        boolean yes = true;
        if (dateStr.trim().length() != 8) {
            yes = false;
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

    //修改服务信息
    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        if (!havingData) {
            JOptionPane.showMessageDialog(this, "没有客户资料！ 请先获取客户资料,再修改");
            return;
        }
        int r = jTable1.getSelectedRow();
        if (r == -1) {
            JOptionPane.showMessageDialog(this, "请先选择要修改的记录（用鼠标点击记录),再修改");
            return;
        }
        this.operationType = UPDTAE_SERVICE_INFO;
        initEditMsg();

        if (JFrameLogin.group_id <= 1) {//仅管理员可以修改经纪人与客户电话
            jTextFieldAgent.setEditable(true);
            jTextFieldTel.setEditable(true);
            jTextFieldMobile.setEditable(true);
        } else {
            jTextFieldAgent.setEditable(false);
            jTextFieldTel.setEditable(false);
            jTextFieldMobile.setEditable(false);
        }
        jTextFieldEmail.setEditable(true);
        jTextFieldQQ.setEditable(true);
        jCheckBox1.setEnabled(true);
        jCheckBox2.setEnabled(true);
        jCheckBox3.setEnabled(true);
        jCheckBox4.setEnabled(true);
        jComboBoxSatisfaction.setEnabled(true);
        jComboBoxSatisfaction1.setEnabled(false);
        jComboBoxSummmary.setEnabled(false);
        jComboBoxInvestmentStyle.setEnabled(true);
        jListFinancingProduct.setEnabled(true);
        jButtonUpdateCallback.setEnabled(false);
        jComboBoxSummmary.setEnabled(false);
        jTextFieldMemo.setEditable(false);
        if (JFrameLogin.group_id == 4) {//柜员只能修改电话
            jTextFieldTel.setEditable(true);
            jTextFieldMobile.setEditable(true);
            jTextFieldEmail.setEditable(false);
            jTextFieldQQ.setEditable(false);
            jCheckBox1.setEnabled(false);
            jCheckBox2.setEnabled(false);
            jCheckBox3.setEnabled(false);
            jCheckBox4.setEnabled(false);
            jListFinancingProduct.setEnabled(false);
            jComboBoxSatisfaction.setEnabled(false);
            jComboBoxInvestmentStyle.setEnabled(false);
            jCheckBox5.setEnabled(false);
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    public void initEditMsg() {
        TableModel m = jTable1.getModel();
        int r = jTable1.getSelectedRow();
        int mr = jTable1.convertRowIndexToModel(r);
        jTextFieldFundAccount.setText(dm.getValueByColumnName(mr, "资金帐号"));
        jTextFieldName.setText(dm.getValueByColumnName(mr, "姓名"));

        jTextFieldAgent.setText(dm.getValueByColumnName(mr, "经纪人"));

        jTextFieldTel.setText(dm.getValueByColumnName(mr, "电话号码"));

        jTextFieldMobile.setText(dm.getValueByColumnName(mr, "手机号码"));
        Object email_O = m.getValueAt(mr, 20);
        String email = "";
        if (email_O != null) {
            email = email_O.toString();
        }
        jTextFieldEmail.setText(email);

        jTextFieldQQ.setText(dm.getValueByColumnName(mr, "QQ号"));

        /* Object investment_style_O = m.getValueAt(mr, 22);
        String investment_style = "";
        if (investment_style_O != null) {
        investment_style = investment_style_O.toString().trim();
        } else {
        investment_style = "不明确";
        }*/
        String investment_style = dm.getValueByColumnName(mr, "投资风格");
        int n = jComboBoxInvestmentStyle.getItemCount();
        for (int i = 0; i < n; i++) {
            String s = jComboBoxInvestmentStyle.getItemAt(i).toString().trim();
            if (s.compareTo(investment_style) == 0) {
                jComboBoxInvestmentStyle.setSelectedIndex(i);
            }
        }
        /* Object financing_product_O = m.getValueAt(mr, 23);
        String financing_product = "";
        if (financing_product_O != null) {
        financing_product = financing_product_O.toString();
        }*/
        String financing_product = dm.getValueByColumnName(mr, "是否购买理财产品");
        stringTolist(financing_product);


        String service_type1 = dm.getValueByColumnName(mr, "服务类型-1").trim();
        if (service_type1.equals("")) {
            jCheckBox1.setSelected(false);
        } else {
            jCheckBox1.setSelected(true);
        }

        String service_type2 = dm.getValueByColumnName(mr, "服务类型-2").trim();
        if (service_type2.equals("")) {
            jCheckBox2.setSelected(false);
        } else {
            jCheckBox2.setSelected(true);
        }

        //String service_type3 = service_type3_O.toString().trim();
        String service_type3 = dm.getValueByColumnName(mr, "服务类型-3").trim();
        if (service_type3.equals("")) {
            jCheckBox3.setSelected(false);
        } else {
            jCheckBox3.setSelected(true);
        }

        String service_type4 = dm.getValueByColumnName(mr, "服务类型-4").trim();
        if (service_type4.equals("")) {
            jCheckBox4.setSelected(false);
        } else {
            jCheckBox4.setSelected(true);
        }

        String service_type5 = dm.getValueByColumnName(mr, "服务类型-5").trim();
        if (service_type5.equals("")) {
            jCheckBox5.setSelected(false);
        } else {
            jCheckBox5.setSelected(true);
        }
        /*Object satisfaction_O = m.getValueAt(mr, 30);
        String satisfaction = "";
        if (satisfaction_O != null) {
        satisfaction = satisfaction_O.toString().trim();
        } else {
        satisfaction = "不确定";
        }*/
        String satisfaction = dm.getValueByColumnName(mr, "满意度").trim();
        int n2 = jComboBoxSatisfaction.getItemCount();
        for (int i = 0; i < n2; i++) {
            String s = jComboBoxSatisfaction.getItemAt(i).toString().trim();
            if (s.compareTo(satisfaction) == 0) {
                jComboBoxSatisfaction.setSelectedIndex(i);
            }
        }
        /*    Object satisfaction1_O = m.getValueAt(mr, 33);
        String satisfaction1 = "";
        if (satisfaction1_O != null) {
        satisfaction1 = satisfaction1_O.toString().trim();
        } else {
        satisfaction1 = "不确定";
        }
         */
        String satisfaction1 = dm.getValueByColumnName(mr, "回访满意度");
        int n3 = jComboBoxSatisfaction1.getItemCount();
        for (int i = 0; i < n3; i++) {
            String s = jComboBoxSatisfaction1.getItemAt(i).toString().trim();
            if (s.compareTo(satisfaction1) == 0) {
                jComboBoxSatisfaction1.setSelectedIndex(i);
            }
        }
        /*    Object summary_O = m.getValueAt(mr, 34);
        String summary = " ";
        if (summary_O != null) {
        summary = summary_O.toString();
        }*/
        String summary = dm.getValueByColumnName(mr, "回访摘要").trim();
        int n4 = jComboBoxSummmary.getItemCount();
        for (int i = 0; i < n4; i++) {
            String s = jComboBoxSummmary.getItemAt(i).toString().trim();
            if (s.compareTo(summary) == 0) {
                jComboBoxSummmary.setSelectedIndex(i);
            }
        }

        String memo = dm.getValueByColumnName(mr, "回访备注").trim();
        jTextFieldMemo.setText(memo);

        String memoS = dm.getValueByColumnName(mr, "服务备注").trim();
        jTextFieldMemoS.setText(memoS);
    }

    public void stringTolist(String s) {

        ListModel md = jListFinancingProduct.getModel();
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
        jListFinancingProduct.setListData(ns);
    }

    private void jListFinancingProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListFinancingProductMouseClicked
        String s = jListFinancingProduct.getSelectedValue().toString();
        int i = jListFinancingProduct.getSelectedIndex();
        if (s.startsWith("√")) {
            s = s.substring(1);
        } else {
            s = "√" + s;
        }

        ListModel md = jListFinancingProduct.getModel();
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
        jListFinancingProduct.setListData(ns);


}//GEN-LAST:event_jListFinancingProductMouseClicked

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jButtonReadData1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReadData1ActionPerformed
        String dateStr = jTextFieldDate.getText().trim();

        if (!dateStr.equals("") && !isValidDateString(dateStr)) {
            JOptionPane.showMessageDialog(this, "日期输入错!");
            return;
        }

        int user_id = JFrameLogin.user_id;
        int query_way = 2;//查询部分
        int order_by = 2;
        String sql = "";
        if (forService) {
            if (dateStr == null || dateStr.equals("")) {
                sql = "execute get_client_list3 ";
            } else {
                sql = "execute get_client_list3_1 ";
            }
        } else {
            if (dateStr == null || dateStr.equals("")) {
                sql = "execute get_client_list4 ";
            } else {
                sql = "execute get_client_list4_1 ";
            }
        }
        sql = sql + user_id + "," + query_way;

        if (dateStr != null && !dateStr.equals("")) {
            sql = sql + ",'" + dateStr + "'";
        }
        dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        boolean addSerial = true;
        System.out.println(sql);
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        MainMenu.makeFace(jTable1);
        fetch_way = FETCH_BY_FILTER;
        havingData = true;
    }//GEN-LAST:event_jButtonReadData1ActionPerformed

    private void jButtonUpdateCallbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateCallbackActionPerformed
        if (!havingData) {
            JOptionPane.showMessageDialog(this, "没有客户资料！ 请先获取客户资料,再修改");
            return;
        }
        int r = jTable1.getSelectedRow();
        if (r == -1) {
            JOptionPane.showMessageDialog(this, "请先选择要修改的记录（用鼠标点击记录),再修改");
            return;
        }
        havingData = true;
        this.operationType = CALL_BACK;
        initEditMsg();

        jComboBoxSatisfaction1.setEnabled(true);
        jTextFieldName.setEditable(false);
        jTextFieldAgent.setEditable(false);
        jTextFieldTel.setEditable(false);
        jTextFieldMobile.setEditable(false);
        jTextFieldEmail.setEditable(false);
        jTextFieldQQ.setEditable(false);
        jComboBoxSatisfaction.setEnabled(false);
        jComboBoxInvestmentStyle.setEnabled(false);

        jCheckBox1.setEnabled(false);
        jCheckBox2.setEnabled(false);
        jCheckBox3.setEnabled(false);
        jCheckBox4.setEnabled(false);
        jCheckBox5.setEnabled(false);
        jListFinancingProduct.setEnabled(false);

        jButtonUpdate.setEnabled(false);
        //jButtonAccount.setEnabled(false);
        //jTextFieldFundAccount.setEditable(false);
        jTextFieldMemoS.setEditable(false);

}//GEN-LAST:event_jButtonUpdateCallbackActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        if (!havingData || operationType == NO_ACTION) {
            JOptionPane.showMessageDialog(this, "你没有做任何数据修改，不需要保存！");
        } else {
            save_update_info(operationType);
            operationType = NO_ACTION;
            //刷新数据
            String dateStr = jTextFieldDate.getText().trim();
            if (!dateStr.equals("") && !isValidDateString(dateStr)) {
                JOptionPane.showMessageDialog(this, "日期输入错!");
                return;
            }
            if (fetch_way == FETCH_BY_FUND_ACCOUNT) {
                long base = 180000000000L;
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
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "输入数据格式错！");
                    return;
                }
                int user_id = JFrameLogin.user_id;
                String sql = "execute get_client_info ";
                sql = sql + user_id + "," + fund_account;
                dm = new JDBTableModel(jTable1);
                Vector columnType = new Vector();
                boolean addSerial = true;
                dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
                MainMenu.makeFace(jTable1);
            } else {
                int user_id = JFrameLogin.user_id;
                int query_way = 1;//全部
                if (fetch_way == FETCH_BY_FILTER) {
                    query_way = 2;//过滤
                }
                int order_by = 2;
                String sql = "";
                if (forService) {
                    if (dateStr == null || dateStr.equals("")) {
                        sql = "execute get_client_list3 ";
                    } else {
                        sql = "execute get_client_list3_1 ";
                    }
                } else {
                    if (dateStr == null || dateStr.equals("")) {
                        sql = "execute get_client_list4 ";
                    } else {
                        sql = "execute get_client_list4_1 ";
                    }
                }
                sql = sql + user_id + "," + query_way;
                if (dateStr != null && !dateStr.equals("")) {
                    sql = sql + ",'" + dateStr + "'";
                }

                dm = new JDBTableModel(jTable1);
                Vector columnType = new Vector();
                boolean addSerial = true;
                dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
                MainMenu.makeFace(jTable1);
            }
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jTextFieldTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTelActionPerformed
    }//GEN-LAST:event_jTextFieldTelActionPerformed

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

        jTextFieldFundAccount.setText(WholeAccount);
        int user_id = JFrameLogin.user_id;
        String sql = "execute get_client_info ";
        sql = sql + user_id + "," + fund_account;
        System.out.println(sql);
        dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        boolean addSerial = true;

        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        MainMenu.makeFace(jTable1);
        fetch_way = FETCH_BY_FUND_ACCOUNT;
        havingData = true;

}//GEN-LAST:event_jButtonAccountActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!havingData) {
            JOptionPane.showMessageDialog(this, "没有客户资料！ 请先获取客户资料");
            return;
        }
        int r = jTable1.getSelectedRow();
        if (r == -1) {
            JOptionPane.showMessageDialog(this, "请先选择记录（用鼠标点击记录)");
            return;
        }

        TableModel m = jTable1.getModel();
        int mr = jTable1.convertRowIndexToModel(r);
        String fundAccount = m.getValueAt(mr, 1).toString();
        long fund_account = Long.valueOf(fundAccount);
        String memo_type = " ";
        if (forService) {
            memo_type = "服务备注";
        } else {
            memo_type = "回访备注";
        }

        String name = m.getValueAt(mr, 2).toString();
        System.out.println("r=" + r + ",mr=" + mr + ",name=" + name + "fund_account=" + fundAccount);
        new JFrameMemoEdit(fund_account, memo_type, name).setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    public void save_update_info(int operationType) {
        String sql = "";
        SqlExecutor sqlx = new SqlExecutor();
        if (operationType == UPDTAE_SERVICE_INFO) {
            int r = jTable1.getSelectedRow();
            int mr = jTable1.convertRowIndexToModel(r);
            TableModel m = jTable1.getModel();
            long fundAccount = ((Long) m.getValueAt(mr, 1));

            String agent = jTextFieldAgent.getText().trim();
            String tel = jTextFieldTel.getText();
            String mobile = jTextFieldMobile.getText();
            String email = jTextFieldEmail.getText();
            String QQ = jTextFieldQQ.getText();
            String investmentStyle = jComboBoxInvestmentStyle.getSelectedItem().toString();
            String serviceType1 = "    ";
            String serviceType2 = "    ";
            String serviceType3 = "    ";
            String serviceType4 = "    ";
            String serviceType5 = "    ";

            if (jCheckBox1.isSelected()) {
                serviceType1 = jLabelT1.getText();
            }

            if (jCheckBox2.isSelected()) {
                serviceType2 = jLabelT2.getText();
            }

            if (jCheckBox3.isSelected()) {
                serviceType3 = jLabelT3.getText();
            }

            if (jCheckBox4.isSelected()) {
                serviceType4 = jLabelT4.getText();
            }

            if (jCheckBox5.isSelected()) {
                serviceType5 = jLabelT5.getText();
            }

            int servicePersonID = JFrameLogin.user_id;
            String financingProduct = listToString(jListFinancingProduct);
            String satisfactionDegree = jComboBoxSatisfaction.getSelectedItem().toString();

            String memo = jTextFieldMemoS.getText().trim();

            sql = "update_service_info " + fundAccount + ",'" + agent + "','" + tel + "','" + mobile + "','";
            sql = sql + email + "','" + QQ + "','" + investmentStyle + "','" + serviceType1 + "','" + serviceType2 + "','";
            sql = sql + serviceType3 + "','" + serviceType4 + "','" + serviceType5 + "',";
            sql = sql + servicePersonID + ",'" + financingProduct + "','" + satisfactionDegree + "','" + memo + "'";
            System.out.println(sql);

        } else if (operationType == CALL_BACK) {
            int r = jTable1.getSelectedRow();
            int mr = jTable1.convertRowIndexToModel(r);
            TableModel m = jTable1.getModel();
            long fundAccount = ((Long) m.getValueAt(mr, 1));

            int servicePersonID = JFrameLogin.user_id;
            String satisfactionDegree = jComboBoxSatisfaction1.getSelectedItem().toString();

            String summary = jComboBoxSummmary.getSelectedItem().toString();
            String memo = jTextFieldMemo.getText().trim();
            sql = "update_callback_info " + fundAccount + "," + servicePersonID + ",'";
            sql = sql + satisfactionDegree + "','" + summary + "','" + memo + "'";
        }
        System.out.println(sql);
        sqlx.execute1(Main.conn, sql);
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
        if (s.startsWith(",")) {
            s = s.substring(1);
        }
        return s;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAccount;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonReadData;
    private javax.swing.JButton jButtonReadData1;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JButton jButtonUpdateCallback;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JComboBox jComboBoxInvestmentStyle;
    private javax.swing.JComboBox jComboBoxSatisfaction;
    private javax.swing.JComboBox jComboBoxSatisfaction1;
    private javax.swing.JComboBox jComboBoxSummmary;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelT1;
    private javax.swing.JLabel jLabelT2;
    private javax.swing.JLabel jLabelT3;
    private javax.swing.JLabel jLabelT4;
    private javax.swing.JLabel jLabelT5;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JList jListFinancingProduct;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldAgent;
    private javax.swing.JTextField jTextFieldDate;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFundAccount;
    private javax.swing.JTextField jTextFieldMemo;
    private javax.swing.JTextField jTextFieldMemoS;
    private javax.swing.JTextField jTextFieldMobile;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldQQ;
    private javax.swing.JTextField jTextFieldService;
    private javax.swing.JTextField jTextFieldTel;
    // End of variables declaration//GEN-END:variables
}
