package un.courcework.rtos.view.component.layout;

import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import un.courcework.rtos.utils.MathUtils;
import un.courcework.rtos.model.FunctionChartType;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.ParametersPanel;
import un.courcework.rtos.view.component.chart.FunctionChart;
import un.courcework.rtos.view.component.chart.TaskChart;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ContentLayout extends GridLayout {

    private TaskChart taskChart1;
    private TaskChart taskChart2;
    private TaskChart taskChart3;
    private FunctionChart functionChart;
    private int count = 0;
    final Label timeLebel;

    VerticalLayout leftContentLayout;
    VerticalLayout rightContentLayout;

    public ContentLayout() {
        super(2, 1);
        addStyleName("content-layout");
        setMargin(true);
        setWidth(Page.getCurrent().getBrowserWindowWidth(), Sizeable.Unit.PIXELS);
        setSpacing(true);

        leftContentLayout = new VerticalLayout();
        rightContentLayout = new VerticalLayout();
        rightContentLayout.setSpacing(true);

        float browserWindowHeight = Page.getCurrent().getBrowserWindowHeight() - 50;

        this.functionChart = new FunctionChart(RtosUI.getCurrent().getMathFunction(), 73.0, Math.PI / 12,
                FunctionChartType.DISCRETE);
        this.functionChart.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);
        this.functionChart.setPerLine(MathUtils.round(72.0 / 6, 2));

        this.taskChart1 = new TaskChart("Задача 1");
        this.taskChart1.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);
        this.taskChart2 = new TaskChart("Задача 2");
        this.taskChart2.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);
        this.taskChart3 = new TaskChart("Задача 3");
        this.taskChart3.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);



        leftContentLayout.addComponent(this.taskChart1);
        leftContentLayout.addComponent(this.taskChart2);
        leftContentLayout.addComponent(this.taskChart3);
        leftContentLayout.addComponent(this.functionChart);
        leftContentLayout.setWidth(Page.getCurrent().getBrowserWindowWidth() - 200 - 20, Sizeable.Unit.PIXELS);


        timeLebel = new Label("<strong>" + (new Date().toLocaleString()) + "<strong>");
        timeLebel.setImmediate(true);
        timeLebel.setContentMode(ContentMode.HTML);

        rightContentLayout.addComponent(getButtonsPanel());
        rightContentLayout.addComponent(getPatamsPanel());

        TabSheet tsh  =  new TabSheet();
        tsh.addStyleName("tros-tabsheet");
        tsh.setWidth(100, Unit.PERCENTAGE);
        tsh.setHeight(328, Unit.PIXELS);
        tsh.addTab(new LegendPanel(), "Ообозначения");
        tsh.addTab(new LogLayout(), "Управление");
