public class UpdateToDoTimeException extends ClippyException {
    UpdateToDoTimeException() {
        super("ToDos do not have a date or time!");
    }
}
