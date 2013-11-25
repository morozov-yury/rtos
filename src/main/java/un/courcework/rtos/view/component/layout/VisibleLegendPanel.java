package un.courcework.rtos.view.component.layout;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class VisibleLegendPanel extends VerticalLayout {

    public VisibleLegendPanel() {
        setWidth(200, Unit.PIXELS);

        addStyleName("legend-panel");

        Table legendTeble = new Table();
        legendTeble.setSelectable(false);
        legendTeble.setImmediate(true);
        legendTeble.setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
        //legendTeble.setRowHeaderMode(Table.RowHeaderMode.ICON_ONLY);
        legendTeble.setFooterVisible(false);
        legendTeble.setPageLength(6);
        legendTeble.setWidth(100, Unit.PERCENTAGE);
        legendTeble.addContainerProperty("image", Image.class, null);
        legendTeble.setColumnWidth("image", 18);
        legendTeble.addContainerProperty("Name", Label.class, null);
        //legendTeble.setColumnWidth("", 20);

        int i = 0;
        Image image;
        Label label;

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
