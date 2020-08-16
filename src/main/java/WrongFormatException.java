public class WrongFormatException extends DukeException {
    String commandName;

    public WrongFormatException(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public String defaultErrorMessage() {
        return super.defaultErrorMessage() + " The " + commandName + " " +
                "command has to be followed by a\n";
    }
}
