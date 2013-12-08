package un.courcework.rtos.view.component;

import com.vaadin.ui.HorizontalLayout;
import un.courcework.rtos.view.component.buttons.*;
import un.courcework.rtos.view.component.layout.LeftPanel;
import un.courcework.rtos.view.component.layout.RightPanel;

public class ButtonsPanel extends HorizontalLayout {

    public ButtonsPanel (LeftPanel leftPanel, RightPanel rightPanel) {
        setWidth(100, Unit.PERCENTAGE);
        addComponent(new StartButton(rightPanel.getParametersPanel()));
        addComponent(new PauseButton(rightPanel.getParametersPanel()));
        addComponent(new StopButton(leftPanel, rightPanel.getParametersPanel()));
        addComponent(new LabOneButton());
        addComponent(new SpecificationButton());
    }
}
