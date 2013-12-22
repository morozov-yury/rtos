package un.courcework.rtos.core.dispatcher.engine.impl;


import com.vaadin.addon.charts.model.style.SolidColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.core.dispatcher.performer.TaskPerformer;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.model.TaskState;
import un.courcework.rtos.model.TaskStatus;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.chart.TaskChart;

import java.util.Map;

public class MultiProcEngine extends AbstractEngine {

    private static Logger log = LoggerFactory.getLogger(MultiProcEngine.class);

    private volatile Map<Integer, TaskPerformer> taskPerformersMap;

    public MultiProcEngine(Map<Integer, TaskPerformer> taskPerformersMap) {
        super(taskPerformersMap);
        this.taskPerformersMap = taskPerformersMap;
    }

    @Override
    public synchronized void timeTick(int time) {
        super.timeTick(time);
        for (Map.Entry<Integer, TaskPerformer> entry : this.taskPerformersMap.entrySet()) {
            TaskPerformer taskPerformer = entry.getValue();
            Task task = taskPerformer.getTask();
            switch (task.getTaskStatus()) {
                case ACTIVE:
                    if (task.gettEndIntActive() != null && task.gettEndIntActive() == time) {
                        task.setTaskStatus(TaskStatus.NOT_ACIVE);
                        log.debug("Задача {}: закончился интервал активности", task.getId());TaskChart taskChart = RtosUI.getCurrent().getTaskChartMap().get(task);
                        taskChart.addPoint(time, TaskChart.MAX_VALUE, SolidColor.PERU);
                    }
                    switch (task.getTaskState()) {
                        case WORKS:
                            if (time == task.gettPlanCall() + task.gettSession()) {
                                log.debug("Задача {}: закончила работать", task.getId());
                                taskPerformer.stop(time);
                                task.settPlanCall(task.gettPlanCall() + task.gettPeriodCall());
                                drawtPlan(time, task);
                                continue;
                            }
                            if (time <= task.gettPlanCall() + task.gettSession()) {
                                wakeUpTask (taskPerformer);
                                log.debug("Задача {}: продолжает работать", task.getId());
                            }
                            break;
                        case READY_TO_WORK:
                            break;
                        case WAIT_FOR_READY:
                            if (task.gettPlanCall() == time) {
                                log.debug("Задача {}: начала работать", task.getId());
                                taskPerformer.start(time);
                                wakeUpTask (taskPerformer);
                            }
                            break;
                        case STOPPED:
                            break;
                    }
                    break;
                case NOT_ACIVE:
                    if (task.gettStartIntActive() != null && task.gettStartIntActive() == time) {
                        task.setTaskStatus(TaskStatus.ACTIVE);
                        taskPerformer.start(time);
                        task.settPlanCall(time);
                        log.debug("Задача {}: наступил интервал активности", task.getId());
                        TaskChart taskChart = RtosUI.getCurrent().getTaskChartMap().get(task);
                        taskChart.addPoint(time, TaskChart.MAX_VALUE, SolidColor.YELLOW);
                        wakeUpTask (taskPerformer);
                    }
                    break;
            }
        }
    }


}
