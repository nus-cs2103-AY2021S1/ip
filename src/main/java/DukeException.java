public class DukeException extends Exception {
    String e;

    DukeException(String e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return e;
    }
}
