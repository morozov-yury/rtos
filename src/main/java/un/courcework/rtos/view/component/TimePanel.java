package un.courcework.rtos.view.component;

import com.vaadin.ui.HorizontalLayout;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.time.SimulationTimeLabel;
import un.courcework.rtos.view.component.time.SystemTimeLabel;

public class TimePanel extends HorizontalLayout {

    public TimePanel() {
        setImmediate(true);
        setSpacing(true);
        addStyleName("time-panel");

        SimulationTimeLabel simulationTimeLabel = new SimulationTimeLabel();
        RtosUI.getCurrent().getDispatcher().getSecondRtosTimer().addTickListener(simulationTimeLabel);
        addComponent(simulationTimeLabel);

        SystemTimeLabel systemTimeLabel = new SystemTimeLabel();
        addComponent(systemTimeLabel);
    }
}
