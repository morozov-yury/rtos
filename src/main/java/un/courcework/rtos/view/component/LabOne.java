package un.courcework.rtos.view.component;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
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
    public static Double tMax = Math.PI * 1.5;

    private Double fMax;
    private Double vMax;
    private Double deltaTSampling;
    private Double deltaTFperiod;

    public LabOne() {
        addComponent(getContent());
    }

    private Component getContent () {
        VerticalLayout contentLayout = new VerticalLayout();
        doStepOne();
        return contentLayout;
    }

    private void doStepOne () {
        Double tStart = 0.0;
        Double tStep = LabOne.tMax / 10;
        for(Double i = tStart; i < LabOne.tMax; i += tStep) {
            System.out.println("i=" + i);
        }
    }

    private Double f(Double t) {
        return Math.cos(2 * t + 1.0);
    }
}
