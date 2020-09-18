package clippy.exception;

public class UpdateToDoTimeException extends ClippyException {
    public UpdateToDoTimeException() {
        super("ToDos do not have a date or time! Please retry with only task description. No changes made.");
    }
}
