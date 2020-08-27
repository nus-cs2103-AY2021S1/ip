package main.java;

import main.java.Task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private Storage savedStorage;
    private List<Task> allItems;

//    private final String TODO = "todo";
//    private final String DEADLINE = "deadline";
//    private final String EVENT = "event";

    public TaskList() {
        this.savedStorage = new Storage();
        this.allItems = this.savedStorage.getSavedTasks();
    }

    public void addTask(Task toAdd) {
        this.allItems.add(toAdd);
        String printTask = "Alright, its in your list now!\n\t" + toAdd +
                "\nNow you have " + this.allItems.size() + " tasks.\n" + Ui.LINE;
        Ui.relayMessageToUser(printTask);
    }

    public List<Task> getAllTasks() {
        return this.allItems;
    }
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

    public void completeTask(int index) {
        Task toComplete = this.allItems.get(index);
        toComplete.finishTask();
    }

    public void deleteTask(int index) {
            this.allItems.remove(index);
            Ui.printDeleteTaskMessage();
    }

    public void saveIntoHarddisk() {
        this.savedStorage.saveIntoHarddisk();
    }

    public void matchTasks(String toMatch) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task: this.allItems) {
            if (task.canMatch(toMatch)) {
                matchingTasks.add(task);
            }
        }
        String printMatchingTasks;
        if (matchingTasks.size() == 0) {
            printMatchingTasks = "There are no tasks that match " + toMatch + "\n" + Ui.LINE;
        } else {
            printMatchingTasks = "Matching tasks: \n";
            for (Task task: matchingTasks) {
                printMatchingTasks = printMatchingTasks.concat(task + "\n");
            }
            printMatchingTasks = printMatchingTasks + Ui.LINE;
        }
        System.out.println(printMatchingTasks);
    }

}
