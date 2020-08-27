package alison.tool;

import alison.task.Task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

}
