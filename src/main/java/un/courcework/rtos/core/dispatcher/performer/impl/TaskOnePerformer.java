package un.courcework.rtos.core.dispatcher.performer.impl;

import un.courcework.rtos.core.dispatcher.performer.TaskPerformer;
import un.courcework.rtos.model.Task;

public class TaskOnePerformer implements TaskPerformer {

    private Task task;

    public TaskOnePerformer(Task task) {
        this.task = task;
    }

    @Override
    public void perform(int time) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
