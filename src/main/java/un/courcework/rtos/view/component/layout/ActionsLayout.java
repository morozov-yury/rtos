package un.courcework.rtos.view.component.layout;

import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import un.courcework.rtos.utils.StringUtils;

public class ActionsLayout extends VerticalLayout {

    public ActionsLayout() {
        addStyleName("log-text-area");
        setWidth(100, Unit.PERCENTAGE);
        setHeight(100, Unit.PERCENTAGE);
        setEnabled(false);

        addComponent(new Label(StringUtils.makeBoldString("События мыши"), ContentMode.HTML));
        addComponent(new Label("Правая кнопка мыши - начало интервала активности для 3й задачи", ContentMode.HTML));
        addComponent(new Label(StringUtils.makeBoldString("События клавиатуры"), ContentMode.HTML));
        addComponent(new Label("Клавиша 1 - начало интервала активности для 1й задачи", ContentMode.HTML));
    }

}
