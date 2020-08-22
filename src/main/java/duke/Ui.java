package duke;

import java.util.Scanner;

public class Ui {
    Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    public void print(String message) {
        String horizontalLine = "____________________________________________________________";
        System.out.printf("%s\n%s\n%s\n", horizontalLine, message, horizontalLine);
    }

    public void showLoadingError() {
        this.print("Something went wrong when loading previously saved tasks! Starting with an " +
                "empty tasks list instead...");
    }

    public void showError(String message) {
        this.print(message);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }
}
