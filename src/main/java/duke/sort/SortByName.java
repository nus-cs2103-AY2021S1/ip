package duke.sort;

import java.util.Comparator;

import duke.task.Task;

public class SortByName implements Comparator<Task> {
    @Override
    public int compare(Task firstTask, Task nextTask) {
        return firstTask.getName().compareTo(nextTask.getName());
    }
}
