package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.core.dispatcher.Dispatcher;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.StringUtils;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class EndIntTextField extends AbstractParamTextField {

    public EndIntTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
        setDescription(StringUtils.makeBoldString("Тн < Тк < 72"));
    }

    @Override
    public Object getTaskValue() {
        return super.getTask().gettEndIntActive();
    }

    @Override
    public void setTaskValue(Object value) {
        super.getTask().settEndIntActive( Integer.valueOf(value.toString()));
    }

    @Override
    public String getMessageError() {
        return "Параметр Тк имет некоректное значение. Он должен быть в диапазоне [0;"
                + Dispatcher.MODELLING_TIME + "]";
    }

    @Override
    public boolean checkValue(Object value) {
        Integer intValue  = Integer.valueOf(value.toString());
        if ( intValue < 0 || intValue > Dispatcher.MODELLING_TIME) {
            return false;
        }
        return true;
    }
}
