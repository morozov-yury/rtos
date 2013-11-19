package un.courcework.rtos.view.component.textfieds;

import com.vaadin.ui.TextField;
import un.courcework.rtos.model.Task;

public abstract class AbstractParamTextField extends TextField {

    private Task task;

    public AbstractParamTextField(Task task) {
        this.task = task;
        setWidth(100, Unit.PERCENTAGE);
        setValue("");
    }

    public Task getTask() {
        return task;
    }

}
