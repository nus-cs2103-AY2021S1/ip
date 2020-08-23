package duke.exception;

public class WrongFormatException extends DukeException {
    String commandName;

    public WrongFormatException(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " The " + commandName + " " +
                "command has to be followed by a\n";
    }
}
