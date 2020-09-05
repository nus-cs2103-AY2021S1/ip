package duke.ui.textui;

import java.util.stream.Stream;

/**
 * Class that simulates the reaction of duke to the user's input
 */

public class Ui {
    private static final String GOODBYE_MSG = "Bye ^.^, Hope to see you again soon!!!";
    private static final String HELLO_DUKE = "Welcome back";
    /**
     * Greets the user.
     */
    public String greetings() {
        return messageFormatter(HELLO_DUKE);
    }

    /**
     * Saying goodbye to user.
     */
    public String goodBye() {
        return messageFormatter(GOODBYE_MSG);
    }

    /**
     * Prints the error 'msg'.
     * @param msg Error message to be printed.
     */
    public String printException(String msg) {
        return messageFormatter(msg);
    }

    /**
     * Formatter to format any message. Easily customizable
     * @param messageList messages to be wrapped around the formatter.
     */
    public String messageFormatter(String... messageList) {
        StringBuffer finalMessage = new StringBuffer();
        Stream.of(messageList).forEachOrdered(message -> finalMessage.append(message).append("\n"));
        return finalMessage.toString();
    }
}
