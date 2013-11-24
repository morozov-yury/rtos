package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class ExecMaxTextField extends AbstractParamTextField {

    public ExecMaxTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.gettExecMax()));
        //setDescription("");
    }
}
