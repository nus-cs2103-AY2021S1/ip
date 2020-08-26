import java.util.Scanner;


public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void display(String displayMessage) {
        System.out.println(displayMessage);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

}
