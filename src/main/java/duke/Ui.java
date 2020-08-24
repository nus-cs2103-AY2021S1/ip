package duke;

import java.util.Scanner;

public class Ui {

    private final String LINE_SEPARATOR = "***********************";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printMessage(String msg) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(msg);
        System.out.println(LINE_SEPARATOR);
    }

    public void greet() {
        printMessage("Hi! I'm Duke :-) What can I do for you?");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void stopReading() {
        scanner.close();
    }
}
