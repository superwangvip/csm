/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameInformClient.java
 *
 * Created on 2010-8-24, 15:21:20
 */
package Modules;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
public class JFrameInformClient extends javax.swing.JFrame {

    /** Creates new form JFrameInformClient */
    public JFrameInformClient() {
        initComponents();
        int iThisWidth = 850;
        int iThisHight = 540;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y, iThisWidth, iThisHight);
        fetchMessageList();

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
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("荐股消息客户告知");

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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("专家荐股消息列表");

        jButton2.setText("产生QQ消息文本");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("产生短信文本");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("取消");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(109, 109, 109)
                                .addComponent(jButton2)
                                .addGap(96, 96, 96)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(146, 146, 146)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String messageFile = "message" + getDateString() + ".txt";
        File write = new File(messageFile);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(write));
            bw.write("尊敬的客户:\n" + Main.branchName + "投资顾问:" + JFrameLogin.user_name + "向您推荐信息如下：\n");
            String msgType = jTable1.getValueAt(0, 4).toString().trim();
            String stock_code = jTable1.getValueAt(0, 5).toString().trim();
            String stock_name = jTable1.getValueAt(0, 6).toString().trim();
            String advised_buying_price = jTable1.getValueAt(0, 7).toString().trim();
            String advised_object_price = jTable1.getValueAt(0, 8).toString().trim();
            String advised_selling_price = jTable1.getValueAt(0, 9).toString().trim();
            String memo = jTable1.getValueAt(0, 10).toString().trim();
            bw.write(msgType + ":" + stock_name + "(" + stock_code + ")," + "建议买价:" + advised_buying_price + ",建议目标价: " + advised_object_price + ",建议卖价:" + advised_selling_price + "\n");
            bw.write("备注:" + memo + "\n");
            bw.write("风险提示:股市有风险，入市需谨慎--本消息仅供投资者参考\n\n\n");
            bw.write("接收短信的客户手机号码如下:\n\n");
            int r = jTable1.getRowCount();
            int c = 0;
            for (int i = 0; i < r; i++) {
                String mobile = jTable1.getValueAt(i, 17).toString();
                if (!mobile.equals("") && mobile.length() == 11) {
                    bw.write(mobile + ";");
                    c++;
                    if (c % 10 == 0) {
                        bw.write("\n");
                    }
                }
            }
            bw.close();
            informClient("短信通知");
            JOptionPane.showMessageDialog(this, "短息文本" + messageFile + "已经保存到工作目录!");
        } catch (IOException ex) {
            Logger.getLogger(JFrameInformClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        fetchMessageList();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String messageFile = "QQmessage" + getDateString() + ".txt";
        File write = new File(messageFile);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(write));
            bw.write("尊敬的客户:\n" + Main.branchName + "投资顾问:" + JFrameLogin.user_name + "向您推荐信息如下：\n");
            String msgType = jTable1.getValueAt(0, 4).toString().trim();
            String stock_code = jTable1.getValueAt(0, 5).toString().trim();
            String stock_name = jTable1.getValueAt(0, 6).toString().trim();
            String advised_buying_price = jTable1.getValueAt(0, 7).toString().trim();
            String advised_object_price = jTable1.getValueAt(0, 8).toString().trim();
            String advised_selling_price = jTable1.getValueAt(0, 9).toString().trim();
            String memo = jTable1.getValueAt(0, 10).toString().trim();
            bw.write(msgType + ":" + stock_name + "(" + stock_code + ")," + "建议买价:" + advised_buying_price + ",建议目标价: " + advised_object_price + ",建议卖价:" + advised_selling_price + "\n");
            bw.write("备注:" + memo + "\n");
            bw.write("风险提示:股市有风险，入市需谨慎--本消息仅供投资者参考\n\n\n");
            bw.write("接消息的客户QQ号码如下:\n\n");
            int r = jTable1.getRowCount();
            int c = 0;
            for (int i = 0; i < r; i++) {
                String QQ = jTable1.getValueAt(i, 18).toString();
                if (!QQ.equals("") && !QQ.equals("0")) {
                    bw.write(QQ + ";");
                    c++;
                    if (c % 10 == 0) {
                        bw.write("\n");
                    }
                }
            }
            bw.close();
            informClient("QQ消息");
            JOptionPane.showMessageDialog(this, "QQ消息文本" + messageFile + "已经保存到工作目录!");
        } catch (IOException ex) {
            Logger.getLogger(JFrameInformClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        fetchMessageList();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    private void fetchMessageList() {

        //String message_time = getDateString();
        String sql = "get_message_list " + JFrameLogin.user_id;
        JDBTableModel dm = new JDBTableModel(jTable1);
        Vector columnType = new Vector();
        boolean addSerial = true;
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial);
    }

    private void informClient(String contact_way) {
        int returnStatus = 0;
        String returnMessage = "消息告知标志已保存";
        int r = jTable1.getRowCount();
        PreparedStatement SqlStatement = null;
        for (int i = 0; i < r; i++) {
            String sql = "execute inform_client ";
            String id = jTable1.getValueAt(i, 1).toString();
            String flag = jTable1.getValueAt(i, 13).toString().trim();
            if (!flag.equals("已告知客户")) {
                int message_id = Integer.valueOf(id);
                sql = sql + message_id + ",'已告知客户','" + contact_way + "','" + contact_way + "'";
                try {
                    SqlStatement = Main.conn.prepareStatement(sql);
                    Boolean HasResult = SqlStatement.execute();
                    while (!HasResult) {
                        HasResult = SqlStatement.getMoreResults();
                    }
                    if (HasResult) {
                        ResultSet SqlResult = SqlStatement.getResultSet();
                        while (SqlResult.next()) {
                            returnStatus = SqlResult.getInt("ret_status");
                            returnMessage = SqlResult.getString("ret_msg");//?????
                        }
                        SqlStatement.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(JFrameInformClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        JOptionPane.showMessageDialog(this, returnMessage);
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
