package un.courcework.rtos.core.dispatcher;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.core.dispatcher.action.RtosAction;
import un.courcework.rtos.core.dispatcher.engine.Engine;
import un.courcework.rtos.core.dispatcher.engine.impl.MultiProcEngine;
import un.courcework.rtos.core.dispatcher.engine.impl.SingleProcEngine;
import un.courcework.rtos.core.dispatcher.performer.TaskPerformer;
import un.courcework.rtos.core.timer.TimerAware;
import un.courcework.rtos.core.timer.impl.SecondRtosTimer;
import un.courcework.rtos.core.timer.impl.TenthOfaSecondTimer;
import un.courcework.rtos.model.MathFunction;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.model.TaskState;
import un.courcework.rtos.model.TaskStatus;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.chart.TaskChart;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Dispatcher implements TimerAware {

    private static Logger log = LoggerFactory.getLogger(Dispatcher.class);

    public static final long MODELLING_TIME = 73;

    private DipatcherMode dipatcherMode;

    private Engine engine;

    private MathFunction mathFunction;

    private SecondRtosTimer secondRtosTimer;

    private TenthOfaSecondTimer tenthOfaSecondTimer;

    private Map<Integer, Task> taskMap;

    private Map<Integer, TaskPerformer> taskPerformersMap;

    private int rtosTime = 0;

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
        tenthOfaSecondTimer = new TenthOfaSecondTimer();
        createTestTasks();
        createPerformers();

        this.engine = new MultiProcEngine(taskPerformersMap);
    }

    public void restart () {
        createTestTasks();
        createPerformers();
    }

    private void createPerformers() {
        this.taskPerformersMap = new ConcurrentHashMap<Integer, TaskPerformer>();
        for (Map.Entry<Integer, Task> entry : this.taskMap.entrySet()) {
            TaskPerformer taskPerformer = new TaskPerformer(entry.getValue());
            taskPerformer.addRtosAction(new RtosAction() {
                @Override
                public void perform(Task task, int time) {
                    TaskChart taskChart = RtosUI.getCurrent().getTaskChartMap().get(task);
                    taskChart.addPoint(time, TaskChart.TASK_VALUE);
                }
            });
            if (entry.getValue().getId() == 3) {
                taskPerformer.addRtosAction(new RtosAction() {
                    @Override
                    public void perform(Task task, int time) {

                    }
                });

            }
            this.taskPerformersMap.put(entry.getValue().getId(), taskPerformer);
        }
    }

    private void createTestTasks () {
        this.taskMap = new ConcurrentSkipListMap<Integer, Task>();
        this.taskMap.put(1, new Task(1, null, null, null, 10, 3, 5, 1, 3, 1, TaskState.WAIT_FOR_READY,
                TaskStatus.NOT_ACIVE));
        this.taskMap.put(2, new Task(2, null, null, null, 6, 3, 4, 2, 3, null, TaskState.WAIT_FOR_READY,
                TaskStatus.NOT_ACIVE));
        this.taskMap.put(3, new Task(3, null, 70, null, 6, 3, 4, 3, 3, null, TaskState.WAIT_FOR_READY,
                TaskStatus.NOT_ACIVE));
    }

    public MathFunction getMathFunction() {
        return mathFunction;
    }

    public SecondRtosTimer getSecondRtosTimer() {
        return secondRtosTimer;
    }

    public TenthOfaSecondTimer getTenthOfaSecondTimer() {
        return tenthOfaSecondTimer;
    }

    public Map<Integer, Task> getTaskMap() {
        return taskMap;
    }

    public void fireKeyEvent(int keyCode) {
        switch (keyCode) {
            case ShortcutAction.KeyCode.Q:
                if (this.taskMap.get(1).gettStartIntActive() == null) {
                    this.taskMap.get(1).settStartIntActive(this.rtosTime + 1);
                    log.debug("Tstart activ in task #1 was set in {}", (this.rtosTime + 1) );
                    Notification.show("Установлено Тн для задачи 1 равное " + (this.rtosTime + 1),
                            Notification.Type.TRAY_NOTIFICATION);
                }
                break;
            case ShortcutAction.KeyCode.W:
                if (this.taskMap.get(1).gettEndIntActive() == null
                        && this.taskMap.get(1).gettStartIntActive() != null) {
                    this.taskMap.get(1).settEndIntActive(this.rtosTime + 1);
                    log.debug("Tend activ in task #1 was set in {}", (this.rtosTime + 1) );
                    Notification.show("Установлено Тк для задачи 1 равное " + (this.rtosTime + 1),
                            Notification.Type.TRAY_NOTIFICATION);
                }
                break;
            case ShortcutAction.KeyCode.E:
                if (this.taskMap.get(2).gettEndIntActive() == null
                        && this.taskMap.get(2).gettStartIntActive() != null) {
                    this.taskMap.get(2).settEndIntActive(this.rtosTime + 1);
                    log.debug("Tend activ in task #2 was set in {}", (this.rtosTime + 1) );
                    Notification.show("Установлено Тк для задачи 2 равное " + (this.rtosTime + 1),
                            Notification.Type.TRAY_NOTIFICATION);
                }
                break;
        }
    }

    public void fireClickEvent() {
        log.debug("Right mouse button was pressed in time {}", this.rtosTime);
        if (this.taskMap.get(3).gettStartIntActive() == null) {
            this.taskMap.get(3).settStartIntActive(this.rtosTime + 1);
            log.debug("Tstart activ in task #3 was set in {}", (this.rtosTime + 1) );
            Notification.show("Установлено Тн для задачи три равное " + (this.rtosTime + 1),
                    Notification.Type.TRAY_NOTIFICATION);
        }
    }

    @Override
    public void timerSecondTick(int second) {
        this.rtosTime = second;
        if (second < 0) {
            return;
        }
        if (second == 0) {
            for (Map.Entry<Integer, TaskPerformer> entry : this.taskPerformersMap.entrySet()) {
                (new Thread(entry.getValue())).start();
            }
        }
        if (second == Dispatcher.MODELLING_TIME) {
            for (Map.Entry<Integer, TaskPerformer> entry : this.taskPerformersMap.entrySet()) {
                entry.getValue().stop();
            }
        }
        this.engine.timeTick(second);
    }

    @Override
    public void timerTenthOfaSecondTick() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public DipatcherMode getDipatcherMode() {
        return dipatcherMode;
    }

    public void setDipatcherMode(DipatcherMode dipatcherMode) {
        this.dipatcherMode = dipatcherMode;
        switch (dipatcherMode) {
            case SINGLE_PROCESSOR:
                this.engine = new SingleProcEngine(this.taskPerformersMap);
                break;
            case MULTIPROCESSOR:
                this.engine = new MultiProcEngine(this.taskPerformersMap);
                break;
        }
    }

    public long getRtosTime() {
        return rtosTime;
    }
}
