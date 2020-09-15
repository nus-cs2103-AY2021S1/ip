package ekud.exceptions;

import java.util.HashMap;

public class DukeInvalidParameterException extends DukeException {

    private final HashMap<String, String> parameters;

    /**
     * Instantiates a new ekud.Duke invalid parameter exception.
     *
     * @param message    the message
     * @param parameters the parameters which were invalid
     */
    public DukeInvalidParameterException(String message, HashMap<String, String> parameters) {
        super(message);
        this.parameters = parameters;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " Parameters supplied: " + parameters;
    }
}
