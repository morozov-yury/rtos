package un.courcework.rtos.view.component.layout;

import com.vaadin.event.LayoutEvents;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.VerticalLayout;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.MathUtils;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.chart.DiscreteFunctionChart;
import un.courcework.rtos.view.component.chart.TaskChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LeftPanel extends VerticalLayout {

    private List<TaskChart> taskChartsList;

    private DiscreteFunctionChart functionChart;

    public LeftPanel() {
        this.taskChartsList = new ArrayList<TaskChart>();
        float browserWindowHeight = Page.getCurrent().getBrowserWindowHeight() - 50;

        this.functionChart = new DiscreteFunctionChart(
                RtosUI.getCurrent().getDispatcher().getMathFunction(), 73.0, Math.PI / 12);
        this.functionChart.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);
        this.functionChart.setPerLine(MathUtils.round(72.0 / 6, 2));

        RtosUI.getCurrent().setFunctionChart(functionChart);

        for (Map.Entry<Integer, Task> entry : RtosUI.getCurrent().getDispatcher().getTaskMap().entrySet()) {
            TaskChart taskChart = RtosUI.getCurrent().getTaskChartMap().get(entry.getValue());
            this.taskChartsList.add(taskChart);
            addComponent(taskChart);
        }

        addComponent(this.functionChart);
        setWidth(Page.getCurrent().getBrowserWindowWidth() - 200 - 20, Sizeable.Unit.PIXELS);

        Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(Page.BrowserWindowResizeEvent event) {
                float height = Page.getCurrent().getBrowserWindowHeight() - 50;
                for (TaskChart taskChart : taskChartsList) {
                    taskChart.setHeight(height / 4, Sizeable.Unit.PIXELS);
                }
                RtosUI.getCurrent().getFunctionChart().setHeight(height / 4, Sizeable.Unit.PIXELS);
                setWidth(Page.getCurrent().getBrowserWindowWidth() - 200 - 20, Sizeable.Unit.PIXELS);
            }
        });

        this.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent event) {
                if (event.getButton() == MouseEventDetails.MouseButton.RIGHT) {
                    RtosUI.getCurrent().getDispatcher().fireClickEvent();
                }
            }
        });

    }

    public List<TaskChart> getTaskChartsList() {
        return taskChartsList;
    }

    public void setTaskChartsList(List<TaskChart> taskChartsList) {
        this.taskChartsList = taskChartsList;
    }

    public DiscreteFunctionChart getFunctionChart () {
        return this.functionChart;
    }

    public void setFunctionChart (DiscreteFunctionChart discreteFunctionChart) {
        this.functionChart = discreteFunctionChart;
    }
}
