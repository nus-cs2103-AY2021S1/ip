package duke.exceptions;

public class StorageNotCreatedException extends DukeException {
    private static String errorMessage = "Somethin' went WRONG!"
            + "It seems your tasks can't be saved upon exit! :(";

    public StorageNotCreatedException() {
        super(errorMessage);
    }
}