//        rightContentLayout.addComponent(new LegendPanel());
//        rightContentLayout.addComponent(new LogLayout());
        rightContentLayout.addComponent(tsh);
        rightContentLayout.setWidth(185, Unit.PIXELS);

        addComponent(leftContentLayout, 0, 0);
        //setExpandRatio(leftContentLayout, 99f);
        addComponent(rightContentLayout, 1, 0);
        //setExpandRatio(rightContentLayout, 1);

        Page.getCurrent().addBrowserWindowResizeListener(new Page.BrowserWindowResizeListener() {
            @Override
            public void browserWindowResized(Page.BrowserWindowResizeEvent event) {
                float height = Page.getCurrent().getBrowserWindowHeight() - 50;
                taskChart1.setHeight(height / 4, Sizeable.Unit.PIXELS);
                taskChart2.setHeight(height / 4, Sizeable.Unit.PIXELS);
                taskChart3.setHeight(height / 4, Sizeable.Unit.PIXELS);
                functionChart.setHeight(height / 4, Sizeable.Unit.PIXELS);
                setWidth(Page.getCurrent().getBrowserWindowWidth(), Sizeable.Unit.PIXELS);
                leftContentLayout.setWidth(Page.getCurrent().getBrowserWindowWidth() - 200 - 20, Sizeable.Unit.PIXELS);
            }
        });

    }

    private Component getButtonsPanel () {
        HorizontalLayout buttonsPanel = new HorizontalLayout();
        buttonsPanel.setWidth(100, Unit.PERCENTAGE);
        //buttonsPanel.addComponent(timeLebel);
        buttonsPanel.addComponent(getStartButton());
        buttonsPanel.addComponent(getPauseButton());
        buttonsPanel.addComponent(getStopButton());
        buttonsPanel.addComponent(getLabOneButton());
        buttonsPanel.addComponent(getSpecificationButton());
        return buttonsPanel;
    }

    private Button getLabOneButton () {
        NativeButton button = new NativeButton();
        button.setSizeFull();
        button.setIcon(new ThemeResource("images/16x16/lab1.png"), "lab1");
        button.setDescription("Show report on the first lab");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Window window = new Window();
                window.setCaption("Lab 1");
                window.setWidth(1000, Sizeable.Unit.PIXELS);
                window.center();
                window.setContent(new LabOneLayout(RtosUI.getCurrent().getMathFunction()));
                UI.getCurrent().addWindow(window);
            }
        });
        return button;
    }

    private Button getSpecificationButton () {
        NativeButton button = new NativeButton();
        button.setSizeFull();
        button.setIcon(new ThemeResource("images/16x16/49.png"), "lab1");
        button.setDescription("Show specification");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Window window = new Window();
                window.setCaption("Specification");
                window.setWidth(1000, Sizeable.Unit.PIXELS);
                window.center();
                //window.setContent();
                UI.getCurrent().addWindow(window);
            }
        });
        return button;
    }

    private Button getStartButton () {
        NativeButton button = new NativeButton();
        button.setSizeFull();
        button.setIcon(new ThemeResource("images/16x16/start.png"), "Start");
        button.setDescription("Run the simulation");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Timer timer = new Timer();
                RtosUI.getCurrent().setSecondTimer(timer);
                TimerTask task = new TimerTask() {
                    public void run() {
                        timeLebel.setValue("<strong>" + (new Date().toLocaleString()) + "<strong>");
                        RtosUI.getCurrent().push();
                        taskChart1.addPoint(1);
                        taskChart3.addPoint(1);
                    }
                };
                taskChart1.removePoints();
                taskChart3.removePoints();
                timer.schedule(task, 0, 1000);
            }
        });
        return button;
    }

    private Button getPauseButton () {
        NativeButton button = new NativeButton();
        button.setSizeFull();
        button.setIcon(new ThemeResource("images/16x16/pause.png"), "Pause");
        button.setDescription("Pause the simulation");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Timer timer = RtosUI.getCurrent().getSecondTimer();
                timer.cancel();

            }
        });
        return button;
    }

    private Button getStopButton () {
        NativeButton button = new NativeButton();
        button.setSizeFull();
        button.setIcon(new ThemeResource("images/16x16/stop.png"), "Stop");
        button.setDescription("Stop the simulation");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Timer timer = RtosUI.getCurrent().getSecondTimer();
                timer.cancel();

                float browserWindowHeight = Page.getCurrent().getBrowserWindowHeight() - 50;

                TaskChart taskChart1new = new TaskChart("Task 1");
                taskChart1new.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);
                TaskChart taskChart2new = new TaskChart("Task 2");
                taskChart2new.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);
                TaskChart taskChart3new = new TaskChart("Task 3");
                taskChart3new.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);

                leftContentLayout.replaceComponent(taskChart1, taskChart1new);
                leftContentLayout.replaceComponent(taskChart2, taskChart2new);
                leftContentLayout.replaceComponent(taskChart3, taskChart3new);

                taskChart1 =  taskChart1new;
                taskChart2 =  taskChart2new;
                taskChart3 =  taskChart3new;
            }
        });
        return button;
    }

    private Component getPatamsPanel () {
        ParametersPanel paramPanel = new ParametersPanel(RtosUI.getCurrent().getTasks());
        return paramPanel;
    }

    private TextField getTextField (String value) {
        TextField f = new TextField();
        f.setValue(value.toString());
        f.setMaxLength(3);
        f.setWidth(40, Sizeable.Unit.PIXELS);
        return  f;
    }

    private TextField getNTextField (String value) {
        TextField f = new TextField();
        f.setValue(value.toString());
        f.setMaxLength(3);
        f.setEnabled(false);
        f.setWidth(40, Sizeable.Unit.PIXELS);
        return  f;
    }


}

