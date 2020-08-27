package main.java;

import main.java.Task.Task;
import java.util.List;

/**
 * Handles actions by user with regards to all their tasks.
 */
public class TaskList {
    private Storage savedStorage;
    private List<Task> allItems;

    public TaskList() {
        this.savedStorage = new Storage();
        this.allItems = this.savedStorage.getSavedTasks();
    }

    /**
     * Adds task to the current list of tasks.
     * @param toAdd Task to add
     */
    public void addTask(Task toAdd) {
        this.allItems.add(toAdd);
        String printTask = "Alright, its in your list now!\n\t" + toAdd +
                "\nNow you have " + this.allItems.size() + " tasks.\n" + Ui.LINE;
        Ui.relayMessageToUser(printTask);
    }

    /**
     * Retrieves all the tasks currently stored.
     * @return
     */
    public List<Task> getAllTasks() {
        return this.allItems;
    }

    /**
     * Prints the output of all tasks in store currently.
     */
    public void printStore() {
        String printList;
        if (this.allItems.size() == 0) {
            printList = "There are no tasks added till now.\nAdd one by just typing its name.\n" + Ui.LINE;
        } else {
            printList = "Please finish these tasks ASAP!\n";
            int counter = 1;
            for (Task task : this.allItems) {
                printList = printList.concat("[" + counter + "] " + task + "\n");
                counter++;
            }
            printList = printList.concat("If you're brave enough to start,\n" + "You're strong enough to finish it!\n" + Ui.LINE);
        }
        Ui.relayMessageToUser(printList);
    }

    /**
     * Sets the selected task to be completed.
     * @param index index of task to be set completed.
     */
    public void completeTask(int index) {
        Task toComplete = this.allItems.get(index);
        toComplete.finishTask();
    }

    /**
     * Removes task from current list.
     * @param index index of task to be removed.
     */
    public void deleteTask(int index) {
        this.allItems.remove(index);
        Ui.printDeleteTaskMessage();
    }

    /**
     * Sends instruction to save the current tasks.
     */
    public void saveIntoHarddisk() {
        this.savedStorage.saveIntoHarddisk();
    }


}
