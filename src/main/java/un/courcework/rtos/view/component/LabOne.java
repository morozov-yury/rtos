package un.courcework.rtos.view.component;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
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
        contentLayout.addComponent(getMaxMathFunctionValueLayout(this.mathFunction));
        return contentLayout;
    }

    private Component getMaxMathFunctionValueLayout(MathFunction mathFunction) {

        VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);

        Double tStart = 0.0;
        MathFunctionValue[] f = new MathFunctionValue[3];
        Double tStep = LabOne.tMax / 10;

        HorizontalLayout  tMinTMaxLayout = new HorizontalLayout();
        tMinTMaxLayout.setSpacing(true);
        tMinTMaxLayout.addComponent(new Label("tMin = " + tStart + ";"));
        tMinTMaxLayout.addComponent(new Label("tMax = " + LabOne.tMax + ";"));

        HorizontalLayout tablesLayout = new HorizontalLayout();

        for (int k = 0; k < 3; k++) {
            f[k] = getMaxFunctionOnPeriod(mathFunction, tStart, LabOne.tMax, tStep / (k + 1));
        }
        //System.out.println("f = [" + f[0].getF() + ", " + f[1].getF() + ", " + f[2].getF() + "]");
        //System.out.println("t = [" + f[0].getT() + ", " + f[1].getT() + ", " + f[2].getT() + "]");
        if (Math.abs(f[0].getF() - f[1].getF()) < LabOne.deltaFMin) {
            //System.out.println("Math.abs(f[0].getF() - f[1].getF()) = " + Math.abs(f[0].getF() - f[1].getF()));
            //return  f[0];
        } else if (Math.abs(f[1].getF() - f[2].getF()) < LabOne.deltaFMin) {
            //System.out.println("Math.abs(f[1].getF() - f[2].getF()) = " + Math.abs(f[1].getF() - f[2].getF()));
            //return  f[1];
        } else {
            //return getMaxFunctionOnPeriod(mathFunction, tStart, LabOne.tMax, tStep / 5);
        }

        content.addComponent(tMinTMaxLayout);
        return content;
    }

    private MathFunctionValue getMaxFunctionOnPeriod (MathFunction mathFunction, Double tStart, Double tMax, Double tStep) {
        MathFunctionValue mathFunctionValue = new MathFunctionValue(0.0, 0.0);
        for(Double i = tStart; i < tMax; i += tStep) {
            if (mathFunctionValue.getF() < mathFunction.getValue(i)) {
                mathFunctionValue.setF(mathFunction.getValue(i));
                mathFunctionValue.setT(i);
            }
        }
        return mathFunctionValue;
    }

}
