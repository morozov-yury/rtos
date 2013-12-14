package un.courcework.rtos.core.timer.impl;

import un.courcework.rtos.core.timer.RtosTimer;
import un.courcework.rtos.core.timer.TimerAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class TenthOfaSecondTimer implements RtosTimer {

    private List<TimerAware> tickListeners;
    private Timer timer;
    private TimerTask task;

    public TenthOfaSecondTimer () {
        this.tickListeners = new ArrayList<TimerAware>();
        initTaimer();
    }

    private void initTaimer () {
        this.timer = new Timer();
        this.task = new TimerTask() {
            public void run() {
                awareListeners();
            }
        };
    }

    private void awareListeners () {
        for (TimerAware timerAware : this.tickListeners) {
            timerAware.timerTenthOfaSecondTick();
        }
    }

    @Override
    public void startTimer() {
        initTaimer();
        this.timer.schedule(task, 0, 100);
    }

    @Override
    public void stopTimer() {
        this.timer.cancel();
        initTaimer();
        awareListeners();
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
