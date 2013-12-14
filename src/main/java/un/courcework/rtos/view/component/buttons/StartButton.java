package un.courcework.rtos.view.component.buttons;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeButton;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.ParametersPanel;

public class StartButton extends NativeButton {

    public StartButton(final ParametersPanel parametersPanel) {
        setSizeFull();
        setIcon(new ThemeResource("images/16x16/start.png"), "Start");
        setDescription("Run the simulation");
        addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                RtosUI.getCurrent().getDispatcher().getSecondRtosTimer().startTimer();
                RtosUI.getCurrent().getDispatcher().getTenthOfaSecondTimer().startTimer();
                parametersPanel.setStatus(ParametersPanel.ParametersPanelStatus.NOT_AVAILABLE_TO_EDIT);
            }
        });
    }
}
