public class DukeException extends Exception {

    public DukeException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return Colour.Red(super.toString());
    }
}
