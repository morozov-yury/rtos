package un.courcework.rtos.view.component.time;

import com.vaadin.ui.Label;
import un.courcework.rtos.core.timer.TimerAware;
import un.courcework.rtos.view.RtosUI;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimulationTimeLabel  extends Label implements TimerAware {

    private Format dateFormatter;

    public SimulationTimeLabel () {
        this.dateFormatter = new SimpleDateFormat("mm:ss");
        setValue(this.dateFormatter.format(new Date(0L)));
        addStyleName("rtos-timer");
        setImmediate(true);
    }

    @Override
    public void timerSecondTick(int second) {
        setValue(this.dateFormatter.format(new Date(second * 1000)));
        RtosUI.getCurrent().push();
    }
}
