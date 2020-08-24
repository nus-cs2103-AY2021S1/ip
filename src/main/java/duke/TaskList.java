package duke;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Encapsulate the TaskList class which deals with the tasks in Duke
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Create a new empty TaskList
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Adds a task to the TaskList
     * @param t Task to be added
     */
    public void addATask(Task t) {
        tasks.add(t);
    }

    /**
     * Removes a task from the TaskList
     * @param ind Index of task to be removed
     * @return Task removed
     * @throws DukeException If the index is invalid
     */
    public Task removeATask(int ind) throws DukeException {
        if (ind < 0 || ind >= tasks.size()) {
            throw new DukeException("Task "+ ind +" does not exist!");
        }
        return tasks.remove(ind);
    }

    /**
     * Mark a task as complete
     * @param ind Index of task to be completed
     * @return Task completed
     * @throws DukeException If the index is invalid
     */
    public Task completeTask(int ind) throws DukeException {
        if (ind < 0 || ind >= tasks.size()) {
            throw new DukeException("Task "+ ind +" does not exist!");
        }
        Task t = tasks.get(ind);
        t.completeTask();
        return t;
    }

    /**
     * Returns the number of tasks
     * @return Number of tasks
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Returns a string that represents all the tasks
     * @return String that represent all the tasks
     */
    public String printTasks() {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        int i=0;
        for(Task task:tasks){
            str.append(String.format("%d.%s", ++i, task));
            if (i != tasks.size()) {
                str.append("\n");
            }
        }
        return str.toString();
    }

    /**
     * Returns the ArrayList of tasks
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

}
