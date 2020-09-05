package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.util.Storage;

/**
 * Class the holds the tasks provided by the user.
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
     * @param storage {@link Storage} object that will load the data.
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
     * @param keyword Keyword for the search.
     * @return
     */
    public String listTasksWithKeyword(String keyword) {
        StringBuilder output = new StringBuilder();
        int i = 1;

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                output.append(i).append(". ").append(task).append("\n");
            }
            i++;
        }

        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }

    @Override
    public String toString() {

        if (size() == 0) {
            return "No tasks added yet!";
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < size(); i++) {
            output.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }

        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }
}
