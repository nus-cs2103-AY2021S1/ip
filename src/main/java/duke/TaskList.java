package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the TaskList and returns it.
     *
     * @param type The type of the task.
     * @param description The description of the task.
     * @param dateTime The date/time of the task if applicable.
     * @return the added task.
     * @throws DukeException if the task cannot be added.
     */
    public Task addTask(TaskType type, String description, LocalDateTime dateTime) throws DukeException {
        switch (type) {
        case TODO:
            return addTodo(description);
        case DEADLINE:
            return addDeadline(description, dateTime);
        case EVENT:
            return addEvent(description, dateTime);
        default:
            throw new DukeException("Task type not recognised!");
        }
    }

    private Todo addTodo(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        Todo todo = new Todo(description);
        this.tasks.add(todo);

        return todo;
    }

    private Deadline addDeadline(String description, LocalDateTime by) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        Deadline deadline = new Deadline(description, by);
        this.tasks.add(deadline);

        return deadline;
    }

    private Event addEvent(String description, LocalDateTime at) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        Event event = new Event(description, at);
        this.tasks.add(event);

        return event;
    }

    /**
     * Returns the task corresponding to the given task number.
     *
     * @param taskNo A number representing a task.
     * @return a task corresponding to the given task number.
     * @throws DukeException if the task number does not correspond to any task.
     */
    public Task getTask(int taskNo) throws DukeException {
        if (taskNo <= 0 || taskNo > this.tasks.size()) {
            throw new DukeException("Task " + taskNo + " does not exist.");
        }

        return this.tasks.get(taskNo - 1);
    }

    /**
     * Marks a task as done and returns it.
     *
     * @param taskNo A number representing a task.
     * @return the task which has been mark as done.
     * @throws DukeException if the task cannot be marked as done.
     */
    public Task doTask(int taskNo) throws DukeException {
        Task task = this.getTask(taskNo);

        task.markAsDone();
        return task;
    }

    /**
     * Deletes a task as done and returns it.
     *
     * @param taskNo A number representing a task.
     * @return the deleted task.
     * @throws DukeException if the task cannot be deleted.
     */
    public Task deleteTask(int taskNo) throws DukeException {
        Task task = this.getTask(taskNo);

        this.tasks.remove(task);
        return task;
    }

    /**
     * Returns a list of tasks that are due on the given date.
     *
     * @param date A date.
     * @return a list of tasks due on the given date.
     */
    public List<Task> getDueTasks(LocalDate date) {
        return this.tasks.stream().filter(task -> task.isDue(date)).collect(Collectors.toList());
    }

    /**
     * Returns a list of tasks containing the given keyword.
     *
     * @param keyword A keyword.
     * @return a list of tasks containing the given keyword.
     */
    public List<Task> findTasks(String keyword) {
        return this.tasks.stream().filter(task -> task.hasKeyword(keyword)).collect(Collectors.toList());
    }

    /**
     * Returns all tasks.
     *
     * @return all tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }
}
