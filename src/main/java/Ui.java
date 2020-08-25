/**
 * Defines a UI class, that handles any UI in Duke.
 *
 * @author Kai Chao
 * @version 1.0
 * @since 26-08-2020
 */
public class Ui {

    /** Divides each command call. */
    final String LINE_BREAK = "==========================================================" +
            "===============";

    /** Prints the welcome logo and how to start. */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you today? (type: \"help\" to view list of commands)\n" +
                LINE_BREAK);
    }

    public void showLineBreak() {
        System.out.println(LINE_BREAK);
    }

    public void showGoodbye() {
        System.out.println("Duke says: Goodbye and have a nice day! :D");
    }

    /** Prints the available input commands and their respective formatting requirements. */
    public void showHelp() {
        System.out.println("list: displays a sequential view of past inputs\n" +
                "done <task number>: denotes a task as done by checking it\n" +
                "delete <task number>: deletes an existing task\n" +
                "deadline <description> /by <YYYY-MM-DD> <HH:MM>: adds a deadline with " +
                "desired date/time\n" +
                "event <description> /at <YYYY-MM-DD> <HH:MM>: adds an event with " +
                "desired date/time\n" +
                "todo <description>: adds a todo task\n" +
                "bye: terminates program");
    }

    public void showNoPastTasks() {
        System.out.println("Duke says: No past tasks found");
    }

    /**
     * Prints the list of stored tasks in hard drive in sequential order.
     *
     * @param taskList The current tasks in hard drive.
     */
    public void showPastTasks(TaskList taskList) {
        System.out.println("Here are your tasks:");
        for (int i = 1; i <= taskList.noOfTasks(); i++) {
            System.out.println(i + ". " + taskList.getTask(i - 1));
        }
        System.out.println("If you wish to mark a task as completed, input: done <task number>");
    }

    public void showTaskIsDone(Task task) {
        System.out.println("Duke says: Good Job! I've marked this task as done:");
        System.out.println(task);
    }

    public void showInvalidTaskNumber() {
        System.out.println("Duke says: Please try again with a valid task number");
    }

    public void showSuccessfulDelete(Task removedTask, int remaining) {
        System.out.println("Successfully deleted the task!\n" + removedTask);
        showRemainingTasks(remaining);
    }

    public void showRemainingTasks(int remaining) {
        System.out.println("You now have " + remaining + " task(s) in your list");
    }

    public void showTasksAdded(Task task, int remaining) {
        System.out.println("Duke added into your task list:\n" + task);
        showRemainingTasks(remaining);
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }

}
