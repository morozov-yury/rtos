package un.courcework.rtos.view.component.textfieds.impl;

import com.vaadin.ui.Component;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.StringUtils;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.textfieds.AbstractParamTextField;

public class StartIntTextField extends AbstractParamTextField {

    public StartIntTextField(ParametersPanel parametersPanel, Task task) {
        super(parametersPanel, task);
        setEnabled(false);
        addStyleName("not-disabled-field");
        setDescription(StringUtils.makeBoldString("0 < Тн < 72"));
    }

    @Override
    public Object getTaskValue() {
        return super.getTask().gettStartIntActive();
    }

    @Override
    public void setTaskValue(Object value) {
        super.getTask().settStartIntActive( Integer.valueOf(value.toString()));
    }

    @Override
    public String getMessageError() {
        return "";
    }

    @Override
    public boolean checkValue(Object value) {
        return true;
    }

    @Override
    public Component getTextField() {
        return this;
    }

}
