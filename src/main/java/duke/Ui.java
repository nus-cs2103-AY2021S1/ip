package duke;

import duke.tasks.Task;

/**
 * Displays various messages to the user.
 */
public class Ui {
    /**
     * Shows an Exception.
     * @param e the Exception to be shown.
     */
    public static void showException(Exception e) {
        System.out.println(e);
    }

    /**
     * Shows the exit message.
     */
    public static void showExit() {
        System.out.println("*You take your leave.*");
    }

    /**
     * Shows a {@link TaskList} to the user.
     * @param tasks the TaskList to be shown.
     */
    public static void showList(TaskList tasks) {
        System.out.println("Here's the extent of our list so far:");
        System.out.println(tasks);
    }

    /**
     * Shows a successful "done" command.
     * @param doneTask the {@link Task} marked as done.
     */
    public static void showDone(Task doneTask) {
        System.out.println("Right. This task is now marked as done:" + doneTask);
    }

    /**
     * Shows a successful "delete" command.
     * @param deletedTask the {@link Task} deleted.
     */
    public static void showDelete(Task deletedTask) {
        System.out.println("Begone! This task is now removed: " + deletedTask);
    }

    /**
     * Shows a successful command that adds a Task. eg. "todo", "deadline", "event"
     * @param addedTask the {@link Task} created.
     */
    public static void showAddTask(Task addedTask) {
        System.out.println("Fine. I added the following to the list: " + addedTask);
    }

    /**
     * Shows the welcome message.
     */
    public static void showWelcome() {
        System.out.println("Famed, of course, for my unique red skin and unparalleled skills as a general of " +
                "the House of War, I, the Red Prince, was raised within the vast palaces of the fabled Forbidden " +
                "City. I was destined to become the next emperor, but my ambitions suffered a bit of a setback " +
                "when I fell from grace for cavorting with demons. Now I'm exiled and hunted by assassins, but I " +
                "assure you: I remain undaunted - and as determined as ever to claim my rightful throne!");
    }
}
