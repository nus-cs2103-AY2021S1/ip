package luke.exception;

public class LukeDataLoadingException extends LukeException {
    public LukeDataLoadingException(String string) {
        super("Sorry, there was an error while loading a data file. Please try again.");
    }
}
