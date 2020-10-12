package duke.logic;

import duke.exception.DukeException;

public class DeleteCommandParser {
    /**
     * Parse Delete command.
     * @param input the input from the user.
     * @return the number which task will be deleted.
     * @throws DukeException handles the exception when running the Duke bot.
     */
    public static int run(String input) throws DukeException {
        if (input.length() <= 7) {
            throw new DukeException("      OOPS!!! The description of delete is incomplete.");
        }
        int n = Integer.parseInt(input.substring(7)) - 1;
        return n;
    }
}
