package un.courcework.rtos.core.timer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.core.dispatcher.Dispatcher;
import un.courcework.rtos.core.timer.RtosTimer;
import un.courcework.rtos.core.timer.TimerAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SecondRtosTimer implements RtosTimer {

    private static Logger log = LoggerFactory.getLogger(SecondRtosTimer.class);

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
                awareListeners(tickCount);
                tickCount++;
                if (tickCount > Dispatcher.MODELLING_TIME) {
                    stopTimer();
                }
            }
        };
    }

    private void awareListeners (int tickCount) {
        log.debug("Modelling time: " + tickCount);
        for (TimerAware timerAware : this.tickListeners) {
            timerAware.timerSecondTick(tickCount);
            //RtosUI.getCurrent().push();
        }
    }

    @Override
    public void startTimer() {
        initTaimer();
        //awareListeners(tickCount);
        this.timer.schedule(task, 0, 1000);
    }

    @Override
    public void stopTimer() {
        this.tickCount = -1;
        awareListeners(tickCount);
        this.tickCount = 0;
        this.timer.cancel();
        initTaimer();
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
