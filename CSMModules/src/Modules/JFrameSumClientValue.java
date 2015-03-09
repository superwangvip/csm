/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameSumClientValue.java
 *
 * Created on 2010-7-29, 2:16:07
 */
package Modules;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author Administrator
 */
public class JFrameSumClientValue extends javax.swing.JFrame {

    Vector columnType = new Vector();
    boolean addSerial = true;
    private boolean hasData = false;

    /** Creates new form JFrameSumClientValue */
    public JFrameSumClientValue() {
        initComponents();

        int iThisWidth = 1036;
        int iThisHight = 500;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y, iThisWidth, iThisHight);
        getDataYear();
        jComboBoxYear.setSelectedIndex(jComboBoxYear.getItemCount()-1);

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
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBoxYear = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("客户价值汇总表");

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
        jTable1.setToolTipText("“环比”、“新增率”与“流失率“都是与上月相比,周转率=成交量/资产");
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel1.setText("客户价值汇总表");

        jButton1.setText("统计");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("保存到Excel");
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

        jButton4.setText("新增与流失对比图");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("总资产柱形图");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("成交量柱形图");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("佣金柱形图");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("保存图形");

        jComboBoxYear.setBackground(new java.awt.Color(255, 204, 204));
        jComboBoxYear.setFont(new java.awt.Font("宋体", 1, 14));
        jComboBoxYear.setForeground(new java.awt.Color(0, 0, 102));
        jComboBoxYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setForeground(new java.awt.Color(51, 0, 204));
        jLabel2.setText("统计年度");

