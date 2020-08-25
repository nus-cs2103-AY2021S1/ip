package duke;

import java.util.Scanner;

public class Ui {
    private static final String horizontalLine = "    ____________________________________________________________";
    private static final String indentation = "     ";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }


    public String readInput() {
        String input = scanner.nextLine();
        return input;
    }

    public void greet() {
        System.out.println(horizontalLine);
        System.out.println(indentation + "Hello! I'm Duke");
        System.out.println(indentation + "What can I do for you?");
        System.out.println(horizontalLine);
    }

    public void exit() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(horizontalLine);
        System.out.println(indentation + bye);
        System.out.println(horizontalLine);
        scanner.close();
    }


    public void printErrorMessage(String message) {
        System.out.println(indentation + "☹ OOPS!!! " + message);
        System.out.println(horizontalLine);
    }

    public void printHorizontalLine() {
        System.out.println(horizontalLine);
    }

}
