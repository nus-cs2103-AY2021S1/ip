package duke.exception;

public class InvalidFormatDoneException extends DukeException {
    public InvalidFormatDoneException() {
        super(" ☹ OOPS! A proper done format would be like +" +
                " e.g. done \'an integer that is between 1(if list is not empty) to the number of items in the list\'");
    }
}
