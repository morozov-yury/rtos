package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.StringUtils;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class PeriodTextField extends AbstractParamTextField {

    public PeriodTextField(Task task) {
        super(task);
        setDescription(StringUtils.makeBoldString("[Tc; Тк-Тн]"));
    }

    @Override
    public Object getTaskValue() {
        return super.getTask().gettPeriodCall();
    }

    @Override
    public void setTaskValue(Object value) {
        super.getTask().settPeriodCall(Integer.valueOf(value.toString()));
    }

    @Override
    public String getMessageError() {
        return "Неккоректное значение Тп. Оно должо быть в диапазоне [Tc; Тк-Тн]";
    }

    @Override
    public boolean checkValue(Object value) {
        return true;
    }
}
