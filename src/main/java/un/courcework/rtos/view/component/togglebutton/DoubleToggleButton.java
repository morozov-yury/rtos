package un.courcework.rtos.view.component.togglebutton;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;

public class DoubleToggleButton extends ToggleButton {

	private static final long serialVersionUID = 7416407839206857684L;

	private Button btnLeft;
	private Button btnRight;
	private int state = STATE_FIRST;
	
	public DoubleToggleButton(String captionFirst, String captionSecond) {
		setSizeUndefined();
		btnLeft = new NativeButton(captionFirst);
		btnLeft.setEnabled(false);
		btnRight = new NativeButton(captionSecond);
		btnLeft.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 4524129202870883307L;
			@Override
			public void buttonClick(ClickEvent event) {
				setToggleState(STATE_FIRST);
			}
		});
		btnRight.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -7820013114056449435L;
			@Override
			public void buttonClick(ClickEvent event) {
				setToggleState(STATE_SECOND);
			}
		});
		HorizontalLayout content = new HorizontalLayout();
		content.addComponent(btnLeft);
		content.addComponent(btnRight);
		addComponent(content);
	}
	
	@Override
	public void setToggleState(int state) {
		this.state = state;
		if(state == STATE_FIRST) {
			btnLeft.setEnabled(false);
			btnRight.setEnabled(true);
		} else if(state == STATE_SECOND) {
			btnLeft.setEnabled(true);
			btnRight.setEnabled(false);
		} else {
			throw new IllegalArgumentException("Wrong state: " + state);
		}
		fireStateCanged(this, state);
	}
	
	@Override
	public int getToggleState() {
		return state;
	}
	
	public void setLeftButtonIcon(Resource leftButtonIcon) {
		btnLeft.setIcon(leftButtonIcon);
	}
	
	public void setRightButtonIcon(Resource rightButtonIcon) {
		btnRight.setIcon(rightButtonIcon);
	}
}
