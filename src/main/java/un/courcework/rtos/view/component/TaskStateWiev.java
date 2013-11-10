package un.courcework.rtos.view.component;

import com.vaadin.client.ui.Icon;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import un.courcework.rtos.model.Task;
import un.courcework.rtos.model.TaskState;
import un.courcework.rtos.model.TaskStatus;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 10.11.13
 * Time: 22:24
 * To change this template use File | Settings | File Templates.
 */
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
