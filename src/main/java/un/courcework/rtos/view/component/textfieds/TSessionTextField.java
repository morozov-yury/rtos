package un.courcework.rtos.view.component.textfieds;

import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.StringUtils;

public class TSessionTextField extends AbstractParamTextField {

    public TSessionTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.gettSession()));
        setDescription(StringUtils.makeBoldString("Tc < 72"));
    }
}
