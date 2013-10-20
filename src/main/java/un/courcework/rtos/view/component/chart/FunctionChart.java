package un.courcework.rtos.view.component.chart;


import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import un.courcework.rtos.model.MathFunction;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 27.09.13
 * Time: 13:18
 * To change this template use File | Settings | File Templates.
 */
public class FunctionChart extends Chart {

    private MathFunction mathFunction;

    public FunctionChart(MathFunction mathFunction) {
        super(ChartType.SPLINE);

        this.mathFunction = mathFunction;

        setWidth("100%");
        setHeight("200px");
        // Modify the default configuration a bit
        Configuration conf = getConfiguration();
        conf.setTitle("cos(2t+1)");
        // The data

        DataSeries series = new DataSeries();
        series.setName("cos(2t+1)");
        for (double i = 0; i < 55; i += 0.1) {
            series.add(new DataSeriesItem(i, mathFunction.getValue(i)));
        }

        conf.addSeries(series);

        XAxis xaxis;
        xaxis = new XAxis();
        xaxis.setTitle("t");
        xaxis.setTickInterval(3.14);
        xaxis.setGridLineWidth(1);
        conf.addxAxis(xaxis);
        // Set the Y axis title
        YAxis yaxis = new YAxis();
        yaxis.setTitle("f");
        yaxis.setExtremes(-1, 1);
        yaxis.setTickPixelInterval(15);
        conf.addyAxis(yaxis);

        conf.getLegend().setEnabled(false);

        PlotOptionsSpline plotOptions = new PlotOptionsSpline();
        plotOptions.setMarker(new Marker(false));

        plotOptions.setAnimation(false);
        plotOptions.setEnableMouseTracking(true);

        plotOptions.setLineWidth(1);
        conf.setPlotOptions(plotOptions);

//        Title title = new Title("Plot With Options");
//
//        AxesDefaults axesDefaults = new AxesDefaults()
//                .setLabelRenderer(LabelRenderers.CANVAS);
//
//        Axes axes = new Axes()
//                .addAxis(
//                        new XYaxis()
//                                .setLabel("X Axis")
//                                .setPad(0))
//                .addAxis(
//                        new XYaxis(XYaxes.Y)
//                                .setLabel("Y Axis"));
//
//        Options options = new Options()
//                .setAxesDefaults(axesDefaults)
//                .setAxes(axes);
//
//        DataSeries dataSeries = new DataSeries() ;
//
//        for (double i = 0; i < 55; i += 0.1) {
//            dataSeries.add(mathFunction.getValue(i));
//            //series.add(new DataSeriesItem(i, mathFunction.getValue(i)));
//        }
//                //.add(3, 7, 9, 1, 4, 6, 8, 2, 5);
//
////        DCharts chart = new DCharts()
////                .setDataSeries(dataSeries)
////                .setOptions(options)
////                .show();
//        setDataSeries(dataSeries);
//        setOptions(options);
//        show();
    }

}
