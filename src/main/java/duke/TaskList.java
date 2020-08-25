package main.java.duke;

import main.java.duke.task.Deadline;
import main.java.duke.task.Event;
import main.java.duke.task.Task;
import main.java.duke.task.ToDo;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a Manager to manage different tasks on hand.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Initialise with no initial task on hand.
     */
    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Initialise with serveral tasks on hand.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a new Task of type ToDo.
     * @param name Name of the ToDo to create.
     * @return The new task added.
     */
    public Task addToDo(String name) {
        Task newTask = new ToDo(name);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Add a new Task of type Deadline.
     * @param name Name Name of the Deadline to be created.
     * @param time Timing of the new Deadline to be created.
     * @return The new Task added.
     * @throws DukeException If time is wrongly formatted.
     */
    public Task addDeadLine(String name, String time) throws DukeException {
        Task newTask = new Deadline(name,time);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Add a new Task of type Event.
     * @param name Name Name of the Event to be created.
     * @param time Timing of the new Event to be created.
     * @return The new Task added.
     * @throws DukeException If time is wrongly formatted.
     */
    public Task addEvent(String name, String time) throws DukeException{
        Task newTask = new Event(name, time);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * @return The total number of tasks at hands.
     */
    public int getTotalTask(){
        return this.tasks.size();
    }

    /**
     * Set the task as done. Notes, task starts counting from 1.
     * @param index The index of task to be done.
     * @throws DukeException If the task at index is already done.
     */
    public void doTask(int index) throws DukeException {
        this.tasks.get(index-1).setDone();
    }

    /**
     * Convert all tasks at hands to a format friendly for saving.
     * @return String of tasks in saved format.
     */
    public String toSaveFormat() {
        String result = "";
        for(Task t: tasks) {
            result += t.toSaveFormat() + "\n";
        }
        return result;
    }

    /**
     * Set the task as done. Notes, task starts counting from 1.
     * @param index The index of task to be deleted.
     * @return Task delted.
     * @throws DukeException If the task does not exists.
     */
    public Task deleteTask(int index) throws DukeException {
        if (index-1 < 1 || index > this.tasks.size()) {
            throw new DukeException("Task #" + index + "does not exist.\n" +
                    "To check for lists of Tasks, type \"list\"");
        }
        return this.tasks.remove(index-1);
    }

    /**
     * Get detail of the Task at index.
     * @param index The index of task that is concerned.
     * @return String representation of the task at concerned.
     */
    public String getTaskStatus(int index) {
        return tasks.get(index-1).toString();
    }

    /**
     * Display all the tasks on hands.
     * @return The String that represents all the tasks on hands in display format.
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 1; i <= tasks.size(); i++) {
            output += i + "." + tasks.get(i-1).toString();
            if (i != tasks.size()) {
                output += "\n";
            }
        }
        return output;
    }
}
