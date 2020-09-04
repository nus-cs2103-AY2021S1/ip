package duke.util;

import duke.DukeException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;

/**
 * Meant to represent an ArrayList of Tasks to simplify operations done.
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Constructor.
     * @param tasks an ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Getter for the ArrayList of Tasks.
     * @return the ArrayList of Tasks.
     */
    public ArrayList<Task> getTasklist() {
        return this.tasks;
    }

    /**
     * Adds a Todo object to the list.
     * @param description Description of the Todo object.
     * @return the ToDo object added to list.
     * @throws DukeException DukeException.
     */
    public Task addToDo(String description) throws DukeException {
        Task t = new Todo(description);
        this.tasks.add(t);
        return t;
    }

    /**
     * Adds a Deadline object to the list.
     * @param description Description of the Deadline.
     * @param by the Deadline object's deadline.
     * @return the Deadline object added.
     * @throws DukeException DukeException.
     */
    public Task addDeadline(String description, String by) throws DukeException {
        Task t = new Deadline(description, by);
        this.tasks.add(t);
        return t;
    }

    /**
     * Adds an Event object to the list.
     * @param description The Event object's description.
     * @param at the date at which the event is being held.
     * @return the Event object added.
     * @throws DukeException DukeException.
     */
    public Task addEvent(String description, String at) throws DukeException {
        Task t = new Event(description, at);
        this.tasks.add(t);
        return t;
    }

    /**
     * Removes a task from the list.
     * @param taskIndex The index of the task that is to be removed.
     * @return the Task removed.
     * @throws DukeException DukeException.
     */
    public Task deleteTask(int taskIndex) throws DukeException {
        try {
            Task t = this.tasks.get(taskIndex - 1);
            this.tasks.remove(taskIndex - 1);
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    /**
     * Marks a Task as done.
     * @param taskIndex The index of the task to be marked as done.
     * @return the Task marked as done.
     * @throws DukeException DukeException.
     */
    public Task doneTask(int taskIndex) throws DukeException {
        try {
            Task t = tasks.get(taskIndex - 1);
            t.markAsDone();
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    /**
     * Getter for the Task at given index.
     * @param index index of the Task to be returned.
     * @return the Task at given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

}
