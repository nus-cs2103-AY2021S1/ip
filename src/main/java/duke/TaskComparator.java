package duke;

import java.util.Comparator;

import duke.task.Task;

public class TaskComparator implements Comparator<Task> {

    @Override
    public int compare(Task task1, Task task2) {
        int dateComparison = task1.getTaskDate().compareTo(task2.getTaskDate());
        int timeComparison = task1.getTaskTime().compareTo(task2.getTaskTime());
        int descComparison = task1.getDescription().compareTo(task2.getDescription());

        if (dateComparison == 0) {
            if (timeComparison == 0) {
                if (descComparison == 0) {
                    return 0;
                } else {
                    return descComparison;
                }
            } else {
                return timeComparison;
            }
        } else {
            return dateComparison;
        }
    }
}
