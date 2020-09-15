package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidUpdateInputException;

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
    public TaskList(Storage storage) throws FileNotFoundException, InvalidDateTimeException, InvalidUpdateInputException {
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
     * Edits description of a Task from list.
     * @param num          Int Task number to be deleted
     * @param description  New description
     * @return Updated task
     */
    public Task editTaskDescription(int num, String description) {
        int index = num -1;
        this.list.get(index).setDescription(description);
        return this.list.get(index);
    }

    /**
     * Edits date and time of a Task from list.
     * @param num   Int Task number to be deleted
     * @param date  String date
     * @param time  String time
     * @return Updated task
     * @throws InvalidDateTimeException
     * @throws InvalidUpdateInputException
     */
    public Task editTaskDatetime(int num, String date, String time) throws InvalidDateTimeException, InvalidUpdateInputException {
        int index = num -1;
        this.list.get(index).setDateTime(date, time);
        return this.list.get(index);
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
        int numOfDoneTask = 0;
        for (Task task : list) {
            if (task.checkIsDone()) {
                numOfDoneTask++;
            }
        }
        return numOfDoneTask;
    }

    /**
     * Find Tasks containing user input.
     * @param input User input
     * @return String format of tasks found
     */
    public String findTasks(String input) {
        StringBuilder tasksFound = new StringBuilder();
        for (Task task: list) {
            if (task.toString().contains(input)) {
                tasksFound.append(task.toString()).append("\n");
            }
        }
        return tasksFound.toString();
    }

    /**
     * Represent a more readable list of tasks for user.
     * @return String representing list of tasks
     */
    private String showList() {
        StringBuffer list = new StringBuffer();
        list.append("Here are the tasks in your list:\n");
        int listSize = getNumOfTask();
        for (int i = 0; i < listSize; i++) {
            list.append((i + 1) + ". " + this.list.get(i).toString() + "\n");
        }
        return list.toString();
    }

    @Override
    public String toString() {
        return showList();
    }


}
