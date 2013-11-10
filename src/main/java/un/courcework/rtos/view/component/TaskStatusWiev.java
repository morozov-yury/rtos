package un.courcework.rtos.view.component;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.model.TaskStatus;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 10.11.13
 * Time: 22:24
 * To change this template use File | Settings | File Templates.
 */
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
