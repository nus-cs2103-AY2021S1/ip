import java.util.Scanner;

public class Ui {

    private Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        showLine();
        System.out.println("        Hello! I'm Duke\n" + "        What can I do for you?");
        showLine();
    }

    public String readCommand() { return scanner.nextLine(); }

    public void showLoadingError() {
        System.out.println("        Failed to load");
    }

    public void showLine() { System.out.println("        ____________________________________________________________"); }

    public void printMessage(String msg) {
        System.out.println("        " + msg);
    }

    public void showError(String errorMsg) { System.out.println("        " + errorMsg); }
}
