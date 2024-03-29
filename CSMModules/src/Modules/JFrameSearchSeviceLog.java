/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameSearchSeviceLog.java
 *
 * Created on 2010-5-28, 13:29:21
 */
package Modules;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author Administrator
 */
public class JFrameSearchSeviceLog extends javax.swing.JFrame {

    /** Creates new form JFrameSearchSeviceLog */
    public JFrameSearchSeviceLog() {
        initComponents();
        int iThisWidth = 760;
        int iThisHight = 600;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y, iThisWidth, iThisHight);
        jTextFieldStrtDate.setText(getDateString());
        jTextFieldEndDate.setText(getDateString());

        //注册键盘监听器，监听键盘动作，把系统无操作等待计时器置0;对非管理员屏蔽批量数据复制
        MainMenu.registerKeyListener(jTable1);
        //注册鼠标动作监听器，监听鼠标动作，把系统无操作等待计时器置0
        MainMenu.registerMouseListener(this);
        MainMenu.registerMouseListener(jTable1);
    }

    String getDateString() {
        String date = "1900";
        GregorianCalendar d = new GregorianCalendar();
        int thisYear = d.get(Calendar.YEAR);
        int thisMonth = d.get(Calendar.MONTH) + 1;
        int thisDay = d.get(Calendar.DAY_OF_MONTH);
        date = String.format("%4d%02d%02d", thisYear, thisMonth, thisDay);
        return date;
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldStrtDate = new javax.swing.JTextField();
        jTextFieldEndDate = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("查询服务信息修改日志");

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
        jTable1.setColumnSelectionAllowed(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

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
        jTable2.setColumnSelectionAllowed(true);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel3.setFont(new java.awt.Font("宋体", 0, 14));
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("点击日志记录可列出相关客户当前服务信息");

        jLabel4.setFont(new java.awt.Font("宋体", 0, 14));
        jLabel4.setText("修改前的客户资料");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        jLabel1.setText("起始日期");

        jTextFieldStrtDate.setText("jTextField1");
        jTextFieldStrtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStrtDateActionPerformed(evt);
            }
        });

        jTextFieldEndDate.setText("jTextField1");

        jLabel2.setText("终止日期");

        jButton1.setText("查询");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98)
                                .addComponent(jButton1)
                                .addGap(63, 63, 63)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldStrtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldStrtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(34, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(36, 36, 36))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldStrtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStrtDateActionPerformed
    }//GEN-LAST:event_jTextFieldStrtDateActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String startDate = jTextFieldStrtDate.getText();
        String endDate = jTextFieldEndDate.getText();
        if (!isValidDateString(startDate)) {
            JOptionPane.showMessageDialog(this, "起始日期输入错！");
            return;
        }
        if (!isValidDateString(endDate)) {
            JOptionPane.showMessageDialog(this, "终止日期输入错！");
            return;
        }
        int user_id = JFrameLogin.user_id;
        String sql = "execute search_service_log ";
        sql = sql + user_id + ",'" + startDate + "','" + endDate + "'";
        JDBTableModel dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        boolean addSerial = true;
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int selectedRow = jTable1.getSelectedRow();
        TableModel md = jTable1.getModel();
        // System.out.print("row="+selectedRow);
        Object fundAccount_O = md.getValueAt(selectedRow, 1);
        String fundAccount = " ";
        if (fundAccount_O != null) {
            fundAccount = fundAccount_O.toString();
        }
        long fund_account = 0;
        try {
            fund_account = Long.valueOf(fundAccount).longValue();
        } catch (NumberFormatException ne) {
            JOptionPane.showMessageDialog(this, "帐号数据格式错");
            return;
        }
        int user_id = JFrameLogin.user_id;
        String sql = "execute get_client_info ";
        sql = sql + user_id + "," + fund_account;
        JDBTableModel dm = new JDBTableModel(jTable2);
        Vector columnType = new Vector();
        boolean addSerial = true;
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
    }//GEN-LAST:event_jTable1MouseClicked

    private boolean isValidDateString(String dateStr) {
        boolean yes = true;
        GregorianCalendar d = new GregorianCalendar();

        if (dateStr.length() != 8) {
            yes = false;
        } else {
            String yyyy = dateStr.substring(0, 4);
            String mm = dateStr.substring(4, 6);
            String dd = dateStr.substring(6);
            int year = 0;
            int month = 0;
            int day = 0;
            try {
                year = Integer.valueOf(yyyy).intValue();
                month = Integer.valueOf(mm).intValue();
                day = Integer.valueOf(dd).intValue();
            //System.out.println("inputDate:" + year + month + day);
            } catch (NumberFormatException e) {
                yes = false;
            }
            int thisYear = d.get(Calendar.YEAR);
            int thisMonth = d.get(Calendar.MARCH) + 1;
            int thisDay = d.get(Calendar.DATE);
            //System.out.println("system daye:" + thisYear + thisMonth + thisDay);
            if (year > thisYear) {
                yes = false;
            } else {
                if (month > thisMonth) {
                    yes = false;
                } else {
                    if (day > thisDay) {
                        yes = false;
                    }
                }
            }
        }

        return yes;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldEndDate;
    private javax.swing.JTextField jTextFieldStrtDate;
    // End of variables declaration//GEN-END:variables
}

