package ip.src.main.java;

import java.util.ArrayList;

public class Tasks {
    ArrayList<String> tasks = new ArrayList<String>();
    Layout layout = new Layout();

    public void addTask(String task) {
        tasks.add(task);
        layout.print("added: " + task);
    }

    public void showTasks() {
        layout.printList(tasks);
    }

}
