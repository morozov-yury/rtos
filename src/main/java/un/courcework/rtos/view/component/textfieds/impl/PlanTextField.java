package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.StringUtils;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class PlanTextField extends AbstractParamTextField {

    public PlanTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.gettPlanCall()));
        setDescription("Тп = " + StringUtils.makeBoldString("Тп + Tп(Тф)"));
    }
}
