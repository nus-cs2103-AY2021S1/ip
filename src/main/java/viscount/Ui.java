package viscount;

import viscount.task.Task;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

/**
 * Represents Viscount's User Interface.
 * 
 * Handles interactions with the user.
 */
public class Ui {
    private static final String VISCOUNT_LOGO =
            "        _  _____  _____                  _    \n" + 
                    "       (_)/ ____|/ ____|                | |   \n" +
                    " __   ___| (___ | |     ___  _   _ _ __ | |_  \n" +
                    " \\ \\ / / |\\___ \\| |    / _ \\| | | | '_ \\| __| \n" +
                    "  \\ V /| |____) | |___| (_) | |_| | | | | |_  \n" +
                    "   \\_/ |_|_____/ \\_____\\___/ \\__,_|_| |_|\\__|";
    private static final String HORIZONTAL_LINE = "__________________________________________________";
    
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
    public void showWelcome() {
        System.out.println(Ui.VISCOUNT_LOGO);
        speak("Good day to you! I'm Viscount.\nWhat can I do for you on this blessed day?");
    }

    /**
     * Prints the exit message.
     */
    public void showExit() {
        speak("Farewell my friend, I hope to see you again!");
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
    public void showList(List<Task> tasks, String modifier, String dateString) {
        String finalDateString = dateString.isEmpty()
                ? dateString
                : ("occurring " + (dateString.equals("today")
                        ? dateString
                        : "on " + dateString) + " ");
                
        
        speak(String.format("Here are the %ss %sin your list:\n%s",
                modifier.isEmpty() ? "task" : modifier,
                finalDateString,
                convertTaskListToString(tasks)));
    }

    /**
     * Prints the add task response.
     * 
     * @param task Task added.
     * @param tasksSize Size of task list after adding new task.
     */
    public void showAdd(Task task, int tasksSize) {
        speak(String.format("Very well. I've added this %s:\n%s\nNow you have %d tasks in the list.",
                task.getTaskType().name().toLowerCase(),
                task.toString(),
                tasksSize));
    }

    /**
     * Prints the done response.
     * 
     * @param task Task marked as done.
     */
    public void showDone(Task task) {
        speak(String.format("Very good! I have marked this %s as done:\n%s", 
                task.getTaskType().name().toLowerCase(),
                task.toString()));
    }

    /**
     * Prints the delete response.
     * 
     * @param task Task deleted.
     * @param tasksSize Size of task list after deleting new task.
     */
    public void showDelete(Task task, int tasksSize) {
        speak(String.format("Very well. I've removed this %s:\n%s\nNow you have %d tasks in the list.",
                task.getTaskType().name().toLowerCase(),
                task.toString(),
                tasksSize));
    }

    /**
     * Prints error message.
     * 
     * @param errorMessage Error message printed.
     */
    public void showError(String errorMessage) {
        speak(errorMessage);
    }

    /**
     * Prints the message into the output in the chatbot format.
     *
     * @param message Message to be printed.
     */
    private void speak(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
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
