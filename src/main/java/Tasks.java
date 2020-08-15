package ip.src.main.java;

import java.util.ArrayList;

public class Tasks {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private Layout layout = new Layout();

    public void addTask(String task) {
        tasks.add(new Task(task));
        layout.print("added: " + task);
    }

    public void showTasks() {
        layout.printTaskList(tasks);
    }

    public void markDone(String i) {
        int index = Integer.parseInt(i);
        try {
            Task task = tasks.get(index - 1);
            task.markDone();
            layout.printMarkedDone(task);
        } catch (IndexOutOfBoundsException e) {
            layout.print("No task labelled " + i);
        }

    }

}
