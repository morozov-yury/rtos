package un.courcework.rtos.core.timer;

import un.courcework.rtos.core.TimerAware;

import java.util.List;

public interface RtosTimer {

    public void startTimer();

    public void stopTimer();

    public void pauseTimer();

    public void addTickListener(TimerAware timerAware);

    public List<TimerAware> listTickListeners();

    public void removeTickListener(TimerAware timerAware);

    public void removeAllTickListener();

}
