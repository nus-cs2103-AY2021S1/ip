package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    /**
     * The constructor for task list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Displays all the tasks that the user has
     */
    public void showTasks() {
        int counter = 1;
        for (Task t : list) {
            System.out.println("        " + counter + "." + t.toString());
            counter++;
        }
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task delete(int num) {
        return list.remove(num);
    }

    public int size() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }

    public List<Task> getList() {
        return list;
    }
}
