package un.courcework.rtos.view.component.textfieds.impl;

import com.vaadin.ui.Component;
import un.courcework.rtos.core.dispatcher.Dispatcher;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.StringUtils;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class EndIntTextField extends AbstractParamTextField {

    public EndIntTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
        setDescription(StringUtils.makeBoldString("Тн < Тк < 72"));
        if (task.getId() == 1 || task.getId() == 2) {
            setEnabled(false);
            addStyleName("not-disabled-field");
        }
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
        return "Должно быть в диапазоне [0,Tmod]: [0;"
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

    @Override
    public Component getTextField() {
        return this;
    }
}
