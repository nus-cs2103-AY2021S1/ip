package duke.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a list of tasks that can be added, deleted, or set as done.
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * Initializes a newly created TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes a newly created TaskList with a given list of tasks.
     *
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     * @return list of tasks.
     */
    public List<Task> getList() {
        return this.tasks;
    }

    /**
     * Returns the size of the list.
     * @return size of list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns a filtered list of tasks according to the given keyword.
     * @param keyword keyword of the find command.
     * @return filtered list of tasks.
     */
    public List<Task> find(String keyword) {
        assert keyword != null;
        Predicate<Task> hasKeyword = task -> task.getDescription().contains(keyword);
        return this.tasks.stream().filter(hasKeyword).collect(Collectors.toList());
    }

    /**
     * Set a task as done.
     * @param idx index of task to be set as done.
     * @return task set as done.
     */
    public Task setAsDone(int idx) {
        int previousSize = this.tasks.size();
        assert idx >= 0 && idx < previousSize;
        Task doneTask = tasks.get(idx).setAsDone();
        this.tasks.set(idx, doneTask);
        assert this.tasks.size() == previousSize;
        return doneTask;
    }

    /**
     * Remove a task from the list.
     *
     * @param idx index of task to be removed from the list.
     * @return removed task.
     */
    public Task remove(int idx) {
        int previousSize = this.tasks.size();
        assert idx >= 0 && idx < previousSize;
        Task rmTask = tasks.get(idx);
        this.tasks.remove(idx);
        assert this.tasks.size() == previousSize - 1;
        return rmTask;
    }

    /**
     * Add a todo task to the list.
     *
     * @param desc description of the task.
     * @param isDone whether the task is done.
     * @return added task.
     */
    public Task addTodo(String desc, boolean isDone) {
        assert desc != null;
        int previousSize = this.tasks.size();
        Task newTask = new Todo(desc, isDone);
        this.tasks.add(newTask);
        assert this.tasks.size() == previousSize + 1;
        return newTask;
    }

    /**
     * Add a deadline or event task to the list with a date.
     *
     * @param type type of the task.
     * @param description description of the task.
     * @param date date of the task.
     * @param isDone whether the task is done.
     * @return added task.
     */
    public Task addTimedTask(String type, String description, LocalDate date, boolean isDone) {
        assert description != null && date != null;
        int previousSize = this.tasks.size();
        Task newTask;
        if (type.equals("deadline")) {
            newTask = new Deadline(description, date, isDone);
        } else {
            assert type.equals("event");
            newTask = new Event(description, date, isDone);
        }
        this.tasks.add(newTask);
        assert this.tasks.size() == previousSize + 1;
        return newTask;
    }

    /**
     * Add a deadline or event task to the list with a date and time.
     *
     * @param type type of the task.
     * @param description description of the task.
     * @param date date of the task.
     * @param time time of the task.
     * @param isDone whether the task is done.
     * @return added task.
     */
    public Task addTimedTask(String type, String description, LocalDate date, LocalTime time, boolean isDone) {
        assert description != null && date != null && time != null;
        int previousSize = this.tasks.size();
        Task newTask;
        if (type.equals("deadline")) {
            newTask = new Deadline(description, date, time, isDone);
        } else {
            newTask = new Event(description, date, time, isDone);
        }
        this.tasks.add(newTask);
        assert this.tasks.size() == previousSize + 1;
        return newTask;
    }
}
