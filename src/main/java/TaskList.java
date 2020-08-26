import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    void add(Task task) {
        taskList.add(task);
    }

    void remove(int index) {
        taskList.remove(index);
    }

    Task get(int index) {
    	return taskList.get(index);
    }

    int size() {
    	return taskList.size();
    }

    ArrayList<Task> getList() {
        return taskList;
    }
}