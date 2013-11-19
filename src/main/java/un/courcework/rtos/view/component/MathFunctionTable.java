package un.courcework.rtos.view.component;

import com.vaadin.ui.Table;
import un.courcework.rtos.utils.MathUtils;
import un.courcework.rtos.model.MathFunction;
import un.courcework.rtos.model.MathFunctionValue;

public class MathFunctionTable extends Table {

    MathFunctionValue mathFunctionValue = new MathFunctionValue(0.0, 0.0);

    public MathFunctionTable(MathFunction mathFunction, Double tStart, Double tMax, Double tStep) {

        setCaption("Step = " + MathUtils.round(tStep, 3) );
        setWidth(200, Unit.PIXELS);
        setSelectable(true);

        addContainerProperty("t", Double.class, 0.0);
        addContainerProperty("f(t)", Double.class, 0.0);

        for(Double i = tStart; i < tMax; i += tStep) {
            if (this.mathFunctionValue.getF() < mathFunction.getValue(i)) {
                this.mathFunctionValue.setF(mathFunction.getValue(i));
                this. mathFunctionValue.setT(i);
            }
            addItem(new Object[]{i, mathFunction.getValue(i)}, new Double(i));
        }

    }

    public MathFunctionValue getMathFunctionValue () {
        return this.mathFunctionValue;
    }

}
