public class DukeException extends Exception {
    DukeException(String msg) {
        super(" ____________________________________________________________\n " +
                "☹ ERROR: " + msg +
                "\n ____________________________________________________________");
    }
}
