package duke.logic;

import duke.exception.DukeException;

public class DoneCommandParser {
    /**
     * Parse Done command.
     * @param input the input from the user.
     * @return the number which task will marked as done.
     * @throws DukeException handles the exception when running the Duke bot.
     */
    public static int run(String input) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("      OOPS!!! The description of done is incomplete.");
        }
        int n = Integer.parseInt(input.substring(5)) - 1;
        return n;
    }
}
