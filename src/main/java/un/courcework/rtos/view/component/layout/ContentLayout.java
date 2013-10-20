package un.courcework.rtos.view.component.layout;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.VerticalLayout;
import org.dussan.vaadin.dcharts.DCharts;
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

        DCharts chartOne = new FunctionChart(MyVaadinUI.getCurrent().getMathFunction());
        DCharts chartTwo = new FunctionChart(MyVaadinUI.getCurrent().getMathFunction());
        addComponent(chartOne);
        addComponent(chartTwo);
        addComponent(new LabOne(MyVaadinUI.getCurrent().getMathFunction()));
    }
}

