package Modules;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.*;
import org.jfree.chart.title.TextTitle;

public class ServicePersonCommisionRateCurve extends JFrame {

    Vector dataSet = new Vector();

    public ServicePersonCommisionRateCurve(String s, Vector dataSet) {
        super(s);
        this.dataSet = dataSet;
        XYDataset xydataset = createDataset();
        JFreeChart jfreechart = createChart(xydataset);
        ChartPanel chartpanel = new ChartPanel(jfreechart, false);
           int month = dataSet.size();
        double zoom = 1.0;
        if (month <= 5) {
            zoom = 0.64;
        }
        if (month >= 6 && month <= 8) {
            zoom = 0.8;
        }
        if (month > 8) {
            zoom = 1.0;
        }
        chartpanel.setPreferredSize(new Dimension((int) (1000 * zoom), (int) 500));
        chartpanel.setMouseZoomable(true, false);
        setContentPane(chartpanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private static JFreeChart createChart(XYDataset xydataset) {
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("佣金率曲线", "年月", "‰", xydataset, true, true, false);
        TextTitle textTitle = jfreechart.getTitle();
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
        jfreechart.setBackgroundPaint(Color.white);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setDomainGridlinePaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.white);

        xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        xyplot.setDomainCrosshairVisible(true);
        xyplot.setRangeCrosshairVisible(true);
        org.jfree.chart.renderer.xy.XYItemRenderer xyitemrenderer = xyplot.getRenderer();
        if (xyitemrenderer instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyitemrenderer;
            xylineandshaperenderer.setBaseShapesVisible(true);
            xylineandshaperenderer.setBaseShapesFilled(true);
        }
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12));
        dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        // 设置图例中文字的字体，要求createBarChart3D中的是否图例必须为true
        jfreechart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        return jfreechart;
    }

    private XYDataset createDataset() {
        TimeSeries timeseries1 = new TimeSeries("服务员-客户平均佣金率", org.jfree.data.time.Month.class);
        TimeSeries timeseries2 = new TimeSeries("营业部平均佣金率", org.jfree.data.time.Month.class);
        int r = dataSet.size();
        for (int i = 0; i < r; i++) {
            Vector line = (Vector) dataSet.get(i);
            String year_month = line.get(0).toString();
            Float value1 = (Float) line.get(1);
            Float value2 = (Float) line.get(2);
            int year = Integer.valueOf(year_month.substring(0, 4));
            int month = Integer.valueOf(year_month.substring(4));
            timeseries1.add(new Month(month, year), value1);
            timeseries2.add(new Month(month, year), value2);
        }

        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries1);
        timeseriescollection.addSeries(timeseries2);
        return timeseriescollection;
    }

    public JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart(createDataset());
        return new ChartPanel(jfreechart);
    }
}

