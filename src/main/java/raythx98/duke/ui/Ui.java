package raythx98.duke.ui;

import java.util.Scanner;

/**
 * Deals with the user interface.
 */
public class Ui {
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a greeting message.
     */
    public void greet() {
        String greeting = "Hello mah dud, itza handsome robo speakin\n"
                + "What duh hell du yu wan?";
        System.out.println(greeting);
    }

    /**
     * Prints a farewell message.
     */
    public void farewell() {
        String farewell = "Never come back,\n"
                + "dun wanna see yu ever agin";
        System.out.println(farewell);
    }
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a horizontal line.
     */
    public void showLine() {
        System.out.println("________________________________________");
    }
    public void showOnScreen(String string) {
        System.out.println(string);
    }

    /**
     * Prints the error message.
     *
     * @param e String of error to be printed.
     */
    public void showError(String e) {
        System.out.println(e);
    }

    /**
     * Prints the loading error message.
     */
    public void showLoadingError() {
        System.out.println("Error when loading file...");
    }
}
