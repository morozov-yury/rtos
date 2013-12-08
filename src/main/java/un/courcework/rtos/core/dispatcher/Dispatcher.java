package un.courcework.rtos.core.dispatcher;

import un.courcework.rtos.core.timer.impl.SecondRtosTimer;
import un.courcework.rtos.model.MathFunction;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.model.TaskState;
import un.courcework.rtos.model.TaskStatus;
import un.courcework.rtos.view.RtosUI;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {

    public static final long MODELLING_TIME = 72;

    private MathFunction mathFunction;

    private SecondRtosTimer secondRtosTimer;

    private List<Task> tasks;

    public Dispatcher () {
        this.mathFunction = new MathFunction() {
            @Override
            public Double getValue(Double t) {
                return Math.cos(2 * t + 1);
            }
        };
        this.secondRtosTimer = new SecondRtosTimer();
        createTestTasks();
    }

    private void createTestTasks () {
        this.tasks = new ArrayList<Task>();
        this.tasks.add(new Task(1, null, null, null, 5, 3, 2, 1, 1, 1, TaskState.ACTIVE,
                TaskStatus.WAIT));
        this.tasks.add(new Task(2, null, null, null, 4, 3, 2, 2, 1, null, TaskState.ACTIVE,
                TaskStatus.WAIT));
        this.tasks.add(new Task(3, null, 20, null, 6, 3, 2, 3, 1, null, TaskState.NOT_ACTIVE,
                TaskStatus.WAIT));
    }

    public MathFunction getMathFunction() {
        return mathFunction;
    }

    public SecondRtosTimer getSecondRtosTimer() {
        return secondRtosTimer;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
