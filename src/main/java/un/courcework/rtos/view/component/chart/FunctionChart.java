package un.courcework.rtos.view.component.chart;


import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import un.courcework.rtos.core.Utils;
import un.courcework.rtos.model.FunctionChartType;
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
    private FunctionChartType functionChartType;
    private Double length;
    private Double koef;

    private Double perLine = Utils.round(Math.PI, 3);

    public FunctionChart() {
        super(ChartType.SPLINE);
    }

    public FunctionChart(MathFunction mathFunction, Double length, Double koef, FunctionChartType chartType) {
        this();
        this.functionChartType = chartType;
        this.length = length;
        this.koef = koef;
        this.mathFunction = mathFunction;
    }

    public FunctionChart(MathFunction mathFunction, Double length, Double koef) {
        this();
        this.length = length;
        this.koef = koef;
        this.mathFunction = mathFunction;
        this.functionChartType = FunctionChartType.ANALOG;
    }


    @Override
    public void attach() {
        super.attach();

        setWidth("100%");

        // Modify the default configuration a bit
        Configuration conf = getConfiguration();
        conf.getChart().setMargin(30, 10, 30, 40);
        conf.setTitle("cos(2t+1)");
        // The data

        DataSeries series = new DataSeries();
        series.setName("cos(2t+1)");
        for (double i = 0.0; i < this.length; i += 0.1) {
            if (koef != null) {
                if (this.functionChartType == FunctionChartType.DISCRETE)    {
                      if (mathFunction.getValue(i  * this.koef) > 0) {
                          series.add(new DataSeriesItem(i, 1.0));
                      } else if (mathFunction.getValue(i  * this.koef) < 0) {
                          series.add(new DataSeriesItem(i, 0));
                      }
                } else {
                    series.add(new DataSeriesItem(i, mathFunction.getValue(i  * this.koef)));
                }
            } else {
                if (this.functionChartType == FunctionChartType.DISCRETE)    {
                    if (mathFunction.getValue(i  * this.koef) > 0) {
                        series.add(new DataSeriesItem(i, 1.0));
                    } else if (mathFunction.getValue(i  * this.koef) < 0) {
                        series.add(new DataSeriesItem(i, 0));
                    }
                } else {
                    series.add(new DataSeriesItem(i, mathFunction.getValue(i)));
                }
            }
        }

        conf.addSeries(series);

        XAxis xaxis;
        xaxis = new XAxis();
        xaxis.setTitle("t");
        xaxis.setTickInterval(perLine);
        xaxis.setGridLineWidth(1);
        xaxis.setMax(this.length);
        xaxis.setMin(0.0);
        conf.addxAxis(xaxis);
        // Set the Y axis title
        YAxis yaxis = new YAxis();
        yaxis.setTitle("f");
        if (functionChartType == FunctionChartType.DISCRETE) {
            yaxis.setMin(0.0);
        } else {
            yaxis.setMin(-1.1);
        }
        yaxis.setMax(2);
        //yaxis.setTickPixelInterval(50);
        conf.addyAxis(yaxis);

        conf.getLegend().setEnabled(false);

        PlotOptionsSpline plotOptions = new PlotOptionsSpline();
        plotOptions.setMarker(new Marker(false));

        plotOptions.setAnimation(false);
        plotOptions.setEnableMouseTracking(true);

        plotOptions.setLineWidth(1);
        conf.setPlotOptions(plotOptions);
    }

    public void setPerLine(Double perLine) {
        this.perLine = perLine;
    }
}
