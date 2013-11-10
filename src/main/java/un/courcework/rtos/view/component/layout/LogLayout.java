package un.courcework.rtos.view.component.layout;

import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.TextArea;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 10.11.13
 * Time: 1:20
 * To change this template use File | Settings | File Templates.
 */
public class LogLayout extends TextArea {

    public LogLayout () {
        addStyleName("log-text-area");
        setWidth(100, Unit.PERCENTAGE);
        setHeight(100, Unit.PERCENTAGE);
        setEnabled(false);
        setWordwrap(true);
        //setReadOnly(true);

        setHeight(Page.getCurrent().getBrowserWindowHeight() - 450, Sizeable.Unit.PIXELS);

        Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(Page.BrowserWindowResizeEvent event) {
                setHeight(Page.getCurrent().getBrowserWindowHeight() - 450, Sizeable.Unit.PIXELS);
            }
        });

        addMessage("--01: Задача №2 получает управление от задачи №1");
        addMessage("--02: Задача №1 получает управление по Тп");
        addMessage("--03: Задача №3 получает управление по таймеру");
        addMessage("--04: Задача №2 прерывается задачей №3");
    }

    public void addMessage (String message) {
        if (getValue().length() == 0) {
            setValue(getValue() + message);
        } else {
            setValue(getValue() + "\n" + message);
        }
    }
}
