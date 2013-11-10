package un.courcework.rtos.view.component.textfiels;

import un.courcework.rtos.model.Task;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 09.11.13
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
public class StartIntValidator extends  ParamTextField {

    public StartIntValidator(Task task) {
        super(task);
        setValue(Integer.toString(task.gettStartIntActive()));
        setDescription(" <b> 0 < Тн < 72</b>");
    }
}
