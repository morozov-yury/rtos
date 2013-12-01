package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class NSessionTextField extends AbstractParamTextField {

    public NSessionTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
    }

    @Override
    public Object getTaskValue() {
        return super.getTask().getnSession();
    }

    @Override
    public void setTaskValue(Object value) {
        super.getTask().setnSession( Integer.valueOf(value.toString()));
    }

    @Override
    public String getMessageError() {
        return "Номер сеанса не может быть отрицательным или нулевым";
    }

    @Override
    public boolean checkValue(Object value) {
        Integer intValue = Integer.valueOf(value.toString());
        if (intValue <= 0) {
            return false;
        }
        return true;
    }
}
