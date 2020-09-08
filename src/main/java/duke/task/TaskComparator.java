package duke.task;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

    /**
     * Overrides the Compare method of the Comparator Class to compare two tasks
     * on 3 conditions
     * 1. If a task is Done
     * 2. If a task is marked as Important
     * 3. If a task date is before or after the other.
     * @param task1 Task object 1
     * @param task2 Task object 2
     * @return Int
     */
    @Override
    public int compare(Task task1, Task task2) {
        if (!task1.isDone() && task2.isDone()) {
            return -1;
        } else if (task1.isDone() && !task2.isDone()) {
            return 1;
        } else if (task1.isTaskImportant() && !task2.isTaskImportant()) {
            return -1;
        } else if (!task1.isTaskImportant() && task2.isTaskImportant()) {
            return 1;
        } else if (task1.getDate().isBefore(task2.getDate())) {
            return -1;
        } else if (task1.getDate().isAfter(task2.getDate())) {
            return 1;
        } else {
            return 0;
        }
    }
}
