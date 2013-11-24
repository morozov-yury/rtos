package un.courcework.rtos.view.component;

import com.vaadin.ui.HorizontalLayout;
import un.courcework.rtos.view.component.buttons.*;
import un.courcework.rtos.view.component.layout.ContentLayout;

public class ButtonsPanel extends HorizontalLayout {

    public ButtonsPanel (ContentLayout contentLayout) {
        setWidth(100, Unit.PERCENTAGE);
        addComponent(new StartButton());
        addComponent(new PauseButton());
        addComponent(new StopButton(contentLayout));
        addComponent(new LabOneButton());
        addComponent(new SpecificationButton());
    }
}
