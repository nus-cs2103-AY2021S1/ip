import java.util.ArrayList;
import java.util.List;

/**
 * Manages {@code Task} objects.
 */
public class TaskManager {
    /** Message prefix to be displayed upon adding a {@code Task}. */
    private static final String ADD_TASK_MESSAGE_PREFIX = "added: ";
    /** Message to be displayed upon marking a {@code Task} as done. */
    private static final String MARK_TASK_DONE_MESSAGE = "Nice! I've marked this task as done:\n  ";

    /** List of {@code Task} objects. */
    private final List<Task> tasks = new ArrayList<>();

    /** Constructs a {@code TaskManager} object. */
    public TaskManager() {}

    /**
     * Adds a new {@code Task} with the specified name.
     *
     * @param taskName the name of the {@code Task} object to be created.
     * @return a string representation of the action of adding a task.
     */
    public String addTask(String taskName) {
        tasks.add(new Task(taskName));
        return ADD_TASK_MESSAGE_PREFIX + taskName;
    }

    /**
     * Mark a {@code Task} as done.
     *
     * @param listIndex the index of the {@code Task} in the {@code TaskManager} list.
     * @return a string representation of the action of marking a task as done.
     */
    public String markAsDone(int listIndex) {
        Task updatedTask = tasks.get(listIndex).markAsDone();
        tasks.set(listIndex, updatedTask);
        return MARK_TASK_DONE_MESSAGE + updatedTask;
    }

    /**
     * Returns a list of {@code Task}s under the {@code TaskManager}.
     *
     * @return a string representation of the {@code TaskManager}.
     */
    @Override
    public String toString() {
        StringBuilder formattedList = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            formattedList.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return formattedList.toString();
    }
}
