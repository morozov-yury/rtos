package un.courcework.rtos.view.component;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.textfieds.TextFieldRefresher;
import un.courcework.rtos.view.component.textfieds.impl.*;

import java.util.ArrayList;
import java.util.List;

public class ParametersPanel extends VerticalLayout {

    private Table paramTeble;
    private List<TextFieldRefresher> TextFieldRefresherList;

    public ParametersPanel (List<Task> tasks) {

        this.TextFieldRefresherList = new ArrayList<TextFieldRefresher>();

        this.paramTeble = new Table();
        this.paramTeble.addStyleName("components-inside");
        this.paramTeble.addStyleName("rtos-table");
        this.paramTeble.setSelectable(false);
        this.paramTeble.setImmediate(true);
        this.paramTeble.setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
        this.paramTeble.setFooterVisible(false);
        this.paramTeble.setPageLength(13);
        this.paramTeble.setWidth(100, Unit.PERCENTAGE);

        this.paramTeble.addContainerProperty("", Component.class, null);
        this.paramTeble.setColumnWidth("", 40);

        List<Object> tasksNames = new ArrayList<Object>();
        tasksNames.add(new Label(""));
        for (Task task : tasks) {
            this.paramTeble.addContainerProperty(task.getId(), Component.class, null);
            Label taskNameLabel = new Label(
                    "<img src='http://localhost:8080/VAADIN/themes/rtos/images/16x16/37.png'></img>"
                            + task.getId(), ContentMode.HTML);
            tasksNames.add(taskNameLabel);
            this.paramTeble.setColumnAlignment(task.getId(), Table.Align.CENTER);
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
            TextFieldRefresher textFieldRefresher;

            textFieldRefresher = new StartIntValidator(task);
            tStartIntActiveList.add(textFieldRefresher);
            this.TextFieldRefresherList.add(textFieldRefresher);

            textFieldRefresher = new EndIntTextField(task);
            tEndIntActiveList.add(textFieldRefresher);
            this.TextFieldRefresherList.add(textFieldRefresher);

            tPlanCallList.add(new PlanTextField(task));

            textFieldRefresher = new PeriodTextField(task);
            tPeriodCallList.add(textFieldRefresher);
            this.TextFieldRefresherList.add(textFieldRefresher);

            textFieldRefresher = new VaitTextField(task);
            tVaitMaxList.add(textFieldRefresher);
            this.TextFieldRefresherList.add(textFieldRefresher);

            textFieldRefresher = new ExecMaxTextField(task);
            tExecMaxList.add(textFieldRefresher);
            this.TextFieldRefresherList.add(textFieldRefresher);

            textFieldRefresher = new PriorityTextField(task);
            priorityList.add(textFieldRefresher);
            this.TextFieldRefresherList.add(textFieldRefresher);

            textFieldRefresher = new TSessionTextField(task);
            tSessionList.add(textFieldRefresher);
            this.TextFieldRefresherList.add(textFieldRefresher);

            textFieldRefresher = new NSessionTextField(task);
            nSessionList.add(textFieldRefresher);
            this.TextFieldRefresherList.add(textFieldRefresher);

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
