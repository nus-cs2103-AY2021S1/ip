public class UnknownInputException extends DukeException {

    // Constructor
    public UnknownInputException(String input) {
        super(String.format("I'm sorry, but I don't know what %s means :-(", input));
    }
}
