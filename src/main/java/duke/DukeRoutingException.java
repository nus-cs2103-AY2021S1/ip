package duke;

import java.util.Arrays;
import java.util.List;

/**
 * This exception is handled within the route function in the Duke class.
 */
public class DukeRoutingException extends Exception {
    private List<String> dukeMessage;

    public DukeRoutingException(List<String> message) {
        super(String.join("\n", message));
        this.dukeMessage = message;
    }

    public DukeRoutingException(String message) {
        this(Arrays.asList(message));
    }

    public List<String> getDukeMessage() {
        return dukeMessage;
    }
}
