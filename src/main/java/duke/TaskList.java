package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskPriority;
import duke.task.TaskType;
import duke.task.Todo;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this(Collections.emptyList());
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
     * @param priority The priority of the task.
     * @param tags The tags associated with the task.
     * @return the added task.
     * @throws DukeException if the task cannot be added.
     */
    public Task addTask(TaskType type, String description, LocalDateTime dateTime,
                        TaskPriority priority, List<String> tags) throws DukeException {
        if (description.contains("|")) {
            throw new DukeException("Description cannot contain \"|\".");
        }

        switch (type) {
        case TODO:
            assert(dateTime == null) : "dateTime should be null for todos";
            return addTodo(description, priority, tags);
        case DEADLINE:
            return addDeadline(description, dateTime, priority, tags);
        case EVENT:
            return addEvent(description, dateTime, priority, tags);
        default:
            throw new DukeException("Task type not recognised!");
        }
    }

    private Todo addTodo(String description, TaskPriority priority, List<String> tags) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        Todo todo = new Todo(description, priority, tags);
        this.tasks.add(todo);

        return todo;
    }

    private Deadline addDeadline(String description, LocalDateTime dateTime,
                                 TaskPriority priority, List<String> tags) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        Deadline deadline = new Deadline(description, dateTime, priority, tags);
        this.tasks.add(deadline);

        return deadline;
    }

    private Event addEvent(String description, LocalDateTime dateTime,
                           TaskPriority priority, List<String> tags) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        Event event = new Event(description, dateTime, priority, tags);
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
        assert(taskNo >= 0) : "Task number should be non-negative";

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

    public List<Task> getPrioritisedTasks(TaskPriority priority) {
        return this.tasks.stream().filter(task -> task.hasPriority(priority)).collect(Collectors.toList());
    }

    public List<Task> getTaggedTasks(List<String> tags) {
        return this.tasks.stream().filter(task -> task.hasTags(tags)).collect(Collectors.toList());
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
