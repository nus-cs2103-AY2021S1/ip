package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.exceptions.TaskCompletedException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

/**
 * Class to initiate a TaskList object. This class contains various methods to interact with the
 * user's task list.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index - 1);
    }

    public void markTaskAsDone(int index) throws TaskCompletedException {
        taskList.get(index).markAsDone();
    }

    public Task getSpecificTask(int index) {
        return taskList.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getTaskListLength() {
        return taskList.size();
    }

    /**
     * Finds task in the current taskList that matches the input keyword.
     * Return TaskList containing the matching tasks.
     *
     * @param keyword Input keyword from the user.
     * @return TaskList of the matching tasks.
     */
    public TaskList findTask(String keyword) {
        ArrayList<Task> matchingTask = new ArrayList<>();

        for (Task task: taskList) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTask.add(task);
            }
        }

        return new TaskList(matchingTask);
    }

    /**
     * Finds task in the task list that is within input number of days from the current date.
     *
     * @param numberOfDays Input number of day from the user.
     * @return TaskList of the matching tasks.
     */
    public TaskList findUpcomingEvents(int numberOfDays) {
        ArrayList<Task> upcomingTask = new ArrayList<>();
        for (Task task: taskList) {
            if (task instanceof Event) {
                Event currentTask = (Event) task;
                LocalDateTime currentTime = LocalDateTime.now();
                LocalDateTime limitTime = currentTime.plusDays(numberOfDays).withHour(23).withMinute(59);
                LocalDateTime activityTime = currentTask.getActivityTime();
                if (activityTime.isBefore(limitTime) && activityTime.isAfter(currentTime)) {
                    upcomingTask.add(task);
                }
            }
            if (task instanceof Deadline) {
                Deadline currentTask = (Deadline) task;
                LocalDateTime currentTime = LocalDateTime.now();
                LocalDateTime limitTime = currentTime.plusDays(numberOfDays).withHour(23).withMinute(59);
                LocalDateTime activityTime = currentTask.getActivityTime();
                if (activityTime.isBefore(limitTime) && activityTime.isAfter(currentTime)) {
                    upcomingTask.add(task);
                }
            }
        }

        return new TaskList(upcomingTask);
    }

    /**
     * Prints out all items in the list and its corresponding status.
     *
     * @return String of the task.
     */
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.size() == i + 1) {
                string.append(i + 1).append(". ").append(taskList.get(i).toString());
            } else {
                string.append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
            }
        }
        return string.toString();
    }

}
