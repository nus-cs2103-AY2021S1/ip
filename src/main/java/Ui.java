import java.util.Scanner;

public class Ui {

    private static final String logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String DIVIDER =
            "    ____________________________________________________________\n";

    private final Scanner sc;

    Ui() {
        sc = new Scanner(System.in);
    }

    String readCommand() {
        return sc.nextLine();
    }

    void showDivider() {
        System.out.println(DIVIDER);
    }

    void showError(DukeException e) {
        System.out.println("     " + e.getMessage());
    }

    void showWelcome() {
        System.out.println("Hello from\n" + logo);
        showDivider();
        System.out.println("     Hello! I'm Duke!\n" + "     What can I do for you?\n");
        showDivider();
    }

    void showGoodbye() {
        System.out.println("     Bye. Hope to see you again soon!\n");
    }

    void showTaskList(String taskListString) {
        System.out.println("     Here are the tasks in your list:\n");
        System.out.println(taskListString);
    }

    void showDoneTask(Task task) {
        System.out.println("     Nice! I've marked this task as done:\n       " + task);
    }

    void showDeleteTask(Task task, int listLength) {
        System.out.println("     Noted. I've removed this task:\n       " + task +
                "\n     Now you have " + listLength + " tasks in the list.");
    }

    void showAddedTask(Task task, int listLength) {
        System.out.println("     Got it. I've added this task:\n       " + task +
                "\n     Now you have " + listLength + " tasks in the list.");
    }
}
