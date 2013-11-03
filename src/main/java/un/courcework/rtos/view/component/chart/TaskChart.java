package un.courcework.rtos.view.component.chart;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.addon.charts.model.style.SolidColor;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 21.10.13
 * Time: 10:30
 * To change this template use File | Settings | File Templates.
 */
public class TaskChart  extends Chart {

    private Configuration conf = getConfiguration();
    private Tooltip tooltip = new Tooltip();
    private PlotOptionsColumn plot = new PlotOptionsColumn();
    private DataSeries ls = new DataSeries();

    private int count = 0;

    public TaskChart (String title) {
        super(ChartType.COLUMN);

        setWidth("100%");


        conf.setTitle(title);
        XAxis x = new XAxis();
        x.setTickInterval(1);
        x.setGridLineWidth(1);
        x.setMax(72);
        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setMin(0);
        y.setMax(2);
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

        conf.addSeries(ls);
        conf.setPlotOptions(plot);

        drawChart(conf);
        addPoint(0);
    }

    public void addPoint (Number value) {
        DataSeriesItem ds = new DataSeriesItem(count, value);
        Marker marker = new Marker();
        marker.setSymbol(
                new MarkerSymbolUrl("http://www.highcharts.com/demo/gfx/snow.png"));
        ds.setMarker(marker);
        ls.add(ds);



        count++;
    }

    public void removePoints () {
        ls.clear();
    }


}
