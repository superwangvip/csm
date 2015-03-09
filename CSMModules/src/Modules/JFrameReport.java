/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameReport.java
 *
 * Created on 2010-4-29, 15:49:55
 */
package Modules;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Administrator
 */
public class JFrameReport extends javax.swing.JFrame {

    Vector columnType = new Vector();
    boolean addSerial = false;

    /** Creates new form JFrameReport */
    public JFrameReport() {
        initComponents();

        int iThisWidth = 1010;
        int iThisHight = 528;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y, iThisWidth, iThisHight);

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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("客户资产分布统计");

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setFont(new java.awt.Font("宋体", 0, 18));
        jButton1.setText("统计");
        jButton1.setToolTipText("重新产生报表");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("宋体", 0, 18));
        jButton2.setText("取消");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("宋体", 0, 18));
        jButton3.setText("保存为Excel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("宋体", 0, 18));
        jButton5.setText("资产分布图");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("宋体", 0, 18));
        jButton6.setText("佣金分布图");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("宋体", 0, 18));
        jCheckBox1.setText("保存图形");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jButton1)
                .addGap(66, 66, 66)
                .addComponent(jButton3)
                .addGap(52, 52, 52)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jCheckBox1)
                .addGap(40, 40, 40)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton2)
                    .addComponent(jCheckBox1)
                    .addComponent(jButton5)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("宋体", 0, 24));
        jLabel1.setText("客户资产及佣金统计表");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(397, 397, 397))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        fetchReport();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "没有数据需要保存!");
            return;
        }
        String outputFile = "客户资产统计" + getDateString() + ".xls";
        JTableToExcel tbexcel = new JTableToExcel();

        try {
            tbexcel.saveToExcel(jTable1, columnType, outputFile, addSerial);
            String msg = outputFile + "已保存到工作目录";
            JOptionPane.showMessageDialog(this, msg);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JFrameReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JFrameReport.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Vector itemData = new Vector();
        boolean HASDATA = false;
        try {
            Statement stat = (Statement) Main.conn.createStatement();
            String sql = "select * from report_piechart_asset";
            boolean hasResultSet = stat.execute(sql);
            if (!hasResultSet) {
                hasResultSet = stat.getMoreResults();
            }
            if (hasResultSet) {
                ResultSet SqlResult = stat.getResultSet();
                while (SqlResult.next()) {
                    String itemName = SqlResult.getString(1);
                    int intValue = SqlResult.getInt(2);
                    Integer value = Integer.valueOf(intValue);
                    Vector line = new Vector();
                    line.add(itemName);
                    line.add(value);
                    itemData.add(line);
                    HASDATA = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFrameReport.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
        }
        if (HASDATA) {
            PieChartAsset pieChart = new PieChartAsset();
            pieChart.createPieChart(itemData);
            if (jCheckBox1.isSelected()) {
                JFreeChart chart = pieChart.getChart();
                try {
                    ChartUtilities.saveChartAsJPEG(new File("资产分布图.jpg"), chart, 800, 500);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "统计表内容为空，请先产生报表!");
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Vector itemData = new Vector();
        boolean HASDATA = false;
        try {
            Statement stat = (Statement) Main.conn.createStatement();
            String sql = "select * from report_piechart_asset_commision";
            boolean hasResultSet = stat.execute(sql);
            if (!hasResultSet) {
                hasResultSet = stat.getMoreResults();
            }
            if (hasResultSet) {
                ResultSet SqlResult = stat.getResultSet();
                while (SqlResult.next()) {
                    String itemName = SqlResult.getString(1);
                    int intValue = SqlResult.getInt(2);
                    Integer value = Integer.valueOf(intValue);
                    Vector line = new Vector();
                    line.add(itemName);
                    line.add(value);
                    itemData.add(line);
                    HASDATA = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFrameReport.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
        }
        if (HASDATA) {
            PieChartAssetCommision pieChart = new PieChartAssetCommision();
            pieChart.createPieChart(itemData);
            if (jCheckBox1.isSelected()) {
                JFreeChart chart = pieChart.getChart();
                try {
                    ChartUtilities.saveChartAsJPEG(new File("佣金分布图.jpg"), chart, 800, 500);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "统计表内容为空，请先产生报表!");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void fetchReport() {
        String sql = "get_report";
        System.out.println(sql);
        JDBTableModel dm = new JDBTableModel(jTable1);
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        hasData = true;
        if (jTable1.getRowCount() > 1) {
            hasData = true;
        }
    }

    public void generateReport() throws SQLException {
        PreparedStatement SqlStatement;
        int returnStatus = 0;

        SqlStatement = Main.conn.prepareStatement("execute generate_report");

        Boolean HasResult = SqlStatement.execute();
        if (!HasResult) {
            HasResult = SqlStatement.getMoreResults();
        }
        if (HasResult) {
            ResultSet SqlResult = SqlStatement.getResultSet();
            while (SqlResult.next()) {
                returnStatus = SqlResult.getInt("ret_status");
                SqlResult.getString("ret_msg");//?????
            }
        }
    }

    public void generateReport1() throws SQLException {
        PreparedStatement SqlStatement;
        int returnStatus = 0;
        //System.out.println("generate_report_piechart:begin");
        SqlStatement = Main.conn.prepareStatement("execute generate_report_piechart");

        Boolean HasResult = SqlStatement.execute();
        while (!HasResult) {
            HasResult = SqlStatement.getMoreResults();
        }
        if (HasResult) {
            ResultSet SqlResult = SqlStatement.getResultSet();
            while (SqlResult.next()) {
                returnStatus = SqlResult.getInt("ret_status");
                SqlResult.getString("ret_msg");//?????
            }
        }
    }

    private String getDateString() {
        String date = "1900";
        GregorianCalendar d = new GregorianCalendar();
        int thisYear = d.get(Calendar.YEAR);
        int thisMonth = d.get(Calendar.MONTH) + 1;
        int thisDay = d.get(Calendar.DAY_OF_MONTH);
        date = String.format("%4d%02d%02d", thisYear, thisMonth, thisDay);
        return date;
    }
    private boolean hasData = false;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}


 
