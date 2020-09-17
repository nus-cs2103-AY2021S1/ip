package duke.exception;

import duke.ImageType;

public class WrongFormatException extends DukeException {
    public WrongFormatException() {
        super("Hmmm, looks like the format didn't work\n"
                + "Refer to the example for help!", ImageType.CROSS);
    }
}
