package un.courcework.rtos.view.component.textfieds;

import un.courcework.rtos.model.Task;

public class PriorityTextField extends AbstractParamTextField {
    public PriorityTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.getPriority()));
        //setDescription("");
    }
}
