import java.util.Scanner;

public class Ui {

    public static String LINE = "===================================================";
    protected TaskList tasks;
    protected String[] command;
    protected Scanner scanner;

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

    public String[] read() {
        command[0] = scanner.next();
        if(command[0].equals("list") || command[0].equals("bye")) {
            command[1] = "";
        } else {
            command[1] = scanner.nextLine();
        }
        return command;
    }

    public void list() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.count(); i++) {
            System.out.println(String.format("  %d. ", i + 1) + tasks.get(i).toString());
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
