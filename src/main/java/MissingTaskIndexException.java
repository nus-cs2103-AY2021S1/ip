public class MissingTaskIndexException extends DukeException {
    public MissingTaskIndexException(InputType inputType) {
        super(inputType.toString().toLowerCase() + " what?");
    }
}
