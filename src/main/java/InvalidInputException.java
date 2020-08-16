public class InvalidInputException extends Exception {

    protected String msg;

    public InvalidInputException(String message) {
        super(message);
        this.msg = message;
    }

    public String getMessage() {
        if(this.msg.length() < 4) {
            return "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        } else if (this.msg.contains("todo")) {
            return "\t☹ OOPS!!! The description of a todo cannot be empty.";
        } else if (this.msg.contains("event")) {
            return "\t☹ OOPS!!! The description of an event cannot be empty.";
        } else if (this.msg.contains("deadline")) {
            return "\t☹ OOPS!!! The description of a deadline cannot be empty.";
        } else {
            return "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}
