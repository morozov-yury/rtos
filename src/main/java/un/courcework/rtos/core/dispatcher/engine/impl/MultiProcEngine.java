package un.courcework.rtos.core.dispatcher.engine.impl;


import un.courcework.rtos.core.dispatcher.performer.TaskPerformer;

import java.util.List;

public class MultiProcEngine extends AbstractEngine {

    public MultiProcEngine(List<TaskPerformer> taskPerformers) {
        super(taskPerformers);
    }

}
