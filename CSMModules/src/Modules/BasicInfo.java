/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BasicInfo.java
 *
 * Created on 2010-4-22, 16:05:46
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

/**
 *
 * @author Administrator
 */
public class BasicInfo extends javax.swing.JFrame {

    public static BasicInfo bsi;

    /** Creates new form BasicInfo */
    public static BasicInfo getBaseicInfo() {
        if (bsi == null) {
            bsi = new BasicInfo();
        }
        return bsi;
    }

    private BasicInfo() {
        initComponents();

        int iThisWidth = 800;
        int iThisHight = 590;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y, iThisWidth, iThisHight);
        setServiceType();
        jListServiceType.setListData(getServiceType());
        setMessageType();
        jListMessageType.setListData(getMessageType());
        setSatisfactionDegree();
        jListSatisfactionDegree.setListData(getSatisfactionDegree());
        setInvestmentStyle();
        jListInvetmentStyle.setListData(getInvestmentStyle());
        setFinancingProduct();
        jListFinancingProduct.setListData(getFinancingProduct());
        setGroupInfo();
        jListGroupInfo.setListData(getGroupName());
        setUserInfo();
        jListUserInfo.setListData(getUserName());
        setCallbackSummary();
        jListSummary.setListData(callback_summary);
        setAdvisoryLevels();
        jListAdvisory_levels.setListData(advisoryLevels);
        setInvestmentAdviser();
        jListInvestmentAdvisor.setListData(investmentAdviser);
        jListAnalyst.setListData(analyst);
        //setOperateWay();4.30版本才需要
        setContractItems();
        jListValueAddedItems.setListData(contractItems);

        //注册鼠标动作监听器，监听鼠标动作，把系统无操作等待计时器置0
        MainMenu.registerMouseListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    public void setMessageType() {
        try {
            PreparedStatement SqlStatement = Main.conn.prepareStatement("execute get_message_type");
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();

                while (SqlResult.next()) {
                    String msgType = SqlResult.getString(1);
                    messageType.add(msgType);
                }
            }

        } catch (SQLException ex) {
            Main.logger.getLogger(BasicInfo.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        }

    }

    public Vector getMessageType() {
        return messageType;
    }

    public void setServiceType() {
        try {
            PreparedStatement SqlStatement = Main.conn.prepareStatement("execute get_service_type");
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();

                while (SqlResult.next()) {
                    String discription = SqlResult.getString(2);
                    serviceType.add(discription);
                }
            }

        } catch (SQLException ex) {
            Main.logger.getLogger(BasicInfo.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        }

    }

    public Vector getServiceType() {
        return serviceType;
    }

    public void setSatisfactionDegree() {
        try {
            PreparedStatement SqlStatement = Main.conn.prepareStatement("execute get_satisfaction");
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();

                while (SqlResult.next()) {
                    String degree = SqlResult.getString(2);
                    satisfactionDegree.add(degree);
                }
            }

        } catch (SQLException ex) {
            Main.logger.getLogger(BasicInfo.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        }
    }

    public Vector getSatisfactionDegree() {
        return satisfactionDegree;
    }

    public void setInvestmentStyle() {
        try {
            PreparedStatement SqlStatement = Main.conn.prepareStatement("execute get_investment_style");
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();

                while (SqlResult.next()) {
                    String discription = SqlResult.getString(2);
                    investmentStyle.add(discription);
                }
            }

        } catch (SQLException ex) {
            Main.logger.getLogger(BasicInfo.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        }
    }

    public Vector getInvestmentStyle() {
        return investmentStyle;
    }

    public void setFinancingProduct() {
        try {
            PreparedStatement SqlStatement = Main.conn.prepareStatement("execute get_financing_product");
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();

                while (SqlResult.next()) {
                    String discription = SqlResult.getString(2);
                    financingProduct.add(discription);
                }
            }

        } catch (SQLException ex) {
            Main.logger.getLogger(BasicInfo.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        }
    }

    public Vector getFinancingProduct() {
        return financingProduct;
    }

    public Vector get_callback_summary() {
        return callback_summary;
    }

    public void setCallbackSummary() {
        try {
            PreparedStatement SqlStatement = Main.conn.prepareStatement("execute get_callback_summary");
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();

                while (SqlResult.next()) {
                    String discription = SqlResult.getString(2);
                    callback_summary.add(discription);
                }
            }

        } catch (SQLException ex) {
            Main.logger.getLogger(BasicInfo.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        }
    }

