package duke.sort;

import java.util.Comparator;

import duke.task.Task;
import duke.task.TaskType;

public class SortByDate implements Comparator<Task> {
    @Override
    public int compare(Task firstTask, Task secondTask) {
        if (firstTask.getType() == TaskType.TODO && secondTask.getType() != TaskType.TODO) {
            return 1;
        } else if (secondTask.getType() == TaskType.TODO && firstTask.getType() != TaskType.TODO) {
            return -1;
        } else if (firstTask.getType() == TaskType.TODO && secondTask.getType() == TaskType.TODO) {
            return 0;
        } else {
            return firstTask.getDate().compareTo(secondTask.getDate());
        }
    }
}
