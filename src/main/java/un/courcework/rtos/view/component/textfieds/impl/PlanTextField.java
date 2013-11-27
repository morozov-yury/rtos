package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.StringUtils;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class PlanTextField extends AbstractParamTextField {

    public PlanTextField(Task task) {
        super(task);
        setDescription("Тп = " + StringUtils.makeBoldString("Тп + Tп(Тф)"));
        setEnabled(false);
    }

    @Override
    public Object getTaskValue() {
        return super.getTask().gettPlanCall();
    }

    @Override
    public void setTaskValue(Object value) {
        super.getTask().settPlanCall( Integer.valueOf(value.toString()));
    }

    @Override
    public String getMessageError() {
        return "";
    }

    @Override
    public boolean checkValue(Object value) {
        return true;
    }
}
