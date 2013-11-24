package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.StringUtils;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class TSessionTextField extends AbstractParamTextField {

    public TSessionTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.gettSession()));
        setDescription(StringUtils.makeBoldString("Tc < 72"));
    }
}
