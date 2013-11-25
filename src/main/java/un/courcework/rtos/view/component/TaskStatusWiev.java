package un.courcework.rtos.view.component;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;
import un.courcework.rtos.model.Task;

public class TaskStatusWiev extends Image {

    private Task task;

    public TaskStatusWiev(Task task) {
        this.task = task;
        addStyleName("task-state-image");
        switch (this.task.getTaskStatus()) {
            case WAIT:
                setSource(new ThemeResource("images/16x16/timeRed.png"));
                setDescription("Задача ожидает");
                break;
        }
    }

}
