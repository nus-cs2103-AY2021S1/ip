import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void doTask(int index) {
        list.get(index).completeTask();
    }

    public void removeTask(int index) {
        list.remove(index);
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public int getNumberOfTask() {
        return list.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n\t ");
        int index = 1;
        for (Task task : list) {
            sb.append(index).append(".").append(task.toString()).append("\n\t ");
            index++;
        }
        return sb.delete(sb.length() - 3, sb.length() - 1).toString();
    }
}
