package un.courcework.rtos.view.component;

import com.vaadin.server.UserError;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.textfieds.TextFieldRefresher;
import un.courcework.rtos.view.component.textfieds.impl.*;

import java.util.ArrayList;
import java.util.List;

public class ParametersPanel extends VerticalLayout {

    public enum ParametersPanelStatus {
        AVAILABLE_TO_EDIT,
        NOT_AVAILABLE_TO_EDIT,
        ERROR;
    }

    private Table paramTeble;
    private List<TextFieldRefresher> textFieldRefresherList;
    private List<PriorityTextField> priorityTextFields;

    public ParametersPanel (List<Task> tasks) {

        this.textFieldRefresherList = new ArrayList<TextFieldRefresher>();
        this.priorityTextFields = new ArrayList<PriorityTextField>();

        this.paramTeble = new Table();
        this.paramTeble.addStyleName("components-inside");
        this.paramTeble.addStyleName("rtos-table");
        this.paramTeble.addStyleName("parameters-panel");
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
        label.setDescription("Т начала - время, когда задача после старта " +
                "По может получить управление первый раз" +
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

            textFieldRefresher = new StartIntTextField(this, task);
            tStartIntActiveList.add(textFieldRefresher);
            this.textFieldRefresherList.add(textFieldRefresher);

            textFieldRefresher = new EndIntTextField(this, task);
            tEndIntActiveList.add(textFieldRefresher);
            this.textFieldRefresherList.add(textFieldRefresher);

            tPlanCallList.add(new PlanTextField(this, task));

            textFieldRefresher = new PeriodTextField(this, task);
            tPeriodCallList.add(textFieldRefresher);
            this.textFieldRefresherList.add(textFieldRefresher);

            textFieldRefresher = new VaitTextField(this, task);
            tVaitMaxList.add(textFieldRefresher);
            this.textFieldRefresherList.add(textFieldRefresher);

            textFieldRefresher = new ExecMaxTextField(this, task);
            tExecMaxList.add(textFieldRefresher);
            this.textFieldRefresherList.add(textFieldRefresher);

            PriorityTextField priorityTextField = new PriorityTextField(this, task);
            priorityList.add(priorityTextField);
            priorityTextFields.add(priorityTextField);
            this.textFieldRefresherList.add(priorityTextField);

            textFieldRefresher = new TSessionTextField(this, task);
            tSessionList.add(textFieldRefresher);
            this.textFieldRefresherList.add(textFieldRefresher);

            textFieldRefresher = new NSessionTextField(this, task);
            nSessionList.add(textFieldRefresher);
            this.textFieldRefresherList.add(textFieldRefresher);

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

    public void refreshAllFiels () {
        for (TextFieldRefresher textField : this.textFieldRefresherList) {
            textField.refreshField();
        }
        System.out.println(priorityTextFields.get(0).toFoloat());
        System.out.println(priorityTextFields.get(1).toFoloat());
        System.out.println(priorityTextFields.get(2).toFoloat());
        if (priorityTextFields.get(0).toFoloat() == priorityTextFields.get(1).toFoloat()
                || priorityTextFields.get(0).toFoloat() == priorityTextFields.get(2).toFoloat()
                || priorityTextFields.get(2).toFoloat() == priorityTextFields.get(1).toFoloat()) {
            System.out.println("Bom bom");
            for (PriorityTextField field : priorityTextFields) {
                field.setComponentError(new UserError("Одинаковые приоритеты недопустимы"));
            }
        } else {
            for (PriorityTextField field : priorityTextFields) {
                field.setComponentError(null);
            }
        }
    }

    public void setStatus (ParametersPanelStatus parametersPanelStatus) {
        switch (parametersPanelStatus) {
            case AVAILABLE_TO_EDIT:
                this.setEnabled(true);
                for (TextFieldRefresher textFieldRefresher : textFieldRefresherList) {
                    textFieldRefresher.getTextField().removeStyleName("not-disabled-field-editable");
                }
                break;
            case NOT_AVAILABLE_TO_EDIT:
                this.setEnabled(false);
                for (TextFieldRefresher textFieldRefresher : textFieldRefresherList) {
                    textFieldRefresher.getTextField().addStyleName("not-disabled-field-editable");
                }
                break;
            case ERROR:
                break;
        }
    }

}
