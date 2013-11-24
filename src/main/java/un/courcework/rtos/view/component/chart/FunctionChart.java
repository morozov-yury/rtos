package un.courcework.rtos.view.component.chart;


import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.addon.charts.model.style.Style;
import un.courcework.rtos.utils.MathUtils;
import un.courcework.rtos.model.FunctionChartType;
import un.courcework.rtos.model.MathFunction;

public class FunctionChart extends Chart {

    private MathFunction mathFunction;
    private Double length;
    private Double koef;

    private Double perLine = 1.0;

    public FunctionChart() {
        super(ChartType.SPLINE);
    }

    public FunctionChart(MathFunction mathFunction, Double length, Double koef) {
        this();
        this.length = length;
        this.koef = koef;
        this.mathFunction = mathFunction;
    }


    @Override
    public void attach() {
        super.attach();

        setWidth("100%");

        // Modify the default configuration a bit
        Configuration conf = getConfiguration();
        conf.getChart().setMargin(30, 10, 30, 40);
        conf.setTitle("Задача объекта");
        // The data

        conf.addSeries(getDataSeries());

        XAxis xaxis;
        xaxis = new XAxis();
        xaxis.setTitle("t");
        xaxis.setTickInterval(1);
        xaxis.setGridLineWidth(1);
        xaxis.setMax(this.length);
        xaxis.setMin(0.0);
        Labels labels = new Labels();
        labels.setAlign(HorizontalAlign.CENTER);
        Style style = new Style();
        style.setFontSize("9px");
        labels.setStyle(style);
        xaxis.setLabels(labels);
        conf.addxAxis(xaxis);
        // Set the Y axis title
        YAxis yaxis = new YAxis();
        yaxis.setTitle("f");
        yaxis.setMin(-1);
        yaxis.setMax(2);
        yaxis.setTickPixelInterval(50);
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

    public DataSeries getDataSeries () {
        DataSeries series = new DataSeries();
        series.setName("cos(2t+1)");
        for (double i = 0.0; i < this.length; i += 0.1) {
            if (koef != null) {
                series.add(new DataSeriesItem(i, mathFunction.getValue(i  * this.koef)));
            } else {
                series.add(new DataSeriesItem(i, mathFunction.getValue(i)));
            }
        }
        return series;
    }

}
