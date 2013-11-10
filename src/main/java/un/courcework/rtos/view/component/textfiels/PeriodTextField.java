package un.courcework.rtos.view.component.textfiels;

import un.courcework.rtos.model.Task;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 09.11.13
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
public class PeriodTextField extends  ParamTextField {

    public PeriodTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.gettPeriodCall()));
        setDescription("Т периода - переодизация вызова задачи. Позволяет планировщику расчитывать" +
                "очередной плановый вызов задачи <b>Тп < Tc</b>");
    }
}
