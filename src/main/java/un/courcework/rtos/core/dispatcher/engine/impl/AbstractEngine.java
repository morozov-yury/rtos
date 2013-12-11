package un.courcework.rtos.core.dispatcher.engine.impl;


import un.courcework.rtos.core.dispatcher.engine.Engine;
import un.courcework.rtos.core.dispatcher.performer.TaskPerformer;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.chart.TaskChart;

import java.util.List;
import java.util.Map;

public abstract class AbstractEngine implements Engine {

    private List<TaskPerformer> taskPerformers;

    public AbstractEngine(List<TaskPerformer> taskPerformers) {
        this.taskPerformers = taskPerformers;
    }

    @Override
    public void timeTick(int time) {
        //System.out.println("AbstractEngine " + time);
        for (TaskPerformer taskPerformer : this.taskPerformers) {
            taskPerformer.timerSecondTick(time);
        }
    }
}
