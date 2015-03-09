package Modules;
/**
 *
 * @author Administrator
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Vector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PieServicePersonCommision {

    int flag = 0;

    public PieServicePersonCommision(int flag) {
        this.flag = flag;
    }
    JFreeChart chart = null;
    Vector itemData = null;

    public void createPieChart(Vector data) {
        itemData = data;
        JFreeChart pieChart = null;
        if (flag == 1) {
            pieChart = ChartFactory.createPieChart("服务员-佣金比较图",
                    createDataset(data), true, true, false);
        }
        else {
                   pieChart = ChartFactory.createPieChart("服务员-资产比较图",
                    createDataset(data), true, true, false);  
        }
        pieChart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
        pieChart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
// RenderingHints做文字渲染参数的修改
        // VALUE_TEXT_ANTIALIAS_OFF表示将文字的抗锯齿关闭.
        pieChart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
//得到饼图的Plot对象
        PiePlot piePlot = (PiePlot) pieChart.getPlot();
        setSection(piePlot);//设置扇区颜色，可省略
        setLabel(piePlot);
        setNoDataMessage(piePlot);
        setNullAndZeroValue(piePlot);
        //将饼图显示在图像界面上
        ChartFrame frame = null;
        if (flag==1) {
             frame = new ChartFrame("服务员-佣金比较图", pieChart);
        }
        else {
          frame = new ChartFrame("服务员-资产比较图", pieChart);  
        }
        frame.pack();
        int iThisWidth = 1020;
        int iThisHight = 720;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - iThisWidth) / 2;
        int y = (screen.height - iThisHight) / 2-20;
        frame.setBounds(x, y, iThisWidth, iThisHight);
        frame.setVisible(true);
        this.chart = pieChart;
    }

    JFreeChart getChart() {
        return this.chart;
    }

    public static DefaultPieDataset createDataset(Vector data) {
        //设置数据
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        int r = data.size();
        for (int i = 0; i < r; i++) {
            Vector line = (Vector) data.get(i);
            String itemName = (String) line.get(0);
            Double ratio = (Double) line.get(1);
            pieDataset.setValue(itemName, ratio);
        }

        return pieDataset;
    }

    public void setSection(PiePlot pieplot) {
//设置扇区颜色
        int r = itemData.size();
        int[] color = {255, 190, 160, 128};
        int decrement = 30;
        for (int i = 0; i < r; i++) {
            Vector line = (Vector) itemData.get(i);
            String itemName = (String) line.get(0);
            if (i > 3) {
                int j = i % 4;
                color[j] = color[j] - decrement;
            }

        //pieplot.setSectionPaint(itemName, new Color(160, 160, color[i % 4]));
        }
//设置扇区分离显示
        // pieplot.setExplodePercent("篮球火", 0.2D);
        //设置扇区边框不可见
        pieplot.setSectionOutlinesVisible(false);
    }

    public void setLabel(PiePlot pieplot) {
//设置扇区标签显示格式：关键字：值(百分比)
        if (this.flag==1) {
        pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0}：{1}(元,佣金){2}"));
        }
        else {
            pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0}：{1}(元,资产){2}"));
        }
        //设置扇区标签颜色
        pieplot.setLabelBackgroundPaint(new Color(220, 220, 220));
        pieplot.setLabelFont((new Font("宋体", Font.PLAIN, 12)));

    }

    public static void setNoDataMessage(PiePlot pieplot) {
//设置没有数据时显示的信息
        pieplot.setNoDataMessage("无数据");
//设置没有数据时显示的信息的字体
        pieplot.setNoDataMessageFont(new Font("宋体", Font.BOLD, 14));
//设置没有数据时显示的信息的颜色
        pieplot.setNoDataMessagePaint(Color.red);
    }

    public static void setNullAndZeroValue(PiePlot piePlot) {
//设置是否忽略0和null值
        piePlot.setIgnoreNullValues(true);
        piePlot.setIgnoreZeroValues(true);
    }
}


