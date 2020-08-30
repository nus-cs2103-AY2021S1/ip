package duke;

import java.util.Scanner;
import duke.task.Task;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {

    private static final String LINE = "    ____________________________________________________________\n";

    private Scanner sc;

    /** Create and initiate an Ui object. */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /** Greet the user. */
    public void greet() {
        String greet = LINE + "    Hello! I'm Duke\n" + "    What can I do for you?\n" + LINE;
        System.out.println(greet);
    }

    /** Say bye to the user. */
    public void bye() {
        String exit = LINE + "     Bye. Hope to see you again soon!\n" + LINE;
        System.out.println(exit);
    }

    /**
     * Inform user that the task is marked as done.
     * @param task The task that has being marked as done.
     */
    public void done(Task task) {
        System.out.println(LINE
                + "    Nice! I've marked this task as done:" + "\n"
                + "    " + task.toString() + "\n"
                + LINE);
    }

    /**
     * Returns the input given by user.
     * @return The input of user.
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Display the error message.
     * @param message The message ti be displayed.
     */
    public void showError(String message) {
        System.out.println(LINE + "    " + message + "\n" + LINE);
    }

    /**
     * Inform the user that the task is deleted.
     * @param task The task that has being deleted.
     * @param taskList The list of task remaining.
     */
    public void deleteTask(Task task, TaskList taskList) {
        System.out.println(LINE
                + "    Noted. I've removed this task:" + "\n"
                + "      " + task.toString() + "\n"
                + String.format("    Now you have %d tasks in the list.\n", taskList.size())
                + LINE);
    }

    /**
     * Inform the user that the task is added to the list.
     * @param task The task that is added.
     * @param taskList The list containing all tasks.
     */
    public void addTask(Task task, TaskList taskList) {
        System.out.println(LINE
                + "    Got it! I have added this task to the list!" + "\n"
                + "      " + task + "\n"
                + String.format("    Now you have %d tasks in the list.", taskList.size()) + "\n"
                + LINE);
    }

    /**
     * Prints the text given.
     * @param text Text to be printed.
     */
    public void print(String text) {
        System.out.println(LINE + text + LINE);
    }
}
