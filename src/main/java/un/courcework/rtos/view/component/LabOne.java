package un.courcework.rtos.view.component;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 06.10.13
 * Time: 15:04
 * To change this template use File | Settings | File Templates.
 */
public class LabOne extends VerticalLayout {

    public static Double deltaFPercent = 5.0;
    public static Double deltaFMax = 100.0;
    public static Double deltaFMin = 0.01;

    private Double fMax;
    private Double vMax;
    private Double deltaTSampling;
    private Double deltaTFperiod;

    public LabOne() {
        addComponent(getContent());
    }

    private Component getContent () {
        VerticalLayout contentLayout = new VerticalLayout();



        return contentLayout;
    }

    private void doStepOne () {

    }
}
