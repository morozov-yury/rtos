package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.core.dispatcher.Dispatcher;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class ExecMaxTextField extends AbstractParamTextField {

    public ExecMaxTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
        //setDescription("");
    }

    @Override
    public Object getTaskValue() {
        return super.getTask().gettExecMax();
    }


    @Override
    public void setTaskValue(Object value) {
        super.getTask().settExecMax( Integer.valueOf(value.toString()));
    }

    @Override
    public String getMessageError() {
        return "Параметр Т выполнения максимальное имет некоректное значение. " +
                "Он должен быть в диапазоне [0;" + super.getTask().gettPeriodCall() + "]";
    }

    @Override
    public boolean checkValue(Object value) {
        Integer intValue  = Integer.valueOf(value.toString());
        if ( intValue < 0 || intValue > Dispatcher.MODELLING_TIME
                || intValue > super.getTask().gettPeriodCall()
                || intValue < super.getTask().gettSession()) {
            return false;
        }
        return true;
    }
}
