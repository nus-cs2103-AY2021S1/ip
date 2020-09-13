public class DukeException extends Throwable {
    String message = "";

    public DukeException() {};

    public DukeException(String message) {
        this.message = message;
    }

    public String toString() {
        if (!message.equals("")) return message;
        else return "Duke is too dumb, Duke dunno what you mean";
    }
}
