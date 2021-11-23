package manager;

import java.util.ArrayList;
import java.util.List;

import tasks.Task;

/**
 * Represents a task list. Performs actions on tasks.
 */
public class TaskList {

    private List<Task> taskList = new ArrayList<>();

    /**
     * Prints out the tasks present in the task list.
     */
    public void listTasks() {
        if (this.taskList.size() == 0) {
            System.out.println("Oohh look at me! Your task list is empty. Existence is pain!");
        } else {
            System.out.println("Yes sireee, look at me! Here are the tasks in your list:");
            for (int i = 0; i < this.taskList.size(); i++) {
                System.out.println((i + 1) + "." + this.taskList.get(i).toString());
            }
        }
    }

    /**
     * Adds the specified task into the task list.
     * @param task to be added
     */
    public void addTask(Task task) {
        if (task != null) {
            this.taskList.add(task);
            System.out.println("Ooooohh yeahhhh cannnnn do, I'm Mr. Meeseeks! I've added this task:");
            System.out.println(task.toString());
            printNumberOfTasks();
        }
        assert task != null : "Task being added is not correctly created";
    }

    /**
     * Marks the task specified by the index as done.
     * @param index of specified task to be marked done
     */
    public void markTaskAsDone(int index) {
        assert index < this.taskList.size() && index >= 0
                : "Index for mark done does not exist";

        this.taskList.get(index).markAsDone();
        System.out.println("Oooh yeahhh, good job! I've marked this task as done:");
        System.out.println(this.taskList.get(index).toString());
    }

    /**
     * Deletes the task specified by the index.
     * @param index of specified task to be deleted
     */
    public void deleteTask(int index) {
        assert index < this.taskList.size() && index >= 0
                : "Index for delete does not exist";

        Task removedTask = this.taskList.remove(index);
        System.out.println("I'm Mr. Meeseeks, look at me! I've removed this task:");

        assert removedTask != null : "Removed task cannot be null";
        System.out.println(removedTask.toString());

        printNumberOfTasks();
    }

    /**
     * Finds tasks with the given keywords.
     * @param toFind keywords
     */
    public void findTasks(String toFind) {
        int currentIndex = 1;
        System.out.println("Oohhh look at me! Here are the matching tasks in your list:");

        // takes note of which tasks were printed
        boolean[] hasBeenPrinted = new boolean[this.taskList.size()];
        String[] keywords = toFind.split(" ");

        for (String word : keywords) {
            for (int i = 0; i < this.taskList.size(); i++) {
                if (hasBeenPrinted[i]) {
                    continue;
                }
                Task task = this.taskList.get(i);
                if (task.getDescription().contains(word) || task.searchTag(word)) {
                    System.out.println(currentIndex + "." + task.toString());
                    hasBeenPrinted[i] = true;
                    currentIndex++;
                }
            }
        }
        if (currentIndex == 1) {
            System.out.println("Looks like you have none!");
        }
    }

    /**
     * Deletes all existing tasks in the task list.
     */
    public void deleteAllTasks() {
        if (this.taskList.size() > 0) {
            this.taskList.subList(0, this.taskList.size()).clear();
        }
        System.out.println("All done! You have 0 tasks now.");
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

    /**
     * Adds a tag to the specified task.
     * @param index of specified task
     * @param tagName the name of the tag
     */
    public void tagTask(int index, String tagName) {
        this.taskList.get(index).tagTask(tagName);
        System.out.println("Yes siree! Your task is tagged: ");
        System.out.println(this.taskList.get(index).toString());
    }

}
