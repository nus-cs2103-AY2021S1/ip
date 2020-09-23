package chatbot.ui;

import java.util.ArrayList;

import chatbot.data.Task;

/**
 * A class representing the command line level user interface. Responsible for reading commands
 * from the user and displaying data, success/error messages to the user.
 */

public class Ui {

    /**
     * Prints out a welcome message.
     * @return welcome message
     */
    public String getWelcomeMessage() {
        return "Hey there, I'm Fukuyama Masaharu the 24/7 Chatbot. What can I do for you (deep voice).";
    }

    public void exit() {
        System.exit(0);
    }

    /**
     * Prints out a success message for adding a task.
     * @param task task added
     * @param count current number of tasks in the task list
     * @return success message
     */
    public String addSuccess(Task task, int count) {
        return "Got it. I've added this task:\n" + task + "\n"
                + String.format("Now you have %d task(s) in the list.", count);
    }

    /**
     * Prints out a success message for deleting a task.
     * @param task task deleted
     * @param count current number of tasks in the task list
     * @return success message
     */
    public String deleteSuccess(Task task, int count) {
        return "Alright. I've removed this task:\n" + task + "\n"
                + String.format("Now you have %d task(s) in the list.", count);
    }

    /**
     * Prints out a success message for marking a task as done.
     * @param task task marked as done
     * @return success message
     */
    public String markDoneSuccess(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Prints out the given error message.
     * @param message the error message
     * @return error message
     */
    public String errorMessage(String message) {
        return message;
    }

    /**
     * Lists out the given tasks.
     * @param ls list of tasks
     * @return list of tasks
     */
    public String list(ArrayList<Task> ls) {

        if (ls.size() == 0) {
            return "No tasks.";
        }

        int index = 1;
        String response = "";

        for (Task l : ls) {
            response = response.concat(index + ". " + l + "\n");
            index++;
        }

        return response;
    }
}
