package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

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

    public String listToString() {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str += tasks.get(i).taskToText() + "\n";
        }
        return str;
    }
}

