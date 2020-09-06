package juke;

import juke.task.Task;

import java.util.ArrayList;

/**
 * The juke.TaskList class holds and manages the current list of Tasks stored
 * by the user.
 */
public class TaskList {
    public static ArrayList<Task> list;

    public TaskList(ArrayList<Task> loadedTasks) {
        this.list = loadedTasks;
    }

    /**
     * Prints a list of current tasks.
     */
    public String printList() {
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= this.list.size(); i++) {
            output.append(i).append(". ").append(this.list.get(i - 1)).append("\n");
        }
        return output.toString();
    }

    /**
     * Removes a juke.task from the list of current tasks.
     *
     * @param taskNo Index of juke.task to remove.
     */
    public String removeFromList(int taskNo) {
        StringBuilder output = new StringBuilder();
        Task removedTask = this.list.remove(taskNo);
        output.append("Well, if you insist. I've removed:\n")
                .append(removedTask);
        return output.toString();
    }

    /**
     * Adds a juke.task to the list of current tasks.
     *
     * @param task juke.task.Task to be added.
     */
    public String addToList(Task task) {
        StringBuilder output = new StringBuilder();
        output.append("Alright matey, I've added this juke.task:\n");
        this.list.add(task);
        output.append(task)
                .append("\n")
                .append("Looks like you have ")
                .append(this.list.size())
                .append(" tasks in total.");
        return output.toString();
    }

    /**
     * Marks a juke.task in list of current tasks as completed.
     *
     * @param taskNo Index of juke.task to mark.
     */
    public String makeTaskDone(int taskNo) {
        StringBuilder output = new StringBuilder();
        Task toBeDone = this.list.get(taskNo);
        toBeDone.markAsDone();
        this.list.set(taskNo, toBeDone);
        output.append("Good Job, this juke.task is now done:\n").append(toBeDone);
        return output.toString();
    }

    public String findTasks(String keyword) {
        StringBuilder output = new StringBuilder();
        int count = 0;
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            String description = task.getDescription();
            boolean thereIsMatch = description.matches(".*\\b" + keyword + "\\b.*");
            if (thereIsMatch) {
                if (count == 0) {
                    output.append("Here are the tasks you're trying to find.\n");
                }
                count++;
                output.append(count)
                        .append(". ")
                        .append(task)
                        .append("\n");
            }
        }

        if (count == 0) {
            output.append("No match found :(");
        }

        return output.toString();
    }
}
