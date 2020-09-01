package duke.dukeexception;

// for delete and done commands
public class WrongItemIndexException extends DukeException {

    public WrongItemIndexException(String commandName, int listLength) {
        super("Cannot find leh. Try typing \"" + commandName + " {index of list item}\"." +
            "\nYour list only got " +
            listLength +
            " things.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
