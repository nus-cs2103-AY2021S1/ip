package data;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Encapsulates TaskList that supports various methods
 */
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints out the current list of tasks.
     */
    public String list() {
        int counter = 1;
        String result = "Here are the tasks in your list: \n";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                result += counter + ". " + tasks.get(i) + "\n";
                counter++;
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * Delete the task with a specified index from the task list.
     *
     * @param num index of task to be deleted.
     */

    public String delete(int num) {
        assert num >= 1;

        try {
            Task task = tasks.get(num - 1);
            tasks.remove(num - 1);
            return "Noted. I've removed the task: \n"
                    + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            return "There isn't such a task!";
        }
    }

    /**
     * Mark the task with a specified index from the task list as done.
     *
     * @param num Index of task to be done.
     */

    public String doTask(int num) {
        assert num >= 1;
        Task task = tasks.get(num - 1);
        task.doTask();
        return "Noted. I've done the task: \n" + task;
    }

    /**
     * Add a task to the task list.
     *
     * @param task Task object to be added to the list.
     */
    public String add(Task task) {


        tasks.add(task);
        return "Got it. I've added this task: \n"
                + task
                + "\nNow you have " + tasks.size() + " task(s) in the list.";
    }

    /**
     * Returns the task with specified index from the task list.
     *
     * @param index Index of task to be returned
     * @return Task from list with specified index
     */

    public Task get(int index) {
        if (index < size() && index >= 0) {
            return tasks.get(index);
        } else {
            return null;
        }

    }

    /**
     * Returns size of task list.
     *
     * @return size of task list.
     */

    public int size() {
        return tasks.size();
    }

    /**
     * Finds and prints task that contains a substring in the description
     *
     * @param substring Substring that the task is to contain.
     */

    public String find(String substring) {
        int counter = 1;
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(substring)) {
                result += counter + "." + tasks.get(i);
                counter++;
            }
        }
        return result;
    }

    /**
     * Snoozes the task and change the date of the task.
     *
     * @param taskNum The task to change
     * @param date Date to change the task to
     * @return String of the new task after it has been modified.
     */

    public String snoozeTask(int taskNum, LocalDate date) {

        Task task = tasks.get(taskNum);
        task.changeDate(date);
        return "The new task is as follows: \n" + task.toString();
    }


}
