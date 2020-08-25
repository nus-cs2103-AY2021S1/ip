package duke;

import java.util.ArrayList;

/**
 * Meant to represent an ArrayList of Tasks to simplify operations done
 */
public class TaskList {
    ArrayList<Task> tasklist;

    /**
     * Constructor
     * @param tasks an ArrayList of Tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasklist = tasks;
    }

    /**
     * Getter for the ArrayList of Tasks
     * @return the ArrayList of Tasks
     */
    public ArrayList<Task> getTasklist() {
        return this.tasklist;
    }

    /**
     * Adds a Todo object to the list
     * @param description
     * @return the ToDo object added to list
     * @throws DukeException
     */
    public Task addToDo(String description) throws DukeException {
        Task t = new Todo(description);
        this.tasklist.add(t);
        return t;
    }

    /**
     * Adds a Deadline object to the list
     * @param description
     * @param by
     * @return the Deadline object added
     * @throws DukeException
     */
    public Task addDeadline(String description, String by) throws DukeException {
        Task t = new Deadline(description, by);
        this.tasklist.add(t);
        return t;
    }

    /**
     * Adds an Event object to the list
     * @param description
     * @param at
     * @return the Event object added
     * @throws DukeException
     */
    public Task addEvent(String description, String at) throws DukeException {
        Task t = new Event(description, at);
        this.tasklist.add(t);
        return t;
    }

    /**
     * Removes a task from the list
     * @param taskIndex
     * @return the Task removed
     * @throws DukeException
     */
    public Task deleteTask(int taskIndex) throws DukeException {
        try {
            Task t = this.tasklist.get(taskIndex - 1);
            this.tasklist.remove(taskIndex - 1);
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    /**
     * Marks a Task as done
     * @param taskIndex
     * @return the Task marked as done
     * @throws DukeException
     */
    public Task doneTask(int taskIndex) throws DukeException {
        try {
            Task t = tasklist.get(taskIndex - 1);
            t.markAsDone();
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    /**
     * Getter for the Task at given index
     * @param index
     * @return the Task at given index
     */
    public Task get(int index) {
        return tasklist.get(index);
    }

}
