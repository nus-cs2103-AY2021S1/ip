package duke;

import java.time.Duration;
import java.time.LocalDateTime;

import task.DeadlineTask;
import task.Task;

public class Reminder {
    private TaskList tasks;
    private LocalDateTime currentDateTime;

    /**
     * Constructor for creating a reminder object
     *
     * @param tasks Arraylist containing tasks.
     */
    public Reminder(TaskList tasks) {
        this.tasks = tasks;
        this.currentDateTime = LocalDateTime.now();
    }

    /**
     * Returns a new TaskList containing deadline tasks that have not been completed and have deadlines within 3 days
     *
     * @return Object of the TaskList class.
     */
    public TaskList getUpcomingTasks() {
        TaskList upcomingTaskList = new TaskList();
        for (Task task : tasks.getTasks()) {
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
