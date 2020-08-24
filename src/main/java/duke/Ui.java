package duke;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }
    public void showWelcome() {
        String logo = " ______  ___       __         __        _____\n"
                + "   |    /         /  \\       /  \\     /\n"
                + "   |    \\___     /____\\     /____\\   |\n"
                + "   |        \\   /      \\   /      \\   \\\n"
                + " ------  ___/  /        \\ /        \\    -----\n";
        System.out.println("Hello! I'm\n" + logo + "\nWhat can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("Created a new directory 'data' and new text file 'database.txt' for you!");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

}
