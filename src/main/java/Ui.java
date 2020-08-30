import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printWelcomeMessage() {
        System.out.println("Hello! I'm Duke baby :)\n" + "What can I do for you?");
    }

    public String readNextLine() {
        return scanner.nextLine();
    }

    public void printDoneMessage(Task doneTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + doneTask);
    }

    public void printAddMessage(Task newTask, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + newTask);
        System.out.println("Now you have " + String.valueOf(taskList.size()) + " tasks in your list.");
    }

    public void printDeleteMessage(Task deletedTask, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + deletedTask);
        System.out.println("Now you have " + String.valueOf(taskList.size()) + " tasks in the list.");
    }

    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("Unable to load file. New file is created.");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
