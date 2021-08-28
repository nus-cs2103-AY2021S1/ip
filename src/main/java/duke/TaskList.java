package duke;

import java.util.ArrayList;

/**
 * A TaskList object contains the task list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns task list.
     *
     * @return ArrayList<Task> task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns task at the specified index.
     *
     * @param index This is the index of the task in the task list.
     * @return Task task retrieved from the task list.
     */
    public Task getTask(int index) {
        assert (index >= 0);
        return taskList.get(index);
    }

    /**
     * Returns size of task list.
     *
     * @return int size of task list.
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param t task to be added
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Deletes specified task from the task list.
     *
     * @param index index of task to be deleted from the task list.
     */
    public void deleteTask(int index) {
        assert (index >= 0);
        taskList.remove(index);
    }

    /**
     * Marks the specified task as done.
     *
     * @param index index of task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        assert (index >= 0);
        taskList.get(index).setDone();
    }

    /**
     * Returns a String containing all the tasks in the task list.
     * String is in a format suitable to be printed by Duke Ui object.
     *
     * @return String task list converted to a string.
     */
    @Override
    public String toString() {
        String listAsString = "";
        int count = 1;
        for (Task i : this.taskList) {
            listAsString = listAsString.concat(count + ". "
                    + i.toString()) + "\n";
            count++;
        }
        return listAsString;
    }
}
