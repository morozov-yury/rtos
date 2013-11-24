package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class VaitTextField extends AbstractParamTextField {

    public VaitTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.gettVaitMax()));
        //setDescription("");

    }
}
