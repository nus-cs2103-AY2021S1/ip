import Task.Task;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Stores tasks and supports various operations on them.
 */
public class TaskList implements java.io.Serializable {

    private ArrayList<Task> tasks;
    String loadMessage = "";

    /**
     * Initializes a new task list
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    private TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        UI.print("added: " + task.toString() + numTasks());
        Storage.store(this);
    }

    /**
     * @return a string describing the number of tasks
     */
    public String numTasks() {
        int size = tasks.size();
        return "You now have " + size + " task" + (size > 1 ? "s" : "") + " in the list.\n";
    }

    /**
     * Prints the list of tasks through the UI class
     */
    public void print_tasks() {
        UI.print(this.toString());
    }

    @Override
    public String toString(){
        if(tasks.size() == 0){
            return "There are no tasks!\n";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i).toString());
        }
        return sb.toString();
    }

    /**
     * @param i Index of the task to be returned.
     * @return The task at index I.
     * @throws DukeException
     */
    public Task get(int i) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("invalid task number");
        }
        return tasks.get(i);
    }

    /**
     * @param i Index of the task to be removed.
     * @throws DukeException
     */
    public void remove(int i) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("invalid task number");
        }
        tasks.remove(i);
        Storage.store(this);
    }

    /**
     * @param i the index of the task to be set.
     * @param value Doneness of the task to be set.
     * @throws DukeException
     */
    public void setDone(int i, boolean value) throws DukeException {
        if (i < 0 || i >= tasks.size()) {
            throw new DukeException("invalid task number");
        }
        tasks.get(i).isDone = value;
        Storage.store(this);
    }

    /**
     * Finds tasks in this tasklist that matches the provided substring, and prints them.
     * @param substring string to match
     */
    public void find(String substring) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        sb.append("Here are the matching tasks in your list:\n");
        for (Task t : tasks) {
            if (t.text.contains(substring)) {
                counter++;
                sb.append(counter + "." + t.toString());
            }
        }
        if (counter > 0) {
            UI.print(sb.toString());
        } else {
            UI.print("No match found!\n");
        }
    }

    /**
     * Sorts tasks by date then prints the new list.
     */
    public void sort(){
        Collections.sort(tasks);
        UI.print("Tasks sorted by date.\n" + this.toString());
    }
}