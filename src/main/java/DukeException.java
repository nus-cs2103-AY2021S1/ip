package main.java;

public class DukeException {
    protected ExceptionType et;

    public DukeException(ExceptionType et) {
        this.et = et;
    }

    public enum ExceptionType {
        no_meaning,
        empty_illegal,
        todo_empty,
        deadline_empty_incomplete,
        event_empty_incomplete,
        read_file
    }

    @Override
    public String toString() {
        String output = "";
        String full_guide = "\n     Please follow the format:" +
                "\n     'list'" + "\n     'done TASK_NUMBER' or 'delete TASK_NUMBER'" + "\n     'todo MY_TASK'" +
                "\n     'deadline MY_TASK /by DATE_OR_TIME'" + "\n     'event MY_TASK /at DATE_OR_TIME'" +
                "\n     'bye'";
        switch(et) {
            case no_meaning:
                output = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + full_guide;
                break;
            case empty_illegal:
                output = "     ☹ OOPS!!! Your number input is empty or invalid." +
                            "\n     Please follow the format: 'done TASK_NUMBER' or 'delete TASK_NUMBER'";
                break;
            case todo_empty:
                output = "     ☹ OOPS!!! The description of a todo cannot be empty." +
                            "\n     Please follow the format: 'todo MY_TASK'";
                break;
            case deadline_empty_incomplete:
                output = "     ☹ OOPS!!! The description of a deadline cannot be empty or incomplete." +
                            "\n     Please follow the format: 'deadline MY_TASK /by DATE_OR_TIME'";
                break;
            case event_empty_incomplete:
                output = "     ☹ OOPS!!! The description of an event cannot be empty or incomplete." +
                            "\n     Please follow the format: 'event MY_TASK /at DATE_OR_TIME'";
                break;
            case read_file:
                output = "     ☹ OOPS!!! There seems to be some problem reading the memory!";
                break;
            default:
                output = "     ☹ OOPS!!! Unspecified problem detected!" + full_guide;
                break;
        }
        return output;
    }

    /*protected String type;
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
                msg += "☹ OOPS!!! You did not enter a valid number.";
            }
        } else if (type.equals("delete")) {
            if (problem.equals("empty")) {
                msg += "☹ OOPS!!! Please indicate which one to delete.";
            } else if (problem.equals("illegal")) {
                msg += "☹ OOPS!!! You did not enter a valid number.";
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
    }*/
}
