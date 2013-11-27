package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.StringUtils;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class StartIntValidator extends AbstractParamTextField {

    public StartIntValidator(Task task) {
        super(task);
        setDescription(StringUtils.makeBoldString("0 < Тн < 72"));
    }

    @Override
    public Object getTaskValue() {
        return super.getTask().gettStartIntActive();
    }

    @Override
    public void setTaskValue(Object value) {
        super.getTask().settStartIntActive( Integer.valueOf(value.toString()));
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
