package juke;

import juke.task.Task;

import java.util.ArrayList;

/**
 * The TaskList class holds and manages the current list of Tasks stored
 * by the user.
 */
public class TaskList {
    public static ArrayList<Task> list;

    /**
     * Constructs a TaskList
     *
     * @param loadedTasks
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        this.list = loadedTasks;
    }

    /**
     * Prints a list of current tasks.
     */
    public String printList() {
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= this.list.size(); i++) {
            String toBeAppended = String.format("%s. %s\n", i, this.list.get(i - 1));
            output.append(toBeAppended);
        }
        return output.toString();
    }

    /**
     * Removes a task from the list of current tasks.
     *
     * @param taskNo Index of task to remove.
     */
    public String removeFromList(int taskNo) {
        assert taskNo < list.size() : "Invalid Task index";

        Task removedTask = this.list.remove(taskNo);

        StringBuilder output = new StringBuilder();
        String toBeAppended = String.format("Well, if you insist. I've removed:\n%s", removedTask);
        output.append(toBeAppended);

        return output.toString();
    }

    /**
     * Adds a task to the list of current tasks.
     *
     * @param task Task to be added.
     */
    public String addToList(Task task) {
        this.list.add(task);

        StringBuilder output = new StringBuilder();
        String toBeAppended = String.format("Alright matey, I've added this task:\n%s\nYou have %s tasks in total.",
                task, this.list.size());
        output.append(toBeAppended);

        return output.toString();
    }

    /**
     * Marks a task in list of current tasks as completed.
     *
     * @param taskNo Index of Task to mark.
     */
    public String markTaskAsDone(int taskNo) {
        assert taskNo < list.size() : "Invalid Task index";
       
        Task toBeDone = this.list.get(taskNo);
        toBeDone.markAsDone();
        this.list.set(taskNo, toBeDone);

        StringBuilder output = new StringBuilder();
        String toBeAppended = String.format("Good Job, this task is now done:\n%s", toBeDone);
        output.append(toBeAppended);

        return output.toString();
    }

    /**
     * Finds a task based on given keyword.
     *
     * @param keyword Keyword to use for search.
     * @return List of Matching tasks.
     */
    public String findTasks(String keyword) {
        StringBuilder output = new StringBuilder();
        int count = 0;

        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            String description = task.getDescription();
            boolean thereIsMatch = description.matches(".*\\b" + keyword + "\\b.*");
            boolean isFirstMatch = thereIsMatch && count == 0;

            if (isFirstMatch) {
                output.append("Here are the tasks you're trying to find.\n");
            }

            if (thereIsMatch) {
                count++;
                String toBeAppended = String.format("%s. %s\n", count, task);
                output.append(toBeAppended);
            }
        }

        boolean thereWasNoMatch = count == 0;
        if (thereWasNoMatch) {
            output.append("No match found :(");
        }

        return output.toString();
    }
}
