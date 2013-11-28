package un.courcework.rtos.view.component.textfieds;

import com.vaadin.event.FieldEvents;
import com.vaadin.server.AbstractErrorMessage;
import com.vaadin.server.ErrorMessage;
import com.vaadin.server.UserError;
import com.vaadin.ui.TextField;
import un.courcework.rtos.model.Task;

public abstract class AbstractParamTextField extends TextField
        implements FieldEvents.TextChangeListener, TextFieldRefresher  {

    private Task task;

    public AbstractParamTextField(Task task) {
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
    public void textChange(FieldEvents.TextChangeEvent event) {
        refresh(event.getText());
    }

    private void refresh (String value) {
        if (!checkValue (value)) {
            setComponentError(new UserError(getMessageError()));
        } else {
            setComponentError(null);
            setTaskValue(value);
            shouldHideErrors();
        }
    }

    @Override
    public void refreshField() {
        refresh(getTaskValue().toString());
    }

    public abstract Object getTaskValue();

    public abstract void setTaskValue(Object value);

    public abstract String getMessageError();

    public abstract boolean checkValue (Object value);

}
