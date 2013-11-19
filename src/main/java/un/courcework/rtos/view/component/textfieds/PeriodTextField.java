package un.courcework.rtos.view.component.textfieds;

import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 09.11.13
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
public class PeriodTextField extends AbstractParamTextField {

    public PeriodTextField(Task task) {
        super(task);
        setValue(Integer.toString(task.gettPeriodCall()));
        setDescription(StringUtils.makeBoldString("Тп < Tc"));
    }
}
