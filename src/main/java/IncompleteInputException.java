public class IncompleteInputException extends DukeException {
    IncompleteInputException() {
        super("please finish your sentence.. :(\neither your task name is blank or youre missing the time!!");
    }
}
