package un.courcework.rtos.view.component.textfieds.impl;

import com.vaadin.ui.Component;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class PlanTextField extends AbstractParamTextField {

    public PlanTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
        setEnabled(false);
        addStyleName("not-disabled-field");
    }

    @Override
    public Object getTaskValue() {
        return super.getTask().gettPlanCall();
    }

    @Override
    public void setTaskValue(Object value) {
        super.getTask().settStartIntActive( Integer.valueOf(value.toString()));
    }

    @Override
    public String getMessageError() {
        return "";
    }

    @Override
    public boolean checkValue(Object value) {
        return true;
    }

    @Override
    public Component getTextField() {
        return this;
    }

}
