import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        String temp = null;
        if (sc.hasNextLine()) {
            temp = sc.nextLine();
        }
        return temp;
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showLine() {
        String line = "--------------------------------------";
        System.out.println(line);
    }

    public void showLoadingError() {
        String error = "error loading Duke, Duke is terminated";
        System.out.println(error);
    }

    public void showWelcome() {
        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greeting);
    }
}