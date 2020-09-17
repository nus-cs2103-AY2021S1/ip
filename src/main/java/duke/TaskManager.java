package duke;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Represents a TaskManager.
 * Stores and manipulates tasks.
 */
public class TaskManager {
    private final TaskStorage taskStorage;

    /**
     * Initializes a TaskManager.
     *
     * @throws IOException If an input or output exception occurred.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public TaskManager() throws IOException, DukeException {
        this.taskStorage = TaskStorage.init();
    }

    /**
     * Creates and adds a Todo to the taskStorage.
     *
     * @param content String description of todo.
     * @param priority Priority of the todo.
     * @return Created todo.
     * @throws IOException If an input or output exception occurred.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Todo addTodo(String content, String priority) throws DukeException, IOException {
        Todo todo = new Todo(content, priority);
        taskStorage.addTask(todo);
        return todo;
    }

    /**
     * Creates and adds a Deadline to the taskStorage.
     *
     * @param content String description of deadline.
     * @param datetimeDue Datetime of when the deadline is.
     * @param priority Priority of the deadline.
     * @return Created deadline.
     * @throws IOException If an input or output exception occurred.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Deadline addDeadline(String content, String datetimeDue, String priority) throws DukeException, IOException {
        Deadline deadline = new Deadline(content, datetimeDue, priority);
        taskStorage.addTask(deadline);
        return deadline;
    }

    /**
     * Creates and adds an Event to the taskStorage.
     *
     * @param content String description of event.
     * @param datetime Datetime of when the event is.
     * @param priority Priority of the event.
     * @return Created todo.
     * @throws IOException If an input or output exception occurred.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Event addEvent(String content, String datetime, String priority) throws DukeException, IOException {
        Event event = new Event(content, datetime, priority);
        taskStorage.addTask(event);
        return event;
    }

    /**
     * Deletes a specified Task in the taskStorage.
     *
     * @param taskNumber Task number of task to be deleted.
     * @return Deleted task.
     * @throws IOException If an input or output exception occurred.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Task deleteTask(int taskNumber) throws DukeException, IOException {
        int index = getIndex(taskNumber);
        return taskStorage.removeTask(index);
    }

    /**
     * Completes a specified Task in the taskStorage.
     *
     * @param taskNumber Task number of task to complete.
     * @return Completed task.
     * @throws IOException If an input or output exception occurred.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Task completeTask(int taskNumber) throws DukeException, IOException {
        int index = getIndex(taskNumber);
        Task task = taskStorage.getTask(index);
        task.setComplete(true);
        taskStorage.save();
        return task;
    }

    private int getIndex(int taskNumber) throws DukeException {
        if (taskNumber <= 0) {
            throw new DukeException("Invalid task number specified, task number must be greater than 0.");
        } else if (taskNumber > taskStorage.getTaskCount()) {
            throw new DukeException("Task number specified is larger than current amount of tasks.");
        }
        int index = taskNumber - 1;
        return index;
    }

    /**
     * Returns a human readable string of all tasks in the taskStorage.
     *
     * @return String concatenation of all tasks.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return IntStream.range(0, taskStorage.getTaskCount())
                .mapToObj(i -> String.format("%d. %s", i + 1, taskStorage.getTaskList().get(i).toString()))
                .collect(Collectors.joining("\n"));
    }

    public int getTaskCount() {
        return taskStorage.getTaskCount();
    }

    /**
     * Returns a list of tasks from the taskManager that contains the content
     *
     * @param content The search term used.
     * @return The list of tasks that contains the content specified
     */
    public List<Task> findAllContaining(String content) {
        return taskStorage.findAllContaining(content);
    }
}
