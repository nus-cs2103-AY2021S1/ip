package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;

/**
 * Class that holds the tasks provided by the user.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Creates a brand new {@code TaskList}.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a {@code TaskList} from existing data.
     *
     * @param storage {@link Storage} object that will load the data.
     * @throws DukeException If the data fails to load.
     */
    public void loadFromStorage(Storage storage) throws DukeException {
        storage.loadData(this);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public void markAsDone(Task task) {
        task.markDone();
    }

    public Task getTask(int taskIdx) {
        return tasks.get(taskIdx - 1);
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Finds and list tasks with description containing the keyword.
     *
     * @param keyword Keyword for the search.
     * @return Tasks containing the keyword.
     */
    public String listTasksWithKeyword(String keyword) {
        assert keyword != null : "Search keyword is null";

        StringBuilder output = new StringBuilder();
        int taskIdx = 1;

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                output.append(String.format("%d. %s\n", taskIdx, task));
            }
            taskIdx++;
        }

        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }

    /**
     * Serializes the {@code TaskList} into a format that can be stored.
     *
     * @return Serialized data of the list.
     */
    public String serializeList() {
        StringBuilder data = new StringBuilder();

        for (Task task : tasks) {
            String taskType = task.getTaskType();
            String serializedTask;

            if (taskType.equals("T")) {
                serializedTask = String.format("%s|%s|%s|%s\n", task.getTaskType(), task.isDone(),
                    task.tagsToString(), task.getDescription());
            } else {
                serializedTask = String.format("%s|%s|%s|%s|%s\n", task.getTaskType(),
                    task.isDone(), task.tagsToString(), task.getDescription(),
                    task.getDateTime().getOriginalInput());
            }

            data.append(serializedTask);
        }

        return data.toString();
    }

    @Override
    public String toString() {

        if (size() == 0) {
            return "No tasks added yet!";
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < size(); i++) {
            Task task = tasks.get(i);
            output.append(String.format("%d. %s\n   Tags: %s\n", i + 1, task, task.tagsToString()));
        }

        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }
}
