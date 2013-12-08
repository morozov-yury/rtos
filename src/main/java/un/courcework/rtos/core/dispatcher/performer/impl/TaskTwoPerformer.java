package un.courcework.rtos.core.dispatcher.performer.impl;

import un.courcework.rtos.core.dispatcher.performer.TaskPerformer;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.chart.TaskChart;

public class TaskTwoPerformer implements TaskPerformer {

    private Task task;

    public TaskTwoPerformer(Task task) {
        this.task = task;
    }

    @Override
    public void perform(int time) {
        TaskChart taskChart = RtosUI.getCurrent().getTaskChartMap().get(this.task);
        if (taskChart == null) {
            return;
        }

    }
    
}
