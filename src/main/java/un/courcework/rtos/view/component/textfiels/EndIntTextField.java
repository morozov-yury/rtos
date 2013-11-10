package un.courcework.rtos.view.component.textfiels;

import un.courcework.rtos.model.Task;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 09.11.13
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
public class EndIntTextField extends  ParamTextField {

    public EndIntTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.gettEndIntActive()));
        setDescription("Т конца - время, когда задача после старта ПО перестает быть активной " +
                "(конец интервала активности задачи). <b> Тн < Тк < 72</b>");
    }
}
