import java.util.Scanner;

public class Ui {

    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("    ___________________________________________________________");
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println("    ___________________________________________________________");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("    ___________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading file");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
