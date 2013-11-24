package un.courcework.rtos.view.component.time;

import com.vaadin.ui.Label;
import un.courcework.rtos.view.RtosUI;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SystemTimeLabel extends Label {

    private Format dateFormatter;
    private Timer timer;
    private TimerTask task;

    public SystemTimeLabel () {
        this.dateFormatter = new SimpleDateFormat("HH:mm:ss");
        setValue(this.dateFormatter.format(new Date()));
        addStyleName("rtos-timer");
        setImmediate(true);
        this.timer = new Timer();
        this.task = new TimerTask() {
            public void run() {
                setValue(dateFormatter.format(new Date()));
                RtosUI.getCurrent().push();
            }
        };
        this.timer.schedule(task, 0, 1000);
    }

}
