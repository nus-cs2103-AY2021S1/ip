package botbot;

import java.util.Scanner;

/**
 * Represents the UI of the chatbot.
 */
public class Ui {
    private Scanner sc;

    /**
     * Creates the UI.
     */
    Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the welcome message to the user.
     */
    void greet() {
        String logo = "\n.-. .-')                .-') _  .-. .-')                .-') _\n"
                + "\\  ( OO )              (  OO) ) \\  ( OO )              (  OO) )\n"
                + " ;-----.\\  .-'),-----. /     '._ ;-----.\\  .-'),-----. /     '._\n"
                + " | .-.  | ( OO'  .-.  '|'--...__)| .-.  | ( OO'  .-.  '|'--...__)\n"
                + " | '-' /_)/   |  | |  |'--.  .--'| '-' /_)/   |  | |  |'--.  .--'\n"
                + " | .-. `. \\_) |  | |  |   |  |   | .-. `. \\_) |  | |  |   |  |\n"
                + " | |  \\  |  \\ |  | |  |   |  |   | |  \\  |  \\ |  | |  |   |  |\n"
                + " | '--'  /   `'  '-'  '   |  |   | '--'  /   `'  '-'  '   |  |\n"
                + " `------'      `-----'    `--'   `------'      `-----'    `--'\n";
        System.out.println("helluu! I'm\n" + logo + "\nwhat would you like me to do?\n");
    }

    /**
     * Prints the exit message to the user.
     */
    public void exit() {
        System.out.println("    bye! see you soon!");
    }

    /**
     * Reads the next command from the user.
     * 
     * @return Input by user.
     */
    String getUserInput() {
        return sc.nextLine();
    }

    /**
     * Prints the status of a command's execution.
     * 
     * @param status Status of command execution.
     */
    public void printStatus(String status) {
        System.out.println(status);
    }
}
