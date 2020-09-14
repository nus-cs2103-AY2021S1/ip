package luke.exception;

public class LukeNoResultException extends LukeException {
    public LukeNoResultException(String keyword) {
        super(String.format("Sorry, there are no tasks that contains the keyword '%s.' Please try again with another keyword.", keyword));
    }
}
