package duke.task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates an ArrayList of Tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds the given task to the list.
     *
     * @param task Task to be added.
     * @return The given task.
     */
    public Task addTask(Task task) {
        taskList.add(task);
        return task;
    }

    /**
     * Adds a Todo task.
     *
     * @param args String representing a Todo task's description.
     * @return The new Todo instance.
     * @throws TaskException If the task cannot be created.
     */
    public Task addTodo(String args) throws TaskException {
        Todo todo = new Todo(args);
        taskList.add(todo);
        return todo;
    }

    /**
     * Adds a Deadline task.
     *
     * @param args String with parameters joined as specified in Deadline.
     * @return The new Deadline instance.
     * @throws TaskException If the task cannot be created.
     */
    public Task addDeadline(String args) throws TaskException {
        try {
            Deadline deadline = Deadline.create(args);
            taskList.add(deadline);
            return deadline;
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format");
        }
    }

    /**
     * Adds an Event.
     *
     * @param args String with parameters joined as specified in Event.
     * @return The new Event instance.
     * @throws TaskException If the task cannot be created.
     */
    public Task addEvent(String args) throws TaskException {
        try {
            Event event = Event.create(args);
            taskList.add(event);
            return event;
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format");
        }
    }

    /**
     * Completes tasks at the given indexes.
     * The given indexes should be as given in the list command.
     *
     * @param indexes An array of indexes whose tasks should be completed.
     * @return Tasks that were completed.
     * @throws InvalidTaskIndexException If any one index is invalid.
     */
    public List<Task> completeTasks(String... indexes) throws InvalidTaskIndexException {
        List<Task> tasks = verifyIndexes(indexes);
        for (Task task : tasks) {
            task.completeTask();
        }
        return tasks;
    }

    /**
     * Deletes a task at the given index.
     * The given index should be as given in the list command.
     *
     * @param index The index whose task should be deleted.
     * @return The deleted task.
     * @throws InvalidTaskIndexException If the index is invalid.
     */
    public Task deleteTask(String index) throws InvalidTaskIndexException {
        verifyIndexes(index);
        int taskIndex = Integer.parseInt(index) - 1;
        return taskList.remove(taskIndex);
    }

    /**
     * Verifies if the given list of indexes are valid and within the taskList range.
     *
     * @param indexes List of indexes to verify. Each index should be based on the list command.
     * @return List of tasks corresponding to the list in the list command.
     * @throws InvalidTaskIndexException If the given list contains one or more invalid indexes.
     */
    private List<Task> verifyIndexes(String... indexes) throws InvalidTaskIndexException {
        List<Task> tasks = new ArrayList<>();
        for (String index : indexes) {
            try {
                int taskIndex = Integer.parseInt(index) - 1;
                tasks.add(taskList.get(taskIndex));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new InvalidTaskIndexException("One or more indexes is not valid."
                        + "Please check the list again.");
            }
        }
        return tasks;
    }

    /**
     * Searches the taskList for tasks whose description contains the searchTerm.
     *
     * @param searchTerm Search term to search with.
     * @return List of tasks whose description has the search term.
     */
    public List<Task> findTasks(String searchTerm) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task t : taskList) {
            if (t.descriptionContains(searchTerm)) {
                results.add(t);
            }
        }
        return results;
    }

    /**
     * Returns a shallow copy of the ArrayList.
     *
     * @return Shallow copy of the ArrayList.
     */
    public List<Task> getTaskList() {
        return List.of(taskList.toArray(new Task[0]));
    }
}
