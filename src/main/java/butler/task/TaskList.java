package butler.task;

import butler.exception.ButlerException;
import java.util.ArrayList;

// contains the task list
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void completeTask(int taskIndex) throws ButlerException {
        try {
            taskList.get(taskIndex - 1).markComplete();
        } catch (IndexOutOfBoundsException e) {
            throw new ButlerException("Please give a valid index. \""
                    + taskIndex + "\" is not a valid index.");
        }
    }

    public void deleteTask(int taskIndex) throws ButlerException {
        try {
            taskList.remove(taskIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new ButlerException("Please give a valid index. \""
                    + taskIndex + "\" is not a valid index.");
        }
    }
}
