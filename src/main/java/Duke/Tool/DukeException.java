package Duke.Tool;

public class DukeException extends Exception {
    
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String emoji = Emoji.ERROR.toString();
        String msgForException = "    ____________________________________________________________\n"
                + "    " + emoji + this.getMessage() + "\n"
                + "    ____________________________________________________________\n";
        return msgForException;
    }
}
