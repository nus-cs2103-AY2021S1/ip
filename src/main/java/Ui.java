import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String SEPARATOR = "    ____________________________________"
            + "________________________\n";
    private static final String ERROR_MESSAGE = String.format("     ☹ OOPS!!! "
            + "Something went wrong!\n%s", SEPARATOR);

    public String[] getInput(Scanner sc) {
        System.out.println();
        String[] input = sc.nextLine().trim().split(" ", 2);
        printSeparator();

        return input;
    }

    public void printExit() {
        System.out.printf("     Bye. Hope to see you again soon!\n%s",
                SEPARATOR);
    }

    public void printGreeting() {
        System.out.printf("%s     Hello! I'm Duke\n"
                + "     What can I do for you?\n%s", SEPARATOR, SEPARATOR);
    }

    public void printTaskList(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++)
            System.out.printf("     %d.%s\n", i + 1, tasks.get(i));
        if (tasks.size() == 0)
            System.out.println("     There are no tasks yet!");
        System.out.print(SEPARATOR);
    }

    public void printAddSuccess(Task task, int taskNum) {
        boolean isSingular = taskNum == 1;
        System.out.printf("     Got it. I've added this task:\n"
                        + "       %s\n"
                        + "     Now you have %d %s in the list.\n%s",
                task, taskNum, isSingular ? "task" : "tasks", SEPARATOR);
    }

    public void printDoneSuccess(Task task) {
        System.out.printf("     Nice! I've marked this task as done:\n"
                        + "       %s\n%s", task, SEPARATOR);
    }

    public void printRemoveSuccess(Task removed, int taskNum) {
        boolean isSingular = taskNum == 1;
        System.out.printf("     Noted. I've removed this task:\n       %s\n"
                        + "     Now you have %d %s in the list.\n%s",
                removed, taskNum, isSingular ? "task" : "tasks", SEPARATOR);
    }

    public void printError() {
        System.out.print(ERROR_MESSAGE);
    }

    public void printExceptionMessage(Exception e) {
        System.out.printf("%s\n%s", e.getMessage(), SEPARATOR);
    }

    public void printSeparator() {
        System.out.printf("%s", SEPARATOR);
    }
}
