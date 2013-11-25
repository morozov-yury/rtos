package un.courcework.rtos.view.component.buttons;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import un.courcework.rtos.view.RtosUI;

public class StartButton extends NativeButton {

    public StartButton() {
        setSizeFull();
        setIcon(new ThemeResource("images/16x16/start.png"), "Start");
        setDescription("Run the simulation");
        addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                RtosUI.getCurrent().getSecondRtosTimer().startTimer();
                RtosUI.getCurrent().showTrayNotification("Start");
            }
        });
    }
}