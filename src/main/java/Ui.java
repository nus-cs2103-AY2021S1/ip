import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    protected TaskList tasks;
    protected String[] command;
    protected Scanner scanner;

    /**
     * Creates a new ui with a task list and initiates the scanner and command.
     * @param tasks the task list to be implemented
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
        scanner = new Scanner(System.in);
        command = new String[2];
    }

    public static String showWelcome() {
        return "Hello! I'm Duke\n" + "What can I do for you?\n"
                + "To know more about Duke, type in 'help'";
    }

    public static String showEnd() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Reads the command from the user.
     * @return a string array command[2]. command[0] represents type and command[1] represents description.
     */
    public String[] read() {
        command[0] = scanner.next();
        if(command[0].equals("list") || command[0].equals("bye")) {
            command[1] = "";
        } else {
            command[1] = scanner.nextLine();
        }
        return command;
    }

    /**
     * Lists out all the tasks in the task list.
     */
    public String list() {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.count(); i++) {
            builder.append(String.format("  %d. ", i + 1) + tasks.get(i).toString() + "\n");
        }
        return builder.toString();
    }

    /**
     * Lists out all the tasks in the matching task list.
     * @param taskList a task list that contains all the matching tasks.
     */
    public String find(TaskList taskList) {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskList.count(); i++) {
            builder.append(String.format("  %d. ", i + 1) + taskList.get(i).toString() + "\n");
        }
        return builder.toString();
    }

    public String printException(DukeException e) {
        return e.getMessage();
    }

    public String printLoadingError() {
        return "Some error occurred when loading.";
    }

    public String printSavingError() {
        return "Some error occurred when saving.";
    }

    public static String printHelpInformation() {
        return  "Instructions in Duke:\n\n"
                + "  1. list -- display all the added tasks\n\n"
                + "  2. todo DESCRIPTION -- add a simple task\n\n"
                + "  3. deadline DESCRIPTION /by DATE(yyyy-mm-dd)\n"
                + "     -- add a task to be done by a date\n\n"
                + "  4. event DESCRIPTION /at DATE(yyyy-mm-dd)\n"
                + "     -- add a task to be done at a date\n\n"
                + "  5. done INDEX -- mark the task at INDEX as done\n\n"
                + "  6. delete INDEX -- delete the task at INDEX\n\n"
                + "  7. find KEYWORD -- display all the tasks that contains the keyword\n\n"
                + "  8. bye -- say bye to Duke and end the app";
    }
}
