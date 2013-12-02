package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.core.dispatcher.Dispatcher;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class VaitTextField extends AbstractParamTextField {

    public VaitTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
        //setDescription("");

    }

    @Override
    public Object getTaskValue() {
        return super.getTask().gettVaitMax();
    }

    @Override
    public void setTaskValue(Object value) {
        super.getTask().settVaitMax( Integer.valueOf(value.toString()));
    }

    @Override
    public String getMessageError() {
        return "Должно быть [1; Tп]: [1;" +
                super.getTask().gettPeriodCall() + "]";
    }

    @Override
    public boolean checkValue(Object value) {
        Integer intValue = Integer.valueOf(value.toString());
        if (intValue < 1 || intValue > Dispatcher.MODELLING_TIME
                || intValue > super.getTask().gettPeriodCall()) {
            return false;
        }
        return true;
    }
}
