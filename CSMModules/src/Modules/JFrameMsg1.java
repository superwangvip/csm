/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameMsg1.java
 *
 * Created on 2011-3-13, 14:44:32
 */
package Modules;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Administrator
 */
public class JFrameMsg1 extends javax.swing.JFrame {

    /** Creates new form JFrameMsg1 */
    public JFrameMsg1() {
        initComponents();
        int iThisWidth = 802;
        int iThisHight = 600;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2;
        this.setBounds(x, y, iThisWidth, iThisHight);
        jTextArea1.setEditable(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("关于证券客户盈亏分析的算法说明");

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jTextArea1.setText("一、关于“买卖盈亏”\n\n\n本系统所说的“买卖盈亏”是指在某时间段内存量客户反复买卖股票所带来的价差损益，分如下几种情况：\n\n1.在该段时期内，客户对某股票的总买入量=总卖出量，股份余额为零，此时，有价差损益，无浮动盈亏；\n\n2.在该段时期内，客户对某股票的总买入量>总卖出量，股份余额>0,  此时既有价差损益又有浮动盈亏；\n\n3.在该段时期内，客户对某股票的总买入量<总卖出量，股份余额<0,此时多卖出的部分必定是前期就持有的股票，只能计算出本段\n\n  时期内买入并全部卖出的那部分的价差损益；\n\n4.客户卖出了在该段时期内并未买入的股票，必定是前期就持有的股票，系统无法确定其购买成本，所以这部分卖出可能为客户带\n\n  来的盈亏就无法计算，只能被系统忽略。\n\n     所以“买卖盈亏”仅反映在指定时期内的交易活跃客户的部分资产损益，只有下面的“资产增值”能够较全面的反映客户的\n\n 真实投资收益,同时必须注意到“买卖盈亏”与导入系统的成交明细密切相关，当查询跨度超越系统保留的成交数据的时间跨度时，\n\n“买卖盈亏”就会缺失超越跨度的那部分。\n\n\n二、关于“资产增值率”\n\n \n客户投资股市的收益情况可以用“资产增值率”来表示：\n\n系统查询到的客户“资产增值历史”所涉及到的概念与计算方法如下：\n\n1.统计的“期初”是指客户的“月末资产”大于5000元的第一个月，“期末”是指统计截止月；\n\n2.资产增减=期末资产-期初资产；\n\n3.资金增减率%=资产增减*100/期初资产：仅反映客户资产的表面上的增减幅度；\n\n4.资产增值=资产增减-从期初至期末之间的资金累计净流[不含期初，要含期末]：指从期初到期末客户的投资收益；\n\n5.追加投入=从期初至期末之间的资金累计净流[不含期初，要含期末，且为正时才计入计算增值率的分母]：指从期\n  初到期末之间客户通过银证转账追加的资金净投入；\n\n6.资产增值率%=资产增值*100/(期初资产+追加投入)：反映客户从期初到期末这段时间内的全部资产投入的收益率。\n\n      由于营业部客户资产状况及其变化过程十分复杂，有太多的特殊情况难以预计，所以计算资产增值率时忽略\n\n  了连续多个月资产平均值不足5000元的客户，经统计可知，这部分客户的资产占比 在0.2%--0.4%之间，对于统计\n\n  而言是可以接受的。\n\n三、关于“浮动盈亏”\n\n  某股票的浮动盈亏=客户持有该股票数量*(最近价-成本价),反映客户当前所持股票的盈亏情况，本系统所引用的\n\n“成本价”来自柜台交易系统的计算结果。\n\n\n四、关于存量客户资产增值统计的算法证明\n\n1.证券客户资产构成：资金+股票市值；\n\n2.导致存量客户资产变动的因素:\n\n a.资金净流(S_flow,转入为正，转出为负) = 转入资金合计-转出资金合计；\n\n b.股票买卖差价（S_difference,盈利为正，亏损为负） = 卖出结算金合计-买入结算金合计,其中结算金额已扣除各项费用；\n\n c.所持股票的浮动盈亏(S_fluctuate)；\n\n d.股票分红（红股与红利,S_bonus）；\n\n e.闲余资金利息(S_interest)；\n\n3.客户资产与各影响因素的数量关系：\n\n  客户资产=资金余额+股票市值；\n\n  客户资产增减=a.资金净流+b.股票买卖差价+c.所持股票的浮动盈亏+d.股票分红+e.资金利息；\n\n显然，只有b.股票买卖差价与c.所持股票的浮动盈亏是我们关心的客户盈亏，是客户资产增值的主要来源，\n\n虽然客户资产增减是上述诸多因素综合作用的结果，但我们可以肯定由交易系统获得的客户资产总值是正确\n\n的，所以如果取得客户第m月与第n月的“月末资产”:Sm与Sn(其中 n>m )，则可以直接计算出客户的资产增减：\n\n   △S=Sn-Sm\n\n而 Sn=Sm+(S_flow_n+ S_difference_n+ S_fluctuate_n) ,(其中 S_bonus与S_interest可以忽略不计，\n\n下标_n表示第n 月)\n\n则, (S_difference_n+S_fluctuate_n)=(Sn-Sm)-S_flow=△S- S_flow_n,\n\n其中S_flow_n=SUM(转入资金)-SUM(转出资金)\n\n\n  即客户资产增值可以用资产增减扣除资金净流的方法得到，本系统即采用上述方法来计算客户资产增值的。 ");
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("取消");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(712, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new JFrameMsg1().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}