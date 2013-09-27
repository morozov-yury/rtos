package un.courcework.rtos.view;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {

            }
        });
        layout.addComponent(button);

        Chart chart = new Chart(ChartType.SPLINE);
        chart.setWidth("100%");
        chart.setHeight("300px");
        // Modify the default configuration a bit
        Configuration conf = chart.getConfiguration();
        conf.setTitle("cos(2t+1)");
        // The data

        DataSeries series = new DataSeries();
        series.setName("cos(2t+1)");
        for (double i = 0; i < 50; i += 0.1) {
            series.add(new DataSeriesItem(i, Math.cos(i * 2 + 1)));
        }


// Modify the color of one point
        //series.get(1960).getMarker().setFillColor(SolidColor.RED);
        conf.addSeries(series);


        XAxis xaxis;
        xaxis = new XAxis();
        xaxis.setTitle("t");
        conf.addxAxis(xaxis);
        // Set the Y axis title
        YAxis yaxis = new YAxis();
        yaxis.setTitle("x");
        conf.addyAxis(yaxis);


        //conf.getTooltip().setEnabled(false);
        conf.getLegend().setEnabled(false);

        PlotOptionsSpline plotOptions = new PlotOptionsSpline();
        plotOptions.setMarker(new Marker(false));

        plotOptions.setAnimation(false);
        plotOptions.setEnableMouseTracking(true);



        plotOptions.setLineWidth(1);
        conf.setPlotOptions(plotOptions);

        layout.addComponent(chart);

    }

}
