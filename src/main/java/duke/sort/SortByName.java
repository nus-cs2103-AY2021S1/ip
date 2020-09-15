package duke.sort;

import duke.task.Task;

import java.util.Comparator;

public class SortByName implements Comparator<Task> {
    @Override
    public int compare(Task firstTask, Task nextTask) {
        return firstTask.getName().compareTo(nextTask.getName());
    }
}
