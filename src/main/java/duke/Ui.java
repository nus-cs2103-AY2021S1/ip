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
    public final static String MESSAGE_DONE = "Right. This task is now marked as done:";
    public final static String MESSAGE_DELETE = "Begone! This task is now removed: ";
    public final static String MESSAGE_ADD = "Fine. I added the following to the list: ";
    public final static String MESSAGE_WELCOME = "Famed, of course, for my unique red skin and unparalleled skills as a general of " +
            "the House of War, I, the Red Prince, was raised within the vast palaces of the fabled Forbidden " +
            "City. I was destined to become the next emperor, but my ambitions suffered a bit of a setback " +
            "when I fell from grace for cavorting with demons. Now I'm exiled and hunted by assassins, but I " +
            "assure you: I remain undaunted - and as determined as ever to claim my rightful throne!";
    public final static String MESSAGE_FOUND = "I found these tasks:";

    /**
     * Shows an Exception.
     * @param e the Exception to be shown.
     */
    public static String showException(Exception e) {
        System.out.println(e);
        return e.toString();
    }

    /**
     * Shows the exit message.
     */
    public static String showExit() {
        System.out.println(MESSAGE_EXIT);
        return MESSAGE_EXIT;
    }

    /**
     * Shows a {@link TaskList} to the user.
     * @param tasks the TaskList to be shown.
     */
    public static String showList(TaskList tasks) {
        String msg = MESSAGE_LIST + "\n" + tasks;
        System.out.println(msg);
        return msg;
    }

    /**
     * Shows a successful "done" command.
     * @param doneTask the {@link Task} marked as done.
     */
    public static String showDone(Task doneTask) {
        String msg = MESSAGE_DONE + doneTask;
        System.out.println(msg);
        return msg;
    }

    /**
     * Shows a successful "delete" command.
     * @param deletedTask the {@link Task} deleted.
     */
    public static String showDelete(Task deletedTask) {
        String msg = MESSAGE_DELETE + deletedTask;
        System.out.println(msg);
        return msg;
    }

    /**
     * Shows a successful command that adds a Task. eg. "todo", "deadline", "event"
     * @param addedTask the {@link Task} created.
     */
    public static String showAddTask(Task addedTask) {
        String msg = MESSAGE_ADD + addedTask;
        System.out.println(msg);
        return msg;
    }

    /**
     * Shows the welcome message.
     */
    public static String showWelcome() {
        System.out.println(MESSAGE_WELCOME);
        return MESSAGE_WELCOME;
    }

    /**
     * Shows a {@link TaskList} of found tasks.
     * @param foundTasks a TaskList of tasks found.
     */
    public static String showFound(TaskList foundTasks) {
        String msg = MESSAGE_FOUND + "\n" + foundTasks;
        System.out.println(msg);
        return msg;
    }

}
