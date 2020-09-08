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
     *
     * @return Welcome message.
     */
    public static String greet() {
        String logo = "\n.-. .-')                .-') _  .-. .-')                .-') _\n"
                + "\\  ( OO )              (  OO) ) \\  ( OO )              (  OO) )\n"
                + " ;-----.\\  .-'),-----. /     '._ ;-----.\\  .-'),-----. /     '._\n"
                + " | .-.  | ( OO'  .-.  '|'--...__)| .-.  | ( OO'  .-.  '|'--...__)\n"
                + " | '-' /_)/   |  | |  |'--.  .--'| '-' /_)/   |  | |  |'--.  .--'\n"
                + " | .-. `. \\_) |  | |  |   |  |   | .-. `. \\_) |  | |  |   |  |\n"
                + " | |  \\  |  \\ |  | |  |   |  |   | |  \\  |  \\ |  | |  |   |  |\n"
                + " | '--'  /   `'  '-'  '   |  |   | '--'  /   `'  '-'  '   |  |\n"
                + " `------'      `-----'    `--'   `------'      `-----'    `--'\n";
        return "helluu! I'm\n" + logo + "\nwhat would you like me to do?\n";
    }

    /**
     * Prints the exit message to the user.
     *
     * @return Exit message.
     */
    public static String exit() {
        return "bye! see you soon!";
    }

    /**
     * Prints and returns the status of a command's execution.
     *
     * @param status Status of command execution.
     * @return Status message.
     */
    public String printStatus(String status) {
        System.out.println(status);
        return status;
    }
}
