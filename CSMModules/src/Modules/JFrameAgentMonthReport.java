/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameAgentMonthReport.java
 *
 * Created on 2011-11-21, 14:42:28
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

/**
 *
 * @author Administrator
 */
public class JFrameAgentMonthReport extends javax.swing.JFrame {

    /** Creates new form JFrameAgentMonthReport */
    public JFrameAgentMonthReport() {
        initComponents();
        int iThisWidth = 960;
        int iThisHight = 640;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y, iThisWidth, iThisHight);
        getyearMonth();
        getagentList();

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
        jButton2 = new javax.swing.JButton();
        jComboBoxYearMonth = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jComboBoxAgent = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("经纪人业绩统计");
        setAlwaysOnTop(true);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTable1.setFont(new java.awt.Font("宋体", 0, 18));
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
        jTable1.setRowHeight(24);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton2.setText("取消");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBoxYearMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("专职按月统计");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBoxAgent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton3.setText("按经纪人查寻");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("兼职按月统计");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("在此选择经纪人");

        jLabel2.setText("在此选择数据年月");

        jLabel3.setFont(new java.awt.Font("宋体", 1, 18));
        jLabel3.setText("经纪人业绩统计表");

        jLabel4.setForeground(new java.awt.Color(51, 0, 204));
        jLabel4.setText("兼职经纪人指营业部后台服务与管理人员兼有经纪人身份，如果他们不需使用此系统应该添加为“其他”角色的用户，便于统计时与专职经纪人区分，");

        jLabel5.setForeground(new java.awt.Color(51, 0, 204));
        jLabel5.setText("专职经纪人指营销部门的专职客户经理，他们不应该在此系统有任何角色的用户。");

