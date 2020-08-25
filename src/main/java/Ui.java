import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        String name = "Omega";
        printHorizontalLine();
        System.out.println("Hi! I am " + name + ", your personal assistant.");
        System.out.println("How may I help you today?");
        printHorizontalLine();
    }

    public void showGoodbye() {
        System.out.println("Goodbye! Shutting down now...");
    }

    public void showLoadingError() {
        printHorizontalLine();
        System.out.println("Sorry, there is an error loading the data");
        printHorizontalLine();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showAllTasks(List<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        showTasks(taskList);
    }

    public void showTasksOnDate(List<Task> taskList, LocalDate date) {
        System.out.println(String.format(
                "Here are the tasks in your list on %s:",
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
        showTasks(taskList);
    }

    public void showTasks(List<Task> taskList) {
        int idx = 1;
        for (Task task : taskList) {
            System.out.println(String.format("%d.%s", idx, task.toString()));
            idx += 1;
        }
    }

    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        printWithIndent(task.toString());
    }

    public void showTaskDeleted(Task task, int numTasksLeft) {
        System.out.println("Noted. I've removed this task:");
        printWithIndent(task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", numTasksLeft));
    }

    public void showTaskAdded(Task task, int numTasksLeft) {
        System.out.println("Got it. I've added this task:");
        printWithIndent(task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", numTasksLeft));
    }

    public void showBlankLine() {
        System.out.println();
    }

    public void showLine() {
        this.printHorizontalLine();
    }

    private void printHorizontalLine() {
        String horizontalLine = "---------------------------------------------";
        System.out.println(horizontalLine);
    }

    private void printWithIndent(String str) {
        System.out.println("  " + str);
    }
}
