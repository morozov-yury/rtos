package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.core.dispatcher.Dispatcher;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.StringUtils;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class TSessionTextField extends AbstractParamTextField {

    public TSessionTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
        setDescription(StringUtils.makeBoldString("Tc < 72"));
    }

    @Override
    public Object getTaskValue() {
        return super.getTask().gettSession();
    }

    @Override
    public void setTaskValue(Object value) {
        super.getTask().settSession( Integer.valueOf(value.toString()));
    }

    @Override
    public String getMessageError() {
        return "Премя выполнения не может быть негативным или нулевым";
    }

    @Override
    public boolean checkValue(Object value) {
        Integer intValue = Integer.valueOf(value.toString());
        if (intValue <= 0 || intValue > Dispatcher.MODELLING_TIME
                || intValue > super.getTask().gettPeriodCall()) {
            return false;
        }
        return true;
    }
}
