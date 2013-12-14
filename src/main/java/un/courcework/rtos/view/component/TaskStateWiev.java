package un.courcework.rtos.view.component;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import un.courcework.rtos.model.Task;

public class TaskStateWiev extends Image {

    private static Logger log = LoggerFactory.getLogger(TaskStateWiev.class);

    private Task task;

    public TaskStateWiev(Task task) {
        this.task = task;
        addStyleName("task-status-image");
        refresh();
    }

    public void refresh () {
        switch (this.task.getTaskState()) {
            case WORKS:
                setSource(new ThemeResource("images/16x16/05.png"));
                setDescription("Задача выполняется");
                break;
            case READY_TO_WORK:
                setSource(new ThemeResource("images/16x16/ready_to_work.png"));
                setDescription("Задача готова к работе");
                break;
            case WAIT_FOR_READY:
                setSource(new ThemeResource("images/16x16/wait_for_ready.png"));
                setDescription("Задача ожидает готовности");
                break;
            case STOPPED:
                setSource(new ThemeResource("images/16x16/stop.gif"));
                setDescription("Задача остановлена");
                break;
        }
    }

}
