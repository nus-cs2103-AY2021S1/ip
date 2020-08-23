package duke.error;

public class InvalidDateInputError extends DukeError {
    public InvalidDateInputError() {
        super("Invalid date entered!\nPlease enter the following format! dd/mm/yyyy HH:MM");
    }
}
