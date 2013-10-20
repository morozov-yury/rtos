package un.courcework.rtos.view.component;

import com.vaadin.ui.*;
import un.courcework.rtos.core.Utils;
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

    private MathFunctionValue fMax;
    private Double fMaxStep;
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
        contentLayout.addComponent(getStepOneLayout(this.mathFunction));
        contentLayout.addComponent(getStepTwoLayout(this.mathFunction));
        return contentLayout;
    }

    private Component getStepOneLayout(MathFunction mathFunction) {

        VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);

        Double tStart = 0.0;
        MathFunctionValue[] f = new MathFunctionValue[3];
        Double tStep = LabOne.tMax / 10;

        VerticalLayout  tMinTMaxLayout = new VerticalLayout();
        tMinTMaxLayout.setSpacing(true);

        HorizontalLayout tablesLayout = new HorizontalLayout();
        tablesLayout.setSpacing(true);
        tMinTMaxLayout.addComponent(tablesLayout);

        for (int k = 0; k < 3; k++) {
            MathFunctionTable table = new MathFunctionTable(mathFunction, tStart, LabOne.tMax, tStep / (k + 1));
            tablesLayout.addComponent(table);
            f[k] = table.getMathFunctionValue();
        }

        if (Math.abs(f[1].getF() - f[2].getF()) < LabOne.deltaFMin) {
            this.fMax = f[2];
            this.fMaxStep = tStep / 3;
        } else {
            MathFunctionTable table = new MathFunctionTable(mathFunction, tStart, LabOne.tMax, tStep / 5);
            tablesLayout.addComponent(table);
            MathFunctionValue value = table.getMathFunctionValue();
            this.fMax = value;
            this.fMaxStep = tStep / 5;
        }

        tMinTMaxLayout.addComponent(new Label("tMax = " + Utils.round(this.fMax.getT(), 3) ));
        tMinTMaxLayout.addComponent(new Label("fMax = " + Utils.round(this.fMax.getF(), 3) ));
        tMinTMaxLayout.addComponent(new Label("fMaxStep = " + Utils.round(this.fMaxStep, 3) ));

        content.addComponent(tMinTMaxLayout);
        return content;
    }

    private Component getStepTwoLayout(MathFunction mathFunction) {
        System.out.println("Step two");
        VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);

        Table table = new Table();
        table.setCaption(" ");
        table.setWidth(300, Unit.PIXELS);
        table.setSelectable(true);
        table.setImmediate(true);

        table.addContainerProperty("t", Double.class, 0.0);
        table.addContainerProperty("f(t)", Double.class, 0.0);
        table.addContainerProperty("df(t)", Double.class, 0.0);
        table.addContainerProperty("vdf(t)", Double.class, 0.0);
        table.addContainerProperty("ht", Double.class, 0.0);


        content.addComponent(table);

        Double h2step =  this.fMaxStep;

        Double v = 0.0;
        Double tv = 0.0;
        Double dfabs = (this.fMax.getF() * this.deltaFPercent ) / 100;

        Double tobr =  0.0;
        Double dtsys =  0.0;
        Double tper = 0.0;
        Double nper = 0.0;

        for (double i = 0; i < LabOne.tMax; i += h2step) {
            System.out.println("//////////// h2step = " + h2step);
            if ( (mathFunction.getValue(tv) - mathFunction.getValue(tv - h2step)) < dfabs) {
                h2step /= 2;
            }

            if ( (mathFunction.getValue(tv) - mathFunction.getValue(tv - h2step)) < dfabs / 10) {
                h2step *= 2;
            }

            if ( (dfabs / 10) < (mathFunction.getValue(tv) - mathFunction.getValue(tv - h2step)) ) {
                tobr =  h2step;
                dtsys =  tobr / 10;
                tper = Math.PI;
                nper = tper / dtsys;

                System.out.println("######## tobr = " + tobr);
                System.out.println("######## dtsys = " + dtsys);
                System.out.println("######## tper = " + tper);
                System.out.println("######## nper = " + nper);
            }

            double df1 = Math.abs(mathFunction.getValue(i + 1) - mathFunction.getValue(i));
            double v1 = df1 / h2step;
            if (v < v1) {
                v = v1;
                tv = i;
            }


            table.addItem(new Object[]{
                    i,
                    mathFunction.getValue(i),
                    (mathFunction.getValue(tv) - mathFunction.getValue(tv - h2step)),
                    0.0,
                    h2step
                },
                    new Double(i));


            System.out.println("///////////////////////////");
        }

        content.addComponent(new Label("tobr = " + tobr));
        content.addComponent(new Label("dtsys = " + dtsys));
        content.addComponent(new Label("tper = " + tper));
        content.addComponent(new Label("nper = " + nper));

        return content;
    }

}
