package un.courcework.rtos.view.component.textfiels;

import un.courcework.rtos.model.Task;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 09.11.13
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */
public class TSessionTextField extends  ParamTextField {

    public TSessionTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.gettSession()));
        setDescription("<b>Tc < 72</b>");
    }
}
