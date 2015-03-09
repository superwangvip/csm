/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameServicePersonAsssetReport.java
 *
 * Created on 2010-6-5, 23:11:57
 */
package Modules;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author Administrator
 */
public class JFrameServicePersonAsssetReport extends javax.swing.JFrame {

    boolean hasData = false;

    /** Creates new form JFrameServicePersonAsssetReport */
    public JFrameServicePersonAsssetReport() {
        initComponents();

        int iThisWidth = 640;
        int iThisHight = 540;
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("服务员客户资产佣金统计");

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
        jTable1.setToolTipText("单击列头可按列排序");
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("统计");
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

        jButton3.setText("保存为Excel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("佣金比较图");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("资产比较图");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(41, 41, 41)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(41, 41, 41)
                        .addComponent(jButton4)
                        .addGap(36, 36, 36)
                        .addComponent(jButton2)
                        .addGap(31, 31, 31))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String sql = "execute service_person_report";
        System.out.println(sql);
        JDBTableModel dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        boolean addSerial = true;
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        hasData = true;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "没有数据需要保存!");
            return;
        }
        TableModel md = jTable1.getModel();
        String outputFile = "服务员客户佣金" + getDateString() + ".xls";

        JTableToExcel tbexcel = new JTableToExcel();
        Vector columnType = new Vector();
        boolean addSerial = true;
        try {
            tbexcel.saveToExcel(jTable1, columnType, outputFile, addSerial);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JFrameServicePersonAsssetReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JFrameServicePersonAsssetReport.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
        }
        String msg = outputFile + " 已保存到工作目录下!";
        JOptionPane.showMessageDialog(this, msg);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "表里没有数据!,请先统计.");
            return;
        }
        Vector itemData = new Vector();
        TableModel m1 = jTable1.getModel();
        int r = m1.getRowCount();
        //求出项目合计
        double sum = 0;

        for (int i = 0; i < r; i++) {
            int mr = jTable1.convertRowIndexToModel(i);
            Double ratio = (Double) m1.getValueAt(mr, 6);
            sum = sum + ratio;
        }
        //读出每个项目
        double sum1 = 0;
        for (int i = 0; i < r; i++) {
            int mr = jTable1.convertRowIndexToModel(i);
            Object obj = m1.getValueAt(mr, 1);
            if (obj != null && !(obj.toString().trim().equals(""))) {
                String itemName = (String) m1.getValueAt(mr, 2);
                Double ratio = (Double) m1.getValueAt(mr, 6);
                if (ratio / sum * 100 >= 2.0) {//占比不足2%者忽略，并归类到其他项目
                    Vector line = new Vector();
                    line.add(itemName);
                    line.add(ratio);
                    itemData.add(line);
                } else {
                    sum1 = sum1 + ratio;
                }
            }
        }
        //补充其他项
        String itemName =  "其他";
        Double s = Double.valueOf(sum1);
        Vector line = new Vector();
        line.add(itemName);
        line.add(s);
        itemData.add(line);
        PieServicePersonCommision pieChart = new PieServicePersonCommision(1);
        pieChart.createPieChart(itemData);


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "表里没有数据!,请先统计.");
            return;
        }
        Vector itemData = new Vector();
        TableModel m1 = jTable1.getModel();
        int r = m1.getRowCount();
        //求出项目合计
        double sum = 0;
        for (int i = 0; i < r; i++) {
            int mr = jTable1.convertRowIndexToModel(i);
            Double ratio = (Double) m1.getValueAt(mr, 4);
            sum = sum + ratio;
        }
        //读出每个项目
        double sum1 = 0;
        for (int i = 0; i < r; i++) {
            int mr = jTable1.convertRowIndexToModel(i);
            Object obj = m1.getValueAt(mr, 1);
            if (obj != null && !(obj.toString().trim().equals(""))) {
                String itemName = (String) m1.getValueAt(mr, 2);
                Double ratio = (Double) m1.getValueAt(mr, 4);
                if (ratio / sum * 100 >= 2.0) {//占比不足2%者忽略，并归类到其他项目
                    Vector line = new Vector();
                    line.add(itemName);
                    line.add(ratio);
                    itemData.add(line);
                } else {
                    sum1 = sum1 + ratio;
                }
            }
        }
        //补充其他项
        String itemName =  "其他";
        Double s = Double.valueOf(sum1);
        Vector line = new Vector();
        line.add(itemName);
        line.add(s);
        itemData.add(line);

        PieServicePersonCommision pieChart = new PieServicePersonCommision(2);
        pieChart.createPieChart(itemData);

    }//GEN-LAST:event_jButton5ActionPerformed

    private String getDateString() {
        String date = "1900";
        GregorianCalendar d = new GregorianCalendar();
        int thisYear = d.get(Calendar.YEAR);
        int thisMonth = d.get(Calendar.MONTH) + 1;
        int thisDay = d.get(Calendar.DAY_OF_MONTH);
        date = String.format("%4d%02d%02d", thisYear, thisMonth, thisDay);
        return date;
    }
    /**
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
