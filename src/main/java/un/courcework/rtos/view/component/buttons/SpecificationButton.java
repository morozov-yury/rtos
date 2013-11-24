package un.courcework.rtos.view.component.buttons;

import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class SpecificationButton extends NativeButton {

    public SpecificationButton() {
        setSizeFull();
        setIcon(new ThemeResource("images/16x16/49.png"), "lab1");
        setDescription("Show specification");
        addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Window window = new Window();
                window.setCaption("Specification");
                window.setWidth(1000, Sizeable.Unit.PIXELS);
                window.center();
                UI.getCurrent().addWindow(window);
            }
        });
    }
}
