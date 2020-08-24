import java.util.ArrayList;

public class TaskList<Task> {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    boolean isEmpty() {
        return taskList.isEmpty();
    }

    int size() {
        return taskList.size();
    }

    Task get(int position) {
        return taskList.get(position);
    }

    void set(int position, Task newTask) {
        taskList.set(position, newTask);
    }

    void add(Task taskToDo) {
        taskList.add(taskToDo);
    }

    void remove(Task taskToRemove) {
        taskList.remove(taskToRemove);
    }

    ArrayList<Task> exportList() {
        return taskList;
    }
}
