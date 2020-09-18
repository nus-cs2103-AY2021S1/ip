package duke.exception;

import duke.ImageType;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("Sorry I didn't understand that\n"
                + "How about entering 'help' instead?\n", ImageType.CROSS);
    }
}
