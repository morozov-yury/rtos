package un.courcework.rtos.view.component.togglebutton;

public class ToggleButtonStateChangeEvent {

	private final ToggleButton source;
	private final int state;
	
	public ToggleButtonStateChangeEvent(ToggleButton source, int state) {
		this.source = source;
		this.state = state;
	}
	
	public ToggleButton getSource() {
		return source;
	}
	
	public int getToggleState() {
		return state;
	}
	
}
