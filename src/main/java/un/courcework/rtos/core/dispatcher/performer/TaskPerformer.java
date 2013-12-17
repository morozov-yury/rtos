package un.courcework.rtos.core.dispatcher.performer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.core.dispatcher.action.RtosAction;
import un.courcework.rtos.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TaskPerformer implements Runnable {

    private static Logger log = LoggerFactory.getLogger(TaskPerformer.class);

    private volatile List<RtosAction> actionsList;

    private volatile Task task;

    public TaskPerformer (Task task) {
        this.task = task;
        this.actionsList = new CopyOnWriteArrayList<RtosAction>();
    }

    public  void addRtosAction (RtosAction rtosAction) {
        this.actionsList.add(rtosAction);
    }

    @Override
    public void run() {
        synchronized (this) {
            while (true) {
                try {
                    this.wait();
                    for (RtosAction rtosAction : this.actionsList) {
                        rtosAction.perform(this.task, null);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Task getTask() {
        return this.task;
    }
}
