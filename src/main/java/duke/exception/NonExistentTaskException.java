package duke.exception;

import duke.ImageType;

public class NonExistentTaskException extends DukeException {
    public NonExistentTaskException() {
        super("Sorry that task doesn't exist\n"
                + "Try using 'list' to find out what tasks you have!\n", ImageType.CROSS);
    }
}
