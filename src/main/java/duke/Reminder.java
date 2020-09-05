package duke;

import task.DeadlineTask;
import task.Task;

import java.time.Duration;
import java.time.LocalDateTime;

public class Reminder {
    TaskList taskList;
    LocalDateTime currentDateTime;

    public Reminder(TaskList taskList) {
        this.taskList = taskList;
        this.currentDateTime = LocalDateTime.now();
    }

    /**
     * Returns a new TaskList containing deadline tasks that have not been completed and have deadlines within 3 days
     *
     * @return Object of the TaskList class.
     */
    public TaskList getUpcomingTasks() {
        TaskList upcomingTaskList = new TaskList();
        for (Task task : taskList.getTaskList()) {
            if (task.getTaskType() != Task.TaskType.DEADLINE) {
                // skip over
                continue;
            }

            if (Duration.between(((DeadlineTask) task).getDeadline(), currentDateTime).toDays() < 3
                    && task.getHasCompletedInt() == 0) {
                upcomingTaskList.addTask(task);
            }
        }
        return upcomingTaskList;
    }

}
