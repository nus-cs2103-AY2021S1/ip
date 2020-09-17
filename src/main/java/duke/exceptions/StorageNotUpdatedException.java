package duke.exceptions;

public class StorageNotUpdatedException extends DukeException {
    private static String errorMessage = "Somethin' BAD happened!"
            + "Your saved list of tasks could not be updated";

    public StorageNotUpdatedException() {
        super(errorMessage);
    }
}
