package un.courcework.rtos.view.component.togglebutton;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.VerticalLayout;

public abstract class ToggleButton extends VerticalLayout {

	private static final long serialVersionUID = -4365738499990038012L;
	
	public static final int STATE_FIRST = 0;
	public static final int STATE_SECOND = 1;
	
	private final List<ToggleButtonStateListener> toggleButtonStateListeners = new ArrayList<ToggleButtonStateListener>();

	protected void fireStateCanged(ToggleButton source, int state) {
		ToggleButtonStateChangeEvent event = new ToggleButtonStateChangeEvent(source, state);
		for(ToggleButtonStateListener l: toggleButtonStateListeners)
			l.stateChanged(event);
	}

	public void addStateListener(ToggleButtonStateListener listener) {
		toggleButtonStateListeners.add(listener);
	}
	
	public void removeClickListener(ToggleButtonStateListener listener) {
		toggleButtonStateListeners.remove(listener);
	}

	public abstract void setToggleState(int state);
	
	public abstract int getToggleState();
}