package un.courcework.rtos.view.component.textfiels;

import un.courcework.rtos.model.Task;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 09.11.13
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
public class PlanTextField extends  ParamTextField {

    public PlanTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.gettPlanCall()));
        setDescription("Тп = <b>Тп + Tп(Тф) </b>");
    }
}
