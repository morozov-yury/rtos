package un.courcework.rtos.view.component.textfieds.impl;

import com.vaadin.ui.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class PeriodTextField extends AbstractParamTextField {

    private static Logger log = LoggerFactory.getLogger(PeriodTextField.class);
    private Task task;

    public PeriodTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
        this.task = task;
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
                + Math.max(super.getTask().gettSession(), Math.max(super.getTask().gettExecMax(), super.getTask().gettWaitMax())) ;
    }

    @Override
    public boolean checkValue(Object value) {
        Integer intValue = Integer.valueOf(value.toString());
        if (intValue <= super.getTask().gettSession()
                || intValue <= super.getTask().gettExecMax()
                || intValue <= super.getTask().gettWaitMax()) {
            log.debug("У задачи {} значение {} = {} не корректно",
                    this.task.getId(), "Тп" ,value);
            return false;
        }
        log.debug("У задачи {} значение {} = {} корректно",
                this.task.getId(), "Тп" ,value);
        return true;
    }

    @Override
    public Component getTextField() {
        return this;
    }

}
