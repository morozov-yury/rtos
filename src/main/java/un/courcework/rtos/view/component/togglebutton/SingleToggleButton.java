package un.courcework.rtos.view.component.togglebutton;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;

public class SingleToggleButton extends ToggleButton {

	private static final long serialVersionUID = 7416407839206857684L;

	private Button btn;
	private int state = STATE_FIRST;
	
	public SingleToggleButton() {
		setSizeUndefined();
		btn = new Button("OFF");
		btn.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -8499199952602796440L;
			@Override
			public void buttonClick(ClickEvent event) {
				setToggleState(state == STATE_FIRST ? STATE_SECOND : STATE_FIRST);
			}
		});
		HorizontalLayout content = new HorizontalLayout();
		content.addComponent(btn);
		addComponent(content);
	}
	
	@Override
	public void setToggleState(int state) {
		this.state = state;
		if(state == STATE_FIRST) {
			btn.setCaption("OFF");
		} else if(state == STATE_SECOND) {
			btn.setCaption("ON");
		} else {
			throw new IllegalArgumentException("Wrong state: " + state);
		}
		fireStateCanged(this, state);
	}
	
	@Override
	public int getToggleState() {
		return state;
	}
	
	public void setIcon(Resource buttonIcon) {
		btn.setIcon(buttonIcon);
	}
}
