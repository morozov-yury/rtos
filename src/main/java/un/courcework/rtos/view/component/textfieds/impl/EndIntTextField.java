package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.StringUtils;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class EndIntTextField extends AbstractParamTextField {

    public EndIntTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.gettEndIntActive()));
        setDescription(StringUtils.makeBoldString("Тн < Тк < 72"));
        if (task.getId() != 3) {
            setValue("");
            setEnabled(false);
        }
    }
}
