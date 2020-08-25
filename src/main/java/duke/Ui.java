package duke;

import java.util.Scanner;

/**
 * Deals with reading the input and printing the output
 */
public class Ui {
    Scanner sc;

    /**
     * Constructor
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Takes in the next input line
     * @return
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the divider line that output is nested in
     */
    public void printDivider() {
        System.out.println("\t______________________________________________\n");
    }

    /**
     * Prints divider line for errors
     */
    public void printErrorDivider() {
        System.out.println("\t!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
    }

    /**
     * Prints welcome message when application starts up
     */
    public void printWelcome() {
        printDivider();
        String MrCamel =
                "\t                  ,,__\n"
                        + "\t        ..  ..   / o._)\n"
                        + "\t       /--'/--\\  \\-'|| \n"
                        + "\t      /        \\_/ / |\n"
                        + "\t    .'\\  \\__\\  __.'.'\n"
                        + "\t      )\\ |  )\\ |\n"
                        + "\t     // \\\\ // \\\\\n"
                        + "\t    ||_  \\\\|_  \\\\_\n"
                        + "\t    '--' '--'' '--'\n";
        System.out.println("\tMr Camel says hello. What can Mr Camel do for you today?\n" + MrCamel);
        printDivider();
    }

    /**
     * Prints goodbye message
     */
    public void printGoodbye() {
        printDivider();
        System.out.println("\tBye. Mr Camel will miss you! :(");
        printDivider();
    }

    /**
     * Prints error message
     * @param e
     */
    public void printError(DukeException e) {
        printErrorDivider();
        System.out.println("\t" + e.getMessage());
        printErrorDivider();
    }

    /**
     * Prints a message passed as argument
     * @param msg
     */
    public void printMsg(String msg) {
        System.out.println("\t" + msg);
    }
}
