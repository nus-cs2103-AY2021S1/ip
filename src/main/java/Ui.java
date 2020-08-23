import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    Ui() {
       this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello from Duke\nHow may I be of service " +
                "to you this fine day sire?");
    }

    public void showLine() {
        System.out.println("_____________________________________");
    }

    public void showLoadingError(String errorMessage) {
        System.out.println("I am unable to load the saved tasks " + errorMessage);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void print(String message) {
        System.out.println(message);
    }
}
