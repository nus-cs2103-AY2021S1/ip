import java.util.Scanner;

public class Ui {
    private Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    private static String horizontalLine = "    ____________________________________________________________\n";

    public String getNextLineInput() {
        return sc.nextLine();
    }

    public void printErrorMessage(String errorMessage) {
        System.out.printf("%s     %s%n%s", horizontalLine, errorMessage, horizontalLine);
    }

    public void printWelcomeMessage() {
        System.out.printf("%s     Hello! I'm Cartona.%n     What can I do for you?%n%s",
                            horizontalLine, horizontalLine);
    }

    public void printTaskAddingMessage(Task task, int taskListSize) {
        System.out.printf("%s     Got it. I've added this task:%n       %s%n     " +
                            "Now you have %d tasks in the list.%n%s",
                                horizontalLine, task,
                                taskListSize, horizontalLine);
    }

    public void printTaskDeletionMessage(Task task, int taskListSize) {
        String deleteMsg = String.format("     Noted. I've removed this task:%n       %s%n", task);
        String countMsg = String.format("     Now you have %d tasks in the list.%n", taskListSize);
        System.out.printf(horizontalLine + deleteMsg + countMsg + horizontalLine);
    }

    public void printTaskDoneMessage(Task task) {
        String completion = "     Nice! I've marked this task as done:\n" +
                String.format("       %s%n", task);
        System.out.printf(horizontalLine + completion + horizontalLine);
    }

    public void printTaskList(TaskList taskList) {
        String toPrint = horizontalLine + "     Here are the tasks in your list:\n";
        toPrint += taskList.toString();
        toPrint += horizontalLine;
        System.out.printf(toPrint);
    }

    public void printExitMessage() {
        String toPrint = horizontalLine + "     List saved! Goodbye!%n" + horizontalLine;
        System.out.printf(toPrint);
    }
}