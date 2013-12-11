package un.courcework.rtos.core.dispatcher.performer;


import un.courcework.rtos.core.dispatcher.action.RtosAction;
import un.courcework.rtos.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskPerformer implements Runnable {

    private List<RtosAction> actionsList;

    private Task task;

    private volatile Integer newTick = null;

    private volatile  boolean isStopped = false;

    public TaskPerformer (Task task) {
        this.task = task;
        this.actionsList = new ArrayList<RtosAction>();
    }

    public  void addRtosAction (RtosAction rtosAction) {
        this.actionsList.add(rtosAction);
    }

    @Override
    public void run() {
        //System.out.println("TaskPerformer run, task " + this.task.getId());
        while (true) {
            //System.out.println("while task " + this.task.getId());
            if (this.isStopped) {
                waitFor(0);
            }
            if (this.newTick != null) {
//                System.out.println("TaskPerformer run, task " + this.task.getId()
//                        + ", tick " + this.newTick);
                for (RtosAction rtosAction : this.actionsList) {
                    rtosAction.perform(this.task, this.newTick);
                }
                this.newTick = null;
            }
            waitFor(100);
        }
    }

    private void waitFor(long timeout) {
        try {
            synchronized (this) {
                this.wait(timeout);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void timerSecondTick(int second) {
//        System.out.println("TaskPerformer timerSecondTick, task " + this.task.getId()
//                + ", tick " + this.newTick);
        this.newTick = new Integer(second);
    }

    public void stop () {
        this.isStopped = true;
    }
}
