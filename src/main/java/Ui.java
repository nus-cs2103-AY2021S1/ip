import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        showPrompt(LOGO
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
        );
    }

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
        showPrompt(promptText, false);
    }

    public void showError(String errorText) {
        showPrompt(errorText, true);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }
}
