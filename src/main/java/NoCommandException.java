public class NoCommandException extends DukeException {

    public NoCommandException() {
        super("I cannot find this command leh. Try sth else?");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
