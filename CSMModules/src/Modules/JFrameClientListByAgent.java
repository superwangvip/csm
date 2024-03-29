/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameClientListByAgent.java
 *
 * Created on 2010-11-3, 14:54:18
 */
package Modules;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
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
public class JFrameClientListByAgent extends javax.swing.JFrame {

    private Vector columnType = new Vector();
    private boolean addSerial = true;
    private boolean hasData = false;
    String outPath = "out";//文件输出目录

    /** Creates new form JFrameClientListByAgent */
    public JFrameClientListByAgent() {
        initComponents();
        int iThisWidth = 1004;
        int iThisHight = 680;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y, iThisWidth, iThisHight);
        getAgentReport();
        //注册键盘监听器，监听键盘动作，把系统无操作等待计时器置0;对非管理员屏蔽批量数据复制
        MainMenu.registerKeyListener(jTable1);
        //注册鼠标动作监听器，监听鼠标动作，把系统无操作等待计时器置0
        MainMenu.registerMouseListener(this);
        MainMenu.registerMouseListener(jTable1);
    }

    private void getAgentReport() {
        String sql = "get_agent_report " + JFrameLogin.user_id;
        System.out.println(sql);
        JDBTableModel dm = new JDBTableModel(jTableAgentReport);
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        hasData = true;
        if (jTable1.getRowCount() > 1) {
            hasData = true;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableAgentReport = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("按经纪人统计查询客户资料");

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
        jTable1.setToolTipText("双击列头可按列排序");
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setFont(new java.awt.Font("宋体", 0, 18));
        jButton3.setText("取消");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("经纪人客户清单");

        jButton4.setFont(new java.awt.Font("宋体", 0, 18));
        jButton4.setText("保存为Excel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jTableAgentReport.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableAgentReport.setToolTipText("单击列头可按列排序，点击记录可列出客户清单");
        jTableAgentReport.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableAgentReport.setColumnSelectionAllowed(true);
        jTableAgentReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAgentReportMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableAgentReport);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jLabel2.setText("经纪人客户资料汇总");

        jLabel3.setForeground(new java.awt.Color(51, 0, 255));
        jLabel3.setText("点击记录可列出客户清单");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(540, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(39, 39, 39)
                .addComponent(jButton3)
                .addGap(222, 222, 222))
            .addGroup(layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addContainerGap(460, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(397, 397, 397)
                .addComponent(jLabel1)
                .addContainerGap(520, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (!hasData) {
            JOptionPane.showMessageDialog(this, "没有数据需要保存!");
            return;
        }
        int selectedRow = jTableAgentReport.getSelectedRow();
        int mr = jTableAgentReport.convertRowIndexToModel(selectedRow);
        TableModel md = jTableAgentReport.getModel();
        Object name_O = md.getValueAt(mr, 1);
        String name = " ";
        if (name_O != null) {
            name = name_O.toString().trim();
        }
        String outputFile = "经纪人_" + name + "的客户清单" + getDateString() + ".xls";
        pathExist(outPath);//检查并创建文件输出目录
        String outputFile1 = outPath + "\\" + outputFile;
        JTableToExcel tbexcel = new JTableToExcel();

        try {
            tbexcel.saveToExcel(jTable1, columnType, outputFile1, addSerial);
            String msg = outputFile1 + "已保存";
            JOptionPane.showMessageDialog(this, msg);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JFrameReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JFrameReport.class.getName()).log(Level.SEVERE, null, ex);
            Main.logger.warning(ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
        }
}//GEN-LAST:event_jButton4ActionPerformed

    private void jTableAgentReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAgentReportMouseClicked
        int selectedRow = jTableAgentReport.getSelectedRow();
        int mr = jTableAgentReport.convertRowIndexToModel(selectedRow);
        TableModel md = jTableAgentReport.getModel();
        Object name_O = md.getValueAt(mr, 1);
        String name = " ";
        if (name_O != null) {
            name = name_O.toString().trim();
        }
        String sql = "get_client_by_agent '" + name + "'," + JFrameLogin.user_id;
        System.out.println(sql);
        JDBTableModel dm = new JDBTableModel(jTable1);
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
        MainMenu.makeFace(jTable1);
    }//GEN-LAST:event_jTableAgentReportMouseClicked

    /**
     * @param args the command line arguments
     */
    private String getDateString() {
        String date = "1900";
        GregorianCalendar d = new GregorianCalendar();
        int thisYear = d.get(Calendar.YEAR);
        int thisMonth = d.get(Calendar.MONTH) + 1;
        int thisDay = d.get(Calendar.DAY_OF_MONTH);

        date = String.format("%4d%02d%02d", thisYear, thisMonth, thisDay);
        return date;
    }

    //检查并创建目录
    public void pathExist(String path) {
        File file = new File(path);
        //判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdir();
            System.out.println(path + "--目录已经建立");
        } else {
            System.out.println(path + "--目录已经存在");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableAgentReport;
    // End of variables declaration//GEN-END:variables
}
