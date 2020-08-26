import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
    }

    public String readCommand() {
        // scans for the next command (input from user)
        return this.scanner.nextLine();
    }

    public void showDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" +
                task);
    }

    public void showDeletedMessage(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task:\n" +
                task +
                "\nNow you have " + totalTasks + " tasks in the list.");
    }

    public void showAddedMessage(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:\n" +
                task +
                "\nNow you have " + totalTasks + " tasks in the list.");
    }

    public void showListMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showLoadingError() {
        System.out.println("File loading error!");
    }

    public void showUpliftingQuote() {
        System.out.println("Those who are crazy enough to think that they can " +
                "change the world are the ones who usually do. Dream big!");
    }
}
