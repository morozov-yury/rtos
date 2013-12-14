package un.courcework.rtos.view.component;

import com.vaadin.server.UserError;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.core.timer.TimerAware;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.RtosUI;
import un.courcework.rtos.view.component.textfieds.TextFieldRefresher;
import un.courcework.rtos.view.component.textfieds.impl.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParametersPanel extends VerticalLayout implements TimerAware {

    public enum ParametersPanelStatus {
        AVAILABLE_TO_EDIT,
        NOT_AVAILABLE_TO_EDIT,
        ERROR;
    }

    private static Logger log = LoggerFactory.getLogger(ParametersPanel.class);

    private Map<Integer, Task> taskMap;

    private Table paramTebleCurrent;

    private List<TextFieldRefresher> textFieldRefresherList;

    private List<PriorityTextField> priorityTextFields;

    private List<TaskStatusWiev> taskStatusWievList;

    private List<TaskStateWiev> taskStateWievList;

    public ParametersPanel (Map<Integer, Task> taskMap) {
        this.taskMap = taskMap;

        RtosUI.getCurrent().getDispatcher().getTenthOfaSecondTimer().addTickListener(this);

        paramTebleCurrent = makeParamTable();
        addComponent(paramTebleCurrent);
    }

    private Table makeParamTable() {
        this.textFieldRefresherList = new ArrayList<TextFieldRefresher>();
        this.priorityTextFields = new ArrayList<PriorityTextField>();
        this.taskStatusWievList = new ArrayList<TaskStatusWiev>();
        this.taskStateWievList = new ArrayList<TaskStateWiev>();
        this.taskMap = RtosUI.getCurrent().getDispatcher().getTaskMap();

        Table paramTeble = new Table();
        paramTeble.addStyleName("components-inside");
        paramTeble.addStyleName("rtos-table");
        paramTeble.addStyleName("parameters-panel");
        paramTeble.setSelectable(false);
        paramTeble.setImmediate(true);
        paramTeble.setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
        paramTeble.setFooterVisible(false);
        paramTeble.setPageLength(13);
        paramTeble.setWidth(100, Unit.PERCENTAGE);

        paramTeble.addContainerProperty("", Component.class, null);
        paramTeble.setColumnWidth("", 40);

        List<Object> tasksNames = new ArrayList<Object>();
        tasksNames.add(new Label(""));
        for (Map.Entry<Integer, Task> entry : taskMap.entrySet()) {
            paramTeble.addContainerProperty(entry.getKey(), Component.class, null);
            Label taskNameLabel = new Label(
                    "<img src='http://localhost:8080/VAADIN/themes/rtos/images/16x16/37.png'></img>"
                            + entry.getKey(), ContentMode.HTML);
            tasksNames.add(taskNameLabel);
            paramTeble.setColumnAlignment(entry.getKey(), Table.Align.CENTER);
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

        for (Map.Entry<Integer, Task> entry : taskMap.entrySet()) {
            TextFieldRefresher textFieldRefresher;

            textFieldRefresher = new StartIntTextField(this, entry.getValue());
            tStartIntActiveList.add(textFieldRefresher);
            this.textFieldRefresherList.add(textFieldRefresher);

            textFieldRefresher = new EndIntTextField(this, entry.getValue());
            tEndIntActiveList.add(textFieldRefresher);
            this.textFieldRefresherList.add(textFieldRefresher);

            tPlanCallList.add(new PlanTextField(this, entry.getValue()));

            textFieldRefresher = new PeriodTextField(this, entry.getValue());
            tPeriodCallList.add(textFieldRefresher);
            this.textFieldRefresherList.add(textFieldRefresher);

            textFieldRefresher = new VaitTextField(this, entry.getValue());
            tVaitMaxList.add(textFieldRefresher);
            this.textFieldRefresherList.add(textFieldRefresher);

            textFieldRefresher = new ExecMaxTextField(this, entry.getValue());
            tExecMaxList.add(textFieldRefresher);
            this.textFieldRefresherList.add(textFieldRefresher);

            PriorityTextField priorityTextField = new PriorityTextField(this, entry.getValue());
            priorityList.add(priorityTextField);
            priorityTextFields.add(priorityTextField);
            this.textFieldRefresherList.add(priorityTextField);

            textFieldRefresher = new TSessionTextField(this, entry.getValue());
            tSessionList.add(textFieldRefresher);
            this.textFieldRefresherList.add(textFieldRefresher);

            textFieldRefresher = new NSessionTextField(this, entry.getValue());
            nSessionList.add(textFieldRefresher);
            this.textFieldRefresherList.add(textFieldRefresher);

            TaskStatusWiev taskStatusWiev = new TaskStatusWiev(entry.getValue());
            statusList.add(taskStatusWiev);
            this.taskStatusWievList.add(taskStatusWiev);

            TaskStateWiev taskStateWiev = new TaskStateWiev(entry.getValue());
            stateList.add(taskStateWiev);
            this.taskStateWievList.add(taskStateWiev);
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
        return paramTeble;
    }

    public void renew () {
        log.debug("Renew parameters panel");
        Table table = makeParamTable();
        replaceComponent(this.paramTebleCurrent, table);
        this.paramTebleCurrent = table;
    }


    public void refreshAllFiels () {
        for (TextFieldRefresher textField : this.textFieldRefresherList) {
            textField.refreshField();
        }
        if (priorityTextFields.get(0).toFoloat() == priorityTextFields.get(1).toFoloat()
                || priorityTextFields.get(0).toFoloat() == priorityTextFields.get(2).toFoloat()
                || priorityTextFields.get(2).toFoloat() == priorityTextFields.get(1).toFoloat()) {
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

    @Override
    public void timerSecondTick(int second) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void timerTenthOfaSecondTick() {
        for (TextFieldRefresher textField : this.textFieldRefresherList) {
            textField.rewriteField();
        }
        for (TaskStateWiev taskStateWiev : this.taskStateWievList) {
            taskStateWiev.refresh();
        }
        for (TaskStatusWiev taskStatusWievList : this.taskStatusWievList) {
            taskStatusWievList.refresh();
        }

    }

}
