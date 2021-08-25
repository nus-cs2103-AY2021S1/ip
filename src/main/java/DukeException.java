/**
 * DukeException is an ArrayIndexOutOfBoundsException that are caused by errors in the Duke program.
 */
public class DukeException extends ArrayIndexOutOfBoundsException {
    private String cause;

    /**
     * this is a constructor for the DukeException class; it contains a message parameter
     * that specifies the error message to be displayed.
     *
     * @param message a String that is used to specify the error message to be produced
     */

    DukeException(String message) {
        this.cause = message;
    }

    /**
     * the toString() method of the class that returns a string representation of the
     * error produced in the program Duke.
     *
     * @return a string representation of the error produced
     */
    @Override
    public String toString() {
        if (this.cause.equals("empty")) {
            return "OOPS!!! The description of a todo cannot be empty.";
        } else if (this.cause.equals("please parse first")){
            return "Please parse!!";
        } else {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}
