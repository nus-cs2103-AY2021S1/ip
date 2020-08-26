package duke.task;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        getTaskList().add(task);
    }

    public void deleteTask(int index) {
        getTaskList().remove(index);
    }

    public Task getTask(int index) {
        return getTaskList().get(index);
    }

    public boolean isTaskPresent(int index) {
        return index <= (totalTask() - 1);
    }

    /**
     * Finds all matching tasks from the task list.
     *
     * @param keyword Keyword to be matched with the task names in the task list.
     * @return ArrayList containing all matching tasks that contain the keyword.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < getTaskList().size(); i++) {
            if (getTask(i).getName().contains(keyword)) {
                foundTasks.add(getTask(i));
            }
        }
        return foundTasks;
    }

    public int totalTask() {
        return getTaskList().size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

}

