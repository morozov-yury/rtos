package un.courcework.rtos.view.component.chart;

import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import un.courcework.rtos.model.MathFunction;

public class DiscreteFunctionChart extends FunctionChart {

    private final MathFunction mathFunction;
    private final Double length;
    private Double koef;

    public DiscreteFunctionChart(MathFunction mathFunction, Double length,  Double koef) {
        super(mathFunction, length, null);
        this.mathFunction = mathFunction;
        this.length = length;
        this.koef = koef;
    }

    @Override
    public DataSeries getDataSeries () {
        DataSeries series = new DataSeries();
        series.setName("cos(2t+1)");
        for (double i = 0.0; i < this.length; i += 0.1) {
            if (mathFunction.getValue(i  * koef) > 0) {
                series.add(new DataSeriesItem(i, 1.0));
            } else if (mathFunction.getValue(i  * koef) < 0) {
                series.add(new DataSeriesItem(i, 0));
            }
        }
        return series;
    }

}
