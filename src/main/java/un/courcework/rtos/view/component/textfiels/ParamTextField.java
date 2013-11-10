package un.courcework.rtos.view.component.textfiels;

import com.vaadin.ui.TextField;
import un.courcework.rtos.model.Task;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 09.11.13
 * Time: 20:39
 * To change this template use File | Settings | File Templates.
 */
public abstract class ParamTextField extends TextField {

    private Task task;

    public ParamTextField (Task task) {
        this.task = task;
        setWidth(100, Unit.PERCENTAGE);
        setValue("5");
    }

}
