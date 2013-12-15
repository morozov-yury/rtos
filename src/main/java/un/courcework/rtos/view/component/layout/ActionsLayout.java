package un.courcework.rtos.view.component.layout;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import un.courcework.rtos.utils.StringUtils;

public class ActionsLayout extends VerticalLayout {

    public ActionsLayout() {
        addStyleName("log-text-area");
        setWidth(100, Unit.PERCENTAGE);
        setHeight(100, Unit.PERCENTAGE);
        setEnabled(false);

        addComponent(new Label(StringUtils.makeBoldString("Кл.\"Q\"") + " - Tн для "
                + StringUtils.makeBoldString("задачи 1"), ContentMode.HTML));
        addComponent(new Label(StringUtils.makeBoldString("Кл.\"W\"") + " - Tк для "
                + StringUtils.makeBoldString("задачи 1"), ContentMode.HTML));
        addComponent(new Label(StringUtils.makeBoldString("Кл.\"E\"") + " - Tк для "
                + StringUtils.makeBoldString("задачи 2"), ContentMode.HTML));
        addComponent(new Label(StringUtils.makeBoldString("Пр.к.м") + " - Тн для "
                + StringUtils.makeBoldString("задачи 3"), ContentMode.HTML));

    }

}
