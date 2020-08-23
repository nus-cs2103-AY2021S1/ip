package duke;

import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");
    }

    public String readCommand(Scanner sc) {
        return sc.nextLine();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showLine() {
        System.out.println("______________________________");
    }
}