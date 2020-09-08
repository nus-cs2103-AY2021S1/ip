package duke.task;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

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