    public void setGroupInfo() {
        try {
            PreparedStatement SqlStatement = Main.conn.prepareStatement("execute get_group_info");
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    Integer group_id = Integer.valueOf(SqlResult.getInt(1));
                    String group_name = SqlResult.getString(2);
                    groupID.add(group_id);
                    groupName.add(group_name);
                }
            }

        } catch (SQLException ex) {
            Main.logger.getLogger(BasicInfo.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        }
    }

    public Vector getGroupName() {
        return groupName;
    }

    public Vector getGroupID() {
        return groupID;
    }

    public void setUserInfo() {
        try {
            PreparedStatement SqlStatement = Main.conn.prepareStatement("execute get_user_list 4");
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    Integer user_id = Integer.valueOf(SqlResult.getInt(1));
                    String user_name = SqlResult.getString(2);
                    int group_id = SqlResult.getInt(4);

                    userID.add(user_id);
                    userName.add(user_name.trim());
                    if (group_id == 1 || group_id == 2 || group_id >= 10) {
                        servicePersonID.add(user_id);
                        servicePerson.add(user_name.trim());
                    }
                    if (group_id == 5) {
                        analystID.add(user_id);
                        analyst.add(user_name.trim());
                    }
                }
            }
            //回访员
            SqlStatement = Main.conn.prepareStatement("execute get_user_list 5");
            HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    Integer user_id = Integer.valueOf(SqlResult.getInt(1));
                    String user_name = SqlResult.getString(2);
                    int group_id = SqlResult.getInt(4);

                    if (group_id == 3 || group_id == 2 || group_id >= 10) {
                        backCallerID.add(user_id);
                        backCaller.add(user_name.trim());
                    }
                }
            }

        } catch (SQLException ex) {
            Main.logger.getLogger(BasicInfo.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        }

    }

    public Vector getUserID() {
        return userID;
    }

    public Vector getUserName() {
        return userName;
    }

    public void setAdvisoryLevels() {
        try {
            PreparedStatement SqlStatement = Main.conn.prepareStatement("execute get_advisory_level");
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    Integer level_id = Integer.valueOf(SqlResult.getInt(1));
                    String advisory_level = SqlResult.getString(2);

                    levelID.add(level_id);
                    advisoryLevels.add(advisory_level);
                }
            }

        } catch (SQLException ex) {
            Main.logger.getLogger(BasicInfo.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        }
    }

    public Vector getLevelID() {
        return levelID;
    }

    public Vector getAdvisoryLevels() {
        return advisoryLevels;
    }

    public void setInvestmentAdviser() {
        try {
            PreparedStatement SqlStatement = Main.conn.prepareStatement("execute get_adviser_list");
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    Integer user_id = Integer.valueOf(SqlResult.getInt(1));
                    String user_name = SqlResult.getString(2);

                    adviserID.add(user_id);
                    investmentAdviser.add(user_name);
                }
            }

        } catch (SQLException ex) {
            Main.logger.getLogger(BasicInfo.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        }
    }

    public Vector getAnalystID() {
        return analystID;
    }

    public Vector getAnalyst() {
        return analyst;
    }

    public Vector getAdvisorID() {
        return adviserID;
    }

    public Vector getInvestmentAdviser() {
        return investmentAdviser;
    }

    public Vector getServicePersonID() {
        return servicePersonID;
    }

    public Vector getServicePerson() {
        return servicePerson;
    }

    public Vector getBackCaller() {
        return backCaller;
    }

    public Vector getBackCallerID() {
        return backCallerID;
    }

    public Vector getContractItems() {
        return contractItems;
    }

    public void setContractItems() {
        try {
            PreparedStatement SqlStatement = Main.conn.prepareStatement("execute get_contract_items");
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {

                    String item = SqlResult.getString(2);

                    contractItems.add(item);
                }
            }

        } catch (SQLException ex) {
            Main.logger.getLogger(BasicInfo.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        }
    }

    public void setOperateWay() {//4.30版本才需要
        try {
            PreparedStatement SqlStatement = Main.conn.prepareStatement("execute get_operate_way");
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {

                    String operate_way = SqlResult.getString(2);

                    operateaWay.add(operate_way);
                }
            }

        } catch (SQLException ex) {
            Main.logger.getLogger(BasicInfo.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        }
    }

    public Vector getOperateWay() {//4.30版本才需要
        return operateaWay;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListServiceType = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListSatisfactionDegree = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListFinancingProduct = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListInvetmentStyle = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListGroupInfo = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        jListUserInfo = new javax.swing.JList();
        jScrollPane7 = new javax.swing.JScrollPane();
        jListSummary = new javax.swing.JList();
        jScrollPane8 = new javax.swing.JScrollPane();
        jListAdvisory_levels = new javax.swing.JList();
        jScrollPane9 = new javax.swing.JScrollPane();
        jListInvestmentAdvisor = new javax.swing.JList();
        jScrollPane10 = new javax.swing.JScrollPane();
        jListMessageType = new javax.swing.JList();
        jScrollPane11 = new javax.swing.JScrollPane();
        jListAnalyst = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jListValueAddedItems = new javax.swing.JList();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("系统数据字典");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jListServiceType.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListServiceType);

        jListSatisfactionDegree.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListSatisfactionDegree);

        jListFinancingProduct.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jListFinancingProduct);

        jListInvetmentStyle.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jListInvetmentStyle);

        jListGroupInfo.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jListGroupInfo);

        jListUserInfo.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(jListUserInfo);

        jListSummary.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane7.setViewportView(jListSummary);

        jListAdvisory_levels.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(jListAdvisory_levels);

        jListInvestmentAdvisor.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane9.setViewportView(jListInvestmentAdvisor);

        jListMessageType.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane10.setViewportView(jListMessageType);

        jListAnalyst.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane11.setViewportView(jListAnalyst);

        jLabel1.setText("服务类型");

        jLabel2.setText("满意度");

        jLabel3.setText("金融产品");

        jLabel4.setText("消息类型");

        jLabel5.setText("投资风格");

        jLabel6.setText("用户组");

        jLabel7.setText("用户列表");

        jLabel8.setText("投资顾问");

        jLabel9.setText("投资顾问级别");

        jLabel10.setText("回访摘要");

        jLabel11.setText("分析师列表");

        jListValueAddedItems.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane12.setViewportView(jListValueAddedItems);

        jLabel12.setText("增值服务项目");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel10)))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel6)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(jLabel8)))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addComponent(jLabel12))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel7)
                                .addGap(150, 150, 150)
                                .addComponent(jLabel11)
                                .addGap(59, 59, 59))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel1)
                        .addGap(157, 157, 157)
                        .addComponent(jLabel2)
                        .addGap(149, 149, 149)
                        .addComponent(jLabel3)
                        .addGap(116, 116, 116)
                        .addComponent(jLabel4)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane12, jScrollPane7, jScrollPane8, jScrollPane9});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane11, jScrollPane4, jScrollPane5, jScrollPane6});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane1, jScrollPane10, jScrollPane2, jScrollPane3});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12, 0, 144, Short.MAX_VALUE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jListAdvisory_levels;
    private javax.swing.JList jListAnalyst;
    private javax.swing.JList jListFinancingProduct;
    private javax.swing.JList jListGroupInfo;
    private javax.swing.JList jListInvestmentAdvisor;
    private javax.swing.JList jListInvetmentStyle;
    private javax.swing.JList jListMessageType;
    private javax.swing.JList jListSatisfactionDegree;
    private javax.swing.JList jListServiceType;
    private javax.swing.JList jListSummary;
    private javax.swing.JList jListUserInfo;
    private javax.swing.JList jListValueAddedItems;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    // End of variables declaration//GEN-END:variables
    private Vector serviceType = new Vector();
    private Vector satisfactionDegree = new Vector();
    private Vector investmentStyle = new Vector();
    private Vector financingProduct = new Vector();
    private Vector groupID = new Vector();
    private Vector groupName = new Vector();
    private Vector userID = new Vector();
    private Vector userName = new Vector();
    private Vector callback_summary = new Vector();
    private Vector advisoryLevels = new Vector();
    private Vector levelID = new Vector();
    private Vector investmentAdviser = new Vector();
    private Vector adviserID = new Vector();
    private Vector messageType = new Vector();
    private Vector analyst = new Vector();
    private Vector analystID = new Vector();
    private Vector servicePerson = new Vector();
    private Vector servicePersonID = new Vector();
    private Vector backCaller = new Vector();
    private Vector backCallerID = new Vector();
    private Vector operateaWay = new Vector();
    private Vector contractItems = new Vector();//4.30版本才需要
}
