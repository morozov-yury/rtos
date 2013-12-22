package un.courcework.rtos.view.component.layout;


import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.VerticalLayout;
import un.courcework.rtos.core.dispatcher.DipatcherMode;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.ButtonsPanel;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.TimePanel;
import un.courcework.rtos.view.component.togglebutton.DoubleToggleButton;
import un.courcework.rtos.view.component.togglebutton.ToggleButton;
import un.courcework.rtos.view.component.togglebutton.ToggleButtonStateChangeEvent;
import un.courcework.rtos.view.component.togglebutton.ToggleButtonStateListener;

public class RightPanel extends VerticalLayout {

    private ParametersPanel parametersPanel;

    public RightPanel (LeftPanel leftPanel) {
        setSpacing(true);
        setWidth(185, Unit.PIXELS);

        PopupView popupViewLegendPanel = new PopupView("Все бозначения", new HiddenLegendPanel());
        final DoubleToggleButton toggleButton = new DoubleToggleButton("1пр", "Мпр");
        HorizontalLayout hl = new HorizontalLayout();
        hl.setSpacing(true);
        hl.addComponent(popupViewLegendPanel);
        hl.addComponent(toggleButton);

        toggleButton.setToggleState(ToggleButton.STATE_FIRST);
        toggleButton.addStateListener(new ToggleButtonStateListener() {
            @Override
            public void stateChanged(ToggleButtonStateChangeEvent event) {
                switch (toggleButton.getToggleState()) {
                    case ToggleButton.STATE_FIRST:
                        RtosUI.getCurrent().getDispatcher().setDipatcherMode(
                                DipatcherMode.SINGLE_PROCESSOR);
                        Notification.show(
                                "Включен однопроцессорный режим",
                                Notification.Type.TRAY_NOTIFICATION);
                        break;
                    case ToggleButton.STATE_SECOND:
                        RtosUI.getCurrent().getDispatcher().setDipatcherMode(
                                DipatcherMode.MULTIPROCESSOR);
                        Notification.show(
                                "Включен многопроцессорный режим",
                                Notification.Type.TRAY_NOTIFICATION);
                        break;
                }
            }
        });

        parametersPanel = new ParametersPanel(RtosUI.getCurrent().getDispatcher().getTaskMap());

        addComponent(new TimePanel());
        addComponent(new ButtonsPanel(leftPanel, this));
        addComponent(parametersPanel);
        addComponent(hl);
        addComponent(new VisibleLegendPanel());
        addComponent(new ActionsLayout());
    }

    public ParametersPanel getParametersPanel () {
        return this.parametersPanel;
    }

}
