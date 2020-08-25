package duke;

import java.util.Iterator;
import java.util.List;
import duke.task.Task;

public class TaskList {
    private List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public void list() {
        Iterator<Task> iterator = list.iterator();
        int count = 0;
        System.out.println(Ui.LINE + "    Here are the tasks in your list:");
        while (iterator.hasNext()) {
            count++;
            System.out.println("    " + count + ". " + iterator.next().toString());
        }
        System.out.println(Ui.LINE);
    }

    public int size() {
        return list.size();
    }

    public Task getTask(int taskNumber) {
        return list.get(taskNumber - 1);
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task removeTask(int taskNumber) {
        return list.remove(taskNumber - 1);
    }
}
