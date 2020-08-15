package task;

public class TaskStorage {
    private Task[] storage;
    private int index;

    public TaskStorage() {
        this.storage = new Task[100];
        this.index = 0;
    }

    public void addTask(Task task) {
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
