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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.core.dispatcher.Dispatcher;
import un.courcework.rtos.core.dispatcher.performer.TaskPerformer;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.MathUtils;
import un.courcework.rtos.view.component.chart.DiscreteFunctionChart;
import un.courcework.rtos.view.component.chart.FunctionChart;
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

    private static Logger log = LoggerFactory.getLogger(RtosUI.class);

    private Dispatcher dispatcher;

    private Map<Task, TaskChart> taskChartMap;

    private DiscreteFunctionChart functionChart;

    public Thread thread = new Thread(
            new TaskPerformer(
                    new Task(null, null, null, null, null, null, null, null, null, null, null, null)));

    @Override
    protected void init(VaadinRequest request) {
        getPushConfiguration().setPushMode(PushMode.MANUAL);
        this.taskChartMap = new HashMap<Task, TaskChart>();
        this.dispatcher = new Dispatcher();
        RtosUI.getCurrent().renewCharts();
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

        mainLayout.addShortcutListener(new ShortcutListener("sad", ShortcutAction.KeyCode.Q, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                dispatcher.fireKeyEvent(ShortcutAction.KeyCode.Q);
            }
        });
        mainLayout.addShortcutListener(new ShortcutListener("sad", ShortcutAction.KeyCode.W, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                dispatcher.fireKeyEvent(ShortcutAction.KeyCode.W);
            }
        });
        mainLayout.addShortcutListener(new ShortcutListener("sad", ShortcutAction.KeyCode.E, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                dispatcher.fireKeyEvent(ShortcutAction.KeyCode.E);
            }
        });
        setContent(mainLayout);
    }

    public static RtosUI getCurrent() {
        return (RtosUI) UI.getCurrent();
    }

    public void renewCharts() {
        float browserWindowHeight = Page.getCurrent().getBrowserWindowHeight() - 50;
        for (Map.Entry<Integer, Task> entry : this.dispatcher.getTaskMap().entrySet()) {
            TaskChart taskChart1new = new TaskChart("Задача " + entry.getValue().getId());
            taskChart1new.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);
            this.taskChartMap.put(entry.getValue(), taskChart1new);
        }
        functionChart = new DiscreteFunctionChart(
                RtosUI.getCurrent().getDispatcher().getMathFunction(), 73.0, Math.PI / 12);
        functionChart.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);
        functionChart.setPerLine(MathUtils.round(72.0 / 6, 2));
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public Map<Task, TaskChart> getTaskChartMap() {
        return this.taskChartMap;
    }

    public DiscreteFunctionChart getFunctionChart() {
        return functionChart;
    }

    public void setFunctionChart(DiscreteFunctionChart functionChart) {
        this.functionChart = functionChart;
    }
}
