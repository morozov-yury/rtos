package un.courcework.rtos.view.component.layout;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;

public class LegendPanel extends VerticalLayout {

    public LegendPanel() {
        setWidth(200, Unit.PIXELS);

        addStyleName("legend-panel");

        Table legendTeble = new Table();
        legendTeble.addStyleName("components-inside");
        legendTeble.addStyleName("legend-table");
        legendTeble.setSelectable(false);
        legendTeble.setImmediate(true);
        legendTeble.setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
        //legendTeble.setRowHeaderMode(Table.RowHeaderMode.ICON_ONLY);
        legendTeble.setFooterVisible(false);
        legendTeble.setPageLength(12);
        legendTeble.setWidth(100, Unit.PERCENTAGE);
        legendTeble.addContainerProperty("image", Image.class, null);
        legendTeble.setColumnWidth("image", 18);
        legendTeble.addContainerProperty("Name", Label.class, null);
        //legendTeble.setColumnWidth("", 20);

        int i = 0;
        Image image = new Image("Старт моделирования", new ThemeResource("images/16x16/start.png"));
        Label label = new Label("Старт моделирования");
        legendTeble.addItem(new Object[] {image, label}, i++);

        image = new Image("Пауза моделирования", new ThemeResource("images/16x16/pause.png"));
        label = new Label("Пауза моделирования");
        legendTeble.addItem(new Object[] {image, label}, i++);

        image = new Image("Стоп моделирования", new ThemeResource("images/16x16/stop.png"));
        label = new Label("Стоп моделирования");
        legendTeble.addItem(new Object[] {image, label}, i++);

        image = new Image("Расчет л.р. №1", new ThemeResource("images/16x16/lab1.png"));
        label = new Label("Расчет л.р. №1");
        legendTeble.addItem(new Object[] {image, label}, i++);

        image = new Image("Техническое задание", new ThemeResource("images/16x16/49.png"));
        label = new Label("Техническое задание");
        legendTeble.addItem(new Object[] {image, label}, i++);

        image = new Image("Задача по времени", new ThemeResource("images/16x16/37.png"));
        label = new Label("Задача по времени");
        legendTeble.addItem(new Object[] {image, label}, i++);

        image = new Image("Задача ожидает", new ThemeResource("images/16x16/timeRed.png"));
        label = new Label("Задача ожидает");
        legendTeble.addItem(new Object[] {image, label}, i++);

        image = new Image("Задача активна", new ThemeResource("images/16x16/43_.png"));
        label = new Label("Задача активна");
        legendTeble.addItem(new Object[] {image, label}, i++);

        image = new Image("Задача не активна", new ThemeResource("images/16x16/44.png"));
        label = new Label("Задача не активна");
        legendTeble.addItem(new Object[] {image, label}, i++);

        image = new Image("Превышение To.m.", new ThemeResource("images/16x16/01.png"));
        label = new Label("Превышение To.m.");
        legendTeble.addItem(new Object[] {image, label}, i++);

        image = new Image("Превышение Tв.m.", new ThemeResource("images/16x16/02.png"));
        label = new Label("Превышение Tв.m.");
        legendTeble.addItem(new Object[] {image, label}, i++);

        image = new Image("Плановый вызов", new ThemeResource("images/16x16/03.png"));
        label = new Label("Плановый вызов");
        legendTeble.addItem(new Object[] {image, label}, i++);

        addComponent(legendTeble);

        addComponent(legendTeble);
    }

}
