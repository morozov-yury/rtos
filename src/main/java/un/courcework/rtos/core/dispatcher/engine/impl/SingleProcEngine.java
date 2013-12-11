package un.courcework.rtos.core.dispatcher.engine.impl;


import un.courcework.rtos.core.dispatcher.performer.TaskPerformer;

import java.util.List;

public class SingleProcEngine extends AbstractEngine {

    public SingleProcEngine(List<TaskPerformer> taskPerformers) {
        super(taskPerformers);
    }
}