        jButton5.setText("专职按月汇总");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("兼职按月汇总");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("专职年度统计");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("兼职年度统计");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(51, 0, 255));
        jLabel6.setText("年度统计汇总需把数据年月");

        jLabel7.setForeground(new java.awt.Color(51, 0, 204));
        jLabel7.setText("选择到该年度内任意月");

        jButton9.setText("专职年度汇总");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("兼职年度汇总");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(255, 51, 102));
        jLabel8.setText("特别说明：");

        jLabel9.setForeground(new java.awt.Color(255, 0, 102));
        jLabel9.setText("操作提示：");

        jLabel10.setText("[若需要查询经纪人最新业绩，请进入客户查询-按经纪人统计查询客户]");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jComboBoxAgent, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jButton3))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(44, 44, 44)
                                            .addComponent(jLabel1))
                                        .addComponent(jLabel8))
                                    .addGap(76, 76, 76)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jComboBoxYearMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(20, 20, 20)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(jLabel2))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(100, 100, 100)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel6))
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(26, 26, 26)
                                            .addComponent(jLabel9))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(233, 233, 233)))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jLabel10)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton10, jButton4, jButton5, jButton6, jButton7, jButton8});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jComboBoxAgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxYearMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton4)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton6)
                                        .addComponent(jButton6)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton10))
                                    .addComponent(jButton8)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String yearMonth = jComboBoxYearMonth.getSelectedItem().toString();
        String sql = "get_agent_month_report " + yearMonth + "," + 1;
        System.out.println(sql);
        Vector columnType = new Vector();
        boolean addSerial = true;
        JDBTableModel dm1 = new JDBTableModel(jTable1);
        System.out.println(sql);
        dm1.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        jLabel3.setText("专职经纪人业绩统计月报--" + yearMonth);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String yearMonth = jComboBoxYearMonth.getSelectedItem().toString();
        String sql = "get_agent_month_report " + yearMonth + "," + 2;
        System.out.println(sql);
        Vector columnType = new Vector();
        boolean addSerial = true;
        JDBTableModel dm1 = new JDBTableModel(jTable1);
        System.out.println(sql);
        dm1.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        jLabel3.setText("兼职经纪人业绩统计月报--" + yearMonth);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String agent = jComboBoxAgent.getSelectedItem().toString();
        int n = agent.indexOf("]");
        agent = agent.substring(n + 1);
        String sql = "get_agent_month_report1 " + agent;
        System.out.println(sql);
        Vector columnType = new Vector();
        boolean addSerial = true;
        JDBTableModel dm1 = new JDBTableModel(jTable1);
        System.out.println(sql);
        dm1.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        jLabel3.setText("经纪人业绩统计报表--" + agent);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String yearMonth = jComboBoxYearMonth.getSelectedItem().toString();
        String sql = "get_agent_month_report_sum " + yearMonth + "," + 1;
        System.out.println(sql);
        Vector columnType = new Vector();
        boolean addSerial = true;
        JDBTableModel dm1 = new JDBTableModel(jTable1);
        System.out.println(sql);
        dm1.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        jLabel3.setText("专职经纪人业绩月汇总--" + yearMonth);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String yearMonth = jComboBoxYearMonth.getSelectedItem().toString();
        String sql = "get_agent_month_report_sum " + yearMonth + "," + 2;
        System.out.println(sql);
        Vector columnType = new Vector();
        boolean addSerial = true;
        JDBTableModel dm1 = new JDBTableModel(jTable1);
        System.out.println(sql);
        dm1.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        jLabel3.setText("兼职经纪人业绩月汇总--" + yearMonth);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String yearMonth = jComboBoxYearMonth.getSelectedItem().toString().substring(0, 4);
        String sql = "get_agent_month_report_sum1 " + yearMonth + "," + 1;
        System.out.println(sql);
        Vector columnType = new Vector();
        boolean addSerial = true;
        JDBTableModel dm1 = new JDBTableModel(jTable1);
        System.out.println(sql);
        dm1.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        jLabel3.setText("专职经纪人业绩年度统计--" + yearMonth);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String year = jComboBoxYearMonth.getSelectedItem().toString().substring(0, 4);
        String sql = "get_agent_month_report_sum1 " + year + "," + 2;
        System.out.println(sql);
        Vector columnType = new Vector();
        boolean addSerial = true;
        JDBTableModel dm1 = new JDBTableModel(jTable1);
        System.out.println(sql);
        dm1.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        jLabel3.setText("兼职经纪人业绩年度统计--" + year);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        String yearMonth = jComboBoxYearMonth.getSelectedItem().toString().substring(0, 4);
        String sql = "get_agent_month_report_sum2 " + yearMonth + "," + 1;
        System.out.println(sql);
        Vector columnType = new Vector();
        boolean addSerial = true;
        JDBTableModel dm1 = new JDBTableModel(jTable1);
        System.out.println(sql);
        dm1.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        jLabel3.setText("专职经纪人业绩年度汇总--" + yearMonth);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String year = jComboBoxYearMonth.getSelectedItem().toString().substring(0, 4);
        String sql = "get_agent_month_report_sum2 " + year + "," + 2;
        System.out.println(sql);
        Vector columnType = new Vector();
        boolean addSerial = true;
        JDBTableModel dm1 = new JDBTableModel(jTable1);
        System.out.println(sql);
        dm1.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        jLabel3.setText("兼职经纪人业绩年度汇总--" + year);
    }//GEN-LAST:event_jButton10ActionPerformed

    /**
     * @param args the command line arguments
     */
    private void getyearMonth() {
        String yearMonth = "";

        jComboBoxYearMonth.removeAllItems();
        try {
            String sql = "select distinct convert(char(6),update_time,112) from basehistory order by convert(char(6),update_time,112)";
            PreparedStatement SqlStatement = Main.conn.prepareStatement(sql);
            Boolean HasResult = SqlStatement.execute();
            while (!HasResult) {
                HasResult = SqlStatement.getMoreResults();
            }
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    yearMonth = SqlResult.getString(1);

                    jComboBoxYearMonth.addItem(yearMonth);
                }
            }
            if (yearMonth == null) {
                JOptionPane.showMessageDialog(this, "资产历史数据没有归档！");
                return;
            }
            jComboBoxYearMonth.setSelectedIndex(jComboBoxYearMonth.getItemCount() - 1);
        } catch (SQLException ex) {
            Logger.getLogger(JFrameCalculate.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getLocalizedMessage());
            new JFrameWarning(ex.getLocalizedMessage()).setVisible(true);
        }
    }

    private void getagentList() {
        String agent = "";
        String agentType = "";
        int n = 0;
        jComboBoxAgent.removeAllItems();
        try {
            //先读出兼职经纪人
            String sql = "select distinct agent,'兼职经纪人' from client" +
                    " where isnull(agent,'')!='' and agent in " +
                    "(select user_name from juser) order by agent";

            PreparedStatement SqlStatement = Main.conn.prepareStatement(sql);
            Boolean HasResult = SqlStatement.execute();
            while (!HasResult) {
                HasResult = SqlStatement.getMoreResults();
            }
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    agent = SqlResult.getString(1);
                    agentType = SqlResult.getString(2);
                    n = n + 1;
                    agent = "[" + n + ":" + agentType + "]" + agent;
                    jComboBoxAgent.addItem(agent);
                }
            }
            //再读出职经纪人
            sql = "select distinct agent,'专职经纪人' from client" +
                    " where isnull(agent,'')!='' and agent not in " +
                    "(select user_name from juser) order by agent";

            SqlStatement = Main.conn.prepareStatement(sql);
            HasResult = SqlStatement.execute();
            while (!HasResult) {
                HasResult = SqlStatement.getMoreResults();
            }
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    agent = SqlResult.getString(1);
                    agentType = SqlResult.getString(2);
                    n = n + 1;
                    agent = "[" + n + ":" + agentType + "]" + agent;
                    jComboBoxAgent.addItem(agent);
                }
            }
            jComboBoxAgent.setSelectedIndex(0);
        } catch (SQLException ex) {
            Logger.getLogger(JFrameCalculate.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getLocalizedMessage());
            new JFrameWarning(ex.getLocalizedMessage()).setVisible(true);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBoxAgent;
    private javax.swing.JComboBox jComboBoxYearMonth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    // End of variables declaration//GEN-END:variables
}
