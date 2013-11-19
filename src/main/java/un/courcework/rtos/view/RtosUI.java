package un.courcework.rtos.view;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import un.courcework.rtos.model.MathFunction;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.model.TaskState;
import un.courcework.rtos.model.TaskStatus;
import un.courcework.rtos.view.component.layout.ContentLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

@SuppressWarnings("serial")
@Theme("rtos")
@Push
public class RtosUI extends UI {

    private MathFunction mathFunction;

    private List<Task> tasks = new ArrayList<Task>();

    private Timer secondTimer;

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

    public List<Task> getTasks() {
        return tasks;
    }

    public Timer getSecondTimer() {
        return secondTimer;
    }

    public void setSecondTimer(Timer secondTimer) {
        this.secondTimer = secondTimer;
    }

    private void createTestTasks () {
        this.tasks.add(new Task("1", 1, 40, 3, 5, 3, 9, 0, 5, 0,TaskState.ACTIVE, TaskStatus.WAIT));
        this.tasks.add(new Task("2", 10, 60, 3, 4, 3, 9, 0, 5, 0, TaskState.ACTIVE, TaskStatus.WAIT));
        this.tasks.add(new Task("3", 5, 20, 3, 6, 3, 9, 0, 5, 0, TaskState.NOT_ACTIVE, TaskStatus.WAIT));
    }
}
