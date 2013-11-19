package un.courcework.rtos.view.component.textfieds;

import un.courcework.rtos.model.Task;

public class VaitTextField extends AbstractParamTextField {

    public VaitTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.gettVaitMax()));
        //setDescription("");

    }
}
