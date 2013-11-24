package un.courcework.rtos.view.component.layout;

import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class ActionsLayout extends VerticalLayout {

    public ActionsLayout() {
        addStyleName("log-text-area");
        setWidth(100, Unit.PERCENTAGE);
        setHeight(100, Unit.PERCENTAGE);
        setEnabled(false);

        Label mauseLabel = new Label("<b>События мыши</b>", ContentMode.HTML);
        Label keyboardLabel = new Label("<b>События клавиатуры</b>", ContentMode.HTML);

        addComponent(mauseLabel);
        addComponent(keyboardLabel);
    }

}
