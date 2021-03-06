package un.courcework.rtos.view.component.layout;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import un.courcework.rtos.model.MathFunction;
import un.courcework.rtos.model.MathFunctionValue;
import un.courcework.rtos.utils.MathUtils;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.MathFunctionTable;
import un.courcework.rtos.view.component.chart.FunctionChart;

public class LabOneLayout extends VerticalLayout {

    HorizontalLayout tablesLayout = new HorizontalLayout();

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

    public LabOneLayout(MathFunction mathFunction) {
        this.mathFunction = mathFunction;
        this.addStyleName("lab-one-layout");
    }

    @Override
    public void attach() {
        super.attach();
        addComponent(getContent());
    }

    private Component getContent () {
        VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.addComponent(getStepOneLayout(this.mathFunction));
        contentLayout.addComponent(getStepTwoLayout(this.mathFunction));

        Button showChartButton = new Button("Show chart");
        showChartButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                FunctionChart chartOne = new FunctionChart(
                        RtosUI.getCurrent().getDispatcher().getMathFunction(), Math.PI * 6, null);
                chartOne.setHeight(200, Unit.PIXELS);
                FunctionChart chartTwo = new FunctionChart(
                        RtosUI.getCurrent().getDispatcher().getMathFunction(), 72.0,  Math.PI / 12);
                chartTwo.setHeight(200, Unit.PIXELS);
                chartTwo.setPerLine( MathUtils.round(72.0 / 6, 2));
                Window window = new Window();
                window.setCaption("Charts");
                window.setWidth(900, Sizeable.Unit.PIXELS);
                window.center();
                VerticalLayout content = new VerticalLayout();
                content.setSpacing(true);
                content.addComponent(chartOne);
                content.addComponent(chartTwo);
                window.setContent(content);
                UI.getCurrent().addWindow(window);
            }
        });
        contentLayout.addComponent(showChartButton);
        return contentLayout;
    }

    private Component getStepOneLayout(MathFunction mathFunction) {

        VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);

        Double tStart = 0.0;
        MathFunctionValue[] f = new MathFunctionValue[3];
        Double tStep = LabOneLayout.tMax / 10;

        VerticalLayout  tMinTMaxLayout = new VerticalLayout();
        tMinTMaxLayout.setSpacing(true);

        tablesLayout.setSpacing(true);
        tMinTMaxLayout.addComponent(tablesLayout);

        for (int k = 0; k < 3; k++) {
            MathFunctionTable table = new MathFunctionTable(
                    mathFunction, tStart, LabOneLayout.tMax, tStep / (k + 1));
            tablesLayout.addComponent(table);
            f[k] = table.getMathFunctionValue();
        }

        if (Math.abs(f[1].getF() - f[2].getF()) < LabOneLayout.deltaFMin) {
            this.fMax = f[2];
            this.fMaxStep = tStep / 3;
        } else {
            MathFunctionTable table = new MathFunctionTable(
                    mathFunction, tStart, LabOneLayout.tMax, tStep / 5);
            tablesLayout.addComponent(table);
            MathFunctionValue value = table.getMathFunctionValue();
            this.fMax = value;
            this.fMaxStep = tStep / 5;
        }

        tMinTMaxLayout.addComponent(new Label("tMax = " + MathUtils.round(this.fMax.getT(), 3) ));
        tMinTMaxLayout.addComponent(new Label("fMax = " + MathUtils.round(this.fMax.getF(), 3) ));
        tMinTMaxLayout.addComponent(new Label("fMaxStep = " + MathUtils.round(this.fMaxStep, 3) ));

        content.addComponent(tMinTMaxLayout);
        return content;
    }

    private Component getStepTwoLayout(MathFunction mathFunction) {
        System.out.println("Step two");
        VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);

        Table table = new Table();
        table.setCaption(" ");
        table.setWidth(350, Unit.PIXELS);
        table.setSelectable(true);

        table.addContainerProperty("t", Double.class, 0.0);
        table.addContainerProperty("f(t)", Double.class, 0.0);
        table.addContainerProperty("f(t+1)", Double.class, 0.0);
        table.addContainerProperty("df", Double.class, 0.0);
        table.addContainerProperty("v", Double.class, 0.0);
        table.addContainerProperty("ht", Double.class, 0.0);

        tablesLayout.addComponent(table);

        Double h2step =  this.fMaxStep;
        Double v = 0.0;
        Double tv = 0.0;
        Double dfabs = (this.fMax.getF() * this.deltaFPercent ) / 100;
        Double tobr =  0.0;
        Double dtsys =  0.0;
        Double tper = 0.0;
        Double nper = 0.0;

        Boolean flag = true;

        do {
           // table.removeAllItems();
            v = 0.0;
            tv = 0.0;
            for (double i = 0; i < Math.PI; i += h2step) {
                double df1 = Math.abs(mathFunction.getValue(i + h2step) - mathFunction.getValue(i));
                double v1 = df1 / h2step;
                if (v < v1) {
                    v = v1;
                    tv = i;
                }
                table.addItem(
                    new Object[]{
                        i,
                        mathFunction.getValue(i),
                        mathFunction.getValue(i + 1),
                        df1,
                        v1,
                        h2step
                    },
                    new Double(i));
            }

            if ( Math.abs((mathFunction.getValue(tv) - mathFunction.getValue(tv - h2step))) > dfabs) {
                h2step /= 2;
                continue;
            } else if ( Math.abs((mathFunction.getValue(tv) - mathFunction.getValue(tv - h2step))) < dfabs / 10) {
                h2step *= 2;
                continue;
            } else {
                flag = false;
            }
        } while (flag);

        tobr =  h2step;
        dtsys =  tobr / 10;
        tper = Math.PI;
        nper = tper / dtsys;

        content.addComponent(new Label("fmax = " + this.fMax.getF()));
        content.addComponent(new Label("dFPercent = " + this.deltaFPercent));
        content.addComponent(new Label("dfabs = " + dfabs));
        content.addComponent(new Label("v = " + v));
        content.addComponent(new Label("tobr = " + tobr));
        content.addComponent(new Label("ht = " + h2step));
        content.addComponent(new Label("dtsys = " + dtsys));
        content.addComponent(new Label("tper = " + tper));
        content.addComponent(new Label("nper = " + nper));

        return content;
    }

}
