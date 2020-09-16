package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Scheduler {

    private TaskList tasksList;

    Scheduler() {}

    Scheduler(TaskList tasksList) {
        this.tasksList = tasksList;
    }

    public void instantiateTasksList(TaskList tasksList) {
        this.tasksList = tasksList;
    }

    public List<Task> getTaskByDate(LocalDateTime beforeDate) {
        List<Task> tasksBeforeDate = new ArrayList<Task>();

        for (int i = 0; i < tasksList.getNumOfTask(); i++) {
            Task task = tasksList.getTask(i);
            Optional<LocalDateTime> localDateTimeOptional = task.getLocalDateTime();
            if (localDateTimeOptional.isPresent()) {
                LocalDateTime date = localDateTimeOptional.get();
                if (date.isBefore(beforeDate)) {
                    tasksBeforeDate.add(task);
                }
            }
        }
        return tasksBeforeDate;
    }
}
