import task.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public Task getTask(int index) {
        return this.list.get(index - 1);
    }

    public void listAllTasks() {
        this.list.forEach(task -> {
            String index = String.valueOf(this.list.indexOf(task) + 1);
            System.out.println(index + ". " + task);
        });
    }


}
