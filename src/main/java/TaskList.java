import java.util.ArrayList;

/**
 * TaskList contains the task list e.g., 
 * it has operations to add/delete tasks in the list.
 */
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