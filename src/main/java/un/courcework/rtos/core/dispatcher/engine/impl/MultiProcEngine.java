package un.courcework.rtos.core.dispatcher.engine.impl;


import un.courcework.rtos.core.dispatcher.performer.TaskPerformer;
import un.courcework.rtos.model.Task;

import java.util.Map;

public class MultiProcEngine extends AbstractEngine {

    private Map<Integer, TaskPerformer> taskPerformersMap;

    public MultiProcEngine(Map<Integer, TaskPerformer> taskPerformersMap) {
        super(taskPerformersMap);
        this.taskPerformersMap = taskPerformersMap;
    }

    @Override
    public void timeTick(int time) {
        for (Map.Entry<Integer, TaskPerformer> entry : this.taskPerformersMap.entrySet()) {
            TaskPerformer taskPerformer = entry.getValue();
            Task task = taskPerformer.getTask();
            switch (task.getTaskStatus()) {
                case ACTIVE:
                    switch (task.getTaskState()) {
                        case WORKS:
                            break;
                        case READY_TO_WORK:
                            break;
                        case WAIT_FOR_READY:
                            break;
                        case STOPPED:
                            break;
                    }
                    break;
                case NOT_ACIVE:

                    break;
            }
            //entry.getValue().timerSecondTick(time);
        }
    }

}
