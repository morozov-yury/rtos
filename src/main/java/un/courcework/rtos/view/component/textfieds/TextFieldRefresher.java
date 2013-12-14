package un.courcework.rtos.view.component.textfieds;

import com.vaadin.ui.Component;

public interface TextFieldRefresher {

    public void refreshField();

    public void rewriteField();

    public Component getTextField();

}
