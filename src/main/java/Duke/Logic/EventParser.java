package duke.logic;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.model.Event;

public class EventParser {
    /**
     * Parse Event command.
     * @param input the input from the user.
     * @return a Event object.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws DateTimeException handles date and time error.
     */
    public static Event run(String input) throws DukeException, DateTimeException {
        if (input.length() <= 6) {
            throw new DukeException("      OOPS!!! The description of an event cannot be empty.");
        }
        String[] ss = input.split("/");
        if (ss.length != 4 || !ss[1].startsWith("at ") || !ss[0].endsWith(" ")) {
            // Exception: eg. event meeting /Mon
            throw new DukeException("      OOPS!!! Please following the format: \n"
                    + "      event XXX /at DD/MM/YYYY HHMM");
        }
        int year = Integer.parseInt(ss[3].split(" ")[0]);
        int month = Integer.parseInt(ss[2]);
        int day = Integer.parseInt(ss[1].substring(3));
        String[] time = ss[3].split(" ");
        if (time.length == 1 || time[1].length() != 4) {
            throw new DukeException("      OOPS!!! Please enter the time in correct format: HHMM");
        }
        int hour = Integer.parseInt(ss[3].split(" ")[1].substring(0, 2));
        int minute = Integer.parseInt(ss[3].split(" ")[1].substring(2));
        Event task = new Event(ss[0].substring(6), LocalDateTime.of(year, month, day, hour, minute));
        return task;
    }
}
