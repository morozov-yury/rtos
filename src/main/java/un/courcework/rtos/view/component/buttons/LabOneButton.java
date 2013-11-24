package un.courcework.rtos.view.component.buttons;

import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.layout.LabOneLayout;

public class LabOneButton extends NativeButton {

    public LabOneButton() {
        setSizeFull();
        setIcon(new ThemeResource("images/16x16/lab1.png"), "lab1");
        setDescription("Show report on the first lab");
        addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Window window = new Window();
                window.setCaption("Lab 1");
                window.setWidth(1000, Sizeable.Unit.PIXELS);
                window.center();
                window.setContent(new LabOneLayout(RtosUI.getCurrent().getMathFunction()));
                UI.getCurrent().addWindow(window);
            }
        });
    }
}
