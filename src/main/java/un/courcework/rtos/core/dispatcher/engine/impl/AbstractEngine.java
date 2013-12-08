package un.courcework.rtos.core.dispatcher.engine.impl;


import un.courcework.rtos.core.dispatcher.engine.Engine;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.chart.TaskChart;

import java.util.List;
import java.util.Map;

public abstract class AbstractEngine implements Engine {

    private List<Task> tasks;

    public AbstractEngine(List<Task> tasks) {

        this.tasks = tasks;
    }

    @Override
    public void timeTick(int time) {
        Map<Task,TaskChart> taskChartMap = RtosUI.getCurrent().getTaskChartMap();
        for (Task task : tasks) {
            TaskChart taskChart = taskChartMap.get(task);
            if (taskChart != null) {
                taskChart.addPoint(time, TaskChart.MAX_VALUE);
            }
        }
    }
}
