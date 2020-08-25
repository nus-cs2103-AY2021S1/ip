package data;

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

    public void list() {
        int counter = 1;
        System.out.println("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) != null) {
                System.out.println(counter + ". " + tasks.get(i));
                counter++;
            } else {
                break;
            }
        }
    }

    /**
     * Delete the task with a specified index from the task list.
     * @param num index of task to be deleted.
     */

    public void delete(int num) {

        try {
            Task task = tasks.get(num - 1);
            tasks.remove(num - 1);
            System.out.println("Noted. I've removed the task: \n"
                + task
                + "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There isn't such a task!");
        }
    }

    /**
     * Mark the task with a specified index from the task list as done.
     * @param num Index of task to be done.
     */

    public void doTask(int num) {
        Task task = tasks.get(num - 1);
        task.doTask();
        System.out.println("Noted. I've done the task: \n"
                + task);
    }

    /**
     * Add a task to the task list.
     * @param task Task object to be added to the list.
     */
    public void add(Task task) {

        tasks.add(task);
        System.out.println("Got it. I've added this task: \n"
                + task
                + "\nNow you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Returns the task with specified index from the task list.
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
     * @return size of task list.
     */

    public int size() {
        return tasks.size();
    }

    public void find(String substring) {
        int counter = 1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(substring)) {
                System.out.println(counter + "." + tasks.get(i));
                counter++;
            }
        }
    }


}
