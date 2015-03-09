/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameStockList.java
 *
 * Created on 2010-8-13, 22:44:05
 */
package Modules;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class JFrameStockList extends javax.swing.JFrame {
    String sh_stock = "show2003.dbf";
    String sz_stock = "sjshq.dbf";
    Vector sample = new Vector();
    Thread myThread1 = null;
    Thread myThread2 = null;
    Timer timer1 = null;
    Timer timer2 = null;
    int interval = 5;
    /** Creates new form JFrameStockList */
    public JFrameStockList() {
        initComponents();
        getFilePath("branch.ini");
        int iThisWidth = 1020;
        int iThisHight = 500;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y, iThisWidth, iThisHight);

        //getSample();
        jTextField1.setText("");
        jTextFieldFlushTime.setText(String.valueOf(interval));
        MainMenu.registerKeyListener(jTable1);
        //注册鼠标动作监听器，监听鼠标动作，把系统无操作等待计时器置0
        MainMenu.registerMouseListener(this);
        MainMenu.registerMouseListener(jTable1);
        MainMenu.TIMER_STEP = 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButtonRefresh = new javax.swing.JButton();
        jButtonShow = new javax.swing.JButton();
        jButtonRefreshPause = new javax.swing.JButton();
        jTextFieldFlushTime = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButtonShowPause = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("实时行情采集");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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

        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setText("觉得屏幕闪烁厉害，可以延长时间间隔； 要修改刷新时间间隔，先“暂停显示刷新”，再输入新的时间间隔秒数，然后后再重新启动“刷新显示股票池”,最后暂停并重启动“采集”");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jButton2.setText("取消");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.setText(" ");

        jButtonRefresh.setText("采集交易所行情");
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        jButtonShow.setText("刷新显示股票池");
        jButtonShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowActionPerformed(evt);
            }
        });

        jButtonRefreshPause.setText("暂停行情采集");
        jButtonRefreshPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshPauseActionPerformed(evt);
            }
        });

        jTextFieldFlushTime.setText("3");

        jLabel1.setText("刷新时间间隔(秒)");

        jButtonShowPause.setText("暂停显示刷新");
        jButtonShowPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowPauseActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel2.setForeground(new java.awt.Color(255, 51, 0));
        jLabel2.setText("交易所行情采集与股票池实时监控");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(jButtonRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButtonRefreshPause)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButtonShow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonShowPause)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(43, 43, 43)
                        .addComponent(jTextFieldFlushTime, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(143, 143, 143))
            .addGroup(layout.createSequentialGroup()
                .addGap(368, 368, 368)
                .addComponent(jLabel2)
                .addContainerGap(388, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRefresh)
                    .addComponent(jButtonRefreshPause)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldFlushTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButtonShowPause)
                    .addComponent(jButtonShow))
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (timer1 != null) {
            timer1.cancel();
        }
        if (timer2 != null) {
            timer2.cancel();
        }
        MainMenu.TIMER_STEP = 1;
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    private void getSample() {
        try {
            PreparedStatement SqlStatement = Main.conn.prepareStatement("select market+stock_code from stock_pool");
            Boolean HasResult = SqlStatement.execute();
            while (!HasResult) {
                HasResult = SqlStatement.getMoreResults();
            }
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    String stock_code = SqlResult.getString(1);
                    sample.add(stock_code);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFrameStockList.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void jButtonShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowActionPerformed
        showList();
}//GEN-LAST:event_jButtonShowActionPerformed

    private void showList() {
        String seconds = jTextFieldFlushTime.getText();
        if (seconds != null) {
            try {
                int time_interval = Integer.valueOf(seconds);
                interval = time_interval;
            } catch (NumberFormatException ex) {
                JOptionPane.showConfirmDialog(this, "刷新时间输入错");
                return;
            }
        }
        if (timer2 == null) {
            timer2 = new Timer();
        }
        ListShowTask task1 = new ListShowTask(jTable1, jTextField2);
        timer2.schedule(task1, 0, interval * 1000);

    }
    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        showList();
        updateList();
}//GEN-LAST:event_jButtonRefreshActionPerformed

    private void updateList() {
        String seconds = jTextFieldFlushTime.getText();
        if (seconds != null) {
            try {
                int time_interval = Integer.valueOf(seconds);
                interval = time_interval;
            } catch (NumberFormatException ex) {
                JOptionPane.showConfirmDialog(this, "刷新时间输入错");
                return;
            }
        }
        if (timer1 == null) {
            timer1 = new Timer();
        }
        timer1.schedule(new StockListTask(sh_stock, sz_stock, jTextField1, sample), 0, interval * 1000);

    }
    private void jButtonRefreshPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshPauseActionPerformed
        if (timer1 != null) {
            timer1.cancel();
            timer1 = null;
            jTextField1.setText("暂停行情采集");
        }
}//GEN-LAST:event_jButtonRefreshPauseActionPerformed

    private void jButtonShowPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowPauseActionPerformed
        if (timer2 != null) {
            timer2.cancel();
            timer2 = null;
            jTextField2.setText("暂停显示刷新");
        }
    }//GEN-LAST:event_jButtonShowPauseActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (timer1 != null) {
            timer1.cancel();
        }
        if (timer2 != null) {
            timer2.cancel();
        }
        MainMenu.TIMER_STEP = 1;
    }//GEN-LAST:event_formWindowClosing

    public void getFilePath(String filename) {
        try {
            FileReader fr = null;
            fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                if (line.startsWith("sh_stock")) {
                    int n = line.indexOf('=');
                    sh_stock = line.substring(n + 1);
                }
                if (line.startsWith("sz_stock")) {
                    int n = line.indexOf('=');
                    sz_stock = line.substring(n + 1);
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            Main.logger.warning(ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
        } catch (IOException ex) {
            Main.logger.warning(ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage());
        }
    }
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonRefreshPause;
    private javax.swing.JButton jButtonShow;
    private javax.swing.JButton jButtonShowPause;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextFieldFlushTime;
    // End of variables declaration//GEN-END:variables
}

class StockListTask extends TimerTask {

    String sh_stock;
    String sz_stock;
    static int times = 0;
    JTextField jTextField = null;
    Vector stock_sample = new Vector();

    public StockListTask(String sh_stock, String sz_stock, JTextField jTextField1, Vector sample) {
        this.sh_stock = sh_stock;
        this.sz_stock = sz_stock;
        jTextField = jTextField1;
        stock_sample = sample;
    }

    @Override
    public synchronized void run() {
        times++;
        jTextField.setText("读交易所行情:" + times);
        JDBF dbf = null;
        JDBF dbf1 = null;
        try {
            Statement SQL = Main.conn.createStatement();
            /*sh_stock*/
            dbf = new JDBF(sh_stock,null);
            for (int i = 0; i < dbf.header.getNumberOfRecords(); i++) {
                String market = "上海";
                String suspension = "正常";
                Object aobj[] = dbf.readRecord(i);//读取记录
                String stock_code = aobj[0].toString().trim();
                String stock_name = aobj[1].toString().trim();
                Double last_close_price = (Double) aobj[2];
                Double open_price = (Double) aobj[3];
                Double current_price = (Double) aobj[7];
                if (open_price == 0.0) {
                    suspension = "未开盘";
                }
                String SqlStatement = " update_stock_list '" + market + "','" + stock_code + "','" + stock_name + "'," + last_close_price + "," + open_price + "," + current_price + ",'" + suspension + "'";
                //System.out.println(SqlStatement);
                SQL.execute(SqlStatement);
            }
            dbf.close();

            /*sz_stock*/
            dbf = new JDBF(sz_stock,null);
            for (int i = 0; i < dbf.header.getNumberOfRecords(); i++) {
                String market = "深圳";
                String suspension = "正常";
                Object aobj[] = dbf.readRecord(i);//读取记录
                String stock_code = aobj[0].toString().trim();
                String stock_name = aobj[1].toString().trim();
                Double last_close_price = (Double) aobj[2];
                Double open_price = (Double) aobj[3];
                Double current_price = (Double) aobj[4];
                if (open_price == 0.0) {
                    suspension = "未开盘";
                }
                String SqlStatement = " update_stock_list '" + market + "','" + stock_code + "','" + stock_name + "'," + last_close_price + "," + open_price + "," + current_price + ",'" + suspension + "'";
                //System.out.println(SqlStatement);
                SQL.execute(SqlStatement);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFrameStockList.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning(ex.getLocalizedMessage()).setVisible(true);
        } catch (JDBFException ex) {
            Logger.getLogger(JFrameStockList.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning(ex.getLocalizedMessage()).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(JFrameStockList.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning(ex.getLocalizedMessage()).setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(JFrameStockList.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning(ex.getLocalizedMessage()).setVisible(true);
        }
    }
}

class ListShowTask extends TimerTask {

    JTable myTable;
    JTextField jTextFeild;
    int times = 0;

    public ListShowTask(JTable jTable, JTextField jTextFeild1) {
        myTable = jTable;
        jTextFeild = jTextFeild1;
    }

    @Override
    public synchronized void run() {
        Vector columnType = new Vector();
        boolean addSerial = true;
        JDBTableModel dm = new JDBTableModel(myTable);
        String sql = "get_stock_list " + JFrameLogin.user_id + ",'监控全部股票池的股票'";
        boolean isColorGrid = true;
        dm.fetchDataToTable(Main.conn, sql, columnType, addSerial, isColorGrid);
        times++;
        jTextFeild.setText("显示股票池:" + times);
        makeFace(myTable);
    }

    private synchronized void makeFace(JTable table) {
        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {

                @Override
                public Component getTableCellRendererComponent(JTable table,
                        Object value, boolean isSelected, boolean hasFocus,
                        int row, int column) {
                    if (row % 2 == 0) {
                        setBackground(Color.white); //设置奇数行底色
                        setForeground(Color.black);
                    } else if (row % 2 == 1) {
                        setBackground(new Color(204, 255, 255));  //设置偶数行底色
                        setForeground(Color.blue);
                    }
                    return super.getTableCellRendererComponent(table, value,
                            isSelected, hasFocus, row, column);
                }
            };
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
            }
        } catch (Exception ex) {
            Logger.getLogger(ListShowTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
