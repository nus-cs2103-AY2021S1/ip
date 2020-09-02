package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;
import duke.ui.Ui;

/**
 * Handles actions by user with regards to all their tasks.
 */
public class TaskList {
    private Storage savedStorage;
    private List<Task> allItems;

    /**
     * Creates an empty Tasklist.
     */
    public TaskList() {
        this.savedStorage = new Storage();
        this.allItems = this.savedStorage.getSavedTasks();
    }

    /**
     * Adds task to the current list of tasks.
     *
     * @param toAdd Task to add
     * @return response to User.
     */
    public String addTask(Task toAdd) {
        this.allItems.add(toAdd);
        String printTask = "Alright, its in your list now!\n\t" + toAdd
                + "\nNow you have " + this.allItems.size() + " tasks.";
        return Ui.printMessage(printTask);
    }

    /**
     * Retrieves all the tasks currently stored.
     *
     * @return all tasks currently stored
     */
    public List<Task> getAllTasks() {
        return this.allItems;
    }

    /**
     * Prints the output of all tasks in store currently.
     *
     * @return response to User.
     */
    public String printStore() {
        String printList;
        if (this.allItems.size() == 0) {
            printList = "There are no tasks added till now.\nAdd one by just typing its name.\n";
        } else {
            printList = "Please finish these tasks ASAP!\n";
            int counter = 1;
            for (Task task : this.allItems) {
                printList = printList.concat("[" + counter + "] " + task + "\n");
                counter++;
            }
            printList = printList.concat("If you're brave enough to start,\n"
                    + "You're strong enough to finish it!\n");
        }
        return Ui.printMessage(printList);
    }

    /**
     * Sets the selected task to be completed.
     *
     * @param index index of task to be set completed.
     * @return response to User.
     */
    public String completeTask(int index) {
        Task toComplete = this.allItems.get(index);
        return toComplete.finishTask();
    }

    /**
     * Removes task from current list.
     *
     * @param index index of task to be removed.
     * @return response to User.
     */
    public String deleteTask(int index) {
        this.allItems.remove(index);
        return Ui.printDeleteTaskMessage();
    }

    /**
     * Sends instruction to save the current tasks.
     */
    public void saveIntoHardDisk() {
        this.savedStorage.saveIntoHardDisk();
    }

    /**
     * Finds and prints matching tasks.
     *
     * @param toMatch the word to match with.
     * @return response to User.
     */
    public String matchTasks(String toMatch) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.allItems) {
            if (task.canMatch(toMatch)) {
                matchingTasks.add(task);
            }
        }

        String printMatchingTasks;
        if (matchingTasks.size() == 0) {
            printMatchingTasks = "There are no tasks that match " + toMatch + "\n";
        } else {
            printMatchingTasks = "Matching tasks: \n";
            for (Task task : matchingTasks) {
                printMatchingTasks = printMatchingTasks.concat(task + "\n");
            }
        }
        return Ui.printMessage(printMatchingTasks);
    }
}
