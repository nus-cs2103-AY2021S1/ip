package duke.exceptions;

public class LackOfTimeException extends DukeException {
    private String typeOfCommand;

    /**
     * Constructor for lack of time exception.
     * @param err
     * @param typeOfCommand
     */
    public LackOfTimeException(String err, String typeOfCommand) {
        super(err);
        this.typeOfCommand = typeOfCommand;
    }

    @Override
    public String getMessage() {
        return "⚠️OOPS!!! The time/date of " + this.typeOfCommand + " cannot be empty\n"
                + "please put the time/date after \"/\"";
    }
}
