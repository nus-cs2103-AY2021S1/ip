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
        String description = "";
        if (input.length() <= 13) {
            throw new DukeException("      OOPS!!! The description of a duration task cannot be empty.");
        }
        String[] ss = input.split(" ");
        if (ss.length < 3) {
            throw new DukeException("      OOPS!!! Please following the format: durationtask XXX HH");
        }
        for (int i = 1; i < ss.length - 1; i++) {
            description += ss[i] + " ";
        }
        if (description.trim().equals("")) {
            throw new DukeException("      OOPS!!! Description cannot be empty.");
        }
        return new DurationTask(description.substring(0, description.length() - 1),
                Integer.parseInt(ss[ss.length - 1]));
    }
}
