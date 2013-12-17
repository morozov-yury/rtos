package un.courcework.rtos.core.dispatcher.action;

import un.courcework.rtos.model.Task;

public interface RtosAction {

    public void perform (Task task, Integer time);

}
