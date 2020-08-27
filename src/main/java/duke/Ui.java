package duke;

import java.util.Scanner;

/**
 * The program's UI helper class.
 */
public class Ui {
    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        this.showPrompt(LOGO
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
        );
    }

    /**
     * Shows a given prompt text enclosed in lines.
     *
     * @param promptText Text to be shown.
     * @param isError if the prompt should be shown as an error.
     */
    public void showPrompt(String promptText, boolean isError) {
        String COLOR_START = isError ? "\033[0;31m" : "";
        String COLOR_END = isError ? "\033[0m" : "";
        String HORIZONTAL_LINE = "____________________________________________________________";
        String INDENTATION = "    ";
        String[] lines = promptText.split("[\\r\\n]+");
        StringBuilder output = new StringBuilder(COLOR_START + INDENTATION + HORIZONTAL_LINE + '\n');

        for (String line : lines) {
            output.append(INDENTATION).append(line).append('\n');
        }

        output.append(INDENTATION).append(HORIZONTAL_LINE).append(COLOR_END).append('\n');

        System.out.println(output.toString());
    }

    public void showPrompt(String promptText) {
        this.showPrompt(promptText, false);
    }

    public void showError(String errorText) {
        this.showPrompt(errorText, true);
    }

    /**
     * Reads a command from the command line and returns a String of that command.
     *
     * @return a String of the entered command.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }
}
