package un.courcework.rtos.view.component.textfieds;

import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.StringUtils;

public class EndIntTextField extends AbstractParamTextField {

    public EndIntTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.gettEndIntActive()));
        setDescription(StringUtils.makeBoldString("Тн < Тк < 72"));
    }
}
