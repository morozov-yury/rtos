package un.courcework.rtos.view.component.textfieds.impl;

import com.vaadin.ui.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.core.dispatcher.Dispatcher;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class TSessionTextField extends AbstractParamTextField {

    private static Logger log = LoggerFactory.getLogger(TSessionTextField.class);
    private Task task;

    public TSessionTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
        this.task = task;
    }

    @Override
    public Object getTaskValue() {
        return super.getTask().gettSession();
    }

    @Override
    public void setTaskValue(Object value) {
        super.getTask().settSession( Integer.valueOf(value.toString()));
    }

    @Override
    public String getMessageError() {
        return "Должно быть в диапазоне [1; Tв.m.]: [1;" + super.getTask().gettExecMax() + "]";
    }

    @Override
    public boolean checkValue(Object value) {
        Integer intValue = Integer.valueOf(value.toString());
        if (intValue <= 0 || intValue > Dispatcher.MODELLING_TIME
                || intValue > super.getTask().gettExecMax()) {
            log.debug("У задачи {} значение {} = {} не корректно",
                    this.task.getId(), "Тс" ,value);
            return false;
        }
        log.debug("У задачи {} значение {} = {} корректно",
                this.task.getId(), "Тс" ,value);
        return true;
    }

    @Override
    public Component getTextField() {
        return this;
    }

}
