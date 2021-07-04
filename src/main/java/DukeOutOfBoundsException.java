public class DukeOutOfBoundsException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "Sorry, that task does not exist, try again!";
    }
}
