package un.courcework.rtos.core.dispatcher.engine.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.core.dispatcher.engine.Engine;
import un.courcework.rtos.core.dispatcher.performer.TaskPerformer;

import java.util.Map;

public abstract class AbstractEngine implements Engine {

    private static Logger log = LoggerFactory.getLogger(AbstractEngine.class);

    private volatile Map<Integer, TaskPerformer> taskPerformersMap;

    public AbstractEngine(Map<Integer, TaskPerformer> taskPerformersMap) {
        this.taskPerformersMap = taskPerformersMap;
    }

    @Override
    public void timeTick(int time) {

    }

}
