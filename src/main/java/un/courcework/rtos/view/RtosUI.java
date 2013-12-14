package un.courcework.rtos.view;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import un.courcework.rtos.core.dispatcher.Dispatcher;
import un.courcework.rtos.core.dispatcher.performer.TaskPerformer;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.chart.TaskChart;
import un.courcework.rtos.view.component.layout.ContentLayout;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
@Theme("rtos")
@Push
public class RtosUI extends UI {

    static {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "DEBUG");
    }

    private Dispatcher dispatcher;

    private Map<Task, TaskChart> taskChartMap;

    public Thread thread = new Thread(
            new TaskPerformer(
                    new Task(null, null, null, null, null, null, null, null, null, null, null, null)));

    @Override
    protected void init(VaadinRequest request) {
        getPushConfiguration().setPushMode(PushMode.MANUAL);
        this.taskChartMap = new HashMap<Task, TaskChart>();
        this.dispatcher = new Dispatcher();
        RtosUI.getCurrent().renewTaskCharts();
        createContetnView();
    }

    private void createContetnView () {
        GridLayout mainLayout = new GridLayout(3, 1);
        mainLayout.setSizeFull();

        mainLayout.addComponent(new VerticalLayout(), 0, 0);
        mainLayout.addComponent(new ContentLayout(), 1, 0);
        mainLayout.addComponent(new VerticalLayout(), 2, 0);

        mainLayout.setColumnExpandRatio(0, 1);
        mainLayout.setColumnExpandRatio(1, 0);
        mainLayout.setColumnExpandRatio(2, 1);

        mainLayout.addShortcutListener(new ShortcutListener("sad", ShortcutAction.KeyCode.NUM1, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                dispatcher.fireKeyEvent(ShortcutAction.KeyCode.NUM1);
            }
        });
        mainLayout.addShortcutListener(new ShortcutListener("sad", ShortcutAction.KeyCode.NUM2, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                dispatcher.fireKeyEvent(ShortcutAction.KeyCode.NUM2);
            }
        });
        mainLayout.addShortcutListener(new ShortcutListener("sad", ShortcutAction.KeyCode.NUM3, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                dispatcher.fireKeyEvent(ShortcutAction.KeyCode.NUM3);
            }
        });
        setContent(mainLayout);
    }

    public static RtosUI getCurrent() {
        return (RtosUI) UI.getCurrent();
    }

    public void renewTaskCharts() {
        float browserWindowHeight = Page.getCurrent().getBrowserWindowHeight() - 50;
        for (Map.Entry<Integer, Task> entry : this.dispatcher.getTaskMap().entrySet()) {
            TaskChart taskChart1new = new TaskChart("Задача " + entry.getValue().getId());
            taskChart1new.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);
            this.taskChartMap.put(entry.getValue(), taskChart1new);
        }
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public Map<Task, TaskChart> getTaskChartMap() {
        return this.taskChartMap;
    }
}
