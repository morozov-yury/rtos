package un.courcework.rtos.view.component.time;

import com.vaadin.ui.Label;
import un.courcework.rtos.core.timer.TimerAware;

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
        if (second == -1){
            setValue(this.dateFormatter.format(new Date(0)));
        } else {
            setValue(this.dateFormatter.format(new Date(second * 1000)));
        }
        //RtosUI.getCurrent().push();
    }

    @Override
    public void timerTenthOfaSecondTick() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
