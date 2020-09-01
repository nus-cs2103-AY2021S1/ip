public class MissingTaskIndexException extends DukeException {
    public MissingTaskIndexException(TaskType inputType) {
        super(inputType.toString().toLowerCase() + " what?");
    }
}
