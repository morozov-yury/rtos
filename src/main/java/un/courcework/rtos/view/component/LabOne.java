package un.courcework.rtos.view.component;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import un.courcework.rtos.model.MathFunction;
import un.courcework.rtos.model.MathFunctionValue;

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

    private MathFunction mathFunction;

    private Double fMax;
    private Double tfMax;
    private Double vMax;
    private Double deltaTSampling;
    private Double deltaTFperiod;

    public LabOne(MathFunction mathFunction) {
        this.mathFunction = mathFunction;
        System.out.println("### LabOne ########################################");
        addComponent(getContent());
    }

    private Component getContent () {
        VerticalLayout contentLayout = new VerticalLayout();
        doStepOne();
        return contentLayout;
    }

    private void doStepOne () {
        Double tStart = 0.0;
        Double[] f = new Double[] {0.0, 0.0, 0.0};
        Double[] t = new Double[] {0.0, 0.0, 0.0};
        Double tStep = LabOne.tMax / 10;
        System.out.println("---------------------------------------------------");
        for (int k = 0; k < 3; k++) {
            for(Double i = tStart; i < LabOne.tMax; i += tStep / (k + 1)) {
                if (f[k] < this.mathFunction.getValue(i)) {
                    f[k] =  this.mathFunction.getValue(i);
                    t[k] = i;
                }
            }
        }
        System.out.println("f = [" + f[0] + ", " + f[1] + ", " + f[2] + "]");
        System.out.println("t = [" + t[0] + ", " + t[1] + ", " + t[2] + "]");
    }

    private MathFunctionValue getMaxFunctionOnPeriod (Double tStart, Double tMax, Double tStep) {
        MathFunctionValue mathFunctionValue = new MathFunctionValue(0.0, 0.0);
        for(Double i = tStart; i < tMax; i += tStep) {
            if (mathFunctionValue.getF() < this.mathFunction.getValue(i)) {
                mathFunctionValue.setF(this.mathFunction.getValue(i));
                mathFunctionValue.setT(i);
            }
        }
        return mathFunctionValue;
    }

}
