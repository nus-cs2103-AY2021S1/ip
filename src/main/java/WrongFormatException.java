public class WrongFormatException extends DukeException {
    private String type;

    public WrongFormatException(String type) {
        this.type = type;
    }

    /**
     * Gives the String representation of the Exception
     *
     * @return a String
     */
    public String toString() {
        if (type.equals("todo")) {
            return " OOPS!!! Proper input format required! Proper format: todo{space}{description} ";
        } else if (type.equals("deadline")) {
            return " OOPS!!! Proper input format required!"
                    + "\n"
                    + " Proper format: deadline{space}{description}{space}/by{space}{YYYY-MM-DD} .";
        } else if (type.equals("event")) {
            return " OOPS!!! Proper input format required!"
                    + "\n"
                    + " Proper format: deadline{space}{description}{space}/at{space}{time-slot}.";
        } else {
            return "No such Task type";
        }
    }
}
