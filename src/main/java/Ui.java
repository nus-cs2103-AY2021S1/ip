import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Error: File cannot be loaded");
    }

    public void showLine() {
        System.out.println("__________________________________________");
    }

    public void closeScanner() {
        sc.close();
    }
}