package duke;

import java.util.ArrayList;
/**
 * Contains list of task and functionality associated with it (ex; delete/add).
 */
public class TaskList {
    public ArrayList<Task> taskList;

    /**
     * Constructor of task.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Constructor of task.
     *
     * @param taskList ArrayList containing list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds tasks to taskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes task in taskList according to the index.
     *
     * @param index Index of task in taskList to be deleted
     */
    public void deleteTask(int index) {
        assert 0 <= index && index < taskList.size() : "invalid index, index is out of range";
        taskList.remove(index);
    }

    /**
     * Returns task in taskList according to the index.
     *
     * @param index Index of task in taskList to be return.
     * @return Task in the taskList according to the index.
     */
    public Task getTask(int index) {
        Integer taskIndex = index;
        assert 0 <= taskIndex && taskIndex < taskList.size() : "invalid index, index is out of range";
        Task task = taskList.get(taskIndex);
        return task;
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        for (Task task: taskList) {
            if (task.description.contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
