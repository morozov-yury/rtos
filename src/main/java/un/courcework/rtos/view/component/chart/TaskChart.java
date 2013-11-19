package un.courcework.rtos.view.component.chart;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.server.ThemeResource;

import java.util.ArrayList;

public class TaskChart  extends Chart {

    private Configuration conf;
    private Tooltip tooltip = new Tooltip();
    private PlotOptionsColumn plot;
    private DataSeries ls;
    private String titile;

    private int count;

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


        count = 0;
        setWidth("100%");


        conf.setTitle(this.titile);
        XAxis x = new XAxis();
        x.setTickInterval(1);
        x.setGridLineWidth(1);
        x.setMax(72);
        x.setMin(0);


        Labels labels = new Labels();
        //labels.setRotation(-90);
        labels.setAlign(HorizontalAlign.CENTER);
        Style style = new Style();
        style.setFontSize("9px");
        //style.setFontFamily("Verdana, sans-serif");
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
        //style.setFontFamily("Verdana, sans-serif");
        dataLabels.setStyle(style);

        plot.setDataLabels(dataLabels);

        conf.addSeries(ls);
        conf.setPlotOptions(plot);

        drawChart(conf);
        addPoint(0);
    }

    public void addPoint (Number value) {
        DataSeriesItem ds = new DataSeriesItem(count, value);
        Marker marker = new Marker(true);
        marker.setSymbol(MarkerSymbolEnum.TRIANGLE);;
        ds.setMarker(marker);
        if (count == 2)  {
            ds.setColor(SolidColor.GREEN);
        }

        if (count == 5)  {
            ds.setColor(SolidColor.RED);
            ds.setY(0.25);
        }

        if (count == 10)  {
            ds.setColor(SolidColor.GOLD);
        }
        ls.add(ds);
        count++;
    }

    public void removePoints () {
        ls.clear();
        count = 0;
    }


}
