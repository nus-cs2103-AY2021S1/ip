public class DukeException extends Exception {
    String message;

    public DukeException(String message) {
        super("OOPS!!! :( " + message);
    }

//    public String toString() {
//        return this.message;
//    }
}
