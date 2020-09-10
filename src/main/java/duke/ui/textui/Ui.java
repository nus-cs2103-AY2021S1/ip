package duke.ui.textui;

import static duke.util.Keyword.KEYWORD_UI_GOODBYE_MSG;
import static duke.util.Keyword.KEYWORD_UI_HELLO_DUKE;

import java.util.stream.Stream;

/**
 * Class that simulates the reaction of duke to the user's input
 */
public class Ui {

    /**
     * Greets the user.
     */
    public String greetings() {
        return messageFormatter(KEYWORD_UI_HELLO_DUKE);
    }

    /**
     * Saying goodbye to user.
     */
    public String goodBye() {
        return messageFormatter(KEYWORD_UI_GOODBYE_MSG);
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
