package exceptions;

import java.util.HashMap;

public class DukeInvalidParameterException extends DukeException {

    private final HashMap<String, String> parameters;

    public DukeInvalidParameterException(String message, HashMap<String, String> parameters) {
        super(message);
        this.parameters = parameters;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " Parameters supplied: " + parameters;
    }
}
