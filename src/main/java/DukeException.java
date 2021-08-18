public class DukeException extends ArrayIndexOutOfBoundsException {
    private String cause;

    DukeException(String message) {
        this.cause = message;
    }

    @Override
    public String toString() {
        if (this.cause.equals("empty")) {
            return "OOPS!!! The description of a todo cannot be empty.";
        } else {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}
