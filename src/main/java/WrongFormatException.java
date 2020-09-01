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
            return "______________________________________________________"
                    + "\n"
                    + " OOPS!!! Proper input format required! Proper format: todo{space}{description}"
                    + "\n"
                    + "______________________________________________________";
        } else if (type.equals("deadline")) {
            return "______________________________________________________"
                    + "\n"
                    + " OOPS!!! Proper input format required!"
                    + " Proper format: deadline{space}{description}{space}/by{space}{YYYY-MM-DD} ."
                    + "\n"
                    + "______________________________________________________";
        } else if (type.equals("event")) {
            return "______________________________________________________"
                    + "\n"
                    + " OOPS!!! Proper input format required!"
                    + " Proper format: deadline{space}{description}{space}/at{space}{time-slot}."
                    + "\n"
                    + "______________________________________________________";
        } else {
            return "No such Task type";
        }
    }
}
