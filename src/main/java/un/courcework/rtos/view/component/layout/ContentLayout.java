package un.courcework.rtos.view.component.layout;

import com.vaadin.addon.charts.Chart;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import un.courcework.rtos.view.MyVaadinUI;
import un.courcework.rtos.view.component.LabOne;
import un.courcework.rtos.view.component.chart.FunctionChart;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 06.10.13
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public class ContentLayout extends HorizontalLayout {

    public ContentLayout() {
        setMargin(true);
        setWidth(1200, Sizeable.Unit.PIXELS);
        setSpacing(true);

        VerticalLayout leftContentLayout = new VerticalLayout();
        leftContentLayout.setSpacing(true);

        VerticalLayout rightContentLayout = new VerticalLayout();
        rightContentLayout.setSpacing(true);

        Chart chartOne = new FunctionChart(MyVaadinUI.getCurrent().getMathFunction());
        Chart chartTwo = new FunctionChart(MyVaadinUI.getCurrent().getMathFunction());
        leftContentLayout.addComponent(chartOne);
        leftContentLayout.addComponent(chartTwo);

        HorizontalLayout buttonsPanel = new HorizontalLayout();
        buttonsPanel.addComponent(getLabOneButton ());
        buttonsPanel.addComponent(new Button("Start"));
        buttonsPanel.addComponent(new Button("Stop"));
        buttonsPanel.addComponent(new Button("Pause"));

        rightContentLayout.addComponent(buttonsPanel);
        rightContentLayout.addComponent(getPatamsPanel());

        addComponent(leftContentLayout);
        setExpandRatio(leftContentLayout, 3);
        addComponent(rightContentLayout);
        setExpandRatio(rightContentLayout, 1);

    }

    private Button getLabOneButton () {
        Button button = new Button("Lab 1");
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

