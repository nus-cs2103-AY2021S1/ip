import java.util.Scanner;

public class Ui {

    private static final String DASH = "     ____________________________________________________________";
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        print("     *Hello, I am Sparkles*\n\n     How can I help you?");
        showLine();
    }

    public void showLine() {
        print(DASH);
    }

    public void print(String str) {
        System.out.println(str);
    }

    public void printListSize(int i) {
        print("     Now you have " + i + " task(s) in your list.");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        print("     File not loaded");
    }

    public void showError(String message) {
        print(message);
    }


}
