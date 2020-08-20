public class DukeException extends Exception {
    String e;

    DukeException() {
        this.e = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    DukeException(String e) {
        this.e = "☹ OOPS!!! " + e;
    }

    @Override
    public String toString() {
        return e;
    }
}
