package un.courcework.rtos.view.component;

import com.vaadin.ui.HorizontalLayout;
import un.courcework.rtos.view.component.buttons.*;
import un.courcework.rtos.view.component.layout.LeftPanel;

public class ButtonsPanel extends HorizontalLayout {

    public ButtonsPanel (LeftPanel leftPanel) {
        setWidth(100, Unit.PERCENTAGE);
        addComponent(new StartButton());
        addComponent(new PauseButton());
        addComponent(new StopButton(leftPanel));
        addComponent(new LabOneButton());
        addComponent(new SpecificationButton());
    }
}
