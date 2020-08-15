package task;

import java.util.ArrayList;

public class TaskStorage {
    private ArrayList<Task> storage;

    public TaskStorage() {
        this.storage = new ArrayList<>();
    }

    public int getCurrCapacity() {
        return this.storage.size();
    }

    public void addTask(Task task) {
        this.storage.add(task);
    }

    public void completeTask(int index) {
        this.storage.get(index - 1).completeTask();
    }

    public void removeTask(int index) {
        this.storage.remove(index - 1);
    }

    public void printTask(int index) {
        System.out.println(this.storage.get(index - 1));
    }

    public void printTaskStorage() {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < this.storage.size(); i++) {
            String output = String.format("%d. %s", i + 1, this.storage.get(i));
            System.out.println(output);
        }
    }
}
