package duke.logic;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.model.Deadline;

public class DeadlineCommandParser {
    /**
     * Parse Deadline command.
     * @param input the input from the user.
     * @return a Deadline object.
     * @throws DukeException handles the exception when running the Duke bot.
     * @throws DateTimeException handles date and time error.
     */
    public static Deadline run(String input) throws DukeException, DateTimeException {
        if (input.length() <= 9) {
            throw new DukeException("      OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] ss = input.split("/");
        if (ss.length != 4 || !ss[1].startsWith("by ") || !ss[0].endsWith(" ")) {
            throw new DukeException("      OOPS!!! Please following the format: \n"
                    + "      deadline XXX /by DD/MM/YYYY HHMM");
        }
        if (ss[0].substring(9).trim().equals("")) {
            throw new DukeException("      OOPS!!! Description cannot be empty.");
        }
        int year = Integer.parseInt(ss[3].split(" ")[0]);
        int month = Integer.parseInt(ss[2]);
        int day = Integer.parseInt(ss[1].substring(3));
        String[] time = ss[3].split(" ");
        if (time.length == 1 || time[1].length() != 4) {
            throw new DukeException("      OOPS!!! Please following the format: \n"
                    + "      deadline XXX /by DD/MM/YYYY HHMM");
        }
        int hour = Integer.parseInt(ss[3].split(" ")[1].substring(0, 2));
        int minute = Integer.parseInt(ss[3].split(" ")[1].substring(2));
        Deadline task = new Deadline(ss[0].substring(9), LocalDateTime.of(year, month, day, hour, minute));
        return task;
    }
}
