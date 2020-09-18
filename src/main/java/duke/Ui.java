package duke;

import duke.task.TaskList;
import duke.task.Task;

/**
 * Deals with the text output that users will see, that is
 * the character lines corresponding to various <code>Command</code>s.
 *
 * @author Hui Ling
 */
public class Ui {
    /**
     * Sole constructor.
     */
    protected Ui() {}

    /**
     * All character lines except the ones for specific tasks and exceptions.
     */
    private enum Speech {
        GREETING("My name? You don't need to know that. Stop bothering me already... "),
        BYE("Finally... don't come back if you can possibly help it, please."),
        LIST("\nI wouldn't bother doing them if I were you."),
        DONE("Oh goody... You actually accomplished something!!\n  "),
        ADD("You're making me feel tired... But if you insist, I've added this:\n  "),
        DELETE("Oh, getting lazy are we? I approve. I've removed this:\n  "),
        UPDATEDTASK("Donezorimasu. Your task is now:\n  "),
        LOADINGERROR("Something went wrong when loading the data file. "
                + "\nGuess you'll be starting from zero.");

        private final String line;

        private Speech(String line) {
            this.line = line;
        }
    }

    /**
     * Returns welcome character line.
     * Called when Duke app first starts up.
     *
     * @return a String of the welcome message when user starts Duke app
     */
    public static String showWelcome() {
        return Speech.GREETING.line;
    }

    /**
     * Returns goodbye character line when Duke app is closing.
     * Called by a <code>TerminationCommand</code>.
     *
     * @return a String of the goodbye message when user terminates Duke app
     */
    public String showGoodbye() {
        return Speech.BYE.line;
    }

    /**
     * Returns character line for adding tasks with the added task as a <code>String</code>.
     * Called when a new <code>Task</code> is added by a <code>TodoCommand</code>,
     * <code>DeadlineCommand</code> or <code>EventCommand</code>.
     *
     * @param t new task added
     * @return  a <code>String</code> combining the "adding tasks" character line and the new task
     */
    public String showAddTask(Task t) {
        return (Speech.ADD.line + "\n" + t);
    }

    /**
     * Returns character line for completing a task with the done task as a <code>String</code>.
     * Called when a task is marked as done by a <code>DoneCommand</code>.
     *
     * @param t task to be marked as done
     * @return  a String combining the "completed a task" character line and the done task
     */
    public String showDoneTask(Task t) {
        return (Speech.DONE.line + t);
    }

    /**
     * Returns character line for deleting a task with the to-be-deleted task as a <code>String</code>.
     * Called when a task is deleted by a <code>DeleteCommand</code>.
     *
     * @param t task to be deleted
     * @return  a String combining the "deleting a task" character line and the to-be-deleted task
     */
    public String showDeleteTask(Task t) {
        return (Speech.DELETE.line + t);
    }

    /**
     * Returns character line for updating a task with the newly updated task as a <code>String</code>.
     * Called when a task is updated by an <code>UpdateCommand</code>.
     *
     * @param t updated task
     * @return  a String combining the "updating a task" character line and the newly updated task
     */
    public String showUpdateTask(Task t) {
        return (Speech.UPDATEDTASK.line + t);
    }

    /**
     * Returns a line describing the number of tasks left in a <code>TaskList</code>.
     *
     * @param taskList  Duke's current list of tasks
     * @return          a String describing the number of tasks left in <code>taskList</code>
     */
    public String showNumberOfTasksLeft(TaskList taskList) {
        int length = taskList.getSize();
        return ("\n\nYou now have " + length
                + (length == 1 ? " thing" : " things") + " in your list");
    }

    /**
     * Returns character line for when there's a problem
     * loading the data file into a <code>TaskList</code>.
     * Called by <code>Duke</code>'s constructor when an exception is thrown.
     *
     * @return a String of the character line in response to failure initialising Duke
     */
    public String showLoadingError() {
        return Speech.LOADINGERROR.line;
    }

    /**
     * Returns character line for listing all tasks, together with a numbered list
     * of all the tasks in a given <code>TaskList</code>.
     * Called by <code>ListCommand</code>.
     *
     * @param taskList  Duke's current list of tasks
     * @return          a String showing all tasks in the <code>taskList</code> and a character line
     */
    public String showAllTasks(TaskList taskList) {
        String s = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            s += (i + 1 + ". " + taskList.getTask(i) + "\n");
        }
        s += Speech.LIST.line;
        return s;
    }
}
