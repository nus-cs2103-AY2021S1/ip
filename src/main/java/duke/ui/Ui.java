package duke.ui;

import java.util.Scanner;

/**
 * Class the simulates the reaction of duke to the user's input
 */

public class Ui {
    private static final String SPACER = "               ";
    private static final String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String OPENING_MSG = " *** Opening and loading relevant documents into Duke ***";
    private static final String GOODBYE_MSG = "Bye ^.^, Hope to see you again soon!!!";
    private static final String HELLO_DUKE = "      " + "Hello! I'm Duke ^.^";
    private static final String QUESTION = "What can I do for you?";

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Greets the user.
     */
    public String startMessage() {
        return messageFormatter(new String[]{OPENING_MSG});
    }

    /**
     * Greets the user.
     */
    public String greetings() {
        return messageFormatter(new String[]{HELLO_DUKE, QUESTION});   
    }

    /**
     * Saying goodbye to user.
     */
    public String goodBye() {
        return messageFormatter(new String[]{GOODBYE_MSG});
    }

    /**
     * Prints the error 'msg'.
     * @param msg Error message to be printed.
     */
    public String printException(String msg) {
        return messageFormatter(new String[]{msg});
    }

    /**
     * Formatter to format any message. Easily customizable
     * @param messageList messages to be wrapped around the formatter.
     */
    public String messageFormatter(String[] messageList) {
        StringBuffer s = new StringBuffer("");
        for (int i = 0; i < messageList.length; i++) {
            s.append(messageList[i]).append("\n"); 
        }
        return s.toString();
    }

    /**
     * Reads in the user's input
     * @return String representing the user's input.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
