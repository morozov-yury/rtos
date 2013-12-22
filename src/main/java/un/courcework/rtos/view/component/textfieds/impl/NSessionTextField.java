package un.courcework.rtos.view.component.textfieds.impl;

import com.vaadin.ui.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class NSessionTextField extends AbstractParamTextField {

    private static Logger log = LoggerFactory.getLogger(NSessionTextField.class);
    private Task task;

    public NSessionTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
        this.task = task;
        if (task.getId() == 2 || task.getId() == 3) {
            setEnabled(false);
            addStyleName("not-disabled-field");
        }
    }

    @Override
    public Object getTaskValue() {
        return super.getTask().getnSession();
    }

    @Override
    public void setTaskValue(Object value) {
        super.getTask().setnSession( Integer.valueOf(value.toString()));
    }

    @Override
    public String getMessageError() {
        return "Номер сеанса не может быть отрицательным или нулевым";
    }

    @Override
    public boolean checkValue(Object value) {
        Integer intValue = Integer.valueOf(value.toString());
        if (intValue <= 0) {
            log.debug("У задачи {} значение {} = {} не корректно",
                    this.task.getId(), "Nc" ,value);
            return false;
        }
        log.debug("У задачи {} значение {} = {} корректно",
                this.task.getId(), "Nc." ,value);
        return true;
    }

    @Override
    public Component getTextField() {
        return this;
    }

}
