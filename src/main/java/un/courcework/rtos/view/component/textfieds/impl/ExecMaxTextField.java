package un.courcework.rtos.view.component.textfieds.impl;

import com.vaadin.ui.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.core.dispatcher.Dispatcher;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class ExecMaxTextField extends AbstractParamTextField {

    private static Logger log = LoggerFactory.getLogger(ExecMaxTextField.class);

    private Task task;

    public ExecMaxTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
        //setDescription("");
        this.task = task;
    }

    @Override
    public Object getTaskValue() {
        return super.getTask().gettExecMax();
    }


    @Override
    public void setTaskValue(Object value) {
        super.getTask().settExecMax( Integer.valueOf(value.toString()));
    }

    @Override
    public String getMessageError() {
        return "Должен быть в диапазоне [Tc;Tп]: [" + super.getTask().gettSession() +
                ";" + super.getTask().gettPeriodCall() + "]";
    }

    @Override
    public boolean checkValue(Object value) {
        Integer intValue  = Integer.valueOf(value.toString());
        if ( intValue < 0 || intValue > Dispatcher.MODELLING_TIME
                || intValue > super.getTask().gettPeriodCall()
                || intValue < super.getTask().gettSession()) {
            log.debug("У задачи {} значение {} = {} не корректно",
                    this.task.getId(), "Тв.m." ,value);
            return false;
        }
        log.debug("У задачи {} значение {} = {} корректно",
                this.task.getId(), "Тв.m." ,value);
        return true;
    }

    @Override
    public Component getTextField() {
        return this;
    }
}
