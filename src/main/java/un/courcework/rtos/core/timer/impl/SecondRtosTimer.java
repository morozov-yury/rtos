package un.courcework.rtos.core.timer.impl;

import com.vaadin.ui.Notification;
import un.courcework.rtos.core.TimerAware;
import un.courcework.rtos.core.timer.RtosTimer;
import un.courcework.rtos.view.RtosUI;

import java.util.*;

public class SecondRtosTimer implements RtosTimer {

    private List<TimerAware> tickListeners;
    private int tickCount;
    private Timer timer;
    private TimerTask task;

    public SecondRtosTimer () {
        this.tickCount = 0;
        this.tickListeners = new ArrayList<TimerAware>();
        initTaimer();
    }

    private void initTaimer () {
        this.timer = new Timer();
        this.task = new TimerTask() {
            public void run() {
                RtosUI.getCurrent().showTrayNotification("Timer tick " + tickCount);
                awareListeners(tickCount);
                tickCount++;
            }
        };
    }

    private void awareListeners (int tickCount) {
        for (TimerAware timerAware : this.tickListeners) {
            timerAware.timerSecondTick(tickCount);
            RtosUI.getCurrent().push();
            System.out.println(tickCount);
        }
    }

    @Override
    public void startTimer() {
        initTaimer();
        awareListeners(tickCount);
        this.timer.schedule(task, 0, 1000);
    }

    @Override
    public void stopTimer() {
        this.tickCount = 0;
        this.timer.cancel();
        initTaimer();
        awareListeners(tickCount);
    }

    @Override
    public void pauseTimer() {
        this.timer.cancel();
        initTaimer();
    }

    @Override
    public void addTickListener(TimerAware timerAware) {
        this.tickListeners.add(timerAware);
    }

    @Override
    public List<TimerAware> listTickListeners() {
        return this.tickListeners;
    }

    @Override
    public void removeTickListener(TimerAware timerAware) {
        this.tickListeners.remove(timerAware);
    }

    @Override
    public void removeAllTickListener() {
        this.tickListeners.clear();
    }

}
