public class DukeException extends Exception{
    public DukeException(String s) {
        super(s);
    }

    public String toString() {
        return this.getMessage();
    }
}
