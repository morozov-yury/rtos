package un.courcework.rtos.view.component.textfiels;

import un.courcework.rtos.model.Task;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 09.11.13
 * Time: 21:16
 * To change this template use File | Settings | File Templates.
 */
public class CritTextField extends  ParamTextField {


    public CritTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.gettCrit()));
        setDescription("");
    }
}
