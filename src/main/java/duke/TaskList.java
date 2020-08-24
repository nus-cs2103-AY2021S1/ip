package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public List<Task> getList() {
        return this.list;
    }

    public String iterateToDo() {
        String output = "";
        int counter = 1;
        for (Task task : list) {
            if (task == null) {
                break;
            } else {
                output += Integer.toString(counter) + ". " + task.toString() + "\n";
                counter++;
            }
        }
        return output;
    }

    public void deleteTask(int number) {
        this.list.remove(number - 1);
    }

    public void addTask(int counter, Task task) {
        this.list.add(counter, task);
    }
}
