package un.courcework.rtos.core.dispatcher.engine.impl;


import com.vaadin.addon.charts.model.style.SolidColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.core.dispatcher.performer.TaskPerformer;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.model.TaskState;
import un.courcework.rtos.model.TaskStatus;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.RequestWindow;
import un.courcework.rtos.view.component.chart.TaskChart;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class SingleProcEngine extends AbstractEngine {

    private static Logger log = LoggerFactory.getLogger(SingleProcEngine.class);

    private volatile Map<Integer, TaskPerformer> taskPerformersMap;

    private volatile List<TaskPerformer> taskPerformerQueue;

    private volatile boolean systemFree;

    public SingleProcEngine(Map<Integer, TaskPerformer> taskPerformersMap) {
        super(taskPerformersMap);
        this.taskPerformersMap = taskPerformersMap;
        this.taskPerformerQueue = new CopyOnWriteArrayList<TaskPerformer>();
        this.systemFree = true;
    }

    @Override
    public synchronized void timeTick(final int time) {
        super.timeTick(time);
        for (Map.Entry<Integer, TaskPerformer> entry : this.taskPerformersMap.entrySet()) {
            final TaskPerformer taskPerformer = entry.getValue();
            final Task task = taskPerformer.getTask();
            switch (task.getTaskStatus()) {
                case ACTIVE:
                    if (task.gettEndIntActive() != null && task.gettEndIntActive() == time) {
                        task.setTaskStatus(TaskStatus.NOT_ACIVE);
                        log.debug("Задача {}: закончился интервал активности", task.getId());
                        TaskChart taskChart = RtosUI.getCurrent().getTaskChartMap().get(task);
                        taskChart.addPoint(time, TaskChart.MAX_VALUE, SolidColor.PERU);
                    }
                    switch (task.getTaskState()) {
                        case WORKS:
                            if (time == task.gettPlanCall() + task.gettSession()) {
                                log.debug("Задача {}: закончила работать", task.getId());
                                taskPerformer.stop(time);
                                task.settPlanCall(task.gettPlanCall() + task.gettPeriodCall());
                                drawtPlan(time, task);
                                this.systemFree = true;
                                continue;
                            }
                            if (time <= task.gettPlanCall() + task.gettSession()) {
                                wakeUpTask (taskPerformer);
                                log.debug("Задача {}: продолжает работать", task.getId());
                            }
                            break;
                        case READY_TO_WORK:
                            log.debug("Задача {}: проверка на превышение времени ожидания {} >= {}", task.getId(), task.gettWait() + 2 ,task.gettWaitMax());
                            if (task.gettWait() + 2 > task.gettWaitMax()) {
                                log.debug("Задача {}: превысила максимальное время ожидания {}",
                                        task.getId(), task.gettWaitMax());
                                task.settWait(0);
                                task.setTaskStatus(TaskStatus.NOT_ACIVE);

                                RequestWindow requestWindow = new RequestWindow(
                                        "Задача " + task.getId() +
                                                " превысила время ожидания, совершить пропуск вызова на " +
                                                "данном такте или снять задачу?") {
                                    @Override
                                    public void skip() {
                                        taskPerformerQueue.remove(taskPerformer);
                                        int nextPlanCall = task.gettPlanCall() + task.gettPeriodCall();

                                        while (nextPlanCall < RtosUI.getCurrent().getDispatcher().getRtosTime()) {
                                            nextPlanCall = task.gettPlanCall() + task.gettPeriodCall();
                                            task.settPlanCall(nextPlanCall);
                                        }
                                        nextPlanCall = task.gettPlanCall() + task.gettPeriodCall();
                                        task.settPlanCall(nextPlanCall);
                                        drawtPlan(time, task);
                                        task.settWait(0);
                                        task.setTaskState(TaskState.WAIT_FOR_READY);
                                        task.setTaskStatus(TaskStatus.ACTIVE);
                                        log.debug("Задача {}: пропускает вызов на данном этапе", task.getId());
                                    }

                                    @Override
                                    public void remove() {
                                        task.setTaskStatus(TaskStatus.NOT_ACIVE);
                                        task.settEndIntActive(time);
                                        taskPerformerQueue.remove(taskPerformer);
                                        log.debug("Задача {}: задача снята", task.getId());
                                        TaskChart taskChart = RtosUI.getCurrent().getTaskChartMap().get(task);
                                        taskChart.addPoint(time, TaskChart.MAX_VALUE, SolidColor.PERU);
                                    }
                                };

                                TaskChart taskChart = RtosUI.getCurrent().getTaskChartMap().get(task);
                                taskChart.addPoint(time, TaskChart.TASK_VALUE, SolidColor.RED);

                                RtosUI.getCurrent().addWindow(requestWindow);

                            } else {
                                task.settWait(task.gettWait() + 1);
                                log.debug("Задача {}: увеличила время ожидания до {}", task.getId(), task.gettWait());
                            }
                            break;
                        case WAIT_FOR_READY:
                            if (task.gettPlanCall() != null && task.gettPlanCall() == time) {
                                //log.debug("Задача {}: начала работать", task.getId());
                                this.taskPerformerQueue.add(taskPerformer);
                                task.setTaskState(TaskState.READY_TO_WORK);
                                task.settWait(0);
                                log.debug("Задача {}: встала на ожидание запусака", task.getId());
//                                taskPerformer.start(time);
//                                wakeUpTask (taskPerformer);
                            }
                            break;
                        case STOPPED:
                            break;
                    }
                    break;
                case NOT_ACIVE:
                    if (task.gettStartIntActive() != null && task.gettStartIntActive() == time) {
                        task.setTaskStatus(TaskStatus.ACTIVE);
                        log.debug("Задача {}: наступил интервал активности", task.getId());
                        TaskChart taskChart = RtosUI.getCurrent().getTaskChartMap().get(task);
                        taskChart.addPoint(time, TaskChart.MAX_VALUE, SolidColor.YELLOW);
                        this.taskPerformerQueue.add(taskPerformer);
                        task.setTaskState(TaskState.READY_TO_WORK);
                        task.settPlanCall(time + 1);
                        log.debug("Задача {}: встала на ожидание запусака", task.getId());
//                        wakeUpTask (taskPerformer);
                    }
                    break;
            }
        }
        if (this.systemFree) {
            log.debug("Система свободная, на запуск ожидают {} задач(и)", this.taskPerformerQueue.size());
            TaskPerformer taskPerformer = getWaitingTaskPerformer ();
            if (taskPerformer != null) {
                log.debug("Задача {}: начала работать", taskPerformer.getTask().getId());
                this.systemFree = false;
                taskPerformer.start(time);
                taskPerformer.getTask().settPlanCall(time);
                taskPerformer.getTask().settWait(0);
                log.debug("Задача {}: сбросила время ожидания", taskPerformer.getTask().getId());
                wakeUpTask (taskPerformer);
            }
        }
    }

    private TaskPerformer getWaitingTaskPerformer (){
        TaskPerformer taskPriorityPerformer = null;
        if (this.taskPerformerQueue.size() > 0) {
            for (TaskPerformer taskPerformer : this.taskPerformerQueue) {
                if (taskPerformer.getTask().getTaskStatus() == TaskStatus.NOT_ACIVE) {
                    continue;
                }
                if (taskPriorityPerformer == null) {
                    taskPriorityPerformer = taskPerformer;
                    continue;
                }
                if (taskPerformer.getTask().getPriority() >= taskPriorityPerformer.getTask().getPriority()) {
                    if (taskPerformer.getTask().getId() == taskPriorityPerformer.getTask().getId()) {
                        taskPriorityPerformer =
                                ( (taskPerformer.getTask().gettPlanCall()
                                        < taskPriorityPerformer.getTask().gettPlanCall()) ?
                                        taskPerformer : taskPriorityPerformer );
                    } else {
                        taskPriorityPerformer = taskPerformer;
                    }
                }
            }
            this.taskPerformerQueue.remove(taskPriorityPerformer);
        }
        return taskPriorityPerformer;
    }

}
