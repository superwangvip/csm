package Modules;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameTest.java
 *
 * Created on 2010-8-7, 23:25:38
 */
/**
 *
 * @author Administrator
 */
public class JFrameArchive extends javax.swing.JFrame {

    /** Creates new form JFrameTest */
    public JFrameArchive() {
        initComponents();
        int iThisWidth = 430;
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
            String sql = "select min(year_month),max(year_month) from month_asset";
            PreparedStatement SqlStatement = Main.conn.prepareStatement(sql);
            Boolean HasResult = SqlStatement.execute();
            if (!HasResult) {
                SqlStatement.getMoreResults();
            }
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    yearMonth1 = SqlResult.getString(1);
                    yearMonth2 = SqlResult.getString(2);
                }
            }
            if (yearMonth1 == null) {
                JOptionPane.showMessageDialog(this, "没有转入资产历史数据！");
                return;
            }
            sql = "select min(convert(char(6),update_time,112)),max( convert(char(6),update_time,112) ) from basehistory";
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

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
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
        jTextFieldTime5 = new javax.swing.JTextField();
        jTextFieldTime6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("资产数据归档");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel1.setText("已转入数据时间段");

        jTextFieldTime1.setFocusable(false);

        jTextFieldTime2.setFocusable(false);
        jTextFieldTime2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTime2ActionPerformed(evt);
            }
        });

        jLabel2.setText("已归档数据时间段");

        jTextFieldTime3.setFocusable(false);

        jTextFieldTime4.setFocusable(false);

        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("本次要归档时间段 ");

        jButton1.setText("归档");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldTime5)
                            .addComponent(jTextFieldTime3)
                            .addComponent(jTextFieldTime1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)))
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldTime2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldTime4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldTime6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTime2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldTime3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTime4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldTime5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTime6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldTime2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTime2ActionPerformed
}//GEN-LAST:event_jTextFieldTime2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String startDate = jTextFieldTime5.getText();
        String endDate = jTextFieldTime6.getText();
        SqlExecutor sqlx = new SqlExecutor();
        if (startDate.equals("------")) {
            JOptionPane.showMessageDialog(this, "无需要归档的数据!");
            return;
        }
        String msg = "正在归档......";
        String sql = "Archive '" + startDate + "','" + endDate + "'";
        System.out.println(sql);
        sqlx.execute(Main.conn, sql, msg);
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed
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
