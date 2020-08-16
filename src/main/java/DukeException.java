public class DukeException extends Exception {

    private String errorMsg;

    public DukeException(String msg) {
        this.errorMsg = msg;
    }

    @Override
    public String toString() {
        return errorMsg;
    }
}
