package un.courcework.rtos.view.component.textfieds.impl;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class PlanTextField extends AbstractParamTextField {

    private static Logger log = LoggerFactory.getLogger(PlanTextField.class);

    private Task task;

    public PlanTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
        this.task = task;
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

    public void refresh () {
        if (task.gettPlanCall() != null) {
            setValue(task.gettPlanCall().toString());
        }
    }

}
