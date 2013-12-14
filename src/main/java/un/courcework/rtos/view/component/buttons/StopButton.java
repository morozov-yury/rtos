package un.courcework.rtos.view.component.buttons;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeButton;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.chart.TaskChart;
import un.courcework.rtos.view.component.layout.LeftPanel;

import java.util.ArrayList;
import java.util.List;

public class StopButton extends NativeButton  {

    public StopButton(final LeftPanel leftPanel, final ParametersPanel parametersPanel) {
        setSizeFull();
        setIcon(new ThemeResource("images/16x16/stop.png"), "Stop");
        setDescription("Stop the simulation");
        addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                RtosUI.getCurrent().getDispatcher().getSecondRtosTimer().stopTimer();
                RtosUI.getCurrent().getDispatcher().getTenthOfaSecondTimer().stopTimer();
                RtosUI.getCurrent().renewTaskCharts();
                List<TaskChart> newTaskChartList = new ArrayList<TaskChart>();
                for (int i = 0; i < RtosUI.getCurrent().getDispatcher().getTaskMap().size(); i++) {
                    Task task = RtosUI.getCurrent().getDispatcher().getTaskMap().get(i + 1);
                    TaskChart newTaskChart = RtosUI.getCurrent().getTaskChartMap().get(task);
                    TaskChart oldtaskChart = leftPanel.getTaskChartsList().get(i);
                    leftPanel.replaceComponent(oldtaskChart, newTaskChart);
                    newTaskChartList.add(newTaskChart);
                }
                leftPanel.setTaskChartsList(newTaskChartList);
                parametersPanel.setStatus(ParametersPanel.ParametersPanelStatus.AVAILABLE_TO_EDIT);
                RtosUI.getCurrent().getDispatcher().restart();
                parametersPanel.renew();
            }
        });
    }
}
