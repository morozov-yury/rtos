package un.courcework.rtos.view.component;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;
import un.courcework.rtos.model.Task;

public class TaskStatusWiev extends Image {

    private Task task;

    public TaskStatusWiev(Task task) {
        this.task = task;
        addStyleName("task-state-image");
        refresh();
    }

    public void refresh () {
        switch (this.task.getTaskStatus()) {
            case ACTIVE:
                setSource(new ThemeResource("images/16x16/43_.png"));
                setDescription("Задача в интервале активности");
                break;
            case NOT_ACIVE:
                setSource(new ThemeResource("images/16x16/44.png"));
                setDescription("Задача вне интервала активности");
                break;
        }
    }

}
