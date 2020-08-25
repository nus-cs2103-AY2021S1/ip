public class WrongFormatException extends DukeException{
    private String type;

    public WrongFormatException(String type) {
        this.type = type;
    }

    public String toString() {
        if (type.equals("todo")) {
            return "____________________________________________________________"
                    + "\n"
                    + " ☹ OOPS!!! Proper input format required! Proper format: todo{space}{description}"
                    + "\n"
                    + "____________________________________________________________";
        } else if (type.equals("deadline")) {
            return "____________________________________________________________"
                    + "\n"
                    + " ☹ OOPS!!! Proper input format required! Proper format: deadline{space}{description}{space}/by{space}{YYYY-MM-DD} ."
                    + "\n"
                    + "____________________________________________________________";
        } else if (type.equals("event")) {
            return "____________________________________________________________"
                    + "\n"
                    + " ☹ OOPS!!! Proper input format required! Proper format: deadline{space}{description}{space}/at{space}{time-slot}."
                    + "\n"
                    + "____________________________________________________________";
        } else {
            return "No such Task type";
        }

    }
}
