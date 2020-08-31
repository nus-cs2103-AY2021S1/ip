package viscount;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import viscount.task.Task;

/**
 * Represents Viscount's User Interface.
 * 
 * Handles interactions with the user.
 */
public class Ui {
    private Scanner scanner;
    
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    
    public Ui(InputStream is) {
        this.scanner = new Scanner(is);
    }

    /**
     * Prints the logo and welcome message.
     */
    public String getWelcomeMessage() {
        return "Good day to you! I'm Viscount.\nWhat can I do for you on this blessed day?";
    }

    /**
     * Reads the next input from the user.
     * 
     * @return Input of user as a String.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Prints the list response.
     * 
     * @param tasks List of tasks listed
     * @param modifier Modifier of list command
     * @param dateString Date argument of list command
     */
    public String getListResponse(List<Task> tasks, String modifier, String dateString) {
        String finalDateString = dateString.isEmpty()
                ? dateString
                : ("occurring " + (dateString.equals("today")
                        ? dateString
                        : "on " + dateString) + " ");
                
        
        return String.format("Here are the %ss %sin your list:\n%s",
                modifier.isEmpty() ? "task" : modifier,
                finalDateString,
                convertTaskListToString(tasks));
    }

    /**
     * Prints the add task response.
     * 
     * @param task Task added.
     * @param tasksSize Size of task list after adding new task.
     */
    public String getAddResponse(Task task, int tasksSize) {
        return String.format("Very well. I've added this %s:\n%s\nNow you have %d tasks in the list.",
                task.getTaskType().name().toLowerCase(),
                task.toString(),
                tasksSize);
    }

    /**
     * Prints the done response.
     * 
     * @param task Task marked as done.
     */
    public String getDoneResponse(Task task) {
        return String.format("Very good! I have marked this %s as done:\n%s", 
                task.getTaskType().name().toLowerCase(),
                task.toString());
    }

    /**
     * Prints the delete response.
     * 
     * @param task Task deleted.
     * @param tasksSize Size of task list after deleting new task.
     */
    public String getDeleteResponse(Task task, int tasksSize) {
        return String.format("Very well. I've removed this %s:\n%s\nNow you have %d tasks in the list.",
                task.getTaskType().name().toLowerCase(),
                task.toString(),
                tasksSize);
    }

    /**
     * Converts task list to String format.
     * 
     * @param tasks Task list to be converted.
     * @return String representation of the task list.
     */
    private String convertTaskListToString(List<Task> tasks) {
        String result = "";

        for (int i = 0; i < tasks.size(); i++) {
            result += (i == tasks.size() - 1)
                    ? String.format("%d.%s", i + 1, tasks.get(i).toString())
                    : String.format("%d.%s\n", i + 1, tasks.get(i).toString());
        }

        return result;
    }
}
