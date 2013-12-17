package un.courcework.rtos.view.component.chart;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskChart  extends Chart {

    private static Logger log = LoggerFactory.getLogger(TaskChart.class);

    public static double ZERO_VALUE = 0.0;
    public static double MAX_VALUE = 2.0;
    public static double TASK_VALUE = 1.5;
    public static double MARK_VALUE = 0.5;

    private Configuration conf;
    private Tooltip tooltip = new Tooltip();
    private PlotOptionsColumn plot;
    private volatile DataSeries ls;
    private String titile;

    private int count = 0;

    public TaskChart (String title) {
        super(ChartType.COLUMN);
        this.titile = title;
        init ();
    }

    private void init () {

        this.conf = getConfiguration();
        this.tooltip = new Tooltip();
        this.plot = new PlotOptionsColumn();
        this.ls = new DataSeries();
        conf.getChart().setMargin(30, 10, 30, 40);
        setWidth("100%");

        conf.setTitle(this.titile);
        XAxis x = new XAxis();
        x.setTickInterval(1);
        x.setGridLineWidth(1);
        x.setMax(72);
        x.setMin(0);

        Labels labels = new Labels();
        labels.setAlign(HorizontalAlign.CENTER);
        Style style = new Style();
        style.setFontSize("9px");
        labels.setStyle(style);
        x.setLabels(labels);

        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setMin(0);
        y.setMax(2);
        y.setTitle("Активность");
        conf.addyAxis(y);

        Legend legend = new Legend();
        legend.setLayout(LayoutDirection.HORIZONTAL);
        legend.setBackgroundColor("#FFFFFF");
        legend.setHorizontalAlign(HorizontalAlign.RIGHT);
        legend.setVerticalAlign(VerticalAlign.BOTTOM);
        legend.setX(1);
        legend.setY(72);
        legend.setFloating(true);
        legend.setShadow(false);

        conf.setLegend(legend);

        tooltip.setFormatter("this.x");
        conf.setTooltip(tooltip);

        plot.setPointPadding(0.2);
        plot.setBorderWidth(1);
        plot.setAnimation(false);
        plot.setEnableMouseTracking(true);
        plot.setPointPlacement(PointPlacement.BETWEEN);
        plot.setPointWidth(12);

        Labels dataLabels = new Labels();
        dataLabels.setEnabled(true);
        dataLabels.setRotation(-90);
        dataLabels.setColor(new SolidColor(255, 255, 255));
        dataLabels.setAlign(HorizontalAlign.CENTER);
        dataLabels.setX(4);
        dataLabels.setY(10);
        dataLabels.setFormatter("this.x");
        style = new Style();
        style.setFontSize("10px");
        dataLabels.setStyle(style);

        plot.setDataLabels(dataLabels);

        conf.addSeries(ls);
        conf.setPlotOptions(plot);

        drawChart(conf);

        DataSeriesItem ds = new DataSeriesItem(0, 0);
        Marker marker = new Marker(true);
        ds.setMarker(marker);
        ls.add(ds);
    }

    public void addPoint (Number posotion, Number value) {
        DataSeriesItem ds = new DataSeriesItem(posotion, value);
        Marker marker = new Marker(true);
        ds.setMarker(marker);
        this.getUI().getSession().getLockInstance().lock();
        try {
            ls.add(ds);
        } finally {
            this.getUI().getSession().getLockInstance().unlock();
        }
    }

    public synchronized void addPoint (Number position, Number value, SolidColor solidColor) {
        if (position.intValue() < 0) {
            return;
        }
        DataSeriesItem ds = new DataSeriesItem(position, value);
        Marker marker = new Marker(true);
        ds.setMarker(marker);
        ds.setColor(solidColor);
        this.getUI().getSession().getLockInstance().lock();
        try {
            ls.add(ds);
        } finally {
            this.getUI().getSession().getLockInstance().unlock();
        }
    }

}
