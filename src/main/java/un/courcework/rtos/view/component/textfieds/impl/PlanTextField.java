package un.courcework.rtos.view.component.textfieds.impl;

import com.vaadin.ui.Label;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.StringUtils;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class PlanTextField extends Label {

    private Task task;

    public PlanTextField(Task task) {
        this.task = task;
        //super(task);
        setDescription("Тпл = " + StringUtils.makeBoldString("Тп + Tп(Тф)"));
        //setEnabled(false);
        if (task.gettPlanCall() != null) {
            setValue(task.gettPlanCall().toString());
        }
    }

    public Object getTaskValue() {
        return this.task.gettPlanCall();
    }

    public void setTaskValue(Object value) {
        this.task.settPlanCall( Integer.valueOf(value.toString()));
    }

}
