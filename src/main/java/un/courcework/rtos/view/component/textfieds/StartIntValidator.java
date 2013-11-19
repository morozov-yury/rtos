package un.courcework.rtos.view.component.textfieds;

import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.StringUtils;

public class StartIntValidator extends AbstractParamTextField {

    public StartIntValidator(Task task) {
        super(task);
        setValue(Integer.toString(task.gettStartIntActive()));
        setDescription(StringUtils.makeBoldString("0 < Тн < 72"));
    }
}
