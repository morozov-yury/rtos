package un.courcework.rtos.view.component.layout;

import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.utils.MathUtils;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.ButtonsPanel;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.TimePanel;
import un.courcework.rtos.view.component.chart.DiscreteFunctionChart;
import un.courcework.rtos.view.component.chart.TaskChart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContentLayout extends GridLayout {

    private List<TaskChart> taskChartsList;

    private DiscreteFunctionChart functionChart;
    final Label timeLebel;

    VerticalLayout leftContentLayout;
    VerticalLayout rightContentLayout;

    public ContentLayout() {
        super(2, 1);
        this.taskChartsList = new ArrayList<TaskChart>();
        addStyleName("content-layout");
        setMargin(true);
        setWidth(Page.getCurrent().getBrowserWindowWidth(), Sizeable.Unit.PIXELS);
        setSpacing(true);

        this.leftContentLayout = new VerticalLayout();
        this.rightContentLayout = new VerticalLayout();
        this.rightContentLayout.setSpacing(true);

        float browserWindowHeight = Page.getCurrent().getBrowserWindowHeight() - 50;

        this.functionChart = new DiscreteFunctionChart(RtosUI.getCurrent().getMathFunction(), 73.0, Math.PI / 12);
        this.functionChart.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);
        this.functionChart.setPerLine(MathUtils.round(72.0 / 6, 2));

        Integer count = 1;
        for (Task task : RtosUI.getCurrent().listTasks()) {
            TaskChart taskChart = RtosUI.getCurrent().getTaskChartMap().get(task);
            this.taskChartsList.add(taskChart);
            this.leftContentLayout.addComponent(taskChart);
        }

        this.leftContentLayout.addComponent(this.functionChart);
        this.leftContentLayout.setWidth(Page.getCurrent().getBrowserWindowWidth() - 200 - 20, Sizeable.Unit.PIXELS);

        this.timeLebel = new Label("<strong>" + (new Date().toLocaleString()) + "<strong>");
        this.timeLebel.setImmediate(true);
        this.timeLebel.setContentMode(ContentMode.HTML);

        this.rightContentLayout.addComponent(new TimePanel());
        this.rightContentLayout.addComponent(new ButtonsPanel(this));
        this.rightContentLayout.addComponent(new ParametersPanel(RtosUI.getCurrent().listTasks()));

        PopupView popupViewLegendPanel = new PopupView("Обозначения", new HiddenLegendPanel());

        this.rightContentLayout.addComponent(popupViewLegendPanel);
        this.rightContentLayout.addComponent(new VisibleLegendPanel());
        this.rightContentLayout.addComponent(new ActionsLayout());
        this.rightContentLayout.setWidth(185, Unit.PIXELS);

        addComponent(this.leftContentLayout, 0, 0);
        addComponent(this.rightContentLayout, 1, 0);

        Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(Page.BrowserWindowResizeEvent event) {
                float height = Page.getCurrent().getBrowserWindowHeight() - 50;
                for (TaskChart taskChart : taskChartsList) {
                    taskChart.setHeight(height / 4, Sizeable.Unit.PIXELS);
                }
                functionChart.setHeight(height / 4, Sizeable.Unit.PIXELS);
                setWidth(Page.getCurrent().getBrowserWindowWidth(), Sizeable.Unit.PIXELS);
                leftContentLayout.setWidth(Page.getCurrent().getBrowserWindowWidth() - 200 - 20, Sizeable.Unit.PIXELS);
            }
        });

    }

    private Button getStopButton () {
        NativeButton button = new NativeButton();
        button.setSizeFull();
        button.setIcon(new ThemeResource("images/16x16/stop.png"), "Stop");
        button.setDescription("Stop the simulation");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                RtosUI.getCurrent().getSecondRtosTimer().stopTimer();
                RtosUI.getCurrent().renewTaskCharts();
                List<TaskChart> newTaskChartList = new ArrayList<TaskChart>();
                for (int i = 0; i < RtosUI.getCurrent().listTasks().size(); i++) {
                    Task task = RtosUI.getCurrent().listTasks().get(i);
                    TaskChart newTaskChart = RtosUI.getCurrent().getTaskChartMap().get(task);
                    TaskChart oldtaskChart = taskChartsList.get(i);
                    leftContentLayout.replaceComponent(oldtaskChart, newTaskChart);
                    newTaskChartList.add(newTaskChart);
                }
                taskChartsList = newTaskChartList;
            }
        });
        return button;
    }

    public List<TaskChart> getTaskChartsList () {
        return this.taskChartsList;
    }

    public void  setTaskChartsList (List<TaskChart> taskChartsList) {
        this.taskChartsList = taskChartsList;
    }

    public VerticalLayout getLeftContentLayout () {
        return  this.leftContentLayout;
    }

}

