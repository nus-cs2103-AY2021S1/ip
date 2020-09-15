package duke;

import java.util.*;

import duke.task.Task;
import duke.ui.Ui;

/**
 * Handles actions by user with regards to all their tasks.
 */
public class TaskList {
    private Storage savedStorage;
    private List<Task> allTasks;
    private static HashMap<Integer, Task> matchingTasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.savedStorage = new Storage();
        this.allTasks = this.savedStorage.getSavedTasks();
    }

    public int getNumberOfTasks() {
        return this.allTasks.size();
    }

    /**
     * Adds task to the current list of tasks.
     *
     * @param toAdd Task to add
     * @return response to User.
     */
    public String addTask(Task toAdd) {
        this.allTasks.add(toAdd);
        String printTask = "Alright, its in your list now!\n\t" + toAdd
                + "\nNow you have " + this.allTasks.size() + " tasks.";
        return Ui.printMessage(printTask);
    }

    /**
     * Retrieves all the tasks currently stored.
     *
     * @return the tasks currently stored in a List
     */
    public List<Task> getAllTasks() {
        return this.allTasks;
    }

    /**
     * Prints the output of all tasks in store currently.
     *
     * @return response to User.
     */
    public String printStore() {
        String printList;
        if (this.allTasks.size() == 0) {
            printList = "There are no tasks added till now.\nAdd one by just typing its name.\n";
        } else {
            printList = "Please finish these tasks ASAP!\n";
            int counter = 1;
            for (Task task : this.allTasks) {
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
        assert index < this.allTasks.size();
        Task toComplete = this.allTasks.get(index);
        return toComplete.finishTask();
    }

    /**
     * Removes task from current list.
     *
     * @param index index of task to be removed.
     * @return response to User.
     */
    public String deleteTask(int index) {
        assert index < this.allTasks.size();
        this.allTasks.remove(index);
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
        TaskList.matchingTasks = new HashMap<>();
        checkAllTasksForMatch(toMatch);
        return matchTasksWithOffset(toMatch, 1);
    }

    private void checkAllTasksForMatch(String toMatch) {
        for (int i = 0; i < this.allTasks.size(); i++) {
            Task task = this.allTasks.get(i);
            if (task.canMatch(toMatch)) {
                TaskList.matchingTasks.put(i , task);
            }
        }
    }

    private String printMatchingTasks( String toMatch) {
        String printMatchingTasks;
        if (TaskList.matchingTasks.size() == 0) {
            printMatchingTasks = "There are no tasks that match " + toMatch + "\n";
        } else {
            printMatchingTasks = "Matching tasks: \n";
            for (Task task : TaskList.matchingTasks.values()) {
                printMatchingTasks = printMatchingTasks.concat(task + "\n");
            }
        }
        return Ui.printMessage(printMatchingTasks);
    }

    private String matchTasksWithOffset( String toMatch, int offset) {
       try {
           int offsetLimit = 5;
           int substringEndIndex = toMatch.length() - offset;
           String toMatchOffset = toMatch.substring(0, substringEndIndex);
           if (offset == offsetLimit) {
               return this.printMatchingTasks( toMatch);
           } else {
               checkAllTasksForMatch(toMatchOffset);
               return matchTasksWithOffset( toMatch, offset+1);
           }
       } catch (StringIndexOutOfBoundsException e) {
           return this.printMatchingTasks(toMatch);
       }
    }
}
