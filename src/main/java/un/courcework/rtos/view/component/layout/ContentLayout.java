package un.courcework.rtos.view.component.layout;

import com.vaadin.addon.charts.Chart;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import un.courcework.rtos.core.Utils;
import un.courcework.rtos.model.FunctionChartType;
import un.courcework.rtos.view.MyVaadinUI;
import un.courcework.rtos.view.component.LabOne;
import un.courcework.rtos.view.component.chart.FunctionChart;
import un.courcework.rtos.view.component.chart.TaskChart;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 06.10.13
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public class ContentLayout extends HorizontalLayout {

    private TaskChart taskChart1;
    private TaskChart taskChart2;
    private TaskChart taskChart3;
    private FunctionChart functionChart;
    private int count = 0;
    final Label timeLebel;

    VerticalLayout leftContentLayout;
    VerticalLayout rightContentLayout;

    public ContentLayout() {
        setMargin(true);
        setWidth(Page.getCurrent().getBrowserWindowWidth(), Sizeable.Unit.PIXELS);
        setSpacing(true);

        leftContentLayout = new VerticalLayout();
        rightContentLayout = new VerticalLayout();
        rightContentLayout.setSpacing(true);

        float browserWindowHeight = Page.getCurrent().getBrowserWindowHeight() - 50;

        this.functionChart = new FunctionChart(MyVaadinUI.getCurrent().getMathFunction(), 73.0, Math.PI / 12,
                FunctionChartType.DISCRETE);
        this.functionChart.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);
        this.functionChart.setPerLine(Utils.round(72.0 / 6, 2));

        this.taskChart1 = new TaskChart("Task 1");
        this.taskChart1.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);
        this.taskChart2 = new TaskChart("Task 2");
        this.taskChart2.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);
        this.taskChart3 = new TaskChart("Task 3");
        this. taskChart3.setHeight(browserWindowHeight / 4, Sizeable.Unit.PIXELS);

        leftContentLayout.addComponent(this.taskChart1);
        leftContentLayout.addComponent(this.taskChart2);
        leftContentLayout.addComponent(this.taskChart3);
        leftContentLayout.addComponent(this.functionChart);


        HorizontalLayout buttonsPanel = new HorizontalLayout();
        timeLebel = new Label("<strong>" + (new Date().toLocaleString()) + "<strong>");
        timeLebel.setImmediate(true);
        timeLebel.setContentMode(ContentMode.HTML);
        buttonsPanel.addComponent(timeLebel);
        buttonsPanel.addComponent(getLabOneButton());
        buttonsPanel.addComponent(getStartButton());
        buttonsPanel.addComponent(getPauseButton());
        buttonsPanel.addComponent(getStopButton());

        rightContentLayout.addComponent(buttonsPanel);
        rightContentLayout.addComponent(getPatamsPanel());

        addComponent(leftContentLayout);
        setExpandRatio(leftContentLayout, 3.5f);
        addComponent(rightContentLayout);
        setExpandRatio(rightContentLayout, 1);

    }

    private Button getLabOneButton () {
        NativeButton button = new NativeButton("L1");
        button.setIcon(new ThemeResource("images/16x16/lab1.png"), "lab1");
        button.setDescription("Show report on the first lab");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Window window = new Window();
                window.setCaption("Lab 1");
                window.setWidth(1000, Sizeable.Unit.PIXELS);
                window.center();
                window.setContent(new LabOne(MyVaadinUI.getCurrent().getMathFunction()));
                UI.getCurrent().addWindow(window);
            }
        });
        return button;
    }

    private Button getStartButton () {
        NativeButton button = new NativeButton();
        button.setIcon(new ThemeResource("images/16x16/start.png"), "Start");
        button.setDescription("Run the simulation");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Timer timer = new Timer();
                MyVaadinUI.getCurrent().setSecondTimer(timer);
                TimerTask task = new TimerTask() {
                    public void run() {
                        timeLebel.setValue("<strong>" + (new Date().toLocaleString()) + "<strong>");
                        MyVaadinUI.getCurrent().push();
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
        button.setIcon(new ThemeResource("images/16x16/pause.png"), "Pause");
        button.setDescription("Pause the simulation");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Timer timer = MyVaadinUI.getCurrent().getSecondTimer();
                timer.cancel();

            }
        });
        return button;
    }

    private Button getStopButton () {
        NativeButton button = new NativeButton();
        button.setIcon(new ThemeResource("images/16x16/stop.png"), "Stop");
        button.setDescription("Stop the simulation");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Timer timer = MyVaadinUI.getCurrent().getSecondTimer();
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
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSpacing(true);
        mainLayout.addComponent(new Label("Параметры задач"));

        GridLayout grid = new GridLayout(5, 13);
        grid.setSpacing(true);
        //grid.setWidth(280, Sizeable.Unit.PIXELS);

        grid.addComponent(new Label("№"), 0, 0);
        grid.addComponent(new Label("1"), 1, 0);
        grid.addComponent(new Label("2"), 2, 0);
        grid.addComponent(new Label("3"), 3, 0);
        grid.addComponent(new Label("4"), 4, 0);

        grid.addComponent(new Label("Тип"), 0, 1);
        grid.addComponent(new Label("Вр"), 1, 1);
        grid.addComponent(new Label("Вр"), 2, 1);
        grid.addComponent(new Label("Пр"), 3, 1);
        grid.addComponent(new Label("Пр"), 4, 1);

        grid.addComponent(new Label("Приоритет"), 0, 2);
        grid.addComponent(new Label("1"), 1, 2);
        grid.addComponent(new Label("1"), 2, 2);
        grid.addComponent(new Label("0"), 3, 2);
        grid.addComponent(new Label("2"), 4, 2);

        grid.addComponent(new Label("Ткрит"), 0, 3);
        grid.addComponent(getTextField ("5"), 1, 3);
        grid.addComponent(getTextField ("6"), 2, 3);
        grid.addComponent(getTextField ("5"), 3, 3);
        grid.addComponent(getTextField ("4"), 4, 3);

        grid.addComponent(new Label("Тc"), 0, 4);
        grid.addComponent(getTextField ("5"), 1, 4);
        grid.addComponent(getTextField ("4"), 2, 4);
        grid.addComponent(getTextField ("3"), 3, 4);
        grid.addComponent(getTextField ("3"), 4, 4);

        grid.addComponent(new Label("Тз.доп."), 0, 5);
        grid.addComponent(getTextField ("1"), 1, 5);
        grid.addComponent(getTextField("2"), 2, 5);
        grid.addComponent(getNTextField(""), 3, 5);
        grid.addComponent(getNTextField(""), 4, 5);

        grid.addComponent(new Label("Тз"), 0, 6);
        grid.addComponent(new Label("0"), 1, 6);
        grid.addComponent(new Label("0"), 2, 6);
        grid.addComponent(new Label(""), 3, 6);
        grid.addComponent(new Label(""), 4, 6);

        grid.addComponent(new Label("Тнач"), 0, 7);
        grid.addComponent(getTextField ("0"), 1, 7);
        grid.addComponent(getTextField (""), 2, 7);
        grid.addComponent(getNTextField(""), 3, 7);
        grid.addComponent(getNTextField(""), 4, 7);

        grid.addComponent(new Label("Тп"), 0, 8);
        grid.addComponent(getTextField ("10"), 1, 8);
        grid.addComponent(getTextField ("10"), 2, 8);
        grid.addComponent(getNTextField(""), 3, 8);
        grid.addComponent(getNTextField(""), 4, 8);

        grid.addComponent(new Label("Ткон"), 0, 9);
        grid.addComponent(getTextField (""), 1, 9);
        grid.addComponent(getTextField ("70"), 2, 9);
        grid.addComponent(getNTextField(""), 3, 9);
        grid.addComponent(getNTextField(""), 4, 9);

        grid.addComponent(new Label("Тсоб"), 0, 10);
        grid.addComponent(getTextField ("3"), 1, 10);
        grid.addComponent(getTextField (""), 2, 10);
        grid.addComponent(getNTextField(""), 3, 10);
        grid.addComponent(getNTextField(""), 4, 10);

        grid.addComponent(new Label("Сост"), 0, 11);
        grid.addComponent(new Label("Активная"), 1, 11);
        grid.addComponent(new Label("Активная"), 2, 11);
        grid.addComponent(new Label("Не активная"), 3, 11);
        grid.addComponent(new Label("Активная"), 4, 11);

        grid.addComponent(new Label("Режим"), 0, 12);
        grid.addComponent(new Label("Ожидание"), 1, 12);
        grid.addComponent(new Label("Ожидание"), 2, 12);
        grid.addComponent(new Label("Ожидание"), 3, 12);
        grid.addComponent(new Label("Выполнение"), 4, 12);


        mainLayout.addComponent(grid);
        return mainLayout;
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

