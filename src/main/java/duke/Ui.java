package duke;

import java.util.Scanner;

/**
 * The UI helper class.
 */
public class Ui {
    private static final String COLOR_START = "\033[0;31m";
    private static final String COLOR_END = "\033[0m";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String INDENTATION = "    ";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        this.showPrompt(this.getWelcome());
    }

    public String getWelcome() {
        return Ui.LOGO
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n";
    }

    public void showPrompt(String promptText) {
        System.out.println(this.getPrompt(promptText));
    }

    public void showError(String errorText) {
        System.out.println(this.getError(errorText));
    }

    public String getPrompt(String promptText, boolean isError) {
        String[] lines = promptText.split("[\\r\\n]+");
        StringBuilder output = new StringBuilder(isError ? Ui.COLOR_START : "");
        output.append(Ui.INDENTATION).append(Ui.HORIZONTAL_LINE).append('\n');

        for (String line : lines) {
            output.append(Ui.INDENTATION).append(line).append('\n');
        }

        output.append(Ui.INDENTATION).append(Ui.HORIZONTAL_LINE).append(isError ? Ui.COLOR_END : "").append('\n');

        return output.toString();
    }

    public String getPrompt(String promptText) {
        return this.getPrompt(promptText, false);
    }

    public String getError(String promptText) {
        return this.getPrompt(promptText, true);
    }

    /**
     * Reads a command from the command line and returns a String containing the command.
     *
     * @return a String containing the command.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }
}
