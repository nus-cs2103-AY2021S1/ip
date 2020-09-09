package luke.exception;

public class LukeIndexOutOfBoundsException extends LukeException {
    public LukeIndexOutOfBoundsException(String size) {
        super(String.format("The current task list contains less than %d number of tasks.\nPlease enter a valid task number.", size));
    }
}
