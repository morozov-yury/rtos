package un.courcework.rtos.view.component.textfieds;

import un.courcework.rtos.model.Task;

public class ExecMaxTextField extends AbstractParamTextField {

    public ExecMaxTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.gettExecMax()));
        //setDescription("");
    }
}
