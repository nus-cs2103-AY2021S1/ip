import java.util.Scanner;

public class Ui {

    // Attributes
    private final Scanner sc;

    // Constants
    private static final String NAME = "DUKE";
    private static final String INTRODUCTION = String.format(
            "Hello! I'm %s\n" +
                    "I was created for the module CS2103T\n" +
                    "What can I do for you?", NAME);

    // Constructor
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    // Methods
    public String readCommand() {
        return sc.nextLine();
    }

    public boolean hasCommand() {
        return sc.hasNext();
    }

    public void showWelcome() {
        showLine();
        showMessage(INTRODUCTION);
        showLine();
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void showMessage(String message) {
        System.out.println("    " + message.replaceAll("\n", "\n    "));
    }
}
