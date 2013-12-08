package un.courcework.rtos.core.dispatcher.engine.impl;


import un.courcework.rtos.model.Task;

import java.util.List;

public class SingleProcEngine extends AbstractEngine {

    public SingleProcEngine(List<Task> tasks) {
        super(tasks);
    }
}