        jButton8.setText("市占率曲线");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBoxYear, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton8))
                            .addComponent(jLabel2))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jCheckBox1)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String year = getDateString().substring(0, 4);
        Object year_O = jComboBoxYear.getSelectedItem();
        if (year_O != null) {
            year = year_O.toString();
        }
        fetchReport(year);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "没有数据需要保存!");
            return;
        }
        String outputFile = "客户价值汇总" + getDateString() + ".xls";
        JTableToExcel tbexcel = new JTableToExcel();

        try {
            tbexcel.saveToExcel(jTable1, columnType, outputFile, addSerial);
            String msg = outputFile + "已保存到工作目录";
            JOptionPane.showMessageDialog(this, msg);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JFrameReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JFrameSumClientValue.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "表里没有数据！请先统计");
            return;
        }
        Vector data = new Vector();

        TableModel m1 = jTable1.getModel();
        int r = m1.getRowCount();
        int c = m1.getColumnCount();

        for (int i = 0; i < r; i++) {
            int mr = jTable1.convertRowIndexToModel(i);
            Double incrementValue = (Double) m1.getValueAt(mr, 20);
            Double decrementValue = (Double) m1.getValueAt(mr, 24);
            String yearMonth = (String) m1.getValueAt(mr, 1);
            Vector line = new Vector();
            line.add(yearMonth);
            line.add(incrementValue);
            line.add(decrementValue);
            data.add(line);
        }
        BarChart frame = new BarChart(data, 1);

        int iThisWidth = 800;
        int iThisHight = 500;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;

        // frame.setSize(600, 300);
        frame.setBounds(x, y, iThisWidth, iThisHight);
        frame.setVisible(true);

        if (jCheckBox1.isSelected()) {
            JFreeChart chart = frame.getChart();
            try {
                ChartUtilities.saveChartAsJPEG(new File("新增与流失对比图.jpg"), chart, 800, 500);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "表里没有数据！请先统计");
            return;
        }
        Vector data = new Vector();

        TableModel m1 = jTable1.getModel();
        int r = m1.getRowCount();
        int c = m1.getColumnCount();

        for (int i = 0; i < r; i++) {
            int mr = jTable1.convertRowIndexToModel(i);
            Double incrementValue = (Double) m1.getValueAt(mr, 4);
            Double decrementValue = (Double) m1.getValueAt(mr, 4);
            String yearMonth = (String) m1.getValueAt(mr, 1);
            Vector line = new Vector();
            line.add(yearMonth);
            line.add(incrementValue);
            line.add(decrementValue);
            data.add(line);
        }

        BarChart frame = new BarChart(data, 2);
        int iThisWidth = 800;
        int iThisHight = 500;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;

        // frame.setSize(600, 300);
        frame.setBounds(x, y, iThisWidth, iThisHight);
        frame.setVisible(true);
        if (jCheckBox1.isSelected()) {
            JFreeChart chart = frame.getChart();
            try {
                ChartUtilities.saveChartAsJPEG(new File("总资产柱形图.jpg"), chart, 800, 500);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
            }
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "表里没有数据！请先统计");
            return;
        }
        Vector data = new Vector();

        TableModel m1 = jTable1.getModel();
        int r = m1.getRowCount();
        int c = m1.getColumnCount();

        for (int i = 0; i < r; i++) {
            int mr = jTable1.convertRowIndexToModel(i);
            Double value1 = (Double) m1.getValueAt(mr, 10);
            Double value2 = (Double) m1.getValueAt(mr, 26);
            String yearMonth = (String) m1.getValueAt(mr, 1);
            Vector line = new Vector();
            line.add(yearMonth);
            line.add(value1);
            line.add(value2);
            data.add(line);
        }

        BarChart frame = new BarChart(data, 3);
        int iThisWidth = 800;
        int iThisHight = 500;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;

        // frame.setSize(600, 300);
        frame.setBounds(x, y, iThisWidth, iThisHight);
        frame.setVisible(true);

        if (jCheckBox1.isSelected()) {
            JFreeChart chart = frame.getChart();
            try {
                ChartUtilities.saveChartAsJPEG(new File("成交量柱形图.jpg"), chart, 800, 500);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "表里没有数据！");
            return;
        }
        Vector data = new Vector();

        TableModel m1 = jTable1.getModel();
        int r = m1.getRowCount();
        int c = m1.getColumnCount();

        for (int i = 0; i < r; i++) {
            int mr = jTable1.convertRowIndexToModel(i);
            Double incrementValue = (Double) m1.getValueAt(mr, 13);
            Double decrementValue = (Double) m1.getValueAt(mr, 28);
            String yearMonth = (String) m1.getValueAt(mr, 1);
            Vector line = new Vector();
            line.add(yearMonth);
            line.add(incrementValue);
            line.add(decrementValue);
            data.add(line);
        }

        BarChart frame = new BarChart(data, 4);
        int iThisWidth = 800;
        int iThisHight = 500;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;

        // frame.setSize(600, 300);
        frame.setBounds(x, y, iThisWidth, iThisHight);
        frame.setVisible(true);
        if (jCheckBox1.isSelected()) {
            JFreeChart chart = frame.getChart();
            try {
                ChartUtilities.saveChartAsJPEG(new File("佣金柱形图.jpg"), chart, 800, 500);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "表里没有数据！请先统计");
            return;
        }
        Vector data = new Vector();

        TableModel m1 = jTable1.getModel();
        int r = m1.getRowCount();
        int c = m1.getColumnCount();

        for (int i = 0; i < r; i++) {
            int mr = jTable1.convertRowIndexToModel(i);
            Double value = (Double) m1.getValueAt(mr, 18);
            Double value1 = (Double) m1.getValueAt(mr, 30);
            String yearMonth = (String) m1.getValueAt(mr, 1);
            Vector line = new Vector();
            line.add(yearMonth);
            line.add(value);
            line.add(value1);
            data.add(line);
        }

        MarketPostionRatioCurve timeseriesdemo1 = new MarketPostionRatioCurve("市占率曲线", data);
        timeseriesdemo1.pack();
        RefineryUtilities.centerFrameOnScreen(timeseriesdemo1);
        timeseriesdemo1.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void fetchReport(String year) {
        String sql = "get_sum_value " + year;
        System.out.println(sql);
        JDBTableModel dm = new JDBTableModel(jTable1);
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        if (jTable1.getRowCount() > 0) {
            hasData = true;
        }
    }

    private void getDataYear() {
        try {
            String sql = "select distinct substring(year_month,1,4) from sum_client_value";
            PreparedStatement SqlStatement = Main.conn.prepareStatement(sql);
            Boolean HasResult = SqlStatement.execute();
            while (!HasResult) {
                HasResult = SqlStatement.getMoreResults();
            }
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                jComboBoxYear.removeAllItems();
                while (SqlResult.next()) {
                    String Year = SqlResult.getString(1);
                    jComboBoxYear.addItem(Year);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFrameSumClientValue.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBoxYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
