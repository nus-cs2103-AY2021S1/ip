/**
 * A class representing the command line level user interface. Responsible for reading commands
 * from the user and displaying data, success/error messages to the user.
 */

package main.java.ui;

import main.java.data.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Ui {

    Scanner sc;

    /**
     * Prints out a welcome message.
     */
    public String greet() {
        return "Hey there, I'm Fukuyama Masaharu the 24/7 Chatbot. What can I do for you (deep voice).";
    }

    /**
     * Prints out a success message for adding a task.
     * @param task task added
     * @param count current number of tasks in the task list
     */
    public String addSuccess(Task task, int count) {
        return "Got it. I've added this task:\n" + task + "\n" +
                String.format("Now you have %d task(s) in the list.", count);
    }

    /**
     * Prints out a success message for deleting a task.
     * @param task task deleted
     * @param count current number of tasks in the task list
     */
    public String deleteSuccess(Task task, int count) {
        return "Alright. I've removed this task:\n" + task + "\n" +
                String.format("Now you have %d task(s) in the list.", count);
    }

    /**
     * Prints out a success message for marking a task as done.
     * @param task task marked as done
     */
    public String markDoneSuccess(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Prints out the given error message.
     * @param message the error message
     */
    public String errorMessage(String message) {
        return message;
    }

    /**
     * List out the given tasks.
     * @param ls list of tasks
     */
    public String list(ArrayList<Task> ls) {

        int index = 1;
        String response = "";

        Iterator<Task> iter = ls.iterator();

        while (iter.hasNext()) {
            response += index + ". " + iter.next() + "\n";
            index++;
        }

        return response;
    }
}
