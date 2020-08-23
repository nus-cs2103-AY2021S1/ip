package duke;

import duke.task.*;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private static final int TASK_LIMIT = 100;

    private final List<Task> taskList = new ArrayList<>(TASK_LIMIT);

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }
}
