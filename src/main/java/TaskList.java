import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasklist;

    private TaskList() {
        this.tasklist = new ArrayList<>();
    }

    public String addTask(Task task) {
        tasklist.add(task);
        return task.toString();
    }

    public String deleteTask(int tasknumber) {
        int i = tasknumber - 1;
        Task removed_task = this.tasklist.get(i);
        this.tasklist.remove(i);
        return removed_task.toString();
    }

    public String updateTask(int tasknumber) {
        int i = tasknumber - 1;
        Task updated_task = this.tasklist.get(i);
        updated_task.setDone();
        return updated_task.toString();
    }

    public int getListSize() {
        return this.tasklist.size();
    }

    public Task getTask(int index) {
        return this.tasklist.get(index);
    }

}
