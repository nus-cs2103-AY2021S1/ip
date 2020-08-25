package duke;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

/**
 * Represents a list of tasks that can be added, deleted, or set as done.
 */
public class TaskList {

    private List<Task> tasks;

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
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns a filtered list of tasks according to the given keyword.
     * @param key keyword of the find command.
     * @return filtered list of tasks.
     */
    public List<Task> find(String key) {
        return this.tasks.stream().filter(task -> task.getDesc().contains(key))
                .collect(Collectors.toList());
    }

    /**
     * Set a task as done.
     * @param idx index of task to be set as done.
     * @return task set as done.
     */
    public Task setDone(int idx) {
        Task doneTask = tasks.get(idx).setDone();
        this.tasks.set(idx, doneTask);
        return doneTask;
    }

    /**
     * Remove a task from the list.
     *
     * @param idx index of task to be removed from the list.
     * @return removed task.
     */
    public Task remove(int idx) {
        Task rmTask = tasks.get(idx);
        this.tasks.remove(idx);
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
        Task newTask = new Todo(desc, isDone);
        this.tasks.add(newTask);
        return newTask;
    }

    /**
     * Add a deadline or event task to the list with a date.
     *
     * @param type type of the task.
     * @param desc description of the task.
     * @param date date of the task.
     * @param isDone whether the task is done.
     * @return added task.
     */
    public Task addTimedTask(String type, String desc, LocalDate date, boolean isDone) {
        Task newTask;
        if (type.equals("deadline")) {
            newTask = new Deadline(desc, date, isDone);
        } else {
            newTask = new Event(desc, date, isDone);
        }
        this.tasks.add(newTask);
        return newTask;
    }

    /**
     * Add a deadline or event task to the list with a date and time.
     *
     * @param type type of the task.
     * @param desc description of the task.
     * @param date date of the task.
     * @param time time of the task.
     * @param isDone whether the task is done.
     * @return added task.
     */
    public Task addTimedTask(String type, String desc, LocalDate date, LocalTime time, boolean isDone) {
        Task newTask;
        if (type.equals("deadline")) {
            newTask = new Deadline(desc, date, time, isDone);
        } else {
            newTask = new Event(desc, date, time, isDone);
        }
        this.tasks.add(newTask);
        return newTask;
    }
}
