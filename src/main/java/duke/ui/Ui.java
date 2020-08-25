package duke.ui;

import java.awt.desktop.SystemEventListener;
import java.util.Scanner;

public class Ui {


    public void showLoadingError() {
        System.out.println("Something went wrong");

    }

    public void showWelcome() {
        // print welcome message
        System.out.println("    _________________________________\n"
                + "    Hello! I'm Duke\n"
                + "    What can I do for you?\n"
                + "    _________________________________");
    }

    public void showLine() {
        System.out.println("    _________________________________");
    }

    public String readCommand() {
        //String result = "";
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
