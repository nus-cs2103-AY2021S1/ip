package duke;

import java.util.Scanner;

public class Ui {
    Scanner scannerObj;

    public Ui () {
        this.scannerObj = new Scanner(System.in);
    }

    private String MSG_DIVIDER = "____________________________________________________________";

    public void showLoadingError() {
        System.out.println("Oops! We couldn't load the file");
    }

    public void showLine() {
        System.out.println(MSG_DIVIDER);
    }

    public void showError(String err) {
        System.out.println("Error: " + err);
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showWelcome() {
        System.out.println("Hello I'm batman");
    }

    public void showFarewell() {
        System.out.println("byeee");
    }

    public String readCommand() {
        String input = scannerObj.nextLine();
        return input;
    }
}