package un.courcework.rtos.core.dispatcher.engine.impl;


import un.courcework.rtos.model.Task;

import java.util.List;

public class MultiProcEngine extends AbstractEngine {

    public MultiProcEngine(List<Task> tasks) {
        super(tasks);
    }

}
