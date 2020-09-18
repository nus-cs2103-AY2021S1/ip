package duke.exception;

import duke.ImageType;

public class NoMatchFoundException extends DukeException {
    public NoMatchFoundException() {
        super("There were no tasks that matched the keyword\n"
                + "Try using 'list' to find out what tasks you have!\n", ImageType.ALERT);
    }
}