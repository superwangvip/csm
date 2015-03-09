/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameNewSumAnalysis.java
 *
 * Created on 2012-6-23, 1:44:37
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

/**
 *
 * @author Administrator
 */
public class JFrameNewSumAnalysis extends javax.swing.JFrame {

    Vector columnType = new Vector();
    boolean addSerial = false;
    private boolean hasData = false;
    private boolean compare = false;
    private boolean compare1 = false;

    /** Creates new form JFrameNewSumAnalysis */
    public JFrameNewSumAnalysis() {
        initComponents();
        int iThisWidth = 1000;
        int iThisHight = 600;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y, iThisWidth, iThisHight);
        getDataYear();
        //注册键盘监听器，监听键盘动作，把系统无操作等待计时器置0;对非管理员屏蔽批量数据复制
        MainMenu.registerKeyListener(jTable1);
        //注册鼠标动作监听器，监听鼠标动作，把系统无操作等待计时器置0
        MainMenu.registerMouseListener(this);
        MainMenu.registerMouseListener(jTable1);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxYear = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("新业务佣金贡献分析");

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setFont(new java.awt.Font("宋体", 0, 14));
        jButton1.setText("客户价值统计");
        jButton1.setToolTipText("重新产生报表");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("宋体", 0, 14));
        jButton3.setText("保存为Excel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("宋体", 0, 14));
        jButton2.setText("取消");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "签约客户", "两融客户", "理财客户" }));

        jLabel1.setText("客户业务类型（客户价值统计需要此选项）");

        jLabel2.setFont(new java.awt.Font("宋体", 1, 14));
        jLabel2.setForeground(new java.awt.Color(51, 0, 255));
        jLabel2.setText("       ");

        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setText("备注：");

        jLabel4.setForeground(new java.awt.Color(51, 0, 204));
        jLabel4.setText("资产创佣比=累计佣金 *10000/资产总值,表示每万元客户资产为营业部累计创造的佣金");

        jLabel5.setForeground(new java.awt.Color(51, 0, 204));
        jLabel5.setText("累计成交额与累计佣金：每年一月份重新开始累计直至12月份");

        jLabel6.setText("统计年度（客户价值统计与创用对比统计需要此选项");

        jComboBoxYear.setFont(new java.awt.Font("宋体", 1, 14));
        jComboBoxYear.setForeground(new java.awt.Color(204, 0, 51));
        jComboBoxYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton4.setFont(new java.awt.Font("宋体", 0, 14));
        jButton4.setText("创佣对比统计");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("宋体", 0, 14));
        jButton5.setText("签约资产佣金贡献分析");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(51, 0, 255));
        jLabel7.setText("佣金贡献比%=100*(佣金/营业部佣金)*(周转率/营业部周转率),即佣金占比与周转率比的乘积");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxYear, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(186, 186, 186))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(350, 350, 350))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton4)
                                    .addComponent(jButton5)
                                    .addComponent(jButton2)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        fetchReport();
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "没有数据需要保存!");
            return;
        }
        String outputFile = "";
        String item = jComboBox1.getSelectedItem().toString().trim();
        String year = jComboBoxYear.getSelectedItem().toString();
        if (compare) {
            outputFile = "创佣对比统计--" + year + ".xls";
        } else if (compare1) {
            outputFile = "签约资产佣金贡献分析.xls";
        } else {
            outputFile = item + "价值汇总表--" + year + ".xls";
        }

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        fetchReport1();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        fetchReport2();
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    private void fetchReport() {
        String sql = "";
        String item = jComboBox1.getSelectedItem().toString().trim();
        String year = jComboBoxYear.getSelectedItem().toString();
        if (item.equals("签约客户")) {
            sql = "contract_clientvalue_report " + year;
            jLabel2.setText("签约客户价值汇总表--" + year);
        }
        if (item.equals("两融客户")) {
            sql = "credit_clientvalue_report " + year;
            jLabel2.setText("两融客户价值汇总表--" + year);
        }
        if (item.equals("理财客户")) {
            sql = "financing_clientvalue_report " + year;
            jLabel2.setText("理财客户价值汇总表--" + year);
        }

        JDBTableModel dm = new JDBTableModel(jTable1);
        System.out.println(sql);
        String msg = "正在统计......";
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial, msg);
        if (jTable1.getRowCount() > 1) {
            hasData = true;
            compare = false;
        }
    }

    private void fetchReport1() {
        String sql = "compare_report ";
        String year = jComboBoxYear.getSelectedItem().toString();
        sql = sql + year;
        jLabel2.setText("创佣对比统计--" + year);
        JDBTableModel dm = new JDBTableModel(jTable1);
        System.out.println(sql);
        String msg = "正在统计......";
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial, msg);
        hasData = true;
        if (jTable1.getRowCount() > 1) {
            hasData = true;
            compare = true;
            compare1 = false;
        }
    }

    private void fetchReport2() {
        String sql = "compare_report1 ";
        String year = jComboBoxYear.getSelectedItem().toString();
        jLabel2.setText("签约资产佣金贡献分析");
        JDBTableModel dm = new JDBTableModel(jTable1);
        System.out.println(sql);
        String msg = "正在统计......";
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial, msg);
        hasData = true;
        if (jTable1.getRowCount() > 1) {
            hasData = true;
            compare = false;
            compare1 = true;
        }
    }

    private void getDataYear() {
        try {
            String sql = "select distinct convert(char(4),update_time,112) from basehistory order by convert(char(4),update_time,112)";
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
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBoxYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
