package duke;

import java.util.ArrayList;

/**
 * TaskList contains the task list e.g., 
 * it has operations to add/delete tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void remove(int index) {
        taskList.remove(index);
    }

    public Task get(int index) {
    	return taskList.get(index);
    }

    public int size() {
    	return taskList.size();
    }

    public ArrayList<Task> getList() {
        return taskList;
    }

    public TaskList find(String condition) {
        ArrayList<Task> newTaskList = new ArrayList<Task>();
        for (Task task: taskList) {
            if (task.getContent().contains(condition)) {
                newTaskList.add(task);
            }
        }
        return new TaskList(newTaskList);
    }
}