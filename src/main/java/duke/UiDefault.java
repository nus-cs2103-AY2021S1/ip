package duke;

import java.util.Scanner;

public class UiDefault extends Ui {

    //Constants used when formatting the bot's message
    protected static final String LINE_BREAK = "    ____________________________________________________________\n";
    protected static final String PRESPACING = "     ";

    private Scanner scanner;

    public UiDefault() {
        scanner = new Scanner(System.in);
    }

    /**
     * Queries the System input stream for the next command in the form of a string.
     * @return string provided by the user
     */
    @Override
    public String getResponse() {
        return scanner.nextLine();
    }

    /**
     * Notifies the System output stream with the given message
     * @param message message to be sent
     */
    @Override
    public void outputMessage(String message) {
        System.out.println(processString(message));
    }

    /**
     * Returns a formatted String according to the needs of the specific implementation of Ui.
     * @param string message to be sent
     * @return formatted message that will be sent
     */
    protected static String processString(String string) {
        return LINE_BREAK + PRESPACING
                + string.replaceAll("\n", '\n' + PRESPACING)
                + '\n' + LINE_BREAK;
    }


}
