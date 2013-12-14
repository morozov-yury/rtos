package un.courcework.rtos.core.dispatcher.engine.impl;


import un.courcework.rtos.core.dispatcher.performer.TaskPerformer;

import java.util.List;
import java.util.Map;

public class SingleProcEngine extends AbstractEngine {

    public SingleProcEngine(Map<Integer, TaskPerformer> taskPerformersMap) {
        super(taskPerformersMap);
    }
}
