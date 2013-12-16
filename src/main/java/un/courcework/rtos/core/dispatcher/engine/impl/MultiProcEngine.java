package un.courcework.rtos.core.dispatcher.engine.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.core.dispatcher.performer.TaskPerformer;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.model.TaskState;
import un.courcework.rtos.model.TaskStatus;

import java.util.Map;

public class MultiProcEngine extends AbstractEngine {

    private static Logger log = LoggerFactory.getLogger(MultiProcEngine.class);

    private Map<Integer, TaskPerformer> taskPerformersMap;

    public MultiProcEngine(Map<Integer, TaskPerformer> taskPerformersMap) {
        super(taskPerformersMap);
        this.taskPerformersMap = taskPerformersMap;
    }

    @Override
    public void timeTick(int time) {
        super.timeTick(time);
        for (Map.Entry<Integer, TaskPerformer> entry : this.taskPerformersMap.entrySet()) {
            TaskPerformer taskPerformer = entry.getValue();
            Task task = taskPerformer.getTask();
            switch (task.getTaskStatus()) {
                case ACTIVE:
                    if (task.gettEndIntActive() != null && task.gettEndIntActive() == time) {
                        log.debug("Task {} закончился интервал активности", task.getId());
                        taskPerformer.timerSecondTick(time);
                        task.setTaskStatus(TaskStatus.NOT_ACIVE);
                    }
                    switch (task.getTaskState()) {
                        case WORKS:
                            if ((time  - task.gettPlanCall()) > task.gettExecMax()) {
                                drawtExexMax(task.gettPlanCall() + task.gettExecMax(), task);
                                task.setTaskState(TaskState.WAIT_FOR_READY);
                                task.settPlanCall(task.gettPlanCall() + task.gettPeriodCall());
                                task.settPlanCall(task.gettPlanCall() + task.gettPeriodCall());
                                drawtPlan(time, task);
                                continue;
                            }
                            if (time == task.gettPlanCall() + task.gettSession()) {
                                log.debug("Task {} закончила работать", task.getId());
                                task.setTaskState(TaskState.WAIT_FOR_READY);
                                task.settPlanCall(task.gettPlanCall() + task.gettPeriodCall());
                                if (task.getId() == 3) {
                                    task.settSession(task.gettSession() + 1);
                                }
                                drawtPlan(time, task);
                                continue;
                            }
                            if (time <= task.gettPlanCall() + task.gettSession()) {
                                taskPerformer.timerSecondTick(time);
                                log.debug("Task {} продолжает работать", task.getId());
                            }
                            break;
                        case READY_TO_WORK:
                            break;
                        case WAIT_FOR_READY:
                            if (task.gettPlanCall() == time) {
                                log.debug("Task {} начала работать", task.getId());
                                task.setTaskState(TaskState.WORKS);
                                task.settPlanCall(time);
                                taskPerformer.timerSecondTick(time);
                            }
                            break;
                        case STOPPED:
                            break;
                    }
                    break;
                case NOT_ACIVE:
                    if (task.gettStartIntActive() != null && task.gettStartIntActive() == time) {
                        task.setTaskStatus(TaskStatus.ACTIVE);
                        task.setTaskState(TaskState.WORKS);
                        task.settPlanCall(time);
                        taskPerformer.timerSecondTick(time);
                        log.debug("Task {} начала работать", task.getId());
                    }
                    break;
            }
            //entry.getValue().timerSecondTick(time);
        }
    }

}
