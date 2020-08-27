package Duke;

import Duke.Exception.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TaskList object to store and edit Tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Initiate Tasklist containing no Tasks stored.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Initiate Tasklist containing Tasks represented in storage.
     * @param storage  Storage containing tasks details
     * @throws FileNotFoundException
     * @throws InvalidDateTimeException
     */
    public TaskList(Storage storage) throws FileNotFoundException, InvalidDateTimeException {
        this.list = storage.getList();
    }

    /**
     * Update Storage file.
     * @param storage  Storage to be updated
     * @throws IOException
     */
    public void updateData(Storage storage) throws IOException {
        storage.updateFile(list);
    }

    /**
     * Add Task to list.
     * @param task  Task to be added
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Delete Task from list.
     * @param num Int Task number to be deleted
     * @return Deleted task
     */
    public Task deleteTask(int num) {
        int index = num - 1;
        Task task = list.get(index);
        this.list.remove(index);
        return task;
    }

    /**
     * Mark Task as done.
     * @param num Int Task number to be mark done
     * @return Task marked as done
     */
    public Task markDone(int num) {
        int index = num - 1;
        this.list.get(index).markAsDone();
        return this.list.get(index);
    }

    /**
     * Get number of Tasks stored in TaskList.
     * @return Int number of number of Tasks
     */
    public int getNumOfTask() {
        return this.list.size();
    }

    /**
     * Get number of completed Tasks stored in TaskList.
     * @return Int number of number of Tasks
     */
    private int getNumOfDoneTask() {
        int num = 0;
        for (Task task : list) {
            if (task.checkDone()) {
                num++;
            }
        }
        return num;
    }

    /**
     * Represent a more readable list of tasks for user.
     * @return String representing list of tasks
     */
    private String showList() {
        StringBuffer lst = new StringBuffer();
        lst.append("Here are the tasks in your list:\n");
        int listSize = getNumOfTask();
        for (int i = 0; i < listSize; i++) {
            lst.append((i + 1) + ". " + this.list.get(i).toString() + "\n");
        }
        return lst.toString();
    }

    @Override
    public String toString() {
        return showList();
    }


}
