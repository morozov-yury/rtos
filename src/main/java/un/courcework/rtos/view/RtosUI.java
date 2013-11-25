package un.courcework.rtos.view;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import un.courcework.rtos.core.timer.impl.SecondRtosTimer;
import un.courcework.rtos.model.MathFunction;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.model.TaskState;
import un.courcework.rtos.model.TaskStatus;
import un.courcework.rtos.view.component.chart.TaskChart;
import un.courcework.rtos.view.component.layout.ContentLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
@Theme("rtos")
@Push
public class RtosUI extends UI {

    private MathFunction mathFunction;

    private SecondRtosTimer secondRtosTimer;

    private List<Task> tasks = new ArrayList<Task>();

    private Map<Task, TaskChart> taskChartMap;


    @Override
    protected void init(VaadinRequest request) {
        installSystem ();
    }

    private void installSystem () {
        getPushConfiguration().setPushMode(PushMode.MANUAL);
        this.mathFunction = new MathFunction() {
            @Override
            public Double getValue(Double t) {
                return Math.cos(2 * t + 1);
            }
        };
        this.secondRtosTimer = new SecondRtosTimer();
        this.taskChartMap = new HashMap<Task, TaskChart>();
        createTestTasks();
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

        setContent(mainLayout);
    }

    public MathFunction getMathFunction() {
        return this.mathFunction;
    }

    public static RtosUI getCurrent() {
        return (RtosUI) UI.getCurrent();
    }

    public List<Task> listTasks() {
        return tasks;
    }

    private void createTestTasks () {
        this.tasks.add(new Task(1, "1", 1, 40, 3, 5, 3, 9, 0, 5, 0,TaskState.ACTIVE, TaskStatus.WAIT));
        this.tasks.add(new Task(2, "2", 10, 60, 3, 4, 3, 9, 0, 5, 0, TaskState.ACTIVE, TaskStatus.WAIT));
        this.tasks.add(new Task(3, "3", 5, 20, 3, 6, 3, 9, 0, 5, 0, TaskState.NOT_ACTIVE, TaskStatus.WAIT));
        renewTaskCharts();
    }

    public SecondRtosTimer getSecondRtosTimer() {
        return secondRtosTimer;
    }

    public Map<Task, TaskChart> getTaskChartMap() {
        return taskChartMap;
    }

    public void renewTaskCharts() {
        float browserWindowHeight = Page.getCurrent().getBrowserWindowHeight() - 50;
        Integer count = 1;
        for (Task task : listTasks()) {
            TaskChart taskChart1new = new TaskChart("Задача " + count++);
            taskChart1new.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);
            this.taskChartMap.put(task, taskChart1new);
        }
    }

    public void showTrayNotification(String message) {
//        Notification notification = new Notification(message);
//        notification.setDelayMsec(800);
//        notification.setPosition(Position.BOTTOM_RIGHT);
//        notification.show(Page.getCurrent());
    }
}
