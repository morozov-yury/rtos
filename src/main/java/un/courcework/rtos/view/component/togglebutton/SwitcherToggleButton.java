package un.courcework.rtos.view.component.togglebutton;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;

public class SwitcherToggleButton extends ToggleButton {

	private static final long serialVersionUID = 8586085709871666672L;

	private final Panel indicatorPanel;
	private HorizontalLayout view;
	private int state = STATE_FIRST;
	
	public SwitcherToggleButton() {
		setWidth(50, Unit.PIXELS);
		setHeight(25, Unit.PIXELS);
		
		view = new HorizontalLayout();
		
		indicatorPanel = new Panel();
		indicatorPanel.setWidth(25, Unit.PIXELS);
		indicatorPanel.setHeight(15, Unit.PIXELS);
		view.addComponent(indicatorPanel);
		view.setComponentAlignment(indicatorPanel, Alignment.MIDDLE_LEFT);
		
		view.addLayoutClickListener(new LayoutClickListener() {
			private static final long serialVersionUID = 740606943857453552L;
			@Override
			public void layoutClick(LayoutClickEvent event) {
				setToggleState(state == STATE_FIRST ? STATE_SECOND : STATE_FIRST);
			}
		});
		view.setSizeFull();
		addComponent(view);
	}
	
	@Override
	public void setToggleState(int state) {
		this.state = state;
		view.setComponentAlignment(indicatorPanel, state == STATE_FIRST ? Alignment.MIDDLE_LEFT : Alignment.MIDDLE_RIGHT);
		fireStateCanged(this, state);
	}

	@Override
	public int getToggleState() {
		return state;
	}
}
