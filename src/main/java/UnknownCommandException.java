public class UnknownCommandException extends DukeException {

    protected String command;

    public UnknownCommandException(String s) {
        this.command = s;
    }

}
