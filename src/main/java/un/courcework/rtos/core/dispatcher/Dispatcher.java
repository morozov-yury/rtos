package un.courcework.rtos.core.dispatcher;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.core.dispatcher.action.RtosAction;
import un.courcework.rtos.core.dispatcher.engine.Engine;
import un.courcework.rtos.core.dispatcher.engine.impl.AbstractEngine;
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

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Dispatcher implements TimerAware, Serializable {

    private static Logger log = LoggerFactory.getLogger(Dispatcher.class);

    public static final long MODELLING_TIME = 73;

    private DipatcherMode dipatcherMode;

    private Engine engine;

    private MathFunction mathFunction;

    private SecondRtosTimer secondRtosTimer;

    private TenthOfaSecondTimer tenthOfaSecondTimer;

    private volatile Map<Integer, Task> taskMap;

    private volatile Map<Integer, TaskPerformer> taskPerformersMap;

    private int rtosTime = 0;

    private int startTc = 0;

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
        this.tenthOfaSecondTimer = new TenthOfaSecondTimer();
        this.tenthOfaSecondTimer.addTickListener(this);
        createTestTasks();
        createPerformers();

        this.engine = new SingleProcEngine(taskPerformersMap);
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
                public void perform(Task task, Integer time) {
                    task.setWorksTime(task.getWorksTime() + 1);
                    TaskChart taskChart = RtosUI.getCurrent().getTaskChartMap().get(task);
                    taskChart.addPoint(rtosTime, TaskChart.TASK_VALUE);
                }
            });
            if (entry.getValue().getId() == 1) {
                taskPerformer.addRtosAction(new RtosAction() {
                    @Override
                    public void perform(Task task, Integer time) {
                        log.debug("Задача 1: проверка на завершение {} {}", task.getWorksTime(), task.gettSession());
                        if (task.getWorksTime() == task.gettSession()) {
                            log.debug("Задача 1: задача проработала {} раз", task.getExecCount());
                            log.debug("Задача 1: увеличила кол-во завершенных запусков");
                            task.setExecCount(task.getExecCount()  + 1);
                            if (task.getExecCount() == task.getnSession()) {
                                taskMap.get(2).settStartIntActive(rtosTime + 1);
                                taskMap.get(2).settPlanCall(rtosTime + 1);
                                taskMap.get(2).setTaskStatus(TaskStatus.ACTIVE);
                                log.debug("Задача 1: устанавливает интервал активности для задачи 2");
                                taskPerformersMap.remove(this);
                            }
                        }
                    }
                });
            }
            if (entry.getValue().getId() == 2) {
                taskPerformer.addRtosAction(new RtosAction() {
                    @Override
                    public void perform(Task task, Integer time) {
                        log.debug("Задача 2: рисует связь с объектом");
                        RtosUI.getCurrent().getFunctionChart()
                                .addPoint(rtosTime, mathFunction.getValue( ((double) rtosTime) *  Math.PI / 12 ));
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
        this.taskMap.put(2, new Task(2, null, null, null, 8, 3, 4, 2, 3, null, TaskState.WAIT_FOR_READY,
                TaskStatus.NOT_ACIVE));
        this.taskMap.put(3, new Task(3, null, 70, null, 6, 3, 4, 3, 4, null, TaskState.WAIT_FOR_READY,
                TaskStatus.NOT_ACIVE));
    }

    public synchronized MathFunction getMathFunction() {
        return mathFunction;
    }

    public synchronized SecondRtosTimer getSecondRtosTimer() {
        return secondRtosTimer;
    }

    public synchronized TenthOfaSecondTimer getTenthOfaSecondTimer() {
        return tenthOfaSecondTimer;
    }

    public synchronized Map<Integer, Task> getTaskMap() {
        return taskMap;
    }

    public synchronized void fireKeyEvent(int keyCode) {
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

    public synchronized void fireClickEvent() {
        log.debug("Right mouse button was pressed in time {}", this.rtosTime);
        if (this.taskMap.get(3).gettStartIntActive() == null) {
            this.taskMap.get(3).settStartIntActive(this.rtosTime + 1);
            log.debug("Tstart activ in task #3 was set in {}", (this.rtosTime + 1) );
            Notification.show("Установлено Тн для задачи три равное " + (this.rtosTime + 1),
                    Notification.Type.TRAY_NOTIFICATION);
        }
    }

    @Override
    public synchronized void timerSecondTick(int second) {
        this.rtosTime = second;
        if (second < 0) {
            return;
        }
        if (second == 0) {
            for (Map.Entry<Integer, TaskPerformer> entry : this.taskPerformersMap.entrySet()) {
                (new Thread(entry.getValue())).start();
            }
            startTc = this.taskMap.get(3).gettSession();
        }
        this.engine.timeTick(second);
    }

    @Override
    public synchronized void timerTenthOfaSecondTick() {
        Task task = this.taskMap.get(3);
        if (task.getTaskState() != TaskState.WORKS) {
            return;
        }
        //log.debug("Задача 3: проверка на превышение времени выполнения {} > {}", task.getWorksTime(), task.gettExecMax());
        if (task.getWorksTime() > task.gettExecMax()) {
            task.setTaskState(TaskState.WAIT_FOR_READY);
            log.debug("Задача 3: превысила время вполнения, установлена в WAIT_FOR_READY");
            task.settPlanCall(task.gettPlanCall() + task.gettPeriodCall());
            task.settPlanCall(task.gettPlanCall() + task.gettPeriodCall());
            log.debug("Задача 3: пропускает один плановый вызов");

            task.settSession(startTc);
            log.debug("Задача 3: сбрасывает время сеанса до " + startTc);

            this.engine.releaseSystem();
            log.debug("Задача {}: овободила систему систему", task.getId());

            task.setWorksTime(0);
            log.debug("Задача 3: сбрасывает время выполнения");
            AbstractEngine.drawtExexMax(this.rtosTime, task);
            AbstractEngine.drawtPlan(this.rtosTime, task);
        }
    }

    public synchronized DipatcherMode getDipatcherMode() {
        return dipatcherMode;
    }

    public synchronized void setDipatcherMode(DipatcherMode dipatcherMode) {
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

    public synchronized long getRtosTime() {
        return rtosTime;
    }
}
