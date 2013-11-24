package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class PriorityTextField extends AbstractParamTextField {
    public PriorityTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.getPriority()));
        //setDescription("");
    }
}
