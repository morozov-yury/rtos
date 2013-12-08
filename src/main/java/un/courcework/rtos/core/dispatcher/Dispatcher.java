package un.courcework.rtos.core.dispatcher;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Notification;
import un.courcework.rtos.core.dispatcher.engine.Engine;
import un.courcework.rtos.core.dispatcher.engine.impl.MultiProcEngine;
import un.courcework.rtos.core.dispatcher.engine.impl.SingleProcEngine;
import un.courcework.rtos.core.timer.TimerAware;
import un.courcework.rtos.core.timer.impl.SecondRtosTimer;
import un.courcework.rtos.model.MathFunction;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.model.TaskState;
import un.courcework.rtos.model.TaskStatus;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.chart.TaskChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Dispatcher implements TimerAware {

    public static final long MODELLING_TIME = 72;

    private DipatcherMode dipatcherMode;

    private Engine engine;

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
        this.dipatcherMode = DipatcherMode.SINGLE_PROCESSOR;
        this.secondRtosTimer = new SecondRtosTimer();
        this.secondRtosTimer.addTickListener(this);
        createTestTasks();
        this.engine = new SingleProcEngine(this.tasks);
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

    public void fireKeyEvent(int keyCode) {
        switch (keyCode) {
            case ShortcutAction.KeyCode.NUM1:
                Notification.show("Нажата клавиша 1", Notification.Type.TRAY_NOTIFICATION);
                break;
            case ShortcutAction.KeyCode.NUM2:
                Notification.show("Нажата клавиша 2", Notification.Type.TRAY_NOTIFICATION);
                break;
            case ShortcutAction.KeyCode.NUM3:
                Notification.show("Нажата клавиша 3", Notification.Type.TRAY_NOTIFICATION);
                break;
        }
    }

    public void fireClickEvent() {
        Notification.show("Нажата правая клавиша мыши", Notification.Type.TRAY_NOTIFICATION);
    }

    @Override
    public void timerSecondTick(int second) {
        this.engine.timeTick(second);
    }

    public DipatcherMode getDipatcherMode() {
        return dipatcherMode;
    }

    public void setDipatcherMode(DipatcherMode dipatcherMode) {
        this.dipatcherMode = dipatcherMode;
        switch (dipatcherMode) {
            case SINGLE_PROCESSOR:
                this.engine = new SingleProcEngine(this.tasks);
                break;
            case MULTIPROCESSOR:
                this.engine = new MultiProcEngine(this.tasks);
                break;
        }
    }
}
