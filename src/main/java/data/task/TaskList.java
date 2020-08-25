package data.task;

import ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents an array list of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;
    private Ui ui;

    public TaskList(ArrayList<Task> taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Adds a task into the list and then generates and prints a success message.
     * @param task to be added into the list.
     */
    public void add(Task task) {
        this.taskList.add(task);
        this.ui.showAddedToList(task);
    }

    /**
     * Adds a task into the list only.
     * Used when loading data from a storage file.
     * @param task to be added into the list.
     */
    public void load(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a specific task based on the task's index in the task array list and then generates and prints a success message.
     * @param i index of task to be deleted.
     */
    public void delete(int i) {
        Task task = this.taskList.get(i - 1);
        this.taskList.remove(i - 1);
        this.ui.showDelete(task);
    }

    /**
     * Marks a specific task as done based on task's index in the task array list and then generates and prints a success message.
     * @param i index of task to be marked.
     */
    public void markDone(int i) {
        Task task = this.taskList.get(i - 1);
        task.markAsDone();
        this.ui.showMarkDone(task);
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task getTask(int i) {
        return this.taskList.get(i);
    }

    public int getTotalTask() {
        return this.taskList.size();
    }

    /**
     * Finds tasks that contain the input keyword within their descriptions.
     * @param keyword to find.
     * @return an array list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasksKeyword(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.descriptionContains(keyword)) {
                result.add(task);
            }
        }
        return  result;
    }

    /**
     * Finds tasks that contain the input date within their dates.
     * @param date to find.
     * @return an array list of tasks that contain the date.
     */
    public ArrayList<Task> findTasksDate(String date) {
        ArrayList<Task> result = new ArrayList<>();
        LocalDate inputDate = LocalDate.parse(date);
        for (Task task : this.taskList) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDate().equals(inputDate)) {
                    result.add(task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getDate().equals(inputDate)) {
                    result.add(task);
                }
            } else {
                //ignore To_Do tasks as they do not have dates
            }
        }
        return  result;
    }
}
