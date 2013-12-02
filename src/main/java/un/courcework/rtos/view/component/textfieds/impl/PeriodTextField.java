package un.courcework.rtos.view.component.textfieds.impl;

import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class PeriodTextField extends AbstractParamTextField {

    public PeriodTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
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
        return "Должо быть больше Max(Tc,To.m.,Tв.m.), > "
                + Math.max(super.getTask().gettSession(), Math.max(super.getTask().gettExecMax(), super.getTask().gettVaitMax())) ;
    }

    @Override
    public boolean checkValue(Object value) {
        Integer intValue = Integer.valueOf(value.toString());
        if (intValue <= super.getTask().gettSession()
                || intValue <= super.getTask().gettExecMax()
                || intValue <= super.getTask().gettVaitMax()) {
            return false;
        }
        return true;
    }

}
