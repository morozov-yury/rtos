package un.courcework.rtos.view.component;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;
import un.courcework.rtos.model.Task;

public class TaskStateWiev extends Image {

    private Task task;

    public TaskStateWiev(Task task) {
        this.task = task;
        addStyleName("task-status-image");
        switch (this.task.getTaskState()) {
            case ACTIVE:
                setSource(new ThemeResource("images/16x16/43_.png"));
                setDescription("Задача активная");
                break;
            case NOT_ACTIVE:
                setSource(new ThemeResource("images/16x16/44.png"));
                setDescription("Задача не активная");
                break;
        }
    }

}
