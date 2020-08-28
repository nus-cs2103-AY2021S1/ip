import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void showWelcomeMsg() {
        System.out.println("____________________________________________________________");
        System.out.println("Eh what's up! I'm Meimei" +
                "\nWhat you want ah?");
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
