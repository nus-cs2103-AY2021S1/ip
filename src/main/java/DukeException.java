package main.java;

public class DukeException {
    protected String type;
    protected String exact;
    protected String problem;

    public DukeException(String type, String exact, String problem) {
        this.type = type;
        this.exact = exact;
        this.problem = problem;
    }

    @Override
    public String toString() {
        String msg = "     ";
        if (type.equals("input") && problem.equals("no_meaning")) {
            msg += "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        } else if (type.equals("done")) {
            if (problem.equals("empty")) {
                msg += "☹ OOPS!!! Please indicate which one to mark.";
            } else if (problem.equals("illegal")) {
                msg += "☹ OOPS!!! Please enter a valid number.";
            }
        } else if (type.equals("todo") && problem.equals("empty")) {
            msg += "☹ OOPS!!! The description of a todo cannot be empty.";
        } else if (type.equals("deadline")) {
            if (exact.equals("content")) {
                msg += "☹ OOPS!!! The description of a deadline cannot be empty.";
            } else if (exact.equals("date")) {
                msg += "☹ OOPS!!! The final date of a deadline cannot be empty.";
            }
        } else if (type.equals("event")) {
            if (exact.equals("content")) {
                msg += "☹ OOPS!!! The description of an event cannot be empty.";
            } else if (exact.equals("date")) {
                msg += "☹ OOPS!!! The date of an event cannot be empty.";
            }
        } else {
            msg += "Unspecified Exception Detected";
        }
        return msg;
    }
}
