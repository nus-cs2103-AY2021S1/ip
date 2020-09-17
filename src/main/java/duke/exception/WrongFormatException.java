package duke.exception;

import duke.ImageType;

public class WrongFormatException extends DukeException {
    public WrongFormatException() {
        super("Hmmm, looks like that didn't work\n"
                + "Make sure you used the right format!", ImageType.CROSS);
    }
}
