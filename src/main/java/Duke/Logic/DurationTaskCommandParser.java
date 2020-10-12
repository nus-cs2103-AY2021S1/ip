package duke.logic;

import duke.exception.DukeException;
import duke.model.DurationTask;

public class DurationTaskCommandParser {
    /**
     * Parse DurationTask command.
     * @param input the input from the user.
     * @return a DurationTask object.
     * @throws DukeException handles the exception when running the Duke bot.
     */
    public static DurationTask run(String input) throws DukeException {
        if (input.length() <= 13) {
            throw new DukeException("      OOPS!!! The description of a duration task cannot be empty.");
        }
        String[] ss = input.split(" ");
        if (ss.length != 3 || ss[2].length() != 2) {
            throw new DukeException("      OOPS!!! Please following the format: durationtask XXX HH");
        }
        DurationTask t = new DurationTask(ss[1], Integer.parseInt(ss[2]));
        return t;
    }
}
