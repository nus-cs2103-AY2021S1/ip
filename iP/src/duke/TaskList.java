package duke;

import java.util.ArrayList;
/**
 * contains list of task and functionality associated with it (ex; delete/add)
 */
public class TaskList {
    public ArrayList<Task> taskList;

    /**
     * constructor of task
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * constructor of task
     * @param taskList ArrayList containing list of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * add tasks to taskList
     * @param task task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * delete task in taskList according to the index
     * @param index index of task in taskList to be deleted
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * get task in taskList according to the index
     * @param index index of task in taskList to be return
     * @return task in the taskList according to the index
     */
    public Task getTask(int index) {
        Task task = taskList.get(index);
        return task;
    }
}
