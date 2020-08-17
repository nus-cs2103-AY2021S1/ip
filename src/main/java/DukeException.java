public class DukeException extends Exception {
    public DukeException(String msg) {
        super("------------------------------------------\n" + "UHOH! "
                + msg + "\n---------------------------------------------------------");
    }
}