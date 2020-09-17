package duke.exceptions;

public class StorageNotLoadedException extends DukeException {
    private static String errorMessage = "Somethin' TERRIBLE happened!"
            + "Your saved list of tasks could not be loaded";

    public StorageNotLoadedException() {
        super(errorMessage);
    }
}
