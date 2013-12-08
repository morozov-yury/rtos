package un.courcework.rtos.view.component.buttons;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeButton;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.ParametersPanel;

public class PauseButton extends NativeButton  {

    public PauseButton(final ParametersPanel parametersPanel) {
        setSizeFull();
        setIcon(new ThemeResource("images/16x16/pause.png"), "Pause");
        setDescription("Pause the simulation");
        addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                RtosUI.getCurrent().getDispatcher().getSecondRtosTimer().pauseTimer();
                parametersPanel.setStatus(ParametersPanel.ParametersPanelStatus.AVAILABLE_TO_EDIT);
            }
        });
    }
}
