package un.courcework.rtos.view.component.layout;


import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.VerticalLayout;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.ButtonsPanel;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.TimePanel;
import un.courcework.rtos.view.component.togglebutton.DoubleToggleButton;

public class RightPanel extends VerticalLayout {

    private ParametersPanel parametersPanel;

    public RightPanel (LeftPanel leftPanel) {
        setSpacing(true);
        setWidth(185, Unit.PIXELS);

        PopupView popupViewLegendPanel = new PopupView("Все бозначения", new HiddenLegendPanel());
        DoubleToggleButton toggleButton = new DoubleToggleButton("1пр", "Мпр");
        HorizontalLayout hl = new HorizontalLayout();
        hl.setSpacing(true);
        hl.addComponent(popupViewLegendPanel);
        hl.addComponent(toggleButton);

        parametersPanel = new ParametersPanel(RtosUI.getCurrent().getDispatcher().getTasks());

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
