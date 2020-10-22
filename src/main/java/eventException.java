public class eventException extends  DukeException {


    public eventException(String message) {
        super(message);
    }

    public String toString() {
        if (!message.equals("")) return message;
        else return "what event gimme smth to write pls";
    }
}
