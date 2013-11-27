package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.core.dispatcher.Dispatcher;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class PriorityTextField extends AbstractParamTextField {
    public PriorityTextField(Task task) {
        super(task);
    }

    @Override
    public Object getTaskValue() {
        return super.getTask().getPriority();
    }

    @Override
    public void setTaskValue(Object value) {
        super.getTask().setPriority( Integer.valueOf(value.toString()));
    }

    @Override
    public String getMessageError() {
        return "Неверное значение приоритета. Он должен быть больше нуля";
    }

    @Override
    public boolean checkValue(Object value) {
        Integer intValue = Integer.valueOf(value.toString());
        if (intValue < 0) {
            return false;
        }
        return true;
    }
}
