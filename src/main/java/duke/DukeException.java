package main.java.duke;

public class DukeException extends Throwable {
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
        read_file,
        improper_dateTime
    }

    @Override
    public String toString() {
        String output = "";
        String full_guide = "\n     Please follow the format:" +
                "\n     'list'" + "\n     'done TASK_NUMBER' or 'delete TASK_NUMBER'" + "\n     'todo MY_TASK'" +
                "\n     'deadline MY_TASK /by DATE_OR_TIME'" + "\n     'event MY_TASK /at DATE_OR_TIME'" +
                "\n     'bye' + '\n     NOTE: \n     Recommended Form of DATE_OR_TIME: 'YYYY-MM-DD HHMM' or 'YYYY-MM-DD''";
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
                            "\n     Please follow the format: 'deadline MY_TASK /by DATE_OR_TIME'" +
                            "\n     (Recommended DATE/TIME format: 'YYYY-MM-DD HHMM' or 'YYYY-MM-DD')";
                break;
            case event_empty_incomplete:
                output = "     ☹ OOPS!!! The description of an event cannot be empty or incomplete." +
                            "\n     Please follow the format: 'event MY_TASK /at DATE_OR_TIME'" +
                            "\n     (Recommended DATE/TIME format: 'YYYY-MM-DD HHMM' or 'YYYY-MM-DD')";
                break;
            case read_file:
                output = "     ☹ OOPS!!! There seems to be some problem accessing the memory file!";
                break;
            case improper_dateTime:
                output = SpecialFormat.starting_line + "☹ OOPS!!! We are unable to understand the Time provided." +
                        "\n     However, we will still add this task. No worries!" +
                        "\n     Next time, you can consider the format 'YYYY-MM-DD HHMM' or 'YYYY-MM-DD'!";
                break;
            default:
                output = "     ☹ OOPS!!! Unspecified problem detected!" + full_guide;
                break;
        }
        return output;
    }

}
