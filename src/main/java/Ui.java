import java.util.Scanner;

/**
 * deals with interactions with the user.
 */
public class Ui {

    public static String LINE = "===================================================";
    protected TaskList tasks;
    protected String[] command;
    protected Scanner scanner;

    /**
     * creates a new ui with a task list and initiates the scanner and command.
     * @param tasks the task list to be implemented
     */
    public Ui(TaskList tasks) {
        this.tasks = tasks;
        scanner = new Scanner(System.in);
        command = new String[2];
    }

    public void showWelcome() {
        printPart("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public void showEnd() {
        printPart("Bye. Hope to see you again soon!");
    }

    /**
     * reads the command from the user.
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
     * lists out all the tasks in the task list.
     */
    public void list() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.count(); i++) {
            System.out.println(String.format("  %d. ", i + 1) + tasks.get(i).toString());
        }
        System.out.println(LINE + "\n");
    }

    /**
     * lists out all the tasks in the matching task list.
     * @param taskList a task list that contains all the matching tasks.
     */
    public void find(TaskList taskList) {
        System.out.println(LINE);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.count(); i++) {
            System.out.println(String.format("  %d. ", i + 1) + taskList.get(i).toString());
        }
        System.out.println(LINE + "\n");
    }

    public void printException(DukeException e) {
        printPart(e.getMessage());
    }

    public void printPart(String str) {
        System.out.println(LINE);
        System.out.println(str);
        System.out.println(LINE + "\n");
    }

    public void printLoadingError() {
        System.out.println("Some error occurred when loading.");
    }

    public void printSavingError() {
        System.out.println("Some error occurred when saving.");
    }
}
