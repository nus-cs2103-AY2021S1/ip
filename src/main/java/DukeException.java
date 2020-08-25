public class DukeException extends Exception{
    public DukeException(String errorMsg) {
        super(errorMsg);
    }

    @Override
    public String toString() {
        return "OOPS!!! " + this.getMessage();
    }
}
