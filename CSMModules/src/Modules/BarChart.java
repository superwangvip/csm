/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Administrator
 */
public class BarChart extends JFrame {

    JFreeChart chart = null;
    int picType = 0;
    private static final long serialVersionUID = 1L;

    public BarChart(Vector data, int picType) {
        this.picType = picType;
        if (picType == 1) {
            setTitle("客户价值汇总:新增与流失客户资产对比");
        } else if (picType == 2) {
            setTitle("客户价值汇总:总资产");
        } else if (picType == 3) {
            setTitle("客户价值汇总:成交量柱形图");
        } else if (picType == 4) {
            setTitle("客户价值汇总:佣金柱形图");
        } else if (picType == 5) {
            setTitle("服务员-客户资产柱形图");
        } else if (picType == 6) {
            setTitle("服务员-客户佣金柱形图");
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        PanMain panMain = new PanMain(createDataSet(data), picType);
        this.chart = panMain.getChart();
      panMain.setToolTipText("将窗口极大化可以放大为全屏");

        add(panMain);
    }

    /**
     * 构建图表显示数据
     *
     * @return
     */
    private CategoryDataset createDataSet(Vector data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int r = data.size();
        for (int i = 0; i < r; i++) {
            Vector line = new Vector();
            line = (Vector) data.get(i);
            String yearMonth = (String) line.get(0);
            Double value1 = (Double) line.get(1);
            Double value2 = (Double) line.get(2);
            if (picType == 1) {
                dataset.setValue(value1, "新增", yearMonth);
                dataset.setValue(value2, "流失", yearMonth);
            } else if (picType == 2) {
                dataset.setValue(value1, "资产", yearMonth);
            } else if (picType == 3) {
                dataset.setValue(value1, "单月成交量", yearMonth);
                dataset.setValue(value2, "累计成交量", yearMonth);
            } else if (picType == 4) {
                dataset.setValue(value1, "单月佣金", yearMonth);
                dataset.setValue(value2, "累计佣金", yearMonth);
            } else if (picType == 5) {
                dataset.setValue(value1, "资产", yearMonth);
            } else if (picType == 6) {
                dataset.setValue(value1, "单月佣金", yearMonth);
                dataset.setValue(value2, "累计佣金", yearMonth);
            }
        }
        return dataset;
    }

    JFreeChart getChart() {
        return this.chart;
    }
}

class PanMain extends JPanel {

    JFreeChart chart = null;
    int picType = 0;
    private static final long serialVersionUID = 1L;

    public PanMain(CategoryDataset dataSet, int picType) {
        this.picType = picType;
        this.setLayout(new BorderLayout());// 边框布局，使生成的柱形图随面板大小变化
        if (picType == 1) {
            setBorder(BorderFactory.createTitledBorder("新增与流失客户资产"));
        } else if (picType == 2) {
            setBorder(BorderFactory.createTitledBorder("客户月平均总资产"));
        } else if (picType == 3) {
            setBorder(BorderFactory.createTitledBorder("成交量"));
        } else if (picType == 4){
            setBorder(BorderFactory.createTitledBorder("佣金"));
        } else if (picType == 5){
            setBorder(BorderFactory.createTitledBorder("服务员-客户资产"));
        } else if (picType == 6){
            setBorder(BorderFactory.createTitledBorder("服务员-客户佣金"));
        }
        ChartPanel p = new ChartPanel(createdBarChart(dataSet));
        add(p, BorderLayout.CENTER);
    }

    private JFreeChart createdBarChart(CategoryDataset dataSet) {
        JFreeChart jfreeChart = ChartFactory.createBarChart3D("客户总资产柱形图",// 图表标题
                "月份",// 目录轴(一般为横轴)标题
                "金额",// 数据轴(一般为纵轴)标题
                dataSet, // 数据集
                PlotOrientation.VERTICAL, // 图表方向
                true,// 是否显示图例(对于简单的柱状图必须是false)
                false,// 是否生成工具
                false// 是否生成URL链接
                );
        // 设置柱形图标题及标题字体
        if (picType == 1) {
            jfreeChart.setTitle(new TextTitle("新增与流失客户资产对比柱形图", new Font("黑体", Font.PLAIN, 14)));
        } else if (picType == 2) {
            jfreeChart.setTitle(new TextTitle("客户总资产柱形图", new Font("黑体", Font.PLAIN, 14)));
        } else if (picType == 3) {
            jfreeChart.setTitle(new TextTitle("成交量柱形图", new Font("黑体", Font.PLAIN, 14)));
        } else if (picType == 4) {
            jfreeChart.setTitle(new TextTitle("佣金柱形图", new Font("黑体", Font.PLAIN, 14)));
        }else if (picType == 5) {
            jfreeChart.setTitle(new TextTitle("服务员-客户资产柱形图", new Font("黑体", Font.PLAIN, 14)));
        }else if (picType == 6) {
            jfreeChart.setTitle(new TextTitle("服务员-客户佣金柱形图", new Font("黑体", Font.PLAIN, 14)));
        }

        // 获得图表，以进行更多属性设置
        CategoryPlot plot = (CategoryPlot) jfreeChart.getPlot();

        // 获得目录轴
        CategoryAxis cAxis = plot.getDomainAxis();

        // 设置目录轴坐标上文字的字体
        cAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));

        // 设置目录轴轴的标题文字的字体
        cAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));

        // 获得数据轴
        ValueAxis vAxis = plot.getRangeAxis();

        // 设置数据轴坐标上的文字的字体
        vAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));

        // 设置数据轴的标题文字的字体
        vAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));

        // 设置图例中文字的字体，要求createBarChart3D中的是否图例必须为true
        jfreeChart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));

        // 渲染柱形图的样式
        BarRenderer3D renderer = new BarRenderer3D();

        renderer.setBaseOutlinePaint(Color.BLACK);

        // 设置 3D效果围墙的颜色
        renderer.setWallPaint(Color.gray);

        // 设置柱的颜色

        if (picType == 1) {
            renderer.setSeriesPaint(0, Color.red);// 第一个柱形
            renderer.setSeriesPaint(1, Color.GREEN);// 第二个柱形
        } else if (picType == 2) {
            renderer.setSeriesPaint(0, Color.orange);// 第一个柱形
        } else if (picType == 3) {
            renderer.setSeriesPaint(0, Color.orange);// 第一个柱形
            renderer.setSeriesPaint(1, Color.magenta);// 第二个柱形
        } else if (picType == 4) {
            renderer.setSeriesPaint(0, Color.pink);// 第一个柱形
            renderer.setSeriesPaint(1, Color.red);// 第二个柱形
        } else if (picType == 5) {
            renderer.setSeriesPaint(0, Color.orange);// 第一个柱形
        } else if (picType == 6) {
            renderer.setSeriesPaint(0, Color.pink);// 第一个柱形
            renderer.setSeriesPaint(1, Color.red);// 第二个柱形
        }

        // 设置柱的轮廓 颜色
        renderer.setSeriesOutlinePaint(0, Color.blue);
        renderer.setSeriesOutlinePaint(1, Color.red);

        // 设置平行柱的之间距离
        renderer.setItemMargin(0.1);

        // 显示每个柱的数值，并修改该数值的字体属性
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);

        // 使图表使用渲染效果
        plot.setRenderer(renderer);

        // 设置柱的透明度
        plot.setForegroundAlpha(0.8f);

        this.chart = jfreeChart;
        return jfreeChart;
    }

    JFreeChart getChart() {
        return this.chart;
    }
}