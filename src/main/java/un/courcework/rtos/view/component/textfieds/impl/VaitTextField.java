package un.courcework.rtos.view.component.textfieds.impl;

import com.vaadin.ui.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.core.dispatcher.Dispatcher;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class VaitTextField extends AbstractParamTextField {

    private static Logger log = LoggerFactory.getLogger(VaitTextField.class);
    private Task task;

    public VaitTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
        //setDescription("");

        this.task = task;
    }

    @Override
    public Object getTaskValue() {
        return super.getTask().gettWaitMax();
    }

    @Override
    public void setTaskValue(Object value) {
        super.getTask().settWaitMax(Integer.valueOf(value.toString()));
    }

    @Override
    public String getMessageError() {
        return "Должно быть [1; Tп]: [1;" +
                super.getTask().gettPeriodCall() + "]";
    }

    @Override
    public boolean checkValue(Object value) {
        Integer intValue = Integer.valueOf(value.toString());
        if (intValue < 1 || intValue > Dispatcher.MODELLING_TIME
                || intValue > super.getTask().gettPeriodCall()) {
            log.debug("У задачи {} значение {} = {} не корректно",
                    this.task.getId(), "Тo.m." ,value);
            return false;
        }
        log.debug("У задачи {} значение {} = {} корректно",
                this.task.getId(), "То.m." ,value);
        return true;
    }

    @Override
    public Component getTextField() {
        return this;
    }

}
