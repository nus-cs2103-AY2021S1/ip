import java.util.Scanner;

public class Ui {
    private Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    private static String HORIZONTAL_LINE = "    ____________________________________________________________\n";

    public String getNextLineInput() {
        return sc.nextLine();
    }

    public void printErrorMessage(String errorMessage) {
        System.out.printf("%s     %s%n%s", HORIZONTAL_LINE, errorMessage, HORIZONTAL_LINE);
    }

    public void printWelcomeMessage() {
        System.out.printf("%s     Hello! I'm Cartona.%n     What can I do for you?%n%s",
                HORIZONTAL_LINE, HORIZONTAL_LINE);
    }

    public void printTaskAddingMessage(Task task, int taskListSize) {
        System.out.printf("%s     Got it. I've added this task:%n       %s%n     "
                        + "Now you have %d tasks in the list.%n%s",
                HORIZONTAL_LINE, task,
                                taskListSize, HORIZONTAL_LINE);
    }

    public void printTaskDeletionMessage(Task task, int taskListSize) {
        String deleteMsg = String.format("     Noted. I've removed this task:%n       %s%n", task);
        String countMsg = String.format("     Now you have %d tasks in the list.%n", taskListSize);
        System.out.printf(HORIZONTAL_LINE + deleteMsg + countMsg + HORIZONTAL_LINE);
    }

    public void printTaskDoneMessage(Task task) {
        String completion = "     Nice! I've marked this task as done:\n"
                                + String.format("       %s%n", task);
        System.out.printf(HORIZONTAL_LINE + completion + HORIZONTAL_LINE);
    }

    public void printTaskList(TaskList taskList) {
        String toPrint = HORIZONTAL_LINE + "     Here are the tasks in your list:\n";
        toPrint += taskList.toString();
        toPrint += HORIZONTAL_LINE;
        System.out.printf(toPrint);
    }

    public void printExitMessage() {
        String toPrint = HORIZONTAL_LINE + "     List saved! Goodbye!%n" + HORIZONTAL_LINE;
        System.out.printf(toPrint);
    }
}