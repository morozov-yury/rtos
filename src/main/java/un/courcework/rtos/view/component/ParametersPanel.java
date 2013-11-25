package un.courcework.rtos.view.component;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.textfieds.impl.*;

import java.util.ArrayList;
import java.util.List;

public class ParametersPanel extends VerticalLayout {

    private Table paramTeble;

    public ParametersPanel (List<Task> tasks) {

        paramTeble = new Table();
        paramTeble.addStyleName("components-inside");
        paramTeble.addStyleName("rtos-table");
        paramTeble.setSelectable(false);
        paramTeble.setImmediate(true);
        paramTeble.setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
        //paramTeble.setRowHeaderMode(Table.RowHeaderMode.ICON_ONLY);
        paramTeble.setFooterVisible(false);
        paramTeble.setPageLength(13);
        paramTeble.setWidth(100, Unit.PERCENTAGE);

        paramTeble.addContainerProperty("", Component.class, null);
        paramTeble.setColumnWidth("", 40);

        List<Object> tasksNames = new ArrayList<Object>();
        tasksNames.add(new Label(""));
        for (Task task : tasks) {
            paramTeble.addContainerProperty(task.getName(), Component.class, null);
            Label taskNameLabel = new Label(
                    "<img src='http://localhost:8080/VAADIN/themes/rtos/images/16x16/37.png'></img>"
                            + task.getName(), ContentMode.HTML);
            tasksNames.add(taskNameLabel);
            paramTeble.setColumnAlignment(task.getName(), Table.Align.CENTER);
        }

        Label label;
        List<Object> tStartIntActiveList = new ArrayList<Object>();
        label =  new Label("Tн");
        label.setDescription("Т начала - время, когда задача после старта По может получить управление первый раз" +
                "(начала интервала активности)");
        tStartIntActiveList.add(label);
        List<Object> tEndIntActiveList = new ArrayList<Object>();
        label  = new Label("Tк") ;
        label.setDescription("Т конца - время, когда задача после старта ПО перестает быть активной " +
                "(конец интервала активности задачи). ");
        tEndIntActiveList.add(label);
        List<Object> tPlanCallList = new ArrayList<Object>();
        label =  new Label("Tпл");
        label.setDescription("Т плановое");
        tPlanCallList.add(label);
        List<Object> tPeriodCallList = new ArrayList<Object>();
        label  =  new Label("Tп");
        label.setDescription("Т периода - переодизация вызова задачи. Позволяет планировщику расчитывать " +
                "очередной плановый вызов задачи ");
        tPeriodCallList.add(label);
        List<Object> tVaitMaxList = new ArrayList<Object>();
        label =  new Label("Tо.m.");
        label.setDescription("Т ожидания максимальное. При превышении в интерфейс пользователя выдается запрос " +
                "\"Пропуск вызова на данном таете\" или \"Снять задачу\".");
        tVaitMaxList.add(label);
        List<Object> tExecMaxList = new ArrayList<Object>();
        label  =  new Label("Tв.m.");
        label.setDescription("Т выполнения максимальное, при превышении происходит пропуск следующего вызова.");
        tExecMaxList.add(label);
        List<Object> priorityList = new ArrayList<Object>();
        label =  new Label("П");
        label.setDescription("Приоритет задачи");
        priorityList.add(label);
        List<Object> tSessionList = new ArrayList<Object>();
        label =  new Label("Tс");
        label.setDescription("Т сеанса - время фактического выполнения задачи");
        tSessionList.add(label);
        List<Object> nSessionList = new ArrayList<Object>();
        label =  new Label("Nс");
        label.setDescription("Номер сессии");
        nSessionList.add(label);
        List<Object> statusList = new ArrayList<Object>();
        label  =  new Label("Status");
        label.setDescription("Статус задачи");
        statusList.add(label);
        List<Object> stateList = new ArrayList<Object>();
        label  =  new Label("State");
        label.setDescription("Состояние задачи");
        stateList.add(label);

        for (Task task : tasks) {
            tStartIntActiveList.add(new StartIntValidator(task));
            tEndIntActiveList.add(new EndIntTextField(task));
            tPlanCallList.add(new PlanTextField(task));
            tPeriodCallList.add(new PeriodTextField(task));
            tVaitMaxList.add(new VaitTextField(task));
            tExecMaxList.add(new ExecMaxTextField(task));
            priorityList.add(new PriorityTextField(task));
            tSessionList.add(new TSessionTextField(task));
            nSessionList.add(new NSessionTextField(task));
            statusList.add(new TaskStatusWiev(task));
            stateList.add(new TaskStateWiev(task));
        }

        List<List<Object>> rowsArray = new ArrayList<List<Object>>();
        rowsArray.add(tasksNames);
        rowsArray.add(tStartIntActiveList);
        rowsArray.add(tEndIntActiveList);
        rowsArray.add(tPeriodCallList);
        rowsArray.add(tVaitMaxList);
        rowsArray.add(tExecMaxList);
        rowsArray.add(priorityList);
        rowsArray.add(tSessionList);
        rowsArray.add(nSessionList);
        rowsArray.add(tPlanCallList);
        rowsArray.add(statusList);
        rowsArray.add(stateList);

        for (List<Object> row : rowsArray) {
            paramTeble.addItem(row.toArray(), row.hashCode());
        }

        addComponent(paramTeble);
    }

    private TextField getParamTextField () {
        TextField textField = new TextField();
        textField.setMaxLength(3);
        textField.setWidth(100, Unit.PERCENTAGE);
        return textField;
    }

}
