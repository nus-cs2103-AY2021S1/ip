import java.util.Scanner;

public class Ui {
    protected Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public static final String LOGO =
            " ____        _\n"
                    + " |  _ \\ _   _| | _____\n"
                    + " | | | | | | | |/ / _ \\\n"
                    + " | |_| | |_| |   <  __/\n"
                    + " |____/ \\__,_|_|\\_\\___|\n";

    public static final String LINE = " ____________________________________________________________\n ";

    public void showLine() {
        System.out.print(LINE);
    }

    public void showWelcome() {
        showLine();
        System.out.println(LOGO + " Hello! I'm Duke\n" + " What can I do for you today?");
        showLine();
    }

    public void showBye() {
        System.out.println(" Goodbye! See you again!");
    }

    public void showAdded(Task task, int numTasks) {
        System.out.println("Got it. I've added this task:\n    " +
                task +
                "\n Now you have " + numTasks + " task(s) in the list.");
    }

    public void showDeleted(Task task, int numTasks) {
        System.out.println("Noted. I've removed this task:\n    " +
                task +
                "\n Now you have " + numTasks + " task(s) in the list.");
    }

    public void showMarkedDone(Task task) {
        System.out.println(
                "Nice! I've marked this task as done:\n    " + task);
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showEmptyTaskList() {
        System.out.println("There are currently no tasks in your list.");
    }

    public void showTaskList(String tasksList) {
        System.out.println(("Task(s) in your list:\n" + tasksList));
    }

    public void showEmptyMatchingList() {
        System.out.println("There are no matching tasks found.");
    }

    public void showMatchingTaskList(String matchingTaskList) {
        System.out.println("Here are the matching tasks in your list:\n" + matchingTaskList);
    }
    public String readCommand() {
        return sc.nextLine();
    }
}
