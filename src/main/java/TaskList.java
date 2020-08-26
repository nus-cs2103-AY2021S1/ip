import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    TaskList add(Task task) {
        taskList.add(task);
        return new TaskList(taskList);
    }

    TaskList remove(int index) {
        taskList.remove(index);
        return new TaskList(taskList);
    }

    Task get(int index) {
        return taskList.get(index);
    }

    int size() {
        return taskList.size();
    }

    TaskList set(int index, Task task) {
        taskList.set(index, task);
        return new TaskList(taskList);
    }

    ArrayList<Task> getList() {
        return taskList;
    }
}
