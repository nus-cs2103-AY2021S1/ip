package duke;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Ui {
    private PrintStream out;
    
    public Ui() {
        try {
            out = new PrintStream(System.out, true, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            out = new PrintStream(System.out, true);
        }
    }

    /**
     * Renders the welcome message onto the console.
     * To be called on programme startup.
     */
    public void showWelcomeScreen() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        out.println("Hello, my name is\n" + logo);
        out.println("What can I do for you?");
    }

    /**
     * Renders the goodbye message onto the console.
     * To be called on programme exit.
     */
    public void showGoodbyeScreen() {
        out.println("See you space cowboy!");
    }

    /**
     * Prints the error message from an exception.
     * @param e the exception that contains the error message to be printed.
     */
    public void printExceptionMessage(Exception e) {
        out.println(e);
    }

    /**
     * Renders a confirmation message to the console to notify the user of the successful 
     * addition of a Task to the TaskList, and displays all lists in the TaskList.
     * @param task The Task that has been added to the task list.
     * @param taskList The TaskList which the task has been added to.
     */
    public void printAddTaskConfirmation(Task task, TaskList taskList) {
        out.println("Got it. I've added this task: ");
        out.println(task);
        out.println("Now you have " + taskList.numTasks() + " tasks in the list.");
    }

    /**
     * Renders a confirmation message to the console to notify the user of the successful 
     * addition of a Task to the TaskList, and displays all lists in the TaskList.
     * @param task The Task that has been removed from the task list.
     * @param taskList The TaskList which the task has been removed from.
     */
    public void printRemoveTaskConfirmation(Task task, TaskList taskList) {
        out.println("Noted. I've removed this task: ");
        out.println(task);
        out.println("Now you have " + taskList.numTasks() + " tasks in the list.");
    }

    /**
     * Renders a confirmation message to the console to notify the user that the specified 
     * task has been successfully marked as complete.
     * @param task The Task that has been marked as complete.
     */
    public void printMarkTaskCompleteConfirmation(Task task) {
        out.println("Nice! I've marked this task as done: ");
        out.println(task);
    }

    /**
     * Renders the all tasks from the TaskList onto the console.
     * @param taskList The TaskList which contains all the tasks to be displayed on the console.
     */
    public void printAllTasks(TaskList taskList) {
        out.println(taskList.getAllTasksAsString());
    }
}
