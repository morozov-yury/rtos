package un.courcework.rtos.view.component;

import com.vaadin.ui.Table;
import un.courcework.rtos.core.Utils;
import un.courcework.rtos.model.MathFunction;
import un.courcework.rtos.model.MathFunctionValue;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 20.10.13
 * Time: 23:39
 * To change this template use File | Settings | File Templates.
 */
public class MathFunctionTable extends Table {

    MathFunctionValue mathFunctionValue = new MathFunctionValue(0.0, 0.0);

    public MathFunctionTable(MathFunction mathFunction, Double tStart, Double tMax, Double tStep) {

        setCaption("Step = " + Utils.round(tStep, 3) );
        setWidth(200, Unit.PIXELS);
        setSelectable(true);
        setImmediate(true);

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
