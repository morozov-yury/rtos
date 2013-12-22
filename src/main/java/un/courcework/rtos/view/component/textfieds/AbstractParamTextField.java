package un.courcework.rtos.view.component.textfieds;

import com.vaadin.event.FieldEvents;
import com.vaadin.server.UserError;
import com.vaadin.ui.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.ParametersPanel;

public abstract class AbstractParamTextField extends TextField
        implements FieldEvents.TextChangeListener, TextFieldRefresher  {

    private static Logger log = LoggerFactory.getLogger(AbstractParamTextField.class);

    private ParametersPanel parametersPanel;

    private Task task;

    public AbstractParamTextField(ParametersPanel parametersPanel, Task task) {
        this.parametersPanel = parametersPanel;
        this.task = task;
        setWidth(100, Unit.PERCENTAGE);
        setImmediate(true);
        addStyleName("param-text-field");
        if (getTaskValue() == null) {
            setEnabled(false);
            setValue("");
        } else {
            setValue(getTaskValue().toString());
        }
        addTextChangeListener(this);
    }

    public Task getTask() {
        return task;
    }

    @Override
    public synchronized void textChange(FieldEvents.TextChangeEvent event) {
        log.debug("Новое значение {}", event.getText());
        try {
            Integer.valueOf(event.getText().toString());
        } catch (NumberFormatException e) {
            log.debug("У задачи {} значение {} = {} не корректно",
                    this.task.getId(), "velue" ,event.getText().toString());
            setComponentError(new UserError(getMessageError()));
            return;
        }
        refresh(event.getText());
        parametersPanel.refreshAllFiels();
        //refresh(event.getText());
    }

    private synchronized void refresh (String value) {

        if (!checkValue (value)) {
            setComponentError(new UserError(getMessageError()));
            setTaskValue(value);
        } else {
            setComponentError(null);
            setTaskValue(value);
            shouldHideErrors();
        }
    }

    @Override
    public synchronized void refreshField() {
        if (getTaskValue() != null) {
            refresh(getTaskValue().toString());
        }
    }

    @Override
    public synchronized void rewriteField() {
        if (getTaskValue() != null) {
            this.getUI().getSession().getLockInstance().lock();
            try {
                setValue(getTaskValue().toString());
            } finally {
                this.getUI().getSession().getLockInstance().unlock();
            }
        }
    }

    public abstract Object getTaskValue();

    public abstract void setTaskValue(Object value);

    public abstract String getMessageError();

    public abstract boolean checkValue (Object value);

}
