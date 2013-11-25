package un.courcework.rtos.view.component.buttons;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeButton;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.chart.TaskChart;
import un.courcework.rtos.view.component.layout.ContentLayout;

import java.util.ArrayList;
import java.util.List;

public class StopButton extends NativeButton  {

    public StopButton(final ContentLayout contentLayout) {
        setSizeFull();
        setIcon(new ThemeResource("images/16x16/stop.png"), "Stop");
        setDescription("Stop the simulation");
        addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                RtosUI.getCurrent().getSecondRtosTimer().stopTimer();
                RtosUI.getCurrent().renewTaskCharts();
                List<TaskChart> newTaskChartList = new ArrayList<TaskChart>();
                for (int i = 0; i < RtosUI.getCurrent().listTasks().size(); i++) {
                    Task task = RtosUI.getCurrent().listTasks().get(i);
                    TaskChart newTaskChart = RtosUI.getCurrent().getTaskChartMap().get(task);
                    TaskChart oldtaskChart = contentLayout.getTaskChartsList().get(i);
                    contentLayout.getLeftContentLayout().replaceComponent(oldtaskChart, newTaskChart);
                    newTaskChartList.add(newTaskChart);
                }
                contentLayout.setTaskChartsList(newTaskChartList);
            }
        });
    }
}
