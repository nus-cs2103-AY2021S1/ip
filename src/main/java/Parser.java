import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents the parser of the Duke application. The parser is responsible for
 * processing user commands so that they can be understood by the application.
 */
public class Parser {

    /**
     * Processes the dates/times for deadlines and events in the task list.
     * @param dates a String that represents either a date in YYYY-MM-DD format, or a timestamp
     *              in YYYY-MM-DD HHMM format.
     * @return a String that represents an alternative format of the date/time.
     * @throws DateException if the given date/time is not in the appropriate format.
     */
    public String processDate(String dates) throws DateException {
        try {
            String[] times = dates.split(" ");
            String d = "";
            String t = "";
            if (times.length == 1) {
                LocalDate date = LocalDate.parse(times[0]);
                d = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                return d;
            } else {
                LocalDate date = LocalDate.parse(times[0]);
                d = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

                double time = Double.parseDouble(times[1]);
                if (time >= 1300) {
                    time -= 1200;
                }
                int hour = (int) (time / 100);
                if(hour == 24 || hour == 0) {
                    t += "12.";
                } else {
                    t += hour + ".";
                }
                int minute = (int) (time % 100);
                if (minute < 10) {
                    t += "0" + minute;
                } else {
                    t += minute;
                }
                if ((Double.parseDouble(times[1]) >= 1200) &&
                        (Double.parseDouble(times[1]) < 2400)) {
                    t += "pm";
                } else {
                    t += "am";
                }
                return d + " " + t;
            }
        } catch (java.time.format.DateTimeParseException e) {
            throw new DateException("Sorry! I don't understand the date/time. Please specify the date/time " +
                    "in YYYY-MM-DD or YYYY-MM-DD HHMM format.");
        }
    }

    /**
     * Processes the user command.
     * @param st the user command
     * @param f the size of the task list.
     * @return a list of String objects that represent the appropriate command to
     * be executed.
     * @throws InvalidDoneException if for a done command, either no task is specified or the
     * argument provided does not represent a valid task.
     * @throws InvalidTaskArgumentException if the command to add tasks is not correctly or
     * completely specified.
     * @throws InvalidDeleteException if for a delete command, either no task is specified or the
     * argument provided does not represent a valid task.
     * @throws InvalidCommandException if the user command cannot be understood
     * @throws DateException if the date/time provided for an event/deadline is not in the appropriate
     * format.
     */
    public ArrayList<String> processString(String st, int f) throws InvalidDoneException, InvalidTaskArgumentException,
            InvalidDeleteException, InvalidCommandException, DateException {
        ArrayList<String> lst = new ArrayList<>();
        if (st.equals("list")) {
            lst.add("Show");
            return lst;
        } else if ((st.length() >= 4) && (st.substring(0, 4).equals("done"))) {
            if (st.length() <= 5) {
                throw new InvalidDoneException("\u2639" + " OOPS!!! The task to be marked as done is not " +
                        "specified.");
            } else {
                try {
                    int i = Integer.parseInt(st.substring(5)) - 1;
                    if ((i < 0) || (i >= f)) {
                        throw new InvalidDoneException("\u2639" + " OOPS!!! The number specified does not represent " +
                                "a valid task.");
                    }
                    lst.add("Done");
                    lst.add(Integer.toString(i));
                    return lst;
                } catch (NumberFormatException e) {
                    throw new InvalidDoneException("\u2639" + " OOPS!!! The task to be marked as done is not " +
                            "specified by a valid number.");
                }
            }
        } else if ((st.length() >= 4) && (st.substring(0, 4).equals("todo"))) {
            if (st.length() <= 5) {
                throw new InvalidTaskArgumentException("\u2639" + " OOPS!!! The description of a todo cannot " +
                        "be empty.");
            } else {
                lst.add("Add");
                lst.add("ToDo");
                lst.add(st.substring(5));
                return lst;
            }
        } else if ((st.length() >= 8) && (st.substring(0, 8).equals("deadline"))) {
            if (st.length() <= 9) {
                throw new InvalidTaskArgumentException("\u2639" + " OOPS!!! The deadline is lacking a " +
                        "description/date.");
            } else {
                String[] arr = st.substring(9).split(" /by ");
                if (arr.length < 2) {
                    throw new InvalidTaskArgumentException("\u2639" + " OOPS!!! The deadline is lacking a " +
                            "description/date.");
                } else {
                    lst.add("Add");
                    lst.add("Deadline");
                    lst.add(arr[0]);
                    lst.add(processDate(arr[1]));
                    return lst;
                }
            }
        } else if ((st.length() >= 5) && (st.substring(0, 5).equals("event"))) {
            if (st.length() <= 6) {
                throw new InvalidTaskArgumentException("\u2639" + " OOPS!!! The event is lacking a " +
                        "description/date.");
            } else {
                String[] arr = st.substring(6).split(" /at ");
                if (arr.length < 2) {
                    throw new InvalidTaskArgumentException("\u2639" + " OOPS!!! The event is lacking a " +
                            "description/date.");
                } else {
                    lst.add("Add");
                    lst.add("Event");
                    lst.add(arr[0]);
                    lst.add(processDate(arr[1]));
                    return lst;
                }
            }
        } else if ((st.length() >= 6) && (st.substring(0, 6).equals("delete"))) {
            if (st.length() <= 7) {
                throw new InvalidDeleteException("\u2639" + " OOPS!!! The task to be deleted is not " +
                        "specified.");
            } else {
                try {
                    int i = Integer.parseInt(st.substring(7)) - 1;
                    if ((i < 0) || (i >= f)) {
                        throw new InvalidDeleteException("\u2639" + " OOPS!!! The number specified does not " +
                                "represent a valid task.");
                    }
                    lst.add("Delete");
                    lst.add(Integer.toString(i));
                    return lst;
                } catch (NumberFormatException e) {
                    throw new InvalidDeleteException("\u2639" + " OOPS!!! The task to be deleted is not " +
                            "specified by a valid number.");
                }
            }
        } else {
            throw new InvalidCommandException("\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
