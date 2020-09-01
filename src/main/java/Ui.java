import java.time.LocalDateTime;
import java.util.Scanner;

public class Ui {

    private static final String LINE = "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";
    private static final Scanner INPUT = new Scanner(System.in);
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Takes in inputs, and passes them to the Parser to perform actions.
     */
    public static void processInput(TaskList taskList) {

        printWithLines("Hello! My name is Duketh Puketh III, but you can call me\n" + LOGO
                + "\n How may I help you today? :)\n The date and time is now "
                + LocalDateTime.now().format(Duke.dateTimeFormat) + "\n");

        String nextInput = INPUT.nextLine();
        String[] inputParts = nextInput.split(" ", 2);
        String inputPrefix = inputParts[0];
        String inputSuffix = inputParts.length == 1 ? "" : inputParts[1];

        while (!nextInput.equals("bye")) {

            try {
                Parser.handleInput(inputPrefix, inputSuffix, taskList);
            } catch (DukeException dukeException) {
                printWithLines(dukeException + "\n");
            }


            nextInput = INPUT.nextLine();
            inputParts = nextInput.split(" ", 2);
            inputPrefix = inputParts[0];
            inputSuffix = inputParts.length == 1 ? "" : inputParts[1];

        }

        printWithLines("Bye! I'll see you again next time!\n");

    }

    /**
     * Prints the desired output with decorative lines.
     */
    public static void printWithLines(String output) {
        System.out.println(LINE + "\n" + output + LINE);
    }

    /**
     * Checks if a given string only has spaces, or if it's an empty string.
     */
    public static boolean isBlankString(String string) {
        if (string.length() != 0) {
            for (char c : string.toCharArray()) {
                if (c != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}
