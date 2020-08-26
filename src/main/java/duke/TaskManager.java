package duke;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a TaskManager.
 * Stores and manipulates tasks.
 */
public class TaskManager {
    private final TaskStorage taskStorage;

    /**
     * Constructor for TaskManager.
     *
     * @throws IOException   If an input or output exception occurred.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public TaskManager() throws IOException, DukeException {
        this.taskStorage = TaskStorage.init();
    }

    /**
     * Creates and adds a Todo to the taskStorage.
     *
     * @param content string description of todo
     * @return created todo
     * @throws IOException   If an input or output exception occurred.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Todo addTodo(String content) throws DukeException, IOException {
        Todo todo = new Todo(content);
        taskStorage.addTask(todo);
        return todo;
    }

    /**
     * Creates and adds a Deadline to the taskStorage.
     *
     * @param content     string description of deadline
     * @param datetimeDue datetime of when the deadline is
     * @return created deadline
     * @throws IOException   If an input or output exception occurred.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Deadline addDeadline(String content, String datetimeDue) throws DukeException, IOException {
        Deadline deadline = new Deadline(content, datetimeDue);
        taskStorage.addTask(deadline);
        return deadline;
    }

    /**
     * Creates and adds an Event to the taskStorage.
     *
     * @param content  string description of event
     * @param datetime datetime of when the event is
     * @return created todo
     * @throws IOException   If an input or output exception occurred.
     * @throws DukeException If an exception related to Duke occurred.
     */
    public Event addEvent(String content, String datetime) throws DukeException, IOException {
        Event event = new Event(content, datetime);
        taskStorage.addTask(event);
        return event;
    }

    /**
     * Deletes a specified Task in the taskStorage.
     *
     * @param taskNumber Task number of task to be deleted.
     * @return deleted task
     * @throws IOException   If an input or output exception occurred.
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
     * @return completed task
     * @throws IOException   If an input or output exception occurred.
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
     * @return string concatenation of all tasks
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return IntStream.range(0, taskStorage.getTaskCount())
                .mapToObj(i -> String.format("%d. %s", i + 1, taskStorage.taskList.get(i).toString()))
                .collect(Collectors.joining("\n"));
    }

    public int getTaskCount() {
        return taskStorage.getTaskCount();
    }

    /**
     * Returns a list of tasks from the taskManager that contains the content
     * @param content the search term used
     * @return the list of tasks that contains the content specified
     */
    public List<Task> findAllContaining(String content) {
        return taskStorage.findAllContaining(content);
    }
}
