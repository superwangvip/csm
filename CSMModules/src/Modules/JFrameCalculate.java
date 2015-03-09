/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameCalculate.java
 *
 * Created on 2010-8-4, 11:05:49
 */
package Modules;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jiang Youquan
 */
public class JFrameCalculate extends javax.swing.JFrame {

    /** Creates new form JFrameCalculate */
    public JFrameCalculate() {
        initComponents();
        int iThisWidth = 462;
        int iThisHight = 330;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y, iThisWidth, iThisHight);
        getyearMonth();
        //注册鼠标动作监听器，监听鼠标动作，把系统无操作等待计时器置0
        MainMenu.registerMouseListener(this);
    }

    private void getyearMonth() {
        String yearMonth1 = "";
        String yearMonth2 = "";
        String yearMonth3 = "";
        String yearMonth4 = "";
        String yearMonth5 = yearMonth4;
        String yearMonth6 = yearMonth2;
        try {
            String sql = "select convert(char(6),min(update_time),112),convert(char(6),max(update_time),112) from basehistory";
            PreparedStatement SqlStatement = Main.conn.prepareStatement(sql);
            Boolean HasResult = SqlStatement.execute();
            while (!HasResult) {
                HasResult = SqlStatement.getMoreResults();
            }
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    yearMonth1 = SqlResult.getString(1);
                    yearMonth2 = SqlResult.getString(2);
                }
            }
            if (yearMonth1 == null) {
                JOptionPane.showMessageDialog(this, "资产历史数据没有归档！");
                return;
            }
            sql = "select min(year_month),max(year_month) from base_value";
            PreparedStatement SqlStatement1 = Main.conn.prepareStatement(sql);
            Boolean HasResult1 = SqlStatement1.execute();
            while (!HasResult1) {
                HasResult1 = SqlStatement1.getMoreResults();
            }
            if (HasResult1) {
                ResultSet SqlResult1 = SqlStatement1.getResultSet();
                while (SqlResult1.next()) {
                    yearMonth3 = SqlResult1.getString(1);
                    yearMonth4 = SqlResult1.getString(2);
                }
            }
            if (yearMonth3 == null) {
                yearMonth3 = "";
                yearMonth4 = "";
                yearMonth5 = yearMonth1;
                yearMonth6 = yearMonth2;
            } else {
                yearMonth5 = yearMonth4;
                int yearMonth = Integer.valueOf(yearMonth4).intValue() + 1;
                yearMonth5 = String.valueOf(yearMonth);
                if (yearMonth5.endsWith("13")) {
                    String year_s = yearMonth4.substring(0, 4);
                    int nextYear = Integer.valueOf(year_s) + 1;
                    yearMonth5 = String.valueOf(nextYear) + "01";
                }
                yearMonth6 = yearMonth2;
                if (yearMonth5.compareTo(yearMonth6) > 0) {
                    yearMonth5 = "------";
                    yearMonth6 = "------";
                }
            }
            jTextFieldTime1.setText(yearMonth1);
            jTextFieldTime2.setText(yearMonth2);
            jTextFieldTime3.setText(yearMonth3);
            jTextFieldTime4.setText(yearMonth4);
            jTextFieldTime5.setText(yearMonth5);
            jTextFieldTime6.setText(yearMonth6);
        } catch (SQLException ex) {
            Main.logger.warning(ex.getLocalizedMessage());
            new JFrameWarning("sql异常："+ex.getLocalizedMessage()).setVisible(true);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldTime1 = new javax.swing.JTextField();
        jTextFieldTime2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldTime3 = new javax.swing.JTextField();
        jTextFieldTime4 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldTime6 = new javax.swing.JTextField();
        jTextFieldTime5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("计算客户价值与服务考核指标");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel1.setText("已归档数据时间段");

        jTextFieldTime1.setFocusable(false);

        jTextFieldTime2.setFocusable(false);
        jTextFieldTime2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTime2ActionPerformed(evt);
            }
        });

        jLabel2.setText("已计算数据时间段");

        jTextFieldTime3.setFocusable(false);

        jTextFieldTime4.setFocusable(false);

        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("本次要计算时间段 ");

        jTextFieldTime5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTime5ActionPerformed(evt);
            }
        });

        jButton1.setText("计算");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldTime1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(jTextFieldTime3)
                    .addComponent(jTextFieldTime5, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextFieldTime4)
                        .addComponent(jTextFieldTime6)
                        .addComponent(jTextFieldTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2))
                .addGap(80, 80, 80))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTime2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTime3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldTime4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldTime5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTime6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextFieldTime2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTime2ActionPerformed
}//GEN-LAST:event_jTextFieldTime2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String startDate = jTextFieldTime6.getText();
        String endDate = jTextFieldTime5.getText();
        SqlExecutor sqlx = new SqlExecutor();
        String msg = "正在计算......";
        String sql = "calculate_client_value '" + startDate + "','" + endDate + "'";
        System.out.println(sql);
        sqlx.execute(Main.conn, sql, msg);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldTime5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTime5ActionPerformed
}//GEN-LAST:event_jTextFieldTime5ActionPerformed
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldTime1;
    private javax.swing.JTextField jTextFieldTime2;
    private javax.swing.JTextField jTextFieldTime3;
    private javax.swing.JTextField jTextFieldTime4;
    private javax.swing.JTextField jTextFieldTime5;
    private javax.swing.JTextField jTextFieldTime6;
    // End of variables declaration//GEN-END:variables
}
