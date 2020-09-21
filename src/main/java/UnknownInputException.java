public class UnknownInputException extends DukeException {

    // Constructor
    public UnknownInputException(String input) {
        super(String.format("I'm sorry, but I don't know what %s means :-(", input));
    }

    public UnknownInputException(String input, String type) {
        super(String.format("I'm sorry, but I don't know what %s means for %s :-(", input, type));
    }
}
