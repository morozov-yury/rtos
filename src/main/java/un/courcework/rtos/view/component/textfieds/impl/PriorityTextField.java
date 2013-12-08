package un.courcework.rtos.view.component.textfieds.impl;

import com.vaadin.ui.Component;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class PriorityTextField extends AbstractParamTextField {

    private boolean error = false;

    public PriorityTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
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
        System.out.println(super.getTask().getId() + " " + error);
        if (intValue < 0 && !error) {
            return false;
        }
        return true;
    }

    public float toFoloat() {
        return super.getTask().getPriority();
    }

    public void setError (Boolean error) {
        this.error = error;
    }

    @Override
    public Component getTextField() {
        return this;
    }

}
