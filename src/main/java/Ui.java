import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public static void printMessage(String s) {
        String output = String.format("____________________________________________________________\n%s\n"
                + "____________________________________________________________\n",s);
        System.out.println(output);
    }

    public static void printException(Exception e) {
        String errorMessage = "☹ OOPS!!! " + e.getMessage();
        printMessage(errorMessage);
    }

    public void showWelcomeMessage() {
        String greetings = "Hello! I'm Duke, your personal assistant.\nWhat can I do for you?";
        printMessage(greetings);
    }

    public void showStartFailedMessage() {
        String errorMessage = "An error occurred in during initialisation :(";
        printMessage(errorMessage);
    }

    public void showGoodbyeMessage() {
        String byeMessage = "Bye. Take care!!";
        printMessage(byeMessage);
    }

    public String getUserInput() {
        return sc.nextLine();
    }
}
