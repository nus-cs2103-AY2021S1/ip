package duke;

import java.util.Scanner;

/**
 * deals with interactions with the user
 */

public class Ui {
    public String showWelcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("hello! i'm mochi :-)\n")
                .append("what can i do for you today?\n");
        return sb.toString();
    }

    public void showLoadingError() {
        System.out.println("There was an error loading the file");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public String exit() {
        return "byebye! hope to see you again soon :-)";
    }

    public void showLine() {
        System.out.println("--------------------------------------------------------------");
    }
}
