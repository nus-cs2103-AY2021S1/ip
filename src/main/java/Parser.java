import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

    public static String processDate(String date) throws DateException {
        try {
            String[] dates = date.split(" ");
            String processedDate = "";
            String processedTime = "";
            if (dates.length == 1) {
                LocalDate localDate = LocalDate.parse(dates[0]);
                processedDate = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                return processedDate;
            } else {
                LocalDate localDate = LocalDate.parse(dates[0]);
                processedDate = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

                double time = Double.parseDouble(dates[1]);
                if (time >= 1300) {
                    time -= 1200;
                }
                int hour = (int) (time / 100);
                if(hour == 24 || hour == 0) {
                    processedTime += "12.";
                } else {
                    processedTime += hour + ".";
                }
                int minute = (int) (time % 100);
                if (minute < 10) {
                    processedTime += "0" + minute;
                } else {
                    processedTime += minute;
                }
                if ((Double.parseDouble(dates[1]) >= 1200) &&
                        (Double.parseDouble(dates[1]) < 2400)) {
                    processedTime += "pm";
                } else {
                    processedTime += "am";
                }
                return processedDate + " " + processedTime;
            }
        } catch (java.time.format.DateTimeParseException e) {
            throw new DateException("Sorry! I don't understand the date/time. Please specify the date/time "
                    + "in YYYY-MM-DD or YYYY-MM-DD HHMM format.");
        }
    }

    public ArrayList<String> processString(String s, int size)
            throws InvalidDoneException, InvalidTaskArgumentException,
            InvalidDeleteException, InvalidCommandException, DateException {
        ArrayList<String> lst = new ArrayList<>();
        if (s.equals("list")) {
            lst.add("Show");
            return lst;
        } else if ((s.length() >= 4) && (s.substring(0, 4).equals("done"))) {
            if (s.length() <= 5) {
                throw new InvalidDoneException("\u2639" + " OOPS!!! The task to be marked as done is not "
                        + "specified.");
            } else {
                try {
                    int i = Integer.parseInt(s.substring(5)) - 1;
                    if ((i < 0) || (i >= size)) {
                        throw new InvalidDoneException("\u2639" + " OOPS!!! The number specified does not represent "
                                + "a valid task.");
                    }
                    lst.add("Done");
                    lst.add(Integer.toString(i));
                    return lst;
                } catch (NumberFormatException e) {
                    throw new InvalidDoneException("\u2639" + " OOPS!!! The task to be marked as done is not "
                            + "specified by a valid number.");
                }
            }
        } else if ((s.length() >= 4) && (s.substring(0, 4).equals("todo"))) {
            if (s.length() <= 5) {
                throw new InvalidTaskArgumentException("\u2639" + " OOPS!!! The description of a todo cannot "
                        + "be empty.");
            } else {
                lst.add("Add");
                lst.add("ToDo");
                lst.add(s.substring(5));
                return lst;
            }
        } else if ((s.length() >= 8) && (s.substring(0, 8).equals("deadline"))) {
            if (s.length() <= 9) {
                throw new InvalidTaskArgumentException("\u2639" + " OOPS!!! The deadline is lacking a "
                        + "description/date.");
            } else {
                String[] arr = s.substring(9).split(" /by ");
                if (arr.length < 2) {
                    throw new InvalidTaskArgumentException("\u2639" + " OOPS!!! The deadline is lacking a "
                            + "description/date.");
                } else {
                    lst.add("Add");
                    lst.add("Deadline");
                    lst.add(arr[0]);
                    lst.add(processDate(arr[1]));
                    return lst;
                }
            }
        } else if ((s.length() >= 5) && (s.substring(0, 5).equals("event"))) {
            if (s.length() <= 6) {
                throw new InvalidTaskArgumentException("\u2639" + " OOPS!!! The event is lacking a "
                        + "description/date.");
            } else {
                String[] arr = s.substring(6).split(" /at ");
                if (arr.length < 2) {
                    throw new InvalidTaskArgumentException("\u2639" + " OOPS!!! The event is lacking a "
                            + "description/date.");
                } else {
                    lst.add("Add");
                    lst.add("Event");
                    lst.add(arr[0]);
                    lst.add(processDate(arr[1]));
                    return lst;
                }
            }
        } else if ((s.length() >= 6) && (s.substring(0, 6).equals("delete"))) {
            if (s.length() <= 7) {
                throw new InvalidDeleteException("\u2639" + " OOPS!!! The task to be deleted is not "
                        + "specified.");
            } else {
                try {
                    int i = Integer.parseInt(s.substring(7)) - 1;
                    if ((i < 0) || (i >= size)) {
                        throw new InvalidDeleteException("\u2639" + " OOPS!!! The number specified does not "
                                + "represent a valid task.");
                    }
                    lst.add("Delete");
                    lst.add(Integer.toString(i));
                    return lst;
                } catch (NumberFormatException e) {
                    throw new InvalidDeleteException("\u2639" + " OOPS!!! The task to be deleted is not "
                            + "specified by a valid number.");
                }
            }
        } else {
            throw new InvalidCommandException("\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
