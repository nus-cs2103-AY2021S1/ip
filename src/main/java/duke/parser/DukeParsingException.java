package duke.parser;

import java.util.Arrays;
import java.util.List;

/**
 * This exception is handled within the route function in the Duke class.
 */
public class DukeParsingException extends Exception {
    private List<String> dukeMessage;

    public DukeParsingException(List<String> message) {
        super(String.join("\n", message));
        this.dukeMessage = message;
    }

    public DukeParsingException(String message) {
        this(Arrays.asList(message));
    }

    public List<String> getDukeMessage() {
        return dukeMessage;
    }
}
