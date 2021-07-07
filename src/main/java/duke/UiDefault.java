package duke;

import java.util.Scanner;

/**
 * Default UI class for testing Duke chat bot with terminal.
 */
public class UiDefault implements Ui {

    //Constants used when formatting the bot's message
    protected static final String LINE_BREAK = "    ____________________________________________________________\n";
    protected static final String PRESPACING = "     ";

    private Scanner scanner;

    /**
     * Constructs a new default UI that uses the system I/O for input and output.
     */
    public UiDefault() {
        scanner = new Scanner(System.in);
    }

    /**
     * Sends the opening message to the output.
     */
    @Override
    public void startup() {
        outputMessage(Ui.OPENING_MESSAGE);
    }

    /**
     * Queries the System input stream for the next command in the form of a string and returns it.
     *
     * @return string provided by the user.
     */
    @Override
    public String getInput() {
        String userInput = "";
        while (userInput.isBlank()) {
            userInput = scanner.nextLine();
        }
        return userInput;
    }

    /**
     * Notifies the System output stream with the given message.
     *
     * @param message message to be sent.
     */
    @Override
    public void outputMessage(String message) {
        System.out.println(processString(message));
    }

    /**
     * Returns a formatted String according to the needs of the specific implementation of Ui.
     *
     * @param string message to be sent.
     * @return formatted message that will be sent.
     */
    protected static String processString(String string) {
        return LINE_BREAK + PRESPACING
                + string.replaceAll("\n", '\n' + PRESPACING)
                + '\n' + LINE_BREAK;
    }

    @Override
    public void exit() {
    }

}
