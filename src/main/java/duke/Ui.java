package duke;

import duke.tasks.Task;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Displays various messages to the user.
 */
public class Ui {
    public final static String MESSAGE_EXIT = "*You take your leave.*";
    public final static String MESSAGE_LIST = "Here's the extent of our list so far:";
    public final static String MESSAGE_DONE = "Right. These task(s) has been marked as done:";
    public final static String MESSAGE_DELETE = "Begone! These task(s) has been removed: ";
    public final static String MESSAGE_ADD = "Fine. I added the following to the list: ";
    public final static String MESSAGE_WELCOME =
            "Famed, of course, for my unique red skin and unparalleled skills as a general of " +
            "the House of War, I, the Red Prince, was raised within the vast palaces of the fabled Forbidden " +
            "City. I was destined to become the next emperor, but my ambitions suffered a bit of a setback " +
            "when I fell from grace for cavorting with demons. Now I'm exiled and hunted by assassins, but I " +
            "assure you: I remain undaunted - and as determined as ever to claim my rightful throne!";
    public final static String MESSAGE_FOUND = "I found these tasks:";

    /**
     * Shows an Exception.
     * @param e the Exception to be shown.
     * @return a String containing the Exception to be shown.
     */
    public static String showException(Exception e) {
        System.out.println(e.getMessage());
        return e.getMessage();
    }

    /**
     * Shows the exit message.
     * @return a String of the exit message.
     */
    public static String showExit() {
        System.out.println(MESSAGE_EXIT);
        return MESSAGE_EXIT;
    }

    /**
     * Shows a {@link TaskList} to the user.
     * @param tasks the TaskList to be shown.
     * @return a String containing the tasks to be shown.
     */
    public static String showList(TaskList tasks) {
        String msg = MESSAGE_LIST + "\n" + tasks;
        System.out.println(msg);
        return msg;
    }

    /**
     * Shows a successful "done" command.
     * @param doneTask the {@link Task} marked as done.
     * @return a String containing the Task marked done.
     */
    public static String showDone(Task doneTask) {
        String msg = MESSAGE_DONE + doneTask;
        System.out.println(msg);
        return msg;
    }

    /**
     * Shows a successful "done" command.
     * @param doneTasks a {@link TaskList} of the Tasks marked as done.
     * @return a String containing the Tasks marked done.
     */
    public static String showDone(TaskList doneTasks) {
        String msg = MESSAGE_DONE + "\n" + doneTasks;
        System.out.println(msg);
        return msg;
    }

    /**
     * Shows a successful "delete" command.
     * @param deletedTask the {@link Task} deleted.
     * @return a String containing the deleted Task.
     */
    public static String showDelete(Task deletedTask) {
        String msg = MESSAGE_DELETE + deletedTask;
        System.out.println(msg);
        return msg;
    }

    /**
     * Shows a successful "delete" command.
     * @param deletedTasks a {@link TaskList} of the deleted Tasks.
     * @return a String containing the deleted Tasks.
     */
    public static String showDelete(TaskList deletedTasks) {
        String msg = MESSAGE_DELETE + "\n" + deletedTasks;
        System.out.println(msg);
        return msg;
    }

    /**
     * Shows a successful command that adds a Task. eg. "todo", "deadline", "event"
     * @param addedTask the {@link Task} created.
     * @return a String containing the added Task.
     */
    public static String showAddTask(Task addedTask) {
        String msg = MESSAGE_ADD + addedTask;
        System.out.println(msg);
        return msg;
    }

    /**
     * Shows the welcome message.
     * @return a String containing the welcome message.
     */
    public static String showWelcome() {
        System.out.println(MESSAGE_WELCOME);
        return MESSAGE_WELCOME;
    }

    /**
     * Shows a {@link TaskList} of found tasks.
     * @param foundTasks a TaskList of tasks found.
     * @return a String containing the found tasks.
     */
    public static String showFound(TaskList foundTasks) {
        String msg = MESSAGE_FOUND + "\n" + foundTasks;
        System.out.println(msg);
        return msg;
    }

}
