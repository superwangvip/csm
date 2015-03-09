/*
 * MainMenu.java
 *
 * Created on 2010-4-15, 16:12:09
 */
package Modules;

import java.awt.Color;
import java.awt.Component;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author Youquan Jiang
 */
public class MainMenu extends javax.swing.JFrame {

    private Vector groupMenu = new Vector();
    private Vector userMenu = new Vector();
    private Vector menuFlag = new Vector();
    int callback_message_cnt = 0;
    public static int NO_ACTION_TIMER = 0;
    public static int TIMER_STEP = 1;
    public static JTable table;
    static int credit_column = 16;
    static int repo_column = 17;
    static int contract_column = 14;

    public MainMenu() {
        initComponents();
        setWorkFlow();//设置工作流程
        checkMemo();//检查备忘录
        if (JFrameLogin.group_id > 0) {//系统管理员无条件操作所有菜单
            getMenuItems();//读取用户组与用户的权限菜单项
            setMenu();//设置菜单权限
        }

        int role = JFrameLogin.group_id;
        callback_message_cnt = getCallbackInDetails();//检查是否打开详细回访功能
        jPanel2.setVisible(false);
        jTextFieldUser.setText(JFrameLogin.user_name);
        jTextFieldRole.setText(JFrameLogin.group_name);
        int iThisWidth = 480;
        int iThisHight = 540;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y, iThisWidth, iThisHight);
        this.setTitle("客户服务管理系统V5.0  ");
        Image image = null;
        try {
            image = ImageIO.read(new File("hxzqIcon1.jpg"));
            this.setIconImage(image);
        } catch (IOException ex) {
            Main.logger.warning(ex.getLocalizedMessage());
            new JFrameWarning("IO异常: " + ex.getLocalizedMessage()).setVisible(true);
        }
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        setSysLock();
    }

    private void setSysLock() {
        //注册鼠标动作监听器，监听鼠标动作，把系统无操作等待计时器置0
        MainMenu.registerMouseListener(this);
        //启动检查操作动作的定时器
        SysLock task1 = new SysLock();
        Timer timer1 = new Timer(10000, task1);
        timer1.start();
    }

    public static void registerKeyListener(JTable table) {

        table.addKeyListener(new KeyListener() {

            public void keyPressed(KeyEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("keyPressed");
            }

            public void keyReleased(KeyEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("keyReleased");
            }

            public void keyTyped(KeyEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("keyTyped");
            }
        });

        table.addKeyListener(new myKeyListener(table));

    }

    public static class myKeyListener implements KeyListener {

        JTable table;

        public myKeyListener(JTable table) {
            this.table = table;
        }

        public void keyPressed(KeyEvent e) {
            if (e.isControlDown() && KeyEvent.VK_C == e.getKeyCode()) {
                System.out.println("e.getID=" + e.getID() + ",e.KEY_PRESSED= " + KeyEvent.KEY_PRESSED);//just for test
                System.out.println("Ctrl + c--KeyPressed");//just for test

                int c = table.getSelectedColumnCount();
                int r = table.getSelectedRowCount();
                if (r > 1 || c > 1) {
                    if (JFrameLogin.group_id > 1) {//非管理员只能复制单个单元
                        e.consume();
                    }
                }
            }
        }

        public void keyReleased(KeyEvent e) {
            System.out.println("e.getID=" + e.getID() + ", KeyEvent.KEY_RELEASED= " + KeyEvent.KEY_RELEASED);//just for test
            System.out.println("Ctrl + c--KeyReleased");//just for test
        }

        public void keyTyped(KeyEvent e) {
            System.out.println("e.getID=" + e.getID() + ",KeyEvent.KEY_TYPED= " + KeyEvent.KEY_TYPED);//just for test
            System.out.println("Ctrl + c--KeyTyped");//just for test
        }
    }

    public static void registerMouseListener(JFrame frame) {
        frame.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("JFrame:mouseEntered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("JFrame:mouseExited");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("JFrame:mousePressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("JFrame:mouseReleased");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("JFrame:mouseClicked");
            }
        });

        frame.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("JFrame:mouseDragged");
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("JFrame:mouseMoved");
            }
        });

    /*
    frame.addMouseWheelListener(new MouseWheelListener() {
    public void mouseWheelMoved(MouseWheelEvent e) {
    MainMenu.NO_ACTION_TIMER = 0;
    System.out.println("JFrame:mouseWheelMoved");
    }
    });*/
    }

    public static void registerMouseListener(JTable table) {
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("JTable:mouseClicked");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("JTable:mouseEntered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("JTable:mouseExited");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("JTable:mousePressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("JTable:mouseReleased");
            }
        });

        table.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("JTable:mouseDragged");
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                MainMenu.NO_ACTION_TIMER = 0;
                System.out.println("JTable:mouseMoved");
            }
        });
    /*
    table.addMouseWheelListener(new MouseWheelListener() {
    public void mouseWheelMoved(MouseWheelEvent e) {
    MainMenu.NO_ACTION_TIMER = 0;
    System.out.println("JTable:mouseWheelMoved");
    }
    });*/
    }

    void setWorkFlow() {
        PreparedStatement SqlStatement;
        try {
            SqlStatement = Main.conn.prepareStatement("select work_flow from work_flow_control");
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                String flow_control = "";
                while (SqlResult.next()) {
                    flow_control = SqlResult.getString(1);
                }
                Main.workFlow = flow_control;
            }
        } catch (SQLException ex) {
            Main.logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            new JFrameWarning("sql异常: " + ex.getLocalizedMessage()).setVisible(true);
        }
    }

    private void checkMemo() {
        PreparedStatement SqlStatement;
        String memo = "";
        try {
            SqlStatement = Main.conn.prepareStatement("execute check_service_memo ?");
            SqlStatement.setInt(1, JFrameLogin.user_id);
            Boolean HasResult = SqlStatement.execute();
            while (!HasResult) {
                HasResult = SqlStatement.getMoreResults();
            }
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();
                while (SqlResult.next()) {
                    memo = SqlResult.getString(1);
                }
            }
            if (!memo.equals("")) {
                JOptionPane.showMessageDialog(this, "今日备忘录：" + memo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //读出用户组与用户权限菜单项

    private void getMenuItems() {
        PreparedStatement SqlStatement;
        String menuName = "";
        String flag = "";
        try {
            String sql = "get_group_menu " + JFrameLogin.group_id;
            System.out.println(sql);
            SqlStatement = Main.conn.prepareStatement(sql);
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();

                while (SqlResult.next()) {
                    menuName = SqlResult.getString(5);
                    groupMenu.add(menuName);
                    System.out.println(menuName);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String sql = "get_user_menu " + JFrameLogin.user_id;
            System.out.println(sql);
            SqlStatement = Main.conn.prepareStatement(sql);
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();

                while (SqlResult.next()) {
                    menuName = SqlResult.getString(5);
                    flag = SqlResult.getString(6);
                    userMenu.add(menuName);
                    menuFlag.add(flag);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return;
    }
//设置菜单权限

    private void setMenu() {
        System.out.println("setMenu():");
        //1.
        jMenuSysControl.setEnabled(checkMenuEnabled("系统管理"));
        jMenuItemUserControl.setEnabled(checkMenuEnabled("用户管理"));
        jMenuItemRoleControl.setEnabled(checkMenuEnabled("角色管理"));
        jMenuItemUserPermision.setEnabled(checkMenuEnabled("用户授权"));
        jMenuItemRolePermision.setEnabled(checkMenuEnabled("角色授权"));
        jMenuItemDict.setEnabled(checkMenuEnabled("数据字典"));
        jMenuItemInvestmentAdviser.setEnabled(checkMenuEnabled("投顾管理"));
        jMenuItemMasterControl.setEnabled(checkMenuEnabled("主管设置"));
        jMenuItemMarketMatchAmount.setEnabled(checkMenuEnabled("录入修改市场成交总量"));
        jMenuItemStockList.setEnabled(checkMenuEnabled("行情实时采集"));
        jMenuItemSetSysParameters.setEnabled(checkMenuEnabled("设置系统功能选项参数"));
        jMenuItemSetWorkFlow.setEnabled(checkMenuEnabled("设置工作流程"));

        //2.
        jMenuServiceManagement.setEnabled(checkMenuEnabled("服务管理"));
        jMenuItemAssignCallback.setEnabled(checkMenuEnabled("回访安排"));
        jMenuItemAssignService.setEnabled(checkMenuEnabled("服务安排"));
        jMenuItemAutoAssign.setEnabled(checkMenuEnabled("自动分配客户"));
        jMenuItemCallback.setEnabled(checkMenuEnabled("客户回访"));
        jMenuItemService.setEnabled(checkMenuEnabled("客户服务"));
        jMenuItemFindCallback.setEnabled(checkMenuEnabled("查询回访安排"));
        jMenuItemFindAssignService.setEnabled(checkMenuEnabled("查询服务安排"));
        jMenuItemsearchServiceLog.setEnabled(checkMenuEnabled("查询服务信息修改日志"));
        jMenuInvestmenAdvisory.setEnabled(checkMenuEnabled("投资顾问业务"));
        jMenuItemContractMagement.setEnabled(checkMenuEnabled("客户签约管理"));
        jMenuItemSumContractedClient.setEnabled(checkMenuEnabled("签约客户统计查询"));
        jMenuItemStockPool1.setEnabled(checkMenuEnabled("股票池管理"));
        jMenuItemjMenuItemMessageManagement.setEnabled(checkMenuEnabled("专家荐股消息管理"));

        jMenuItemInform.setEnabled(checkMenuEnabled("荐股消息客户告知"));
        jMenuItemClearMessage.setEnabled(checkMenuEnabled("荐股消息清理"));
        jMenuItemRealMontitor.setEnabled(checkMenuEnabled("签约客户资产监控"));
        jMenuItemAssetMonitor.setEnabled(checkMenuEnabled("一般客户股票盈亏监控表"));
        jMenuItemFindStockBalance.setEnabled(checkMenuEnabled("查询客户证券资产(来自交易系统)"));
        jMenuItemMarginTradingStokAsset.setEnabled(checkMenuEnabled("查询两融客户证券资产"));

        jMenuItemSumMatch2.setEnabled(checkMenuEnabled("投顾-营业部成交汇总对比"));
        jMenuItemSumMatch.setEnabled(checkMenuEnabled("投顾推荐股票成交汇总及明细查询"));
        jMenuItemClientMatchDetails.setEnabled(checkMenuEnabled("查询客户历史成交明细"));
        jMenuItemFindOrder.setEnabled(checkMenuEnabled("查询客户当日委托"));

        jMenuItemGrantServiceRelation.setEnabled(checkMenuEnabled("服务关系转授"));
        jMenuItemAssignLog.setEnabled(checkMenuEnabled("查询服务安排日志"));
        jMenuItemCallbackInDetails.setEnabled(checkMenuEnabled("客户详细回访"));
        jMenuItemSearchByCallbackDetails.setEnabled(checkMenuEnabled("按详细回访信息组合查询客户"));

        //3.
        jMenuClientSearch.setEnabled(checkMenuEnabled("客户查询"));
        jMenuItemAccurateSearch.setEnabled(checkMenuEnabled("按帐号精确查询"));
        jMenuItemClientByTel.setEnabled(checkMenuEnabled("按电话查询客户"));
        jMenuItemFuzzySearch.setEnabled(checkMenuEnabled("按姓氏模糊查询"));
        jMenuItemAssetSearch.setEnabled(checkMenuEnabled("按资产模糊查询"));
        jMenuSearchByAge.setEnabled(checkMenuEnabled("按年龄模糊查询"));
        jMenuItemSearchByServicePerson.setEnabled(checkMenuEnabled("按服务员统计查询"));
        jMenuItemSearchBySatisfaction.setEnabled(checkMenuEnabled("按客户满意度查询"));
        jMenuItemClientNoTel.setEnabled(checkMenuEnabled("查无电话或电话空号客户清单"));
        jMenuItemSearchBySummary.setEnabled(checkMenuEnabled("按回访摘要分类查询客户清单"));
        jMenuItemSearchByCallbackDate.setEnabled(checkMenuEnabled("按回访日期分类查询客户清单"));
        jMenuItemClientListByAgent.setEnabled(checkMenuEnabled("按经纪人统计查询客户"));
        jMenuItemClientListByBirthday.setEnabled(checkMenuEnabled("按生日查询客户清单"));
        jMenuAlalystReport.setEnabled(checkMenuEnabled("按分析师统计查询客户清单"));
        jMenuItemContractInfo.setEnabled(checkMenuEnabled("查询客户签约信息"));
        jMenuItemCreditClient.setEnabled(checkMenuEnabled("查询开通融资融券的客户"));
        jMenuItemLostClient.setEnabled(checkMenuEnabled("查询隐性流失客户清单"));
        jMenuItemRepoDetails.setEnabled(checkMenuEnabled("查询回购交易明细"));
        jMenuItemRepoDetails.setEnabled(checkMenuEnabled("按电子签约日期查询客户清单"));
        //4.
        jMenuStatics.setEnabled(checkMenuEnabled("统计分析"));
        jMenuItemAssetStatics.setEnabled(checkMenuEnabled("客户资产分布统计"));
        jMenuItemTotalAsset.setEnabled(checkMenuEnabled("客户总资产统计"));
        jMenuItemSatifactionDegree.setEnabled(checkMenuEnabled("客户满意度统计"));
        jMenuItemWorkReport.setEnabled(checkMenuEnabled("回访与服务安排统计"));
        jMenuItemAgentReport.setEnabled(checkMenuEnabled("经纪人客户资产佣金统计"));
        jMenuItemServicePersonClientAsset.setEnabled(checkMenuEnabled("服务员客户资产佣金统计"));
        jMenuItemCommisionDistribution.setEnabled(checkMenuEnabled("客户佣金分布统计"));
        jMenuItemServiceCheck.setEnabled(checkMenuEnabled("服务考核统计表"));
        jMenuItemSumClientValue.setEnabled(checkMenuEnabled("客户价值汇总表"));
        jMenuItemServiceCheckReport.setEnabled(checkMenuEnabled("服务考核报表一"));
        jMenuItemAgentMonthReport.setEnabled(checkMenuEnabled("经纪人业绩统计"));
        jMenuItemAgentMonthReport1.setEnabled(checkMenuEnabled("经纪人业绩统计一"));
        jMenuProfitAnalysis.setEnabled(checkMenuEnabled("客户盈亏分析"));
        jMenuItemProfitAnalysis.setEnabled(checkMenuEnabled("买卖盈亏统计"));
        jMenuItemAssetIncrement.setEnabled(checkMenuEnabled("资产增值统计"));
        jMenuItemSumProfit.setEnabled(checkMenuEnabled("盈亏汇总分析"));
        jMenuItemFloatProfit.setEnabled(checkMenuEnabled("浮动盈亏分析"));
        jMenuItemClientProfit.setEnabled(checkMenuEnabled("客户盈亏查询"));
        jMenuItemSumAppreciation.setEnabled(checkMenuEnabled("按投顾汇总客户盈亏"));
        jMenuItemNewSumAnalysis.setEnabled(checkMenuEnabled("新业务客户价值汇总分析"));
        //5.
        jMenuFinanceProduct.setEnabled(checkMenuEnabled("理财产品"));
        jMenuItemProductInfo.setEnabled(checkMenuEnabled("产品信息维护"));
        jMenuItemProductInfoQuery.setEnabled(checkMenuEnabled("产品信息查询"));
        jMenuItemProductClientQuery.setEnabled(checkMenuEnabled("产品-客户查询"));
        jMenuItemClientProductQuery.setEnabled(checkMenuEnabled("客户-产品查询"));
        jMenuItemproductTradingQuery.setEnabled(checkMenuEnabled("产品-交易查询"));
        jMenuItemClientTradingQuery.setEnabled(checkMenuEnabled("客户-交易查询"));
        jMenuItemMultiQuery.setEnabled(checkMenuEnabled("综合查询"));
        jMenuItemMultiSum.setEnabled(checkMenuEnabled("综合统计"));
        //6.
        jMenuDataConverter.setEnabled(checkMenuEnabled("数据维护"));
    }
    //检查菜单项权限

    private boolean checkMenuEnabled(String menuName) {
        boolean enabled = false;
        enabled = groupMenu.contains(menuName);
        System.out.println("group:" + menuName + ":" + enabled);
        if (enabled) {
            enabled = userMenu.contains(menuName);
            System.out.println("user1:" + menuName + ":" + enabled);
            if (enabled) {
                String flag = menuFlag.get(userMenu.indexOf(menuName)).toString();
                if (flag.equals("禁止")) {
                    System.out.println("user2:" + menuName + ":" + flag);
                    enabled = false;
                }
            } else {
                enabled = true;
            }
        } else {
            enabled = userMenu.contains(menuName);
            System.out.println("user1:" + menuName + ":" + enabled);
            if (enabled) {
                String flag = menuFlag.get(userMenu.indexOf(menuName)).toString();
                if (flag.equals("禁止")) {
                    System.out.println("user2:" + menuName + ":" + flag);
                    enabled = false;
                }
            }
        }
        System.out.println("user3:" + menuName + ":" + enabled);
        return enabled;
    }

    private int getCallbackInDetails() {
        PreparedStatement SqlStatement;
        int callback_message_count = 0;
        try {
            SqlStatement = Main.conn.prepareStatement("select count(*) from sysobjects where name='callback_message'");
            Boolean HasResult = SqlStatement.execute();
            if (HasResult) {
                ResultSet SqlResult = SqlStatement.getResultSet();

                while (SqlResult.next()) {
                    callback_message_count = SqlResult.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return callback_message_count;
    }
/*
    class SysLock implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (NO_ACTION_TIMER >= Main.MAX_IDLE_TIME) {
                new JFrameKeyboardLock().setVisible(true);
            }
            if (JFrameKeyboardLock.sysLocked) {
                NO_ACTION_TIMER = 0;
            } else {
                NO_ACTION_TIMER = NO_ACTION_TIMER + TIMER_STEP;
            }
            System.out.println(NO_ACTION_TIMER);
        }
    }*/

    public static void makeFace(JTable table) {
        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {

                @Override
                public Component getTableCellRendererComponent(JTable table,
                        Object value, boolean isSelected, boolean hasFocus,
                        int row, int column) {
                    TableModel m = table.getModel();

                    for (int i = 0; i < row + 1; i++) {

                        String contract = "";
                        String credit = "";
                        String repo_mark = "";
                        int mr = table.convertRowIndexToModel(i);
                        Object credit_O = m.getValueAt(mr, credit_column);

                        if (credit_O != null) {
                            credit = credit_O.toString().trim();
                        }
                        Object contract_O = m.getValueAt(mr, contract_column);
                        if (contract_O != null) {
                            contract = contract_O.toString().trim();
                        }
                        Object repo_O = m.getValueAt(mr, repo_column);
                        if (repo_O != null) {
                            repo_mark = repo_O.toString().trim();
                        }

                        if (contract.equals("签约")) {//签约客户以特浅蓝底深蓝字显示
                            setBackground(new Color(204, 255, 255));
                            setForeground(Color.blue);
                        } else if (credit.equals("已开通")) { //两融客户以特浅蓝底红色字显示
                            setBackground(new Color(204, 255, 255));
                            setForeground(Color.RED);
                        } else if (repo_mark.equals("开通质押回购")) {//开通回购的客户以红底篮字显示
                            setBackground(Color.PINK);
                            setForeground(Color.BLUE);
                        } else {
                            setForeground(Color.BLACK);
                            setBackground(Color.WHITE);
                        }
                    }
                    return super.getTableCellRendererComponent(table, value,
                            isSelected, hasFocus, row, column);
                }
            };
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
            }
        } catch (Exception ex) {
            Logger.getLogger(JFrameRealtimeMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabelProgress = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        jTextFieldRole = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuSysControl = new javax.swing.JMenu();
        jMenuItemUserControl = new javax.swing.JMenuItem();
        jMenuItemUserPermision = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuItemRoleControl = new javax.swing.JMenuItem();
        jMenuItemRolePermision = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JSeparator();
        jMenuItemDict = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JSeparator();
        jMenuItemInvestmentAdviser = new javax.swing.JMenuItem();
        jMenuItemMasterControl = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JSeparator();
        jMenuItemMarketMatchAmount = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JSeparator();
        jMenuItemStockList = new javax.swing.JMenuItem();
        jMenuItemSetSysParameters = new javax.swing.JMenuItem();
        jMenuItemSetWorkFlow = new javax.swing.JMenuItem();
        jMenuServiceManagement = new javax.swing.JMenu();
        jMenuItemAssignCallback = new javax.swing.JMenuItem();
        jMenuItemAssignService = new javax.swing.JMenuItem();
        jMenuItemAutoAssign = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItemCallback = new javax.swing.JMenuItem();
        jMenuItemService = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JSeparator();
        jMenuItemFindCallback = new javax.swing.JMenuItem();
        jMenuItemFindAssignService = new javax.swing.JMenuItem();
        jMenuItemsearchServiceLog = new javax.swing.JMenuItem();
        jSeparator18 = new javax.swing.JSeparator();
        jMenuInvestmenAdvisory = new javax.swing.JMenu();
        jMenuItemContractMagement = new javax.swing.JMenuItem();
        jMenuItemSumContractedClient = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JSeparator();
        jMenuItemStockPool1 = new javax.swing.JMenuItem();
        jMenuItemjMenuItemMessageManagement = new javax.swing.JMenuItem();
        jMenuItemInform = new javax.swing.JMenuItem();
        jMenuItemClearMessage = new javax.swing.JMenuItem();
        jMenuItemRealMontitor = new javax.swing.JMenuItem();
        jSeparator20 = new javax.swing.JSeparator();
        jMenuItemAssetMonitor = new javax.swing.JMenuItem();
        jMenuItemFindStockBalance = new javax.swing.JMenuItem();
        jMenuItemMarginTradingStokAsset = new javax.swing.JMenuItem();
        jSeparator28 = new javax.swing.JSeparator();
        jMenuItemSumMatch2 = new javax.swing.JMenuItem();
        jMenuItemSumMatch = new javax.swing.JMenuItem();
        jSeparator34 = new javax.swing.JSeparator();
        jMenuItemClientMatchDetails = new javax.swing.JMenuItem();
        jMenuItemFindOrder = new javax.swing.JMenuItem();
        jSeparator23 = new javax.swing.JSeparator();
        jMenuItemGrantServiceRelation = new javax.swing.JMenuItem();
        jMenuItemAssignLog = new javax.swing.JMenuItem();
        jMenuItemCallbackInDetails = new javax.swing.JMenuItem();
        jMenuItemSearchByCallbackDetails = new javax.swing.JMenuItem();
        jMenuClientSearch = new javax.swing.JMenu();
        jMenuItemAccurateSearch = new javax.swing.JMenuItem();
        jMenuItemClientByTel = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuItemFuzzySearch = new javax.swing.JMenuItem();
        jMenuItemAssetSearch = new javax.swing.JMenuItem();
        jMenuSearchByAge = new javax.swing.JMenuItem();
        jMenuItemSearchByServicePerson = new javax.swing.JMenuItem();
        jMenuItemSearchBySatisfaction = new javax.swing.JMenuItem();
        jMenuItemClientNoTel = new javax.swing.JMenuItem();
        jMenuItemSearchBySummary = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JSeparator();
        jMenuItemSearchByCallbackDate = new javax.swing.JMenuItem();
        jMenuItemClientListByAgent = new javax.swing.JMenuItem();
        jMenuItemClientListByBirthday = new javax.swing.JMenuItem();
        jMenuAlalystReport = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JSeparator();
        jMenuItemCreditClient = new javax.swing.JMenuItem();
        jSeparator22 = new javax.swing.JSeparator();
        jMenuItemLostClient = new javax.swing.JMenuItem();
        jSeparator36 = new javax.swing.JSeparator();
        jMenuItemRepoDetails = new javax.swing.JMenuItem();
        clientList_byEsignDate = new javax.swing.JMenuItem();
        jMenuStatics = new javax.swing.JMenu();
        jMenuItemAssetStatics = new javax.swing.JMenuItem();
        jMenuItemTotalAsset = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        jMenuItemSatifactionDegree = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JSeparator();
        jMenuItemWorkReport = new javax.swing.JMenuItem();
        jMenuItemAgentReport = new javax.swing.JMenuItem();
        jMenuItemServicePersonClientAsset = new javax.swing.JMenuItem();
        jMenuItemCommisionDistribution = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JSeparator();
        jMenuItemServiceCheck = new javax.swing.JMenuItem();
        jMenuItemSumClientValue = new javax.swing.JMenuItem();
        jMenuItemServiceCheckReport = new javax.swing.JMenuItem();
        jMenuItemAgentMonthReport = new javax.swing.JMenuItem();
        jMenuItemAgentMonthReport1 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JSeparator();
        jMenuProfitAnalysis = new javax.swing.JMenu();
        jMenuItemProfitAnalysis = new javax.swing.JMenuItem();
        jMenuItemAssetIncrement = new javax.swing.JMenuItem();
        jMenuItemSumProfit = new javax.swing.JMenuItem();
        jMenuItemClientProfit = new javax.swing.JMenuItem();
        jMenuItemFloatProfit = new javax.swing.JMenuItem();
        jSeparator29 = new javax.swing.JSeparator();
        jMenuItemSumAppreciation = new javax.swing.JMenuItem();
        jSeparator33 = new javax.swing.JSeparator();
        jMenuItemNewSumAnalysis = new javax.swing.JMenuItem();
        jMenuItemContractInfo = new javax.swing.JMenuItem();
        jMenuFinanceProduct = new javax.swing.JMenu();
        jMenuItemProductInfo = new javax.swing.JMenuItem();
        jMenuItemProductInfoQuery = new javax.swing.JMenuItem();
        jSeparator38 = new javax.swing.JSeparator();
        jMenuItemProductClientQuery = new javax.swing.JMenuItem();
        jMenuItemClientProductQuery = new javax.swing.JMenuItem();
        jSeparator39 = new javax.swing.JSeparator();
        jMenuItemproductTradingQuery = new javax.swing.JMenuItem();
        jMenuItemClientTradingQuery = new javax.swing.JMenuItem();
        jSeparator40 = new javax.swing.JSeparator();
        jMenuItemMultiQuery = new javax.swing.JMenuItem();
        jMenuItemMultiSum = new javax.swing.JMenuItem();
        jMenuDataConverter = new javax.swing.JMenu();
        jMenuBasicData = new javax.swing.JMenu();
        jMenuItemZjdbf = new javax.swing.JMenuItem();
        jMenuItemZcdbf = new javax.swing.JMenuItem();
        jMenuItemFundBalance = new javax.swing.JMenuItem();
        jMenuItemCjdbf = new javax.swing.JMenuItem();
        jMenuItem1UdateClientInfo = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JSeparator();
        jMenuItemBackup = new javax.swing.JMenuItem();
        jMenuItemRestoreClient = new javax.swing.JMenuItem();
        jMenuItemRestoreFromDBF = new javax.swing.JMenuItem();
        jMenuAdditionData = new javax.swing.JMenu();
        jMenuItemClientTelDBF = new javax.swing.JMenuItem();
        jMenuItemClientQQ = new javax.swing.JMenuItem();
        jMenuItemIdCard = new javax.swing.JMenuItem();
        jMenuItemClientAgent = new javax.swing.JMenuItem();
        jSeparator19 = new javax.swing.JSeparator();
        jMenuItemIportServicePersons = new javax.swing.JMenuItem();
        jSeparator25 = new javax.swing.JSeparator();
        jMenuItemIputStockPool = new javax.swing.JMenuItem();
        jSeparator32 = new javax.swing.JSeparator();
        jMenuItemDBFUser = new javax.swing.JMenuItem();
        jSeparator21 = new javax.swing.JSeparator();
        jMenuServiceCheck = new javax.swing.JMenu();
        jMenuItemMonthEndAsset = new javax.swing.JMenuItem();
        jMenuItemCancelLog = new javax.swing.JMenuItem();
        jMenuItemTransfer = new javax.swing.JMenuItem();
        jSeparator24 = new javax.swing.JSeparator();
        jMenuItemAssetArchive = new javax.swing.JMenuItem();
        jMenuItemCalculte = new javax.swing.JMenuItem();
        jSeparator30 = new javax.swing.JSeparator();
        jMenuItemMatchHhistory = new javax.swing.JMenuItem();
        jMenuItemDataCheck = new javax.swing.JMenuItem();
        jMenuIteInvestmentAdvice = new javax.swing.JMenu();
        jMenuItemContractClientTradingDetails = new javax.swing.JMenuItem();
        jMenuItemsettlement = new javax.swing.JMenuItem();
        jSeparator26 = new javax.swing.JSeparator();
        jMenuItemStockAsset = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItemOrderData = new javax.swing.JMenuItem();
        jSeparator27 = new javax.swing.JSeparator();
        jMenuItemProfitCalculate = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JSeparator();
        jMenuItemCreditClientInfo = new javax.swing.JMenuItem();
        jMenuItemFinancing = new javax.swing.JMenuItem();
        jMenuItemContractData = new javax.swing.JMenuItem();
        jSeparator35 = new javax.swing.JSeparator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator37 = new javax.swing.JSeparator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator31 = new javax.swing.JSeparator();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItemSQL = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("客户服务管理系统V4.30");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("华文中宋", 0, 18));
        setLocationByPlatform(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabelProgress.setFont(new java.awt.Font("宋体", 0, 18));
        jLabelProgress.setForeground(new java.awt.Color(51, 0, 255));
        jLabelProgress.setText("jLabel2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelProgress, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelProgress)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("宋体", 0, 14));
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("操作员");

        jLabel3.setFont(new java.awt.Font("宋体", 0, 14));
        jLabel3.setForeground(new java.awt.Color(0, 0, 153));
        jLabel3.setText("角色");

        jTextFieldUser.setEditable(false);
        jTextFieldUser.setFont(new java.awt.Font("宋体", 0, 14));
        jTextFieldUser.setForeground(new java.awt.Color(0, 0, 153));
        jTextFieldUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUserActionPerformed(evt);
            }
        });

        jTextFieldRole.setEditable(false);
        jTextFieldRole.setFont(new java.awt.Font("宋体", 0, 14));
        jTextFieldRole.setForeground(new java.awt.Color(0, 0, 153));
        jTextFieldRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRoleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldUser, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                        .addGap(71, 71, 71)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldRole, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Modules/hxzqIcon.jpg"))); // NOI18N
        jLabel4.setText(" ");
        jLabel4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel4MouseDragged(evt);
            }
        });

        jMenuSysControl.setText("系统管理");
        jMenuSysControl.setFont(new java.awt.Font("宋体", 0, 14));

        jMenuItemUserControl.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemUserControl.setText("用户管理");
        jMenuItemUserControl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUserControlActionPerformed(evt);
            }
        });
        jMenuSysControl.add(jMenuItemUserControl);

        jMenuItemUserPermision.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemUserPermision.setText("用户授权");
        jMenuItemUserPermision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUserPermisionActionPerformed(evt);
            }
        });
        jMenuSysControl.add(jMenuItemUserPermision);
        jMenuSysControl.add(jSeparator2);

        jMenuItemRoleControl.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemRoleControl.setText("角色管理");
        jMenuItemRoleControl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRoleControlActionPerformed(evt);
            }
        });
        jMenuSysControl.add(jMenuItemRoleControl);

        jMenuItemRolePermision.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemRolePermision.setText("角色授权");
        jMenuItemRolePermision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRolePermisionActionPerformed(evt);
            }
        });
        jMenuSysControl.add(jMenuItemRolePermision);
        jMenuSysControl.add(jSeparator9);

        jMenuItemDict.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemDict.setText("数据字典");
        jMenuItemDict.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDictActionPerformed(evt);
            }
        });
        jMenuSysControl.add(jMenuItemDict);
        jMenuSysControl.add(jSeparator14);

        jMenuItemInvestmentAdviser.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemInvestmentAdviser.setText("投顾管理");
        jMenuItemInvestmentAdviser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInvestmentAdviserActionPerformed(evt);
            }
        });
        jMenuSysControl.add(jMenuItemInvestmentAdviser);

        jMenuItemMasterControl.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemMasterControl.setText("主管设置");
        jMenuItemMasterControl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMasterControlActionPerformed(evt);
            }
        });
        jMenuSysControl.add(jMenuItemMasterControl);
        jMenuSysControl.add(jSeparator17);

        jMenuItemMarketMatchAmount.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemMarketMatchAmount.setText("录入修改市场成交总量");
        jMenuItemMarketMatchAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMarketMatchAmountActionPerformed(evt);
            }
        });
        jMenuSysControl.add(jMenuItemMarketMatchAmount);
        jMenuSysControl.add(jSeparator16);

        jMenuItemStockList.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemStockList.setText("行情实时采集");
        jMenuItemStockList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemStockListActionPerformed(evt);
            }
        });
        jMenuSysControl.add(jMenuItemStockList);

        jMenuItemSetSysParameters.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemSetSysParameters.setText("设置系统功能选项参数");
        jMenuItemSetSysParameters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSetSysParametersActionPerformed(evt);
            }
        });
        jMenuSysControl.add(jMenuItemSetSysParameters);

        jMenuItemSetWorkFlow.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemSetWorkFlow.setText("设置工作流程");
        jMenuItemSetWorkFlow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSetWorkFlowActionPerformed(evt);
            }
        });
        jMenuSysControl.add(jMenuItemSetWorkFlow);

        jMenuBar1.add(jMenuSysControl);

        jMenuServiceManagement.setText("服务管理");
        jMenuServiceManagement.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuServiceManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuServiceManagementActionPerformed(evt);
            }
        });

        jMenuItemAssignCallback.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemAssignCallback.setText("回访安排");
        jMenuItemAssignCallback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAssignCallbackActionPerformed(evt);
            }
        });
        jMenuServiceManagement.add(jMenuItemAssignCallback);

        jMenuItemAssignService.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemAssignService.setText("服务安排");
        jMenuItemAssignService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAssignServiceActionPerformed(evt);
            }
        });
        jMenuServiceManagement.add(jMenuItemAssignService);

        jMenuItemAutoAssign.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemAutoAssign.setText("自动分配客户");
        jMenuItemAutoAssign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAutoAssignActionPerformed(evt);
            }
        });
        jMenuServiceManagement.add(jMenuItemAutoAssign);
        jMenuServiceManagement.add(jSeparator1);

        jMenuItemCallback.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemCallback.setText("客户回访");
        jMenuItemCallback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCallbackActionPerformed(evt);
            }
        });
        jMenuServiceManagement.add(jMenuItemCallback);

        jMenuItemService.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemService.setText("客户服务");
        jMenuItemService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemServiceActionPerformed(evt);
            }
        });
        jMenuServiceManagement.add(jMenuItemService);
        jMenuServiceManagement.add(jSeparator10);

        jMenuItemFindCallback.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemFindCallback.setText("查询回访安排");
        jMenuItemFindCallback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFindCallbackActionPerformed(evt);
            }
        });
        jMenuServiceManagement.add(jMenuItemFindCallback);

        jMenuItemFindAssignService.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemFindAssignService.setText("查询服务安排");
        jMenuItemFindAssignService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFindAssignServiceActionPerformed(evt);
            }
        });
        jMenuServiceManagement.add(jMenuItemFindAssignService);

        jMenuItemsearchServiceLog.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemsearchServiceLog.setText("查询服务信息修改日志");
        jMenuItemsearchServiceLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemsearchServiceLogActionPerformed(evt);
            }
        });
        jMenuServiceManagement.add(jMenuItemsearchServiceLog);
        jMenuServiceManagement.add(jSeparator18);

        jMenuInvestmenAdvisory.setText("投资顾问业务");
        jMenuInvestmenAdvisory.setFont(new java.awt.Font("宋体", 0, 14));

        jMenuItemContractMagement.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemContractMagement.setText("客户签约管理");
        jMenuItemContractMagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContractMagementActionPerformed(evt);
            }
        });
        jMenuInvestmenAdvisory.add(jMenuItemContractMagement);

        jMenuItemSumContractedClient.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemSumContractedClient.setText("签约客户统计查询");
        jMenuItemSumContractedClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSumContractedClientActionPerformed(evt);
            }
        });
        jMenuInvestmenAdvisory.add(jMenuItemSumContractedClient);
        jMenuInvestmenAdvisory.add(jSeparator13);

        jMenuItemStockPool1.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemStockPool1.setText("股票池管理");
        jMenuItemStockPool1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemStockPool1ActionPerformed(evt);
            }
        });
        jMenuInvestmenAdvisory.add(jMenuItemStockPool1);

        jMenuItemjMenuItemMessageManagement.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemjMenuItemMessageManagement.setText("专家荐股消息管理");
        jMenuItemjMenuItemMessageManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemjMenuItemMessageManagementActionPerformed(evt);
            }
        });
        jMenuInvestmenAdvisory.add(jMenuItemjMenuItemMessageManagement);

        jMenuItemInform.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemInform.setText("荐股消息客户告知");
        jMenuItemInform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInformActionPerformed(evt);
            }
        });
        jMenuInvestmenAdvisory.add(jMenuItemInform);

        jMenuItemClearMessage.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemClearMessage.setText("荐股消息清理");
        jMenuItemClearMessage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItemClearMessageMouseClicked(evt);
            }
        });
        jMenuItemClearMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClearMessageActionPerformed(evt);
            }
        });
        jMenuInvestmenAdvisory.add(jMenuItemClearMessage);

        jMenuItemRealMontitor.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemRealMontitor.setText("签约客户资产监控");
        jMenuItemRealMontitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRealMontitorActionPerformed(evt);
            }
        });
        jMenuInvestmenAdvisory.add(jMenuItemRealMontitor);
        jMenuInvestmenAdvisory.add(jSeparator20);

        jMenuItemAssetMonitor.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemAssetMonitor.setText("一般客户股票盈亏监控表");
        jMenuItemAssetMonitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAssetMonitorActionPerformed(evt);
            }
        });
        jMenuInvestmenAdvisory.add(jMenuItemAssetMonitor);

        jMenuItemFindStockBalance.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemFindStockBalance.setText("查询客户证券资产(来自交易系统)");
        jMenuItemFindStockBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFindStockBalanceActionPerformed(evt);
            }
        });
        jMenuInvestmenAdvisory.add(jMenuItemFindStockBalance);

        jMenuItemMarginTradingStokAsset.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemMarginTradingStokAsset.setText("查询两融客户证券资产");
        jMenuItemMarginTradingStokAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMarginTradingStokAssetActionPerformed(evt);
            }
        });
        jMenuInvestmenAdvisory.add(jMenuItemMarginTradingStokAsset);
        jMenuInvestmenAdvisory.add(jSeparator28);

        jMenuItemSumMatch2.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemSumMatch2.setText("投顾-营业部成交汇总对比");
        jMenuItemSumMatch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSumMatch2ActionPerformed(evt);
            }
        });
        jMenuInvestmenAdvisory.add(jMenuItemSumMatch2);

        jMenuItemSumMatch.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemSumMatch.setText("投顾推荐股票成交汇总及明细查询");
        jMenuItemSumMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSumMatchActionPerformed(evt);
            }
        });
        jMenuInvestmenAdvisory.add(jMenuItemSumMatch);
        jMenuInvestmenAdvisory.add(jSeparator34);

        jMenuItemClientMatchDetails.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemClientMatchDetails.setText("查询客户历史成交明细");
        jMenuItemClientMatchDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientMatchDetailsActionPerformed(evt);
            }
        });
        jMenuInvestmenAdvisory.add(jMenuItemClientMatchDetails);

        jMenuItemFindOrder.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemFindOrder.setText("查询客户当日委托");
        jMenuItemFindOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFindOrderActionPerformed(evt);
            }
        });
        jMenuInvestmenAdvisory.add(jMenuItemFindOrder);

        jMenuServiceManagement.add(jMenuInvestmenAdvisory);
        jMenuServiceManagement.add(jSeparator23);

        jMenuItemGrantServiceRelation.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemGrantServiceRelation.setText("服务关系转授");
        jMenuItemGrantServiceRelation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGrantServiceRelationActionPerformed(evt);
            }
        });
        jMenuServiceManagement.add(jMenuItemGrantServiceRelation);

        jMenuItemAssignLog.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemAssignLog.setText("查询服务安排日志");
        jMenuItemAssignLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAssignLogActionPerformed(evt);
            }
        });
        jMenuServiceManagement.add(jMenuItemAssignLog);

        jMenuItemCallbackInDetails.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemCallbackInDetails.setText("客户详细回访");
        jMenuItemCallbackInDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCallbackInDetailsActionPerformed(evt);
            }
        });
        jMenuServiceManagement.add(jMenuItemCallbackInDetails);

        jMenuItemSearchByCallbackDetails.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemSearchByCallbackDetails.setText("按详细回访信息组合查询客户");
        jMenuItemSearchByCallbackDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSearchByCallbackDetailsActionPerformed(evt);
            }
        });
        jMenuServiceManagement.add(jMenuItemSearchByCallbackDetails);

        jMenuBar1.add(jMenuServiceManagement);

        jMenuClientSearch.setText("客户查询");
        jMenuClientSearch.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuClientSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuClientSearchActionPerformed(evt);
            }
        });

        jMenuItemAccurateSearch.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemAccurateSearch.setText("按帐号精确查询");
        jMenuItemAccurateSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listUsers(evt);
            }
        });
        jMenuClientSearch.add(jMenuItemAccurateSearch);

        jMenuItemClientByTel.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemClientByTel.setText("按电话查询客户");
        jMenuItemClientByTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientByTelActionPerformed(evt);
            }
        });
        jMenuClientSearch.add(jMenuItemClientByTel);
        jMenuClientSearch.add(jSeparator3);

        jMenuItemFuzzySearch.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemFuzzySearch.setText("按姓氏模糊查询");
        jMenuItemFuzzySearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFuzzySearchActionPerformed(evt);
            }
        });
        jMenuClientSearch.add(jMenuItemFuzzySearch);

        jMenuItemAssetSearch.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemAssetSearch.setText("按资产模糊查询");
        jMenuItemAssetSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAssetSearchActionPerformed(evt);
            }
        });
        jMenuClientSearch.add(jMenuItemAssetSearch);

        jMenuSearchByAge.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuSearchByAge.setText("按年龄模糊查询");
        jMenuSearchByAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSearchByAgeActionPerformed(evt);
            }
        });
        jMenuClientSearch.add(jMenuSearchByAge);

        jMenuItemSearchByServicePerson.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemSearchByServicePerson.setText("按服务员统计查询");
        jMenuItemSearchByServicePerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSearchByServicePersonActionPerformed(evt);
            }
        });
        jMenuClientSearch.add(jMenuItemSearchByServicePerson);

        jMenuItemSearchBySatisfaction.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemSearchBySatisfaction.setText("按客户满意度查询");
        jMenuItemSearchBySatisfaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSearchBySatisfactionActionPerformed(evt);
            }
        });
        jMenuClientSearch.add(jMenuItemSearchBySatisfaction);

        jMenuItemClientNoTel.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemClientNoTel.setText("查无电话或电话空号客户清单");
        jMenuItemClientNoTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientNoTelActionPerformed(evt);
            }
        });
        jMenuClientSearch.add(jMenuItemClientNoTel);

        jMenuItemSearchBySummary.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemSearchBySummary.setText("按回访摘要分类查询客户清单");
        jMenuItemSearchBySummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSearchBySummaryActionPerformed(evt);
            }
        });
        jMenuClientSearch.add(jMenuItemSearchBySummary);
        jMenuClientSearch.add(jSeparator12);

        jMenuItemSearchByCallbackDate.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemSearchByCallbackDate.setText("按回访日期分类查询客户清单");
        jMenuItemSearchByCallbackDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSearchByCallbackDateActionPerformed(evt);
            }
        });
        jMenuClientSearch.add(jMenuItemSearchByCallbackDate);

        jMenuItemClientListByAgent.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemClientListByAgent.setText("按经纪人统计查询客户");
        jMenuItemClientListByAgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientListByAgentActionPerformed(evt);
            }
        });
        jMenuClientSearch.add(jMenuItemClientListByAgent);

        jMenuItemClientListByBirthday.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemClientListByBirthday.setText("按生日查询客户清单");
        jMenuItemClientListByBirthday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientListByBirthdayActionPerformed(evt);
            }
        });
        jMenuClientSearch.add(jMenuItemClientListByBirthday);

        jMenuAlalystReport.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuAlalystReport.setText("按分析师统计查询客户清单");
        jMenuAlalystReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAlalystReportActionPerformed(evt);
            }
        });
        jMenuClientSearch.add(jMenuAlalystReport);
        jMenuClientSearch.add(jSeparator8);

        jMenuItemCreditClient.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemCreditClient.setText("查询开通融资融券的客户");
        jMenuItemCreditClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCreditClientActionPerformed(evt);
            }
        });
        jMenuClientSearch.add(jMenuItemCreditClient);
        jMenuClientSearch.add(jSeparator22);

        jMenuItemLostClient.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemLostClient.setText("查询隐性流失客户清单");
        jMenuItemLostClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLostClientActionPerformed(evt);
            }
        });
        jMenuClientSearch.add(jMenuItemLostClient);
        jMenuClientSearch.add(jSeparator36);

        jMenuItemRepoDetails.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemRepoDetails.setText("查询回购交易明细");
        jMenuItemRepoDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRepoDetailsActionPerformed(evt);
            }
        });
        jMenuClientSearch.add(jMenuItemRepoDetails);

        clientList_byEsignDate.setFont(new java.awt.Font("宋体", 0, 14));
        clientList_byEsignDate.setText("按电子签约日期查询客户清单");
        clientList_byEsignDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientList_byEsignDateActionPerformed(evt);
            }
        });
        jMenuClientSearch.add(clientList_byEsignDate);

        jMenuBar1.add(jMenuClientSearch);

        jMenuStatics.setText("统计分析");
        jMenuStatics.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuStatics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuStaticsActionPerformed(evt);
            }
        });

        jMenuItemAssetStatics.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemAssetStatics.setText("客户资产分布统计");
        jMenuItemAssetStatics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAssetStaticsActionPerformed(evt);
            }
        });
        jMenuStatics.add(jMenuItemAssetStatics);

        jMenuItemTotalAsset.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemTotalAsset.setText("客户总资产统计");
        jMenuItemTotalAsset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItemTotalAssetMouseClicked(evt);
            }
        });
        jMenuItemTotalAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTotalAssetActionPerformed(evt);
            }
        });
        jMenuStatics.add(jMenuItemTotalAsset);
        jMenuStatics.add(jSeparator4);

        jMenuItemSatifactionDegree.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemSatifactionDegree.setText("客户满意度统计");
        jMenuItemSatifactionDegree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSatifactionDegreeActionPerformed(evt);
            }
        });
        jMenuStatics.add(jMenuItemSatifactionDegree);
        jMenuStatics.add(jSeparator7);

        jMenuItemWorkReport.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemWorkReport.setText("回访与服务安排统计");
        jMenuItemWorkReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemWorkReportActionPerformed(evt);
            }
        });
        jMenuStatics.add(jMenuItemWorkReport);

        jMenuItemAgentReport.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemAgentReport.setText("经纪人客户资产佣金统计");
        jMenuItemAgentReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAgentReportActionPerformed(evt);
            }
        });
        jMenuStatics.add(jMenuItemAgentReport);

        jMenuItemServicePersonClientAsset.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemServicePersonClientAsset.setText("服务员客户资产佣金统计");
        jMenuItemServicePersonClientAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemServicePersonClientAssetActionPerformed(evt);
            }
        });
        jMenuStatics.add(jMenuItemServicePersonClientAsset);

        jMenuItemCommisionDistribution.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemCommisionDistribution.setText("客户佣金分布统计");
        jMenuItemCommisionDistribution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCommisionDistributionActionPerformed(evt);
            }
        });
        jMenuStatics.add(jMenuItemCommisionDistribution);
        jMenuStatics.add(jSeparator15);

        jMenuItemServiceCheck.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemServiceCheck.setText("服务考核统计表");
        jMenuItemServiceCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemServiceCheckActionPerformed(evt);
            }
        });
        jMenuStatics.add(jMenuItemServiceCheck);

        jMenuItemSumClientValue.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemSumClientValue.setText("客户价值汇总表");
        jMenuItemSumClientValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSumClientValueActionPerformed(evt);
            }
        });
        jMenuStatics.add(jMenuItemSumClientValue);

        jMenuItemServiceCheckReport.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemServiceCheckReport.setText("服务考核报表一");
        jMenuItemServiceCheckReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemServiceCheckReportActionPerformed(evt);
            }
        });
        jMenuStatics.add(jMenuItemServiceCheckReport);

        jMenuItemAgentMonthReport.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemAgentMonthReport.setText("经纪人业绩统计");
        jMenuItemAgentMonthReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAgentMonthReportActionPerformed(evt);
            }
        });
        jMenuStatics.add(jMenuItemAgentMonthReport);

        jMenuItemAgentMonthReport1.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemAgentMonthReport1.setText("经纪人业绩统计一");
        jMenuItemAgentMonthReport1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAgentMonthReport1ActionPerformed(evt);
            }
        });
        jMenuStatics.add(jMenuItemAgentMonthReport1);
        jMenuStatics.add(jSeparator5);

        jMenuProfitAnalysis.setText("客户盈亏分析");
        jMenuProfitAnalysis.setFont(new java.awt.Font("宋体", 0, 14));

        jMenuItemProfitAnalysis.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemProfitAnalysis.setText("买卖盈亏统计");
        jMenuItemProfitAnalysis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProfitAnalysisActionPerformed(evt);
            }
        });
        jMenuProfitAnalysis.add(jMenuItemProfitAnalysis);

        jMenuItemAssetIncrement.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemAssetIncrement.setText("资产增值统计");
        jMenuItemAssetIncrement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAssetIncrementActionPerformed(evt);
            }
        });
        jMenuProfitAnalysis.add(jMenuItemAssetIncrement);

        jMenuItemSumProfit.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemSumProfit.setText("盈亏汇总分析");
        jMenuItemSumProfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSumProfitActionPerformed(evt);
            }
        });
        jMenuProfitAnalysis.add(jMenuItemSumProfit);

        jMenuItemClientProfit.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemClientProfit.setText("客户盈亏查询");
        jMenuItemClientProfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientProfitActionPerformed(evt);
            }
        });
        jMenuProfitAnalysis.add(jMenuItemClientProfit);

        jMenuItemFloatProfit.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemFloatProfit.setText("浮动盈亏分析");
        jMenuItemFloatProfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFloatProfitActionPerformed(evt);
            }
        });
        jMenuProfitAnalysis.add(jMenuItemFloatProfit);
        jMenuProfitAnalysis.add(jSeparator29);

        jMenuItemSumAppreciation.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemSumAppreciation.setText("按投顾汇总客户盈亏");
        jMenuItemSumAppreciation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSumAppreciationActionPerformed(evt);
            }
        });
        jMenuProfitAnalysis.add(jMenuItemSumAppreciation);

        jMenuStatics.add(jMenuProfitAnalysis);
        jMenuStatics.add(jSeparator33);

        jMenuItemNewSumAnalysis.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemNewSumAnalysis.setText("新业务佣金贡献分析");
        jMenuItemNewSumAnalysis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNewSumAnalysisActionPerformed(evt);
            }
        });
        jMenuStatics.add(jMenuItemNewSumAnalysis);

        jMenuItemContractInfo.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemContractInfo.setText("查询客户签约信息");
        jMenuItemContractInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContractInfoActionPerformed(evt);
            }
        });
        jMenuStatics.add(jMenuItemContractInfo);

        jMenuBar1.add(jMenuStatics);

        jMenuFinanceProduct.setText("理财产品");
        jMenuFinanceProduct.setFont(new java.awt.Font("宋体", 0, 14));

        jMenuItemProductInfo.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemProductInfo.setText("产品信息维护");
        jMenuItemProductInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProductInfoActionPerformed(evt);
            }
        });
        jMenuFinanceProduct.add(jMenuItemProductInfo);

        jMenuItemProductInfoQuery.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemProductInfoQuery.setText("产品信息查询");
        jMenuItemProductInfoQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProductInfoQueryActionPerformed(evt);
            }
        });
        jMenuFinanceProduct.add(jMenuItemProductInfoQuery);
        jMenuFinanceProduct.add(jSeparator38);

        jMenuItemProductClientQuery.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemProductClientQuery.setText("产品-客户查询");
        jMenuItemProductClientQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProductClientQueryActionPerformed(evt);
            }
        });
        jMenuFinanceProduct.add(jMenuItemProductClientQuery);

        jMenuItemClientProductQuery.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemClientProductQuery.setText("客户-产品查询");
        jMenuItemClientProductQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientProductQueryActionPerformed(evt);
            }
        });
        jMenuFinanceProduct.add(jMenuItemClientProductQuery);
        jMenuFinanceProduct.add(jSeparator39);

        jMenuItemproductTradingQuery.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemproductTradingQuery.setText("产品-交易查询");
        jMenuItemproductTradingQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemproductTradingQueryActionPerformed(evt);
            }
        });
        jMenuFinanceProduct.add(jMenuItemproductTradingQuery);

        jMenuItemClientTradingQuery.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemClientTradingQuery.setText("客户-交易查询");
        jMenuItemClientTradingQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientTradingQueryActionPerformed(evt);
            }
        });
        jMenuFinanceProduct.add(jMenuItemClientTradingQuery);
        jMenuFinanceProduct.add(jSeparator40);

        jMenuItemMultiQuery.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemMultiQuery.setText("综合查询");
        jMenuItemMultiQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMultiQueryActionPerformed(evt);
            }
        });
        jMenuFinanceProduct.add(jMenuItemMultiQuery);

        jMenuItemMultiSum.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemMultiSum.setText("综合统计");
        jMenuItemMultiSum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMultiSumActionPerformed(evt);
            }
        });
        jMenuFinanceProduct.add(jMenuItemMultiSum);

        jMenuBar1.add(jMenuFinanceProduct);

        jMenuDataConverter.setText("数据维护");
        jMenuDataConverter.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuDataConverter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuDataConverterActionPerformed(evt);
            }
        });

        jMenuBasicData.setText("基本数据维护");
        jMenuBasicData.setFont(new java.awt.Font("宋体", 0, 14));

        jMenuItemZjdbf.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemZjdbf.setText("转入资金资料表");
        jMenuItemZjdbf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemZjdbfActionPerformed(evt);
            }
        });
        jMenuBasicData.add(jMenuItemZjdbf);

        jMenuItemZcdbf.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemZcdbf.setText("转入客户资产表");
        jMenuItemZcdbf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemZcdbfActionPerformed(evt);
            }
        });
        jMenuBasicData.add(jMenuItemZcdbf);

        jMenuItemFundBalance.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemFundBalance.setText("转入资金余额表");
        jMenuItemFundBalance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFundBalanceActionPerformed(evt);
            }
        });
        jMenuBasicData.add(jMenuItemFundBalance);

        jMenuItemCjdbf.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemCjdbf.setText("转入成交汇总表");
        jMenuItemCjdbf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCjdbfActionPerformed(evt);
            }
        });
        jMenuBasicData.add(jMenuItemCjdbf);

        jMenuItem1UdateClientInfo.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItem1UdateClientInfo.setText("更新客户服务数据");
        jMenuItem1UdateClientInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1UdateClientInfoActionPerformed(evt);
            }
        });
        jMenuBasicData.add(jMenuItem1UdateClientInfo);
        jMenuBasicData.add(jSeparator11);

        jMenuItemBackup.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemBackup.setText("备份客户服务数据");
        jMenuItemBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBackupActionPerformed(evt);
            }
        });
        jMenuBasicData.add(jMenuItemBackup);

        jMenuItemRestoreClient.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemRestoreClient.setText("恢复客户服务数据");
        jMenuItemRestoreClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRestoreClientActionPerformed(evt);
            }
        });
        jMenuBasicData.add(jMenuItemRestoreClient);

        jMenuItemRestoreFromDBF.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemRestoreFromDBF.setText("从DBF恢复客户服务数据");
        jMenuItemRestoreFromDBF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRestoreFromDBFActionPerformed(evt);
            }
        });
        jMenuBasicData.add(jMenuItemRestoreFromDBF);

        jMenuDataConverter.add(jMenuBasicData);

        jMenuAdditionData.setText("附加信息维护");
        jMenuAdditionData.setFont(new java.awt.Font("宋体", 0, 14));

        jMenuItemClientTelDBF.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemClientTelDBF.setText("转入并更新客户电话");
        jMenuItemClientTelDBF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientTelDBFActionPerformed(evt);
            }
        });
        jMenuAdditionData.add(jMenuItemClientTelDBF);

        jMenuItemClientQQ.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemClientQQ.setText("转入并更新客户QQ号码");
        jMenuItemClientQQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientQQActionPerformed(evt);
            }
        });
        jMenuAdditionData.add(jMenuItemClientQQ);

        jMenuItemIdCard.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemIdCard.setText("转入并更新客户身份证");
        jMenuItemIdCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemIdCardActionPerformed(evt);
            }
        });
        jMenuAdditionData.add(jMenuItemIdCard);

        jMenuItemClientAgent.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemClientAgent.setText("转入并更新客户经纪人关系");
        jMenuItemClientAgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientAgentActionPerformed(evt);
            }
        });
        jMenuAdditionData.add(jMenuItemClientAgent);
        jMenuAdditionData.add(jSeparator19);

        jMenuItemIportServicePersons.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemIportServicePersons.setText("转入并更新客户-服务员关系");
        jMenuItemIportServicePersons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemIportServicePersonsActionPerformed(evt);
            }
        });
        jMenuAdditionData.add(jMenuItemIportServicePersons);
        jMenuAdditionData.add(jSeparator25);

        jMenuItemIputStockPool.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemIputStockPool.setText("从DBF导入股票池");
        jMenuItemIputStockPool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemIputStockPoolActionPerformed(evt);
            }
        });
        jMenuAdditionData.add(jMenuItemIputStockPool);
        jMenuAdditionData.add(jSeparator32);

        jMenuItemDBFUser.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemDBFUser.setText("从dbf导入用户");
        jMenuItemDBFUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDBFUserActionPerformed(evt);
            }
        });
        jMenuAdditionData.add(jMenuItemDBFUser);

        jMenuDataConverter.add(jMenuAdditionData);
        jMenuDataConverter.add(jSeparator21);

        jMenuServiceCheck.setText("服务考核数据维护");
        jMenuServiceCheck.setFont(new java.awt.Font("宋体", 0, 14));

        jMenuItemMonthEndAsset.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemMonthEndAsset.setText("转入月末资产数据");
        jMenuItemMonthEndAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMonthEndAssetActionPerformed(evt);
            }
        });
        jMenuServiceCheck.add(jMenuItemMonthEndAsset);

        jMenuItemCancelLog.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemCancelLog.setText("转入开户销户日志");
        jMenuItemCancelLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCancelLogActionPerformed(evt);
            }
        });
        jMenuServiceCheck.add(jMenuItemCancelLog);

        jMenuItemTransfer.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemTransfer.setText("转入银证转账流水");
        jMenuItemTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTransferActionPerformed(evt);
            }
        });
        jMenuServiceCheck.add(jMenuItemTransfer);
        jMenuServiceCheck.add(jSeparator24);

        jMenuItemAssetArchive.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemAssetArchive.setText("资产数据归档");
        jMenuItemAssetArchive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAssetArchiveActionPerformed(evt);
            }
        });
        jMenuServiceCheck.add(jMenuItemAssetArchive);

        jMenuItemCalculte.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemCalculte.setText("计算服务考核指标");
        jMenuItemCalculte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCalculteActionPerformed(evt);
            }
        });
        jMenuServiceCheck.add(jMenuItemCalculte);
        jMenuServiceCheck.add(jSeparator30);

        jMenuItemMatchHhistory.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemMatchHhistory.setText("转入历史成交明细");
        jMenuItemMatchHhistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMatchHhistoryActionPerformed(evt);
            }
        });
        jMenuServiceCheck.add(jMenuItemMatchHhistory);

        jMenuItemDataCheck.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemDataCheck.setText("数据完整性查询");
        jMenuItemDataCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDataCheckActionPerformed(evt);
            }
        });
        jMenuServiceCheck.add(jMenuItemDataCheck);

        jMenuDataConverter.add(jMenuServiceCheck);

        jMenuIteInvestmentAdvice.setText("投顾业务数据维护");
        jMenuIteInvestmentAdvice.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuIteInvestmentAdvice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIteInvestmentAdviceActionPerformed(evt);
            }
        });

        jMenuItemContractClientTradingDetails.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemContractClientTradingDetails.setText("转入当日成交明细");
        jMenuItemContractClientTradingDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContractClientTradingDetailsActionPerformed(evt);
            }
        });
        jMenuIteInvestmentAdvice.add(jMenuItemContractClientTradingDetails);

        jMenuItemsettlement.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemsettlement.setText("当日成交清算");
        jMenuItemsettlement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemsettlementActionPerformed(evt);
            }
        });
        jMenuIteInvestmentAdvice.add(jMenuItemsettlement);
        jMenuIteInvestmentAdvice.add(jSeparator26);

        jMenuItemStockAsset.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemStockAsset.setText("转入客户证券资产");
        jMenuItemStockAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemStockAssetActionPerformed(evt);
            }
        });
        jMenuIteInvestmentAdvice.add(jMenuItemStockAsset);

        jMenuItem8.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItem8.setText("转入两融客户证券资产");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenuIteInvestmentAdvice.add(jMenuItem8);

        jMenuItemOrderData.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemOrderData.setText("转入当日委托数据");
        jMenuItemOrderData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOrderDataActionPerformed(evt);
            }
        });
        jMenuIteInvestmentAdvice.add(jMenuItemOrderData);
        jMenuIteInvestmentAdvice.add(jSeparator27);

        jMenuItemProfitCalculate.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemProfitCalculate.setText("买卖盈亏分析计算");
        jMenuItemProfitCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProfitCalculateActionPerformed(evt);
            }
        });
        jMenuIteInvestmentAdvice.add(jMenuItemProfitCalculate);
        jMenuIteInvestmentAdvice.add(jSeparator6);

        jMenuItemCreditClientInfo.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemCreditClientInfo.setText("转入融资融券客户资料");
        jMenuItemCreditClientInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCreditClientInfoActionPerformed(evt);
            }
        });
        jMenuIteInvestmentAdvice.add(jMenuItemCreditClientInfo);

        jMenuItemFinancing.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemFinancing.setText("转入理财产品库存数据");
        jMenuItemFinancing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFinancingActionPerformed(evt);
            }
        });
        jMenuIteInvestmentAdvice.add(jMenuItemFinancing);

        jMenuItemContractData.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemContractData.setText("转入签约手工记录数据");
        jMenuItemContractData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContractDataActionPerformed(evt);
            }
        });
        jMenuIteInvestmentAdvice.add(jMenuItemContractData);
        jMenuIteInvestmentAdvice.add(jSeparator35);

        jMenuItem1.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItem1.setText("转入回购账户资料");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuIteInvestmentAdvice.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItem2.setText("转入回购交易资料");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuIteInvestmentAdvice.add(jMenuItem2);
        jMenuIteInvestmentAdvice.add(jSeparator37);

        jMenuItem6.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItem6.setText("转入电子签约资料");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenuIteInvestmentAdvice.add(jMenuItem6);

        jMenuDataConverter.add(jMenuIteInvestmentAdvice);
        jMenuDataConverter.add(jSeparator31);

        jMenu1.setText("理财产品数据维护");
        jMenu1.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N

        jMenuItem7.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jMenuItem7.setText("导入基金资产数据");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem9.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jMenuItem9.setText("导入基金交易数据");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuItem10.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        jMenuItem10.setText("导入理财产品信息");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        jMenuDataConverter.add(jMenu1);

        jMenuItemSQL.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItemSQL.setText("加载SQL脚本");
        jMenuItemSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSQLActionPerformed(evt);
            }
        });
        jMenuDataConverter.add(jMenuItemSQL);

        jMenuBar1.add(jMenuDataConverter);

        jMenu2.setText("其他功能");
        jMenu2.setFont(new java.awt.Font("宋体", 0, 14));

        jMenuItem3.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItem3.setText("修改密码");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItem4.setText("系统锁定");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setFont(new java.awt.Font("宋体", 0, 14));
        jMenuItem5.setText("备忘录管理");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(475, 475, 475)
                .addComponent(jLabel1))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listUsers(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listUsers
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameAccurateSearch().setVisible(true);
    }//GEN-LAST:event_listUsers

    private void jMenuClientSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuClientSearchActionPerformed
        // TODO add your handling code here:
        //new myTable().setVisible(true);
}//GEN-LAST:event_jMenuClientSearchActionPerformed

    private void jMenuItemAssignServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAssignServiceActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameAssignServicePerson(1).setVisible(true);// 服务安排
}//GEN-LAST:event_jMenuItemAssignServiceActionPerformed

    private void jMenuItemUserControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUserControlActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameUserControl().setVisible(true);
    }//GEN-LAST:event_jMenuItemUserControlActionPerformed

    private void jMenuItemAssignCallbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAssignCallbackActionPerformed
        // TODO add your handling code here
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameAssignServicePerson(2).setVisible(true); //回访安排
}//GEN-LAST:event_jMenuItemAssignCallbackActionPerformed

    private void jMenuItemCallbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCallbackActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameServiceCallback(2).setVisible(true);//客户回访
}//GEN-LAST:event_jMenuItemCallbackActionPerformed

    private void jMenuItemServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemServiceActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameServiceCallback(1).setVisible(true);//客户服务
}//GEN-LAST:event_jMenuItemServiceActionPerformed

    private void jMenuItemDictActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDictActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        BasicInfo.getBaseicInfo().setVisible(true);
}//GEN-LAST:event_jMenuItemDictActionPerformed

    private void jMenuItemFuzzySearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFuzzySearchActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSearchByName().setVisible(true);
}//GEN-LAST:event_jMenuItemFuzzySearchActionPerformed

    private void jMenuItemAssetSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAssetSearchActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSearchByAsset().setVisible(true);
}//GEN-LAST:event_jMenuItemAssetSearchActionPerformed

    private void jMenuItemSearchByServicePersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSearchByServicePersonActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSearchByServicePerson().setVisible(true);
    }//GEN-LAST:event_jMenuItemSearchByServicePersonActionPerformed

    private void jMenuItemSearchBySatisfactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSearchBySatisfactionActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSearchBySatisfaction().setVisible(true);
    }//GEN-LAST:event_jMenuItemSearchBySatisfactionActionPerformed

    private void jMenuItemClientNoTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientNoTelActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameClientNoTel().setVisible(true);
}//GEN-LAST:event_jMenuItemClientNoTelActionPerformed

    private void jMenuItemSearchBySummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSearchBySummaryActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSearchBySummary().setVisible(true);
}//GEN-LAST:event_jMenuItemSearchBySummaryActionPerformed

    private void jMenuItemInvestmentAdviserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInvestmentAdviserActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameAdviserControl().setVisible(true);
}//GEN-LAST:event_jMenuItemInvestmentAdviserActionPerformed

    private void jMenuItemFindCallbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFindCallbackActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameFindCallback().setVisible(true);
    }//GEN-LAST:event_jMenuItemFindCallbackActionPerformed

    private void jMenuItemFindAssignServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFindAssignServiceActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameFindService().setVisible(true);
    }//GEN-LAST:event_jMenuItemFindAssignServiceActionPerformed

    private void jMenuServiceManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuServiceManagementActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jMenuServiceManagementActionPerformed

    private void jMenuItemsearchServiceLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemsearchServiceLogActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSearchSeviceLog().setVisible(true);
    }//GEN-LAST:event_jMenuItemsearchServiceLogActionPerformed

    private void jTextFieldRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRoleActionPerformed

    private void jMenuItemSearchByCallbackDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSearchByCallbackDateActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSearchByDate().setVisible(true);
}//GEN-LAST:event_jMenuItemSearchByCallbackDateActionPerformed

    private void jLabel4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseDragged
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(this, "版权所有:华西证券成都东一环路营业部，作者：蒋友全");
        JOptionPane.showMessageDialog(this,
                "<HTML>" +
                "<H4>版权所有：华西证券成都东一环路营业部" +
                "<P>" +
                "<H2>作者：蒋友全");
    }//GEN-LAST:event_jLabel4MouseDragged

    private void jMenuItemMasterControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMasterControlActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameMasterControl().setVisible(true);
}//GEN-LAST:event_jMenuItemMasterControlActionPerformed

    private void jMenuItemMarketMatchAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMarketMatchAmountActionPerformed
        // TODO add your handling code here:

        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameMarketMatchAmount().setVisible(true);
}//GEN-LAST:event_jMenuItemMarketMatchAmountActionPerformed

    private void jMenuItemContractMagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContractMagementActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSignContract().setVisible(true);
    }//GEN-LAST:event_jMenuItemContractMagementActionPerformed

    private void jMenuItemStockPool1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemStockPool1ActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameStockPoolControl().setVisible(true);
    }//GEN-LAST:event_jMenuItemStockPool1ActionPerformed

    private void jMenuItemjMenuItemMessageManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemjMenuItemMessageManagementActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSendMessage().setVisible(true);
}//GEN-LAST:event_jMenuItemjMenuItemMessageManagementActionPerformed

    private void jMenuItemInformActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInformActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameInformClient().setVisible(true);
}//GEN-LAST:event_jMenuItemInformActionPerformed

    private void jMenuItemClearMessageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemClearMessageMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_jMenuItemClearMessageMouseClicked

    private void jMenuItemClearMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClearMessageActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameClearMessage().setVisible(true);
}//GEN-LAST:event_jMenuItemClearMessageActionPerformed

    private void jMenuItemRealMontitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRealMontitorActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameRealtimeMonitor().setVisible(true);
}//GEN-LAST:event_jMenuItemRealMontitorActionPerformed

    private void jMenuItemSumMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSumMatchActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSumMatch().setVisible(true);
    }//GEN-LAST:event_jMenuItemSumMatchActionPerformed

    private void jMenuItemSumMatch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSumMatch2ActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSumMatch2().setVisible(true);
}//GEN-LAST:event_jMenuItemSumMatch2ActionPerformed

    private void jMenuItemStockListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemStockListActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameStockList().setVisible(true);
}//GEN-LAST:event_jMenuItemStockListActionPerformed

    private void jMenuItemSetSysParametersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSetSysParametersActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSetFlag().setVisible(true);
}//GEN-LAST:event_jMenuItemSetSysParametersActionPerformed

    private void jMenuItemAssetMonitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAssetMonitorActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameAssetMonitor().setVisible(true);
    }//GEN-LAST:event_jMenuItemAssetMonitorActionPerformed

    private void jMenuItemFindStockBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFindStockBalanceActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameFindStockAsset().setVisible(true);
    }//GEN-LAST:event_jMenuItemFindStockBalanceActionPerformed

    private void jMenuItemFindOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFindOrderActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameFindOrders().setVisible(true);
    }//GEN-LAST:event_jMenuItemFindOrderActionPerformed

    private void jMenuItemClientListByAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientListByAgentActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameClientListByAgent().setVisible(true);
    }//GEN-LAST:event_jMenuItemClientListByAgentActionPerformed

    private void jMenuItemClientListByBirthdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientListByBirthdayActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameClientListByBirthDay().setVisible(true);
    }//GEN-LAST:event_jMenuItemClientListByBirthdayActionPerformed

    private void jMenuItemSumContractedClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSumContractedClientActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameContractSignedClientList().setVisible(true);
}//GEN-LAST:event_jMenuItemSumContractedClientActionPerformed

    private void jTextFieldUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUserActionPerformed

    private void jMenuAlalystReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAlalystReportActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSerchByAnalyst().setVisible(true);
    }//GEN-LAST:event_jMenuAlalystReportActionPerformed

    private void jMenuItemGrantServiceRelationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGrantServiceRelationActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameGrantServiceRelation().setVisible(true);
    }//GEN-LAST:event_jMenuItemGrantServiceRelationActionPerformed

    private void jMenuDataConverterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuDataConverterActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jMenuDataConverterActionPerformed

    private void jMenuItemZjdbfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemZjdbfActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入资金资料表......");
            DBFImporter1 myThread = new DBFImporter1(name, jPanel2, jLabelProgress);
            // SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItemZjdbfActionPerformed

    private void jMenuItemZcdbfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemZcdbfActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入客户资产表......");
            DBFImporter2 myThread = new DBFImporter2(name, jPanel2, jLabelProgress);
            // SwingUtilities.invokeLater(myThread);
            myThread.start();

        }
    }//GEN-LAST:event_jMenuItemZcdbfActionPerformed

    private void jMenuItemCjdbfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCjdbfActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入成交汇总表......");
            DBFImporter3 myThread = new DBFImporter3(name, jPanel2, jLabelProgress);
            // SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItemCjdbfActionPerformed

    private void jMenuItem1UdateClientInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1UdateClientInfoActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        jPanel2.setVisible(true);
        jLabelProgress.setText("正在更新......");
        ClientUpdate myThread = new ClientUpdate(jPanel2, jLabelProgress);
        //SwingUtilities.invokeLater(myThread);
        myThread.start();
    }//GEN-LAST:event_jMenuItem1UdateClientInfoActionPerformed

    private void jMenuItemBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBackupActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        jPanel2.setVisible(true);
        jLabelProgress.setText("备份client到client_backup......");
        new BackupClient(jPanel2, jLabelProgress).backup();
    }//GEN-LAST:event_jMenuItemBackupActionPerformed

    private void jMenuItemRestoreClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRestoreClientActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameRestoreClient().setVisible(true);
    }//GEN-LAST:event_jMenuItemRestoreClientActionPerformed

    private void jMenuItemRestoreFromDBFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRestoreFromDBFActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("从DBF恢复数据到client表 ......");
            DBFImporter9 myThread = new DBFImporter9(name, jPanel2, jLabelProgress);
            //SwingUtilities.invokeLater(myThread);
            myThread.start();

        }
    }//GEN-LAST:event_jMenuItemRestoreFromDBFActionPerformed

    private void jMenuItemClientTelDBFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientTelDBFActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        System.out.println(name);
        if (name != null) {
            jLabelProgress.setText("正在转入并更新客户电话......");
            DBFImporter4 myThread = new DBFImporter4(name, jPanel2, jLabelProgress);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItemClientTelDBFActionPerformed

    private void jMenuItemClientQQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientQQActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入并更新客户QQ号码......");
            DBFImporter6 myThread = new DBFImporter6(name, jPanel2, jLabelProgress);
            //SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItemClientQQActionPerformed

    private void jMenuItemIdCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemIdCardActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {

            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入并更新客户身份证......");

            DBFImporter16 myThread = new DBFImporter16(name, jPanel2, jLabelProgress);
            //SwingUtilities.invokeLater(myThread);
            myThread.start();

        }
    }//GEN-LAST:event_jMenuItemIdCardActionPerformed

    private void jMenuItemClientAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientAgentActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        System.out.println(name);
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入客户-经纪人信息......");
            DBFImporter5 myThread = new DBFImporter5(name, jPanel2, jLabelProgress);
            //SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItemClientAgentActionPerformed

    private void jMenuItemIportServicePersonsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemIportServicePersonsActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jLabelProgress.setText("正在转入客户-服务员关系......");
            DBFImporter13 myThread = new DBFImporter13(name, jPanel2, jLabelProgress);
            //SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItemIportServicePersonsActionPerformed

    private void jMenuItemIputStockPoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemIputStockPoolActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在导入股票池.....");
            DBFImporter12 myThread = new DBFImporter12(name, jPanel2, jLabelProgress);
            //SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItemIputStockPoolActionPerformed

    private void jMenuItemDBFUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDBFUserActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在导入用户信息.....");
            DBFImporter10 myThread = new DBFImporter10(name, jPanel2, jLabelProgress);
            //SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItemDBFUserActionPerformed

    private void jMenuItemMonthEndAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMonthEndAssetActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入月末资产数据......");
            DBFImporter18 myThread = new DBFImporter18(name, jPanel2, jLabelProgress);
            //SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
}//GEN-LAST:event_jMenuItemMonthEndAssetActionPerformed

    private void jMenuItemCancelLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCancelLogActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入开户销户日志......");
            DBFImporter8 myThread = new DBFImporter8(name, jPanel2, jLabelProgress);
            //SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItemCancelLogActionPerformed

    private void jMenuItemAssetArchiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAssetArchiveActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameArchive().setVisible(true);
    }//GEN-LAST:event_jMenuItemAssetArchiveActionPerformed

    private void jMenuItemCalculteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCalculteActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameCalculate().setVisible(true);
    }//GEN-LAST:event_jMenuItemCalculteActionPerformed

    private void jMenuItemContractClientTradingDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContractClientTradingDetailsActionPerformed
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jLabelProgress.setText("正在转入当日成交明细......");
            DBFImporter11 myThread = new DBFImporter11(name, jPanel2, jLabelProgress);
            //SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItemContractClientTradingDetailsActionPerformed

    private void jMenuItemsettlementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemsettlementActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        SqlExecutor sqlx = new SqlExecutor();
        String msg = "正在清算......";
        String sql = "asset_clearing";
        System.out.println(sql);
        sqlx.execute(Main.conn, sql, msg);
    }//GEN-LAST:event_jMenuItemsettlementActionPerformed

    private void jMenuItemStockAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemStockAssetActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入客户证券资产数据......");
            DBFImporter14 myThread = new DBFImporter14(name, jPanel2, jLabelProgress);
            // SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItemStockAssetActionPerformed

    private void jMenuItemOrderDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOrderDataActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入当日委托数据......");
            DBFImporter15 myThread = new DBFImporter15(name, jPanel2, jLabelProgress);
            //SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItemOrderDataActionPerformed

    private void jMenuItemProfitCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProfitCalculateActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameProfitCalculate().setVisible(true);
}//GEN-LAST:event_jMenuItemProfitCalculateActionPerformed

    private void jMenuIteInvestmentAdviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIteInvestmentAdviceActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameProfitCalculate().setVisible(true);
}//GEN-LAST:event_jMenuIteInvestmentAdviceActionPerformed

    private void jMenuItemTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTransferActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入银证转账流水......");
            DBFImporter17 myThread = new DBFImporter17(name, jPanel2, jLabelProgress);
            //SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
}//GEN-LAST:event_jMenuItemTransferActionPerformed

    private void jMenuItemSQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSQLActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSql().setVisible(true);
}//GEN-LAST:event_jMenuItemSQLActionPerformed

    private void jMenuItemSetWorkFlowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSetWorkFlowActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSetWorkFlow().setVisible(true);
}//GEN-LAST:event_jMenuItemSetWorkFlowActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.out.println("Exit From Menu: ");
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItemMatchHhistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMatchHhistoryActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入历史成交明细......");
            DBFImporter19 myThread = new DBFImporter19(name, jPanel2, jLabelProgress);
            //SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
}//GEN-LAST:event_jMenuItemMatchHhistoryActionPerformed

    private void jMenuItemDataCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDataCheckActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameDataCheck().setVisible(true);
}//GEN-LAST:event_jMenuItemDataCheckActionPerformed

    private void jMenuItemClientMatchDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientMatchDetailsActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameCliantMatchDetails().setVisible(true);
    }//GEN-LAST:event_jMenuItemClientMatchDetailsActionPerformed

    private void jMenuItemFundBalanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFundBalanceActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入资金余额表......");
            DBFImporter21 myThread = new DBFImporter21(name, jPanel2, jLabelProgress);
            // SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
}//GEN-LAST:event_jMenuItemFundBalanceActionPerformed

    private void jMenuItemCallbackInDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCallbackInDetailsActionPerformed
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        if (callback_message_cnt > 0) {
            new JFrameCallbackInDetail().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "该版本不支持本功能!");
        }
    }//GEN-LAST:event_jMenuItemCallbackInDetailsActionPerformed

    private void jMenuItemSearchByCallbackDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSearchByCallbackDetailsActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        if (callback_message_cnt > 0) {
            new JFrameSearchByCallbackDetails().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "该版本不支持本功能!");
        }
    }//GEN-LAST:event_jMenuItemSearchByCallbackDetailsActionPerformed

    private void jMenuItemUserPermisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUserPermisionActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameUserPermision().setVisible(true);
}//GEN-LAST:event_jMenuItemUserPermisionActionPerformed

    private void jMenuItemAssignLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAssignLogActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameAssignLog().setVisible(true);
    }//GEN-LAST:event_jMenuItemAssignLogActionPerformed

    private void jMenuStaticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuStaticsActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jMenuStaticsActionPerformed

    private void jMenuItemSumAppreciationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSumAppreciationActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSumAppreciationReport().setVisible(true);
}//GEN-LAST:event_jMenuItemSumAppreciationActionPerformed

    private void jMenuItemClientProfitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientProfitActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameClientProfit().setVisible(true);
}//GEN-LAST:event_jMenuItemClientProfitActionPerformed

    private void jMenuItemFloatProfitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFloatProfitActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameFloatProfit().setVisible(true);
}//GEN-LAST:event_jMenuItemFloatProfitActionPerformed

    private void jMenuItemSumProfitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSumProfitActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameProfitSumAnalysis().setVisible(true);
}//GEN-LAST:event_jMenuItemSumProfitActionPerformed

    private void jMenuItemAssetIncrementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAssetIncrementActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameAssetIncrement().setVisible(true);
}//GEN-LAST:event_jMenuItemAssetIncrementActionPerformed

    private void jMenuItemProfitAnalysisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProfitAnalysisActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameBuySellProfitStatistics().setVisible(true);
}//GEN-LAST:event_jMenuItemProfitAnalysisActionPerformed

    private void jMenuItemServiceCheckReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemServiceCheckReportActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameServicePersonClientValue().setVisible(true);
}//GEN-LAST:event_jMenuItemServiceCheckReportActionPerformed

    private void jMenuItemSumClientValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSumClientValueActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSumClientValue().setVisible(true);
}//GEN-LAST:event_jMenuItemSumClientValueActionPerformed

    private void jMenuItemServiceCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemServiceCheckActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameServiceCheck().setVisible(true);
    }//GEN-LAST:event_jMenuItemServiceCheckActionPerformed

    private void jMenuItemCommisionDistributionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCommisionDistributionActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameReport1().setVisible(true);
}//GEN-LAST:event_jMenuItemCommisionDistributionActionPerformed

    private void jMenuItemServicePersonClientAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemServicePersonClientAssetActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameServicePersonAsssetReport().setVisible(true);
    }//GEN-LAST:event_jMenuItemServicePersonClientAssetActionPerformed

    private void jMenuItemAgentReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAgentReportActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameAgentReport().setVisible(true);
}//GEN-LAST:event_jMenuItemAgentReportActionPerformed

    private void jMenuItemWorkReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemWorkReportActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameWorkReport().setVisible(true);
}//GEN-LAST:event_jMenuItemWorkReportActionPerformed

    private void jMenuItemSatifactionDegreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSatifactionDegreeActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSatisfactionReport().setVisible(true);
}//GEN-LAST:event_jMenuItemSatifactionDegreeActionPerformed

    private void jMenuItemTotalAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTotalAssetActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameTotalAssetReport().setVisible(true);
}//GEN-LAST:event_jMenuItemTotalAssetActionPerformed

    private void jMenuItemTotalAssetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemTotalAssetMouseClicked
        // TODO add your handling code here
}//GEN-LAST:event_jMenuItemTotalAssetMouseClicked

    private void jMenuItemAssetStaticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAssetStaticsActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameReport().setVisible(true);
}//GEN-LAST:event_jMenuItemAssetStaticsActionPerformed

    private void jMenuItemAgentMonthReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAgentMonthReportActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameAgentMonthReport().setVisible(true);
    }//GEN-LAST:event_jMenuItemAgentMonthReportActionPerformed

    private void jMenuItemAgentMonthReport1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAgentMonthReport1ActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameAgentMonthReport1().setVisible(true);
    }//GEN-LAST:event_jMenuItemAgentMonthReport1ActionPerformed

    private void jMenuItemAutoAssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAutoAssignActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameAutoAssign().setVisible(true);
    }//GEN-LAST:event_jMenuItemAutoAssignActionPerformed

    private void jMenuSearchByAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSearchByAgeActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameSearchByAge().setVisible(true);
    }//GEN-LAST:event_jMenuSearchByAgeActionPerformed

    private void jMenuItemCreditClientInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCreditClientInfoActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入融资融券客户资料......");
            DBFImporter22 myThread = new DBFImporter22(name, jPanel2, jLabelProgress);
            // SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItemCreditClientInfoActionPerformed

    private void jMenuItemFinancingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFinancingActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入理财产品库存数据......");
            DBFImporter23 myThread = new DBFImporter23(name, jPanel2, jLabelProgress);
            // SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItemFinancingActionPerformed

    private void jMenuItemCreditClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCreditClientActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameCreditInfo().setVisible(true);
    }//GEN-LAST:event_jMenuItemCreditClientActionPerformed

    private void jMenuItemContractDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContractDataActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入签约手工记录数据......");
            DBFImporter24 myThread = new DBFImporter24(name, jPanel2, jLabelProgress);
            // SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItemContractDataActionPerformed

    private void jMenuItemLostClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLostClientActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameLostClient().setVisible(true);
    }//GEN-LAST:event_jMenuItemLostClientActionPerformed

    private void jMenuItemClientByTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientByTelActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameClientByTel().setVisible(true);
    }//GEN-LAST:event_jMenuItemClientByTelActionPerformed

    private void jMenuItemContractInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContractInfoActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameContractInfo().setVisible(true);
    }//GEN-LAST:event_jMenuItemContractInfoActionPerformed

    private void jMenuItemNewSumAnalysisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNewSumAnalysisActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameNewSumAnalysis().setVisible(true);
    }//GEN-LAST:event_jMenuItemNewSumAnalysisActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameKeyboardLock().setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameChangePassword().setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameServiceMemo().setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItemRoleControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRoleControlActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameRoleControl().setVisible(true);
}//GEN-LAST:event_jMenuItemRoleControlActionPerformed

    private void jMenuItemRolePermisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRolePermisionActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameRolePermision().setVisible(true);
    }//GEN-LAST:event_jMenuItemRolePermisionActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入回购客户资料......");
            DBFImporter25 myThread = new DBFImporter25(name, jPanel2, jLabelProgress);
            // SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入回购交易资料......");
            DBFImporter26 myThread = new DBFImporter26(name, jPanel2, jLabelProgress);
            // SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItemRepoDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRepoDetailsActionPerformed
        // TODO add your handling code here:
        new JFrameaRepoInfo().setVisible(true);
}//GEN-LAST:event_jMenuItemRepoDetailsActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入电子签约......");
            DBFImporter27 myThread = new DBFImporter27(name, jPanel2, jLabelProgress);
            // SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void clientList_byEsignDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientList_byEsignDateActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameClientlist_byEsignDate().setVisible(true);
    }//GEN-LAST:event_clientList_byEsignDateActionPerformed

    private void jMenuItemMarginTradingStokAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMarginTradingStokAssetActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameMarginTradingStockasset().setVisible(true);
}//GEN-LAST:event_jMenuItemMarginTradingStokAssetActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jPanel2.setVisible(true);
            jLabelProgress.setText("正在转入两融客户证券资产数据......");
            DBFImporter28 myThread = new DBFImporter28(name, jPanel2, jLabelProgress);
            // SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItemProductInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProductInfoActionPerformed
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameFinanceProductInfo().setVisible(true);
    }//GEN-LAST:event_jMenuItemProductInfoActionPerformed

    private void jMenuItemProductInfoQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProductInfoQueryActionPerformed
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameGetProductInfo().setVisible(true);
    }//GEN-LAST:event_jMenuItemProductInfoQueryActionPerformed

    private void jMenuItemProductClientQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProductClientQueryActionPerformed
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameProductClientList().setVisible(true);
    }//GEN-LAST:event_jMenuItemProductClientQueryActionPerformed

    private void jMenuItemClientProductQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientProductQueryActionPerformed
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameClientProductList().setVisible(true);
    }//GEN-LAST:event_jMenuItemClientProductQueryActionPerformed

    private void jMenuItemproductTradingQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemproductTradingQueryActionPerformed
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameProductTrading().setVisible(true);
    }//GEN-LAST:event_jMenuItemproductTradingQueryActionPerformed

    private void jMenuItemClientTradingQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientTradingQueryActionPerformed
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameClientProductTrading().setVisible(true);
    }//GEN-LAST:event_jMenuItemClientTradingQueryActionPerformed

    private void jMenuItemMultiQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMultiQueryActionPerformed
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameMultiQueey().setVisible(true);
    }//GEN-LAST:event_jMenuItemMultiQueryActionPerformed

    private void jMenuItemMultiSumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMultiSumActionPerformed
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        new JFrameMultiSum().setVisible(true);
    }//GEN-LAST:event_jMenuItemMultiSumActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jLabelProgress.setText("正在转入基金资产......");
            DBFImporter23 myThread = new DBFImporter23(name, jPanel2, jLabelProgress);
            //SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jLabelProgress.setText("正在转入基金交易数据......");
            DBFImporter29 myThread = new DBFImporter29(name, jPanel2, jLabelProgress);
            //SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        if (JFrameKeyboardLock.sysLocked) {
            return;
        }
        String name = DBFFChooser();
        if (name != null) {
            jLabelProgress.setText("正在转入理财产品信息......");
            DBFImporter30 myThread = new DBFImporter30(name, jPanel2, jLabelProgress);
            //SwingUtilities.invokeLater(myThread);
            myThread.start();
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private String DBFFChooser() {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.addChoosableFileFilter(new DBFFilter());
        String name = null;
        int result = chooser.showOpenDialog(null);
        //if file selected, open it and read it in to SQLServer
        if (result == JFileChooser.APPROVE_OPTION) {
            name = chooser.getSelectedFile().getPath();
        }
        return name;
    }
    private JFileChooser chooser;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem clientList_byEsignDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelProgress;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenuAdditionData;
    private javax.swing.JMenuItem jMenuAlalystReport;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuBasicData;
    private javax.swing.JMenu jMenuClientSearch;
    private javax.swing.JMenu jMenuDataConverter;
    private javax.swing.JMenu jMenuFinanceProduct;
    private javax.swing.JMenu jMenuInvestmenAdvisory;
    private javax.swing.JMenu jMenuIteInvestmentAdvice;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem1UdateClientInfo;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemAccurateSearch;
    private javax.swing.JMenuItem jMenuItemAgentMonthReport;
    private javax.swing.JMenuItem jMenuItemAgentMonthReport1;
    private javax.swing.JMenuItem jMenuItemAgentReport;
    private javax.swing.JMenuItem jMenuItemAssetArchive;
    private javax.swing.JMenuItem jMenuItemAssetIncrement;
    private javax.swing.JMenuItem jMenuItemAssetMonitor;
    private javax.swing.JMenuItem jMenuItemAssetSearch;
    private javax.swing.JMenuItem jMenuItemAssetStatics;
    private javax.swing.JMenuItem jMenuItemAssignCallback;
    private javax.swing.JMenuItem jMenuItemAssignLog;
    private javax.swing.JMenuItem jMenuItemAssignService;
    private javax.swing.JMenuItem jMenuItemAutoAssign;
    private javax.swing.JMenuItem jMenuItemBackup;
    private javax.swing.JMenuItem jMenuItemCalculte;
    private javax.swing.JMenuItem jMenuItemCallback;
    private javax.swing.JMenuItem jMenuItemCallbackInDetails;
    private javax.swing.JMenuItem jMenuItemCancelLog;
    private javax.swing.JMenuItem jMenuItemCjdbf;
    private javax.swing.JMenuItem jMenuItemClearMessage;
    private javax.swing.JMenuItem jMenuItemClientAgent;
    private javax.swing.JMenuItem jMenuItemClientByTel;
    private javax.swing.JMenuItem jMenuItemClientListByAgent;
    private javax.swing.JMenuItem jMenuItemClientListByBirthday;
    private javax.swing.JMenuItem jMenuItemClientMatchDetails;
    private javax.swing.JMenuItem jMenuItemClientNoTel;
    private javax.swing.JMenuItem jMenuItemClientProductQuery;
    private javax.swing.JMenuItem jMenuItemClientProfit;
    private javax.swing.JMenuItem jMenuItemClientQQ;
    private javax.swing.JMenuItem jMenuItemClientTelDBF;
    private javax.swing.JMenuItem jMenuItemClientTradingQuery;
    private javax.swing.JMenuItem jMenuItemCommisionDistribution;
    private javax.swing.JMenuItem jMenuItemContractClientTradingDetails;
    private javax.swing.JMenuItem jMenuItemContractData;
    private javax.swing.JMenuItem jMenuItemContractInfo;
    private javax.swing.JMenuItem jMenuItemContractMagement;
    private javax.swing.JMenuItem jMenuItemCreditClient;
    private javax.swing.JMenuItem jMenuItemCreditClientInfo;
    private javax.swing.JMenuItem jMenuItemDBFUser;
    private javax.swing.JMenuItem jMenuItemDataCheck;
    private javax.swing.JMenuItem jMenuItemDict;
    private javax.swing.JMenuItem jMenuItemFinancing;
    private javax.swing.JMenuItem jMenuItemFindAssignService;
    private javax.swing.JMenuItem jMenuItemFindCallback;
    private javax.swing.JMenuItem jMenuItemFindOrder;
    private javax.swing.JMenuItem jMenuItemFindStockBalance;
    private javax.swing.JMenuItem jMenuItemFloatProfit;
    private javax.swing.JMenuItem jMenuItemFundBalance;
    private javax.swing.JMenuItem jMenuItemFuzzySearch;
    private javax.swing.JMenuItem jMenuItemGrantServiceRelation;
    private javax.swing.JMenuItem jMenuItemIdCard;
    private javax.swing.JMenuItem jMenuItemInform;
    private javax.swing.JMenuItem jMenuItemInvestmentAdviser;
    private javax.swing.JMenuItem jMenuItemIportServicePersons;
    private javax.swing.JMenuItem jMenuItemIputStockPool;
    private javax.swing.JMenuItem jMenuItemLostClient;
    private javax.swing.JMenuItem jMenuItemMarginTradingStokAsset;
    private javax.swing.JMenuItem jMenuItemMarketMatchAmount;
    private javax.swing.JMenuItem jMenuItemMasterControl;
    private javax.swing.JMenuItem jMenuItemMatchHhistory;
    private javax.swing.JMenuItem jMenuItemMonthEndAsset;
    private javax.swing.JMenuItem jMenuItemMultiQuery;
    private javax.swing.JMenuItem jMenuItemMultiSum;
    private javax.swing.JMenuItem jMenuItemNewSumAnalysis;
    private javax.swing.JMenuItem jMenuItemOrderData;
    private javax.swing.JMenuItem jMenuItemProductClientQuery;
    private javax.swing.JMenuItem jMenuItemProductInfo;
    private javax.swing.JMenuItem jMenuItemProductInfoQuery;
    private javax.swing.JMenuItem jMenuItemProfitAnalysis;
    private javax.swing.JMenuItem jMenuItemProfitCalculate;
    private javax.swing.JMenuItem jMenuItemRealMontitor;
    private javax.swing.JMenuItem jMenuItemRepoDetails;
    private javax.swing.JMenuItem jMenuItemRestoreClient;
    private javax.swing.JMenuItem jMenuItemRestoreFromDBF;
    private javax.swing.JMenuItem jMenuItemRoleControl;
    private javax.swing.JMenuItem jMenuItemRolePermision;
    private javax.swing.JMenuItem jMenuItemSQL;
    private javax.swing.JMenuItem jMenuItemSatifactionDegree;
    private javax.swing.JMenuItem jMenuItemSearchByCallbackDate;
    private javax.swing.JMenuItem jMenuItemSearchByCallbackDetails;
    private javax.swing.JMenuItem jMenuItemSearchBySatisfaction;
    private javax.swing.JMenuItem jMenuItemSearchByServicePerson;
    private javax.swing.JMenuItem jMenuItemSearchBySummary;
    private javax.swing.JMenuItem jMenuItemService;
    private javax.swing.JMenuItem jMenuItemServiceCheck;
    private javax.swing.JMenuItem jMenuItemServiceCheckReport;
    private javax.swing.JMenuItem jMenuItemServicePersonClientAsset;
    private javax.swing.JMenuItem jMenuItemSetSysParameters;
    private javax.swing.JMenuItem jMenuItemSetWorkFlow;
    private javax.swing.JMenuItem jMenuItemStockAsset;
    private javax.swing.JMenuItem jMenuItemStockList;
    private javax.swing.JMenuItem jMenuItemStockPool1;
    private javax.swing.JMenuItem jMenuItemSumAppreciation;
    private javax.swing.JMenuItem jMenuItemSumClientValue;
    private javax.swing.JMenuItem jMenuItemSumContractedClient;
    private javax.swing.JMenuItem jMenuItemSumMatch;
    private javax.swing.JMenuItem jMenuItemSumMatch2;
    private javax.swing.JMenuItem jMenuItemSumProfit;
    private javax.swing.JMenuItem jMenuItemTotalAsset;
    private javax.swing.JMenuItem jMenuItemTransfer;
    private javax.swing.JMenuItem jMenuItemUserControl;
    private javax.swing.JMenuItem jMenuItemUserPermision;
    private javax.swing.JMenuItem jMenuItemWorkReport;
    private javax.swing.JMenuItem jMenuItemZcdbf;
    private javax.swing.JMenuItem jMenuItemZjdbf;
    private javax.swing.JMenuItem jMenuItemjMenuItemMessageManagement;
    private javax.swing.JMenuItem jMenuItemproductTradingQuery;
    private javax.swing.JMenuItem jMenuItemsearchServiceLog;
    private javax.swing.JMenuItem jMenuItemsettlement;
    private javax.swing.JMenu jMenuProfitAnalysis;
    private javax.swing.JMenuItem jMenuSearchByAge;
    private javax.swing.JMenu jMenuServiceCheck;
    private javax.swing.JMenu jMenuServiceManagement;
    private javax.swing.JMenu jMenuStatics;
    private javax.swing.JMenu jMenuSysControl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator28;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator30;
    private javax.swing.JSeparator jSeparator31;
    private javax.swing.JSeparator jSeparator32;
    private javax.swing.JSeparator jSeparator33;
    private javax.swing.JSeparator jSeparator34;
    private javax.swing.JSeparator jSeparator35;
    private javax.swing.JSeparator jSeparator36;
    private javax.swing.JSeparator jSeparator37;
    private javax.swing.JSeparator jSeparator38;
    private javax.swing.JSeparator jSeparator39;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator40;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextFieldRole;
    private javax.swing.JTextField jTextFieldUser;
    // End of variables declaration//GEN-END:variables
}


