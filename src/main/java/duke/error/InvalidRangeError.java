package duke.error;

public class InvalidRangeError extends DukeError {
    public InvalidRangeError() {
        super("Uh oh! That number looks like it is out of range.\nCheck again!");
    }
}
