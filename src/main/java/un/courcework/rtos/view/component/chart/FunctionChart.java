package un.courcework.rtos.view.component.chart;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 27.09.13
 * Time: 13:18
 * To change this template use File | Settings | File Templates.
 */
public class FunctionChart extends Chart {

    public FunctionChart() {
        super(ChartType.SPLINE);
        setWidth("100%");
        setHeight("300px");
        // Modify the default configuration a bit
        Configuration conf = getConfiguration();
        conf.setTitle("cos(2t+1)");
        // The data

        DataSeries series = new DataSeries();
        series.setName("cos(2t+1)");
        for (double i = 0; i < 50; i += 0.1) {
            series.add(new DataSeriesItem(i, Math.cos(i * 2 + 1)));
        }

        conf.addSeries(series);

        XAxis xaxis;
        xaxis = new XAxis();
        xaxis.setTitle("t");
        conf.addxAxis(xaxis);
        // Set the Y axis title
        YAxis yaxis = new YAxis();
        yaxis.setTitle("x");
        conf.addyAxis(yaxis);

        conf.getLegend().setEnabled(false);

        PlotOptionsSpline plotOptions = new PlotOptionsSpline();
        plotOptions.setMarker(new Marker(false));

        plotOptions.setAnimation(false);
        plotOptions.setEnableMouseTracking(true);

        plotOptions.setLineWidth(1);
        conf.setPlotOptions(plotOptions);
    }

}
