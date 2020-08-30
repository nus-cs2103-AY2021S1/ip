package duke.tasklist;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a list of tasks. A <code>TaskList</code> object corresponds to
 * a list of todos, deadlines, and events based on user input.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns the list of tasks in the <code>TaskList</code> as a string,
     * where each line of the string corresponds to a <code>Task</code>.
     *
     * @return String where each line corresponds to a different task.
     */
    public String listToString() {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str += tasks.get(i).taskToText() + "\n";
        }
        return str;
    }
}

