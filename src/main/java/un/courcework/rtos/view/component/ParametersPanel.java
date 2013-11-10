package un.courcework.rtos.view.component;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.view.component.textfiels.*;

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
        paramTeble.setPageLength(10);
        paramTeble.setWidth(100, Unit.PERCENTAGE);

        paramTeble.addContainerProperty("", Component.class, null);
        paramTeble.setColumnWidth("", 65);

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

        List<Object> tStartIntActiveList = new ArrayList<Object>();
        tStartIntActiveList.add(new Label("Tн"));
        List<Object> tEndIntActiveList = new ArrayList<Object>();
        tEndIntActiveList.add(new Label("Tк"));
        List<Object> tPeriodCallList = new ArrayList<Object>();
        tPeriodCallList.add(new Label("Tп"));
        List<Object> tVaitMaxList = new ArrayList<Object>();
        tVaitMaxList.add(new Label("Tож.max."));
        List<Object> tExecMaxList = new ArrayList<Object>();
        tExecMaxList.add(new Label("Tвып.max."));
        List<Object> tCritList = new ArrayList<Object>();
        tCritList.add(new Label("Tк"));
        List<Object> priorityList = new ArrayList<Object>();
        priorityList.add(new Label("П"));
        List<Object> tSessionList = new ArrayList<Object>();
        tSessionList.add(new Label("Tс"));

        for (Task task : tasks) {
            tStartIntActiveList.add(new StartIntValidator(task));
            tEndIntActiveList.add(new EndIntTextField(task));
            tPeriodCallList.add(new PeriodTextField(task));
            tVaitMaxList.add(new VaitTextField(task));
            tExecMaxList.add(new ExecTextField(task));
            tCritList.add(new CritTextField(task));
            priorityList.add(new PriorityTextField(task));
            tSessionList.add(new SessionTextField(task));
        }

        List<List<Object>> rowsArray = new ArrayList<List<Object>>();
        rowsArray.add(tasksNames);
        rowsArray.add(tStartIntActiveList);
        rowsArray.add(tEndIntActiveList);
        rowsArray.add(tPeriodCallList);
        rowsArray.add(tVaitMaxList);
        rowsArray.add(tExecMaxList);
        rowsArray.add(tCritList);
        rowsArray.add(priorityList);
        rowsArray.add(tSessionList);

        for (List<Object> row : rowsArray) {
            paramTeble.addItem(row.toArray(), row.hashCode());
        }




//        paramTeble.addItem(new Object[] {
//                new Label(""),
//                new Label("Task 1"),
//                new Label("Task 2"),
//                new Label("Task 3")
//        }, 1);
//
//        paramTeble.addItem(new Object[] {
//                new Label("Tн"),
//                getParamTextField(),
//                getParamTextField(),
//                getParamTextField()
//            }, 1);
//
//        paramTeble.addItem(new Object[] {
//                new Label("Tк"),
//                getParamTextField(),
//                getParamTextField(),
//                getParamTextField()
//        }, 2);
//
//        paramTeble.addItem(new Object[]{
//                new Label("Tn"),
//                getParamTextField(),
//                getParamTextField(),
//                getParamTextField()
//        }, 3);

        addComponent(paramTeble);

    }

    private TextField getParamTextField () {
        TextField textField = new TextField();
        textField.setMaxLength(3);
        textField.setWidth(100, Unit.PERCENTAGE);
        return textField;
    }

}
