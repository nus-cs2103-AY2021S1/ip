package duke.task;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    @Override
    public Iterator<Task> iterator() {
        return this.taskList.iterator();
    }

    public int getCurrCapacity() {
        return this.taskList.size();
    }

    public boolean isValidIndex(int index) {
        return index <= this.taskList.size() && index > 0;
    }

    public Task addTask(Task task) {
        this.taskList.add(task);
        return task;
    }

    public Task completeTask(int index) {
        Task task = this.taskList.get(index - 1);
        task.completeTask();
        return task;
    }

    public Task removeTask(int index) {
        return this.taskList.remove(index - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            String taskLine = String.format("%d. %s", i + 1, this.taskList.get(i));
            sb.append(taskLine);
            sb.append("\n");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
