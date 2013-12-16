package un.courcework.rtos.core.dispatcher.engine.impl;


import com.vaadin.addon.charts.model.style.SolidColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.core.dispatcher.engine.Engine;
import un.courcework.rtos.core.dispatcher.performer.TaskPerformer;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.chart.TaskChart;

import java.util.Map;

public abstract class AbstractEngine implements Engine {

    private static Logger log = LoggerFactory.getLogger(AbstractEngine.class);

    private volatile Map<Integer, TaskPerformer> taskPerformersMap;

    public AbstractEngine(Map<Integer, TaskPerformer> taskPerformersMap) {
        this.taskPerformersMap = taskPerformersMap;
    }

    @Override
    public void timeTick(int time) {
        for (Map.Entry<Task, TaskChart> entry : RtosUI.getCurrent().getTaskChartMap().entrySet()) {
            entry.getValue().addPoint(time, TaskChart.MAX_VALUE, new SolidColor(226, 226, 226));
        }
    }

    protected void drawtPlan(int time, Task task) {
        TaskChart taskChart = RtosUI.getCurrent().getTaskChartMap().get(task);
        taskChart.addPoint(task.gettPlanCall(), TaskChart.MARK_VALUE, SolidColor.BLUE);
    }

    protected void drawtExexMax(int time, Task task) {
        TaskChart taskChart = RtosUI.getCurrent().getTaskChartMap().get(task);
        taskChart.addPoint(time, TaskChart.TASK_VALUE, SolidColor.RED);
    }

}
