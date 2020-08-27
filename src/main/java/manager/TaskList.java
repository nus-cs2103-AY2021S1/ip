package main.java.manager;
import main.java.tasks.Task;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a task list. Performs actions on tasks.
 */
public class TaskList {

    private List<Task> taskList = new ArrayList<>();

    /**
     * Prints out the tasks present in the task list.
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + "." + this.taskList.get(i).toString());
        }
    }

    /**
     * Adds the specified task into the task list.
     * @param task to be added
     */
    public void addTask(Task task) {
        if (task != null) {
            this.taskList.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
            printNumberOfTasks();
        }
    }

    /**
     * Mark the task specified by the index as done.
     * @param index of specified task to be marked done
     */
    public void markTaskAsDone(int index) {
        this.taskList.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.taskList.get(index).toString());
    }

    /**
     * Deletes the task specified by the index.
     * @param index of specified task to be deleted
     */
    public void deleteTask(int index) {
        Task removedTask = this.taskList.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask.toString());
        printNumberOfTasks();
    }

    /**
     * Finds tasks with the given keyword.
     * @param toFind keyword
     */
    public void findTasks(String toFind) {
        int currentIndex = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : this.taskList) {
            if (task.getDescription().contains(toFind)) {
                System.out.println(currentIndex + "." + task.toString());
                currentIndex++;
            }
        }
    }

    /**
     * Deletes all existing tasks in the task list.
     */
    public void deleteAllTasks() {
        if (this.taskList.size() > 0) {
            this.taskList.subList(0, this.taskList.size()).clear();
        }
    }

    /**
     * Obtains the total number of tasks in the task list.
     * @return the number of tasks
     */
    public int getNumberOfTasks() {
        return this.taskList.size();
    }

    /**
     * Prints the total number of tasks in the task list.
     */
    public void printNumberOfTasks() {
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    /**
     * Obtains the list of tasks.
     * @return the list of tasks
     */
    public List<Task> getList() {
        return this.taskList;
    }

    /**
     * Sets the task list to the specified list of tasks.
     * @param taskList specified to be set
     */
    public void setList(List<Task> taskList) {
        this.taskList = taskList;
    }

}
