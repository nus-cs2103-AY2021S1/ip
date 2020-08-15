package storage;

public class TaskStorage {
    private String[] storage;
    private int index;

    public TaskStorage() {
        this.storage = new String[100];
        this.index = 0;
    }

    public void addTask(String task) {
        this.storage[index] = task;
        index++;
    }

    public void printTaskStorage() {
        for (int i = 0; i < index; i++) {
            String output = String.format("%d. %s", i + 1, this.storage[i]);
            System.out.println(output);
        }
    }
}
