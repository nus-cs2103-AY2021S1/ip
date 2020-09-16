package duke;

import java.util.Scanner;

/**
 * Deals with interactions with the user by displaying messages to user.
 */
public class Ui {
    private static final String NAME = "CHUBS";
    private static final String LOGO = "              ░                                             ░               "
            + "░░░  \n"
            + "                                                         ████            ░                          \n"
            + "                                                      █▓▒▒▒█                                      \n"
            + "                                             ███████▒█                                    \n"
            + "                                      ██░░░░░░░░██    ██                              \n"
            + "                                  █░░░░░░░░░░░░░█▓▓█                            \n"
            + "                              █░░░░░░░░░░░░░░░ ██▒█                            \n"
            + "                              █░░░░░░░░░░░░░░░░░░█                              \n"
            + "                          █░░░░░░░░░░░░░░░░░░░ █   █                          \n"
            + "                          █░░░░░░░░░░░░░░░░░░░░█▓█                        \n"
            + "                          █░░██░░░░░░░██░░░░░░░█▓█                        \n"
            + "                          █▒▒██░░░░░░░██▒▒▒░░░░██                          \n"
            + "                          █▒▒▒░░█░█░█░░░▒▒▒░░░░█                            \n"
            + "                          █░░░░░░█░█░░░░░░░░░░█▒█                          \n"
            + "                          █░░░░░░░░░░░░░░░░░░░█▓█                          \n"
            + "                             █░░░░░░░░░░░░░░░░░█▓▓█                          \n"
            + "                 ░             ██░░░░░░░░░░░░██    ██                            \n"
            + "                                         ██████████                                      \n"
            + "                                         █░░░░░░░░██                                    \n"
            + "                                         █░░░░░░░░█░█                                  \n"
            + "      ░.                            █░█░░░░░░█░█░█                                \n"
            + "                                  █░░░█░░░░█░░░█░░█                              \n"
            + "                                  █░░▓█░░░ █▓░░░███                                \n"
            + "                                     ███   ███    ████                                      \n"
            + "                                                                                        \n"
            + "                                                                                        \n"
            + "                                                                   ░                                  ";
    private Scanner sc;

    /**
     * Creates an Ui instance.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays welcome messages to user when Duke starts up.
     */
    public String showWelcome() {

        return "Hello I am " + NAME + "\n" + LOGO + "\n" + "Feed me some stuff! :3\n"
                + "If you are unsure where to start, type help to see all the available commands! :D";
    }

    /**
     * Reads user command line input and returns that line as a string.
     *
     * @return String of user input.
     */
    public String readCommand() {

        String command = sc.nextLine();
        return command;

    }

    /**
     * Displays error message to user.
     *
     * @param message Error message to be displayed to user.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays message to user.
     *
     * @param message Message to be displayed to user.
     */
    public void printMessage(String message) {
        System.out.println(String.format(message));
    }

}
