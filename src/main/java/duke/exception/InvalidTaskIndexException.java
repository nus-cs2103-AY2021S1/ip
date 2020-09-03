package duke.exception;

/**
 * Represents a Invalid Instruction Format DukeException in the program.
 * It is to feedback to the user about instructions require a list index but the index is invalid.
 */
public class InvalidTaskIndexException extends DukeException {

    public InvalidTaskIndexException() {
        super("Task Number is invalid! Please check!", InvalidTaskIndexException.class.getName());
    }

    @Override
    public String guiString() {
        return "...zzz...This is not a valid task number...\n"
                + "wake me up when you have the correct task numb-...zzz...";
    }
}
