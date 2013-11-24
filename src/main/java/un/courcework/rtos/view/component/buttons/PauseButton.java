package un.courcework.rtos.view.component.buttons;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeButton;
import un.courcework.rtos.view.RtosUI;

public class PauseButton extends NativeButton  {

    public PauseButton() {
        setSizeFull();
        setIcon(new ThemeResource("images/16x16/pause.png"), "Pause");
        setDescription("Pause the simulation");
        addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                RtosUI.getCurrent().getSecondRtosTimer().pauseTimer();
            }
        });
    }
}
