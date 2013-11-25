package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class NSessionTextField extends AbstractParamTextField {

    public NSessionTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.getnSession()));
        if (task.getId() != 1) {
            setValue("");
            setEnabled(false);
        }
    }
}
