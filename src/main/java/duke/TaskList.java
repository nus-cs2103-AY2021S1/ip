package duke;

import duke.task.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A list in charge of storing tasks created by user.
 */
public class TaskList {

    private static final int TASK_LIMIT = 100;

    private final List<Task> taskList = new ArrayList<>(TASK_LIMIT);

    /**
     * Adds task into TaskList object.
     * @param task A Task object created by user.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes task from TaskList object.
     * @param index Integer index of task that user wants to remove.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Gets size of TaskList object.
     * @return Integer representing size of TaskList object.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Gets Task object from TaskList object.
     * @param index Integer index of Task that user wants to get from TaskList object.
     * @return Task object.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }
}
