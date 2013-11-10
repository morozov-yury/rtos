package un.courcework.rtos.view.component.textfiels;

import un.courcework.rtos.model.Task;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 09.11.13
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */
public class SessionTextField extends  ParamTextField {

    public SessionTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.gettSession()));
        setDescription("Т сеанса - время фактического выполнения задачи <b>Tc < 72</b>");
    }
}
