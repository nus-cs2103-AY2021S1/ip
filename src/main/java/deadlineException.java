public class deadlineException extends DukeException {

    public deadlineException(String message) {
        super(message);
    }

    public String toString() {
        if (!message.equals("")) return message;
        else return "what deadline gimme smth to write pls";
    }
}
