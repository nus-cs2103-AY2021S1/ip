package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public Task addTask(TaskType type, String description, LocalDateTime dateTime) throws DukeException {
        switch (type) {
            case TODO:
                return addTodo(description);
            case DEADLINE:
                return addDeadline(description, dateTime);
            case EVENT:
                return addEvent(description, dateTime);
            default:
                return null;
        }
    }

    public Todo addTodo(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        Todo todo = new Todo(description);
        tasks.add(todo);

        return todo;
    }

    public Deadline addDeadline(String description, LocalDateTime by) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);

        return deadline;
    }

    public Event addEvent(String description, LocalDateTime at) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        Event event = new Event(description, at);
        tasks.add(event);

        return event;
    }

    public Task getTask(int taskNo) throws DukeException {
        if (taskNo <= 0 || taskNo > tasks.size()) {
            throw new DukeException("Task " + taskNo + " does not exist.");
        }

        return tasks.get(taskNo - 1);
    }

    public Task doTask(int taskNo) throws DukeException {
        Task task = getTask(taskNo);

        task.markAsDone();
        return task;
    }

    public Task deleteTask(int taskNo) throws DukeException {
        Task task = getTask(taskNo);

        tasks.remove(task);
        return task;
    }

    public List<Task> getDueTasks(LocalDate date) {
        return tasks.stream().filter(task -> task.isDue(date)).collect(Collectors.toList());
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
