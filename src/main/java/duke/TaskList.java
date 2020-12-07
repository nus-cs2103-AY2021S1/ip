package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;



/**
 * Represents a Manager to manage different tasks on hand.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Initialise with no initial task on hand.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
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
    public Task addTodo(String name) {
        Task newTask = new ToDo(name);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Find for Tasks that contain the keyword.
     * @param keyword The keyword to search.
     * @return String representation of Tasks that contains the keyword
     */
    public String find(String keyword) {
        String result = "";
        for (Task currentTask : tasks) {
            if (!currentTask.getName().contains(keyword)) {
                continue;
            } else if (result.equals("")) {
                result += "\n" + currentTask.toString();
                continue;
            }
            result += " " + currentTask.toString();
        }
        return result;
    }

    /**
     * Add a new Task of type Deadline.
     * @param name Name Name of the Deadline to be created.
     * @param time Timing of the new Deadline to be created.
     * @return The new Task added.
     * @throws DukeException If time is wrongly formatted.
     */
    public Task addDeadLine(String name, String time) throws DukeException {
        Task newTask = new Deadline(name, time);
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
    public Task addEvent(String name, String time) throws DukeException {
        Task newTask = new Event(name, time);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * @return The total number of tasks at hands.
     */
    public int getTotalTask() {
        return this.tasks.size();
    }

    /**
     * Set the task as done. Notes, task starts counting from 1.
     * @param index The index of task to be done.
     * @throws DukeException If the task at index is already done.
     */
    public void doTask(int index) throws DukeException {
        this.tasks.get(index - 1).setDone();
    }

    /**
     * Convert all tasks at hands to a format friendly for saving.
     * @return Array of String that describes tasks at hands in saved format.
     */
    public String[] toSaveFormat() {
        String[] result = new String[this.tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            result[i] = tasks.get(i).toSaveFormat();
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
        if (index - 1 < 1 || index > this.tasks.size()) {
            throw new DukeException("Task #" + index + "does not exist.\n"
                    + "To check for lists of Tasks, type \"list\"");
        }
        assert index - 1 > 0 : "Error. Delete Task don't catch index out of bound exception";
        return this.tasks.remove(index - 1);
    }

    /**
     * Get detail of the Task at index.
     * @param index The index of task that is concerned.
     * @return String representation of the task at concerned.
     */
    public String getTaskStatus(int index) throws DukeException {
        try {
            return tasks.get(index - 1).toString();
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("Please key in an index between 1 and " + tasks.size());
        }

    }

    /**
     * Display all the tasks on hands.
     * @return The String that represents all the tasks on hands in display format.
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 1; i <= tasks.size(); i++) {
            output += i + "." + tasks.get(i - 1).toString();
            if (i != tasks.size()) {
                output += "\n"; //Add a new line only if the current task is not the last.
            }
        }
        return output;
    }

    /**
     * Replace the current data with archived data.
     */
    public void loadArchivedTasks(ArrayList<Task> dataRead) {
        this.tasks = dataRead;
    }
}
