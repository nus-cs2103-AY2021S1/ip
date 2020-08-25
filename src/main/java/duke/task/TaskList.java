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
     * Completes a task at the given index.
     *
     * The given index should be offset by 1 (i.e. the first index is 1).
     *
     * @param index The index of the task to complete.
     * @return The task that was completed.
     * @throws InvalidTaskIndexException If the index is invalid.
     */
    public Task completeTask(String index) throws InvalidTaskIndexException {
        try {
            int intIndex = Integer.parseInt(index) - 1;
            return completeTask(intIndex);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException("Invalid task index");
        }
    }

    private Task completeTask(int index) throws InvalidTaskIndexException {
        try {
            return taskList.get(index).completeTask();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("Invalid task index.");
        }
    }

    /**
     * Deletes a task at the given index.
     *
     * The given index should be offset by 1 (i.e. the first index is 1).
     *
     * @param index The index of the task to delete.
     * @return The task that was deleted.
     * @throws InvalidTaskIndexException If the index is invalid.
     */
    public Task deleteTask(String index) throws InvalidTaskIndexException {
        try {
            int intIndex = Integer.parseInt(index) - 1;
            return deleteTask(intIndex);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException("Invalid task index");
        }
    }

    private Task deleteTask(int index) throws InvalidTaskIndexException {
        try {
            return taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("Invalid task index while deleting.");
        }
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
