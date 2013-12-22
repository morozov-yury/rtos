package un.courcework.rtos.view.component.textfieds.impl;

import com.vaadin.ui.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.core.dispatcher.Dispatcher;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.StringUtils;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class EndIntTextField extends AbstractParamTextField {

    private static Logger log = LoggerFactory.getLogger(EndIntTextField.class);
    private Task task;

    public EndIntTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
        this.task = task;
        setDescription(StringUtils.makeBoldString("Тн < Тк < 72"));
        if (task.getId() == 1 || task.getId() == 2) {
            setEnabled(false);
            addStyleName("not-disabled-field");
        }
    }

    @Override
    public Object getTaskValue() {
        return super.getTask().gettEndIntActive();
    }

    @Override
    public void setTaskValue(Object value) {
        super.getTask().settEndIntActive( Integer.valueOf(value.toString()));
    }

    @Override
    public String getMessageError() {
        return "Должно быть в диапазоне [0,Tmod]: [0;"
                + Dispatcher.MODELLING_TIME + "]";
    }

    @Override
    public boolean checkValue(Object value) {
        Integer intValue  = Integer.valueOf(value.toString());
        if ( intValue < 0 || intValue > Dispatcher.MODELLING_TIME) {
            log.debug("У задачи {} значение {} = {} не корректно",
                    this.task.getId(), "Тк" ,value);
            return false;
        }
        log.debug("У задачи {} значение {} = {} корректно",
                this.task.getId(), "Тк" ,value);
        return true;
    }

    @Override
    public Component getTextField() {
        return this;
    }
}
