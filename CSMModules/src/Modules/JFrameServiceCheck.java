/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameServiceCheck.java
 *
 * Created on 2010-7-29, 0:55:33
 */
package Modules;

import java.awt.Dimension;
import java.awt.Toolkit;
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

/**
 *
 * @author Administrator
 */
public class JFrameServiceCheck extends javax.swing.JFrame {

    Vector columnType1 = new Vector();
    Vector columnType2 = new Vector();
    Vector columnType3 = new Vector();
    boolean addSerial = true;
    private boolean hasData = false;
    String year_month;

    /** Creates new form JFrameServiceCheck */
    public JFrameServiceCheck() {
        initComponents();
        int iThisWidth = 834;
        int iThisHight = 710;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y, iThisWidth, iThisHight);
        getDataYear();
        jComboBoxYear.setSelectedIndex(jComboBoxYear.getItemCount() - 1);

        //注册键盘监听器，监听键盘动作，把系统无操作等待计时器置0;对非管理员屏蔽批量数据复制
        MainMenu.registerKeyListener(jTable1);
        MainMenu.registerKeyListener(jTable2);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBoxYear = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("服务考核统计表");

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
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

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
        jTable2.setCellSelectionEnabled(true);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.setToolTipText("单击记录可以显示流失客户清单");
        jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable3.setColumnSelectionAllowed(true);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("存量客户价值统计表");

        jLabel2.setText("新增客户价值统计表(单击记录可列出新增客户清单)");

        jLabel3.setText("流失客户价值统计表(单击记录可以显示流失客户清单)");

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

        jButton3.setText("保存到Excel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBoxYear.setBackground(new java.awt.Color(255, 204, 204));
        jComboBoxYear.setForeground(new java.awt.Color(51, 0, 153));
        jComboBoxYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setForeground(new java.awt.Color(51, 0, 204));
        jLabel4.setText("数据年月");

        jButton6.setText("服务员佣金比较图");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton9.setText("服务员资产比较图");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxYear, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(1, 1, 1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(27, 27, 27)
                        .addComponent(jButton3)
                        .addGap(33, 33, 33)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(jButton6))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String year_month = getDateString().substring(0, 4);
        Object year_month_O = jComboBoxYear.getSelectedItem();
        if (year_month_O != null) {
            year_month = year_month_O.toString();
        }
        fetchReport(year_month);
        this.year_month = year_month;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void getDataYear() {
        try {
            String sql = "select distinct year_month from base_value";
            PreparedStatement SqlStatement = Main.conn.prepareStatement(sql);
            Boolean HasResult = SqlStatement.execute();
            while (!HasResult) {
                SqlStatement.getMoreResults();
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
            Logger.getLogger(JFrameServiceCheck.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "没有数据需要保存!");
            return;
        }
        String outputFile1 = "存量客户价值" + this.year_month + ".xls";
        String outputFile2 = "新增客户价值" + this.year_month + ".xls";
        String outputFile3 = "流失客户价值" + this.year_month + ".xls";
        JTableToExcel tbexcel = new JTableToExcel();

        try {
            tbexcel.saveToExcel(jTable1, columnType1, outputFile1, addSerial);
            tbexcel.saveToExcel(jTable2, columnType2, outputFile2, addSerial);
            tbexcel.saveToExcel(jTable3, columnType3, outputFile3, addSerial);
            String msg = "报表已保存到工作目录";
            JOptionPane.showMessageDialog(this, msg);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JFrameServiceCheck.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JFrameServiceCheck.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "表里没有数据！请先统计");
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
                String itemName = (String) m1.getValueAt(mr, 1);
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
        String itemName = "其他";
        Double s = Double.valueOf(sum1);
        Vector line = new Vector();
        line.add(itemName);
        line.add(s);
        itemData.add(line);
        PieServicePersonCommision PieChart = new PieServicePersonCommision(2);
        PieChart.createPieChart(itemData);

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "表里没有数据！请先统计");
            return;
        }
        Vector itemData = new Vector();
        TableModel m1 = jTable1.getModel();
        int r = m1.getRowCount();
        //求出项目合计
        double sum = 0;
        for (int i = 0; i < r; i++) {
            int mr = jTable1.convertRowIndexToModel(i);
            Double ratio = (Double) m1.getValueAt(mr, 12);
            sum = sum + ratio;
        }
        //读出每个项目
        double sum1 = 0;
        for (int i = 0; i < r; i++) {
            int mr = jTable1.convertRowIndexToModel(i);
            Object obj = m1.getValueAt(mr, 1);
            if (obj != null && !(obj.toString().trim().equals(""))) {
                String itemName = (String) m1.getValueAt(mr, 1);
                Double ratio = (Double) m1.getValueAt(mr, 12);
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
        PieServicePersonCommision PieChart = new PieServicePersonCommision(1);
        PieChart.createPieChart(itemData);


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        int selectedRow = jTable3.getSelectedRow();
        int mr = jTable3.convertRowIndexToModel(selectedRow);
        TableModel md = jTable3.getModel();
        // System.out.print("row="+selectedRow);
        Object name_O = md.getValueAt(mr, 1);
        String name = " ";
        if (name_O != null) {
            name = name_O.toString().trim();
        }

        Object date_O = md.getValueAt(mr, 2);
        String date = " ";
        if (date_O != null) {
            date = date_O.toString().trim();
        }
        new JFrameOutflowClientList(name, date).setVisible(true);
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int selectedRow = jTable2.getSelectedRow();
        int mr = jTable2.convertRowIndexToModel(selectedRow);
        TableModel md = jTable2.getModel();
        // System.out.print("row="+selectedRow);
        Object name_O = md.getValueAt(mr, 1);
        String name = " ";
        if (name_O != null) {
            name = name_O.toString().trim();
        }

        Object date_O = md.getValueAt(mr, 2);
        String date = " ";
        if (date_O != null) {
            date = date_O.toString().trim();
        }
        new JFrameNewClientList(name, date).setVisible(true);
    }//GEN-LAST:event_jTable2MouseClicked

    private void fetchReport(String year) {
        String sql = "";
        JDBTableModel dm1 = new JDBTableModel(jTable1);
        JDBTableModel dm2 = new JDBTableModel(jTable2);
        JDBTableModel dm3 = new JDBTableModel(jTable3);
        sql = "get_base_value " + year;
        System.out.println(sql);
        dm1.fetchDataToTable(Main.conn, sql, columnType1, addSerial);
        sql = "get_increment_value " + year;
        System.out.println(sql);
        dm2.fetchDataToTable(Main.conn, sql, columnType2, addSerial);
        sql = "get_decrement_value " + year;
        System.out.println(sql);
        dm3.fetchDataToTable(Main.conn, sql, columnType3, addSerial);
        if (jTable1.getRowCount() > 0) {
            hasData = true;
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
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBoxYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
