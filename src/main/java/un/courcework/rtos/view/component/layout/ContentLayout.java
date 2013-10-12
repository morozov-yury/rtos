package un.courcework.rtos.view.component.layout;

import com.vaadin.addon.charts.Chart;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import un.courcework.rtos.view.MyVaadinUI;
import un.courcework.rtos.view.component.LabOne;
import un.courcework.rtos.view.component.chart.FunctionChart;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 06.10.13
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public class ContentLayout extends VerticalLayout {

    public ContentLayout() {
        setMargin(true);
        setWidth(1000, Sizeable.Unit.PIXELS);

        Chart chart = new FunctionChart(MyVaadinUI.getCurrent().getMathFunction());
        addComponent(chart);
        addComponent(new LabOne(MyVaadinUI.getCurrent().getMathFunction()));
    }
}

