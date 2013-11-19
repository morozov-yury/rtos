package un.courcework.rtos.view.component.textfieds;

import un.courcework.rtos.model.Task;

public class NSessionTextField extends AbstractParamTextField {

    public NSessionTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.getnSession()));
        //setDescription("N сеанса");
    }
}
