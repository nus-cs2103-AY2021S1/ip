package duke.logic;

import duke.exception.DukeException;

public class FindCommandParser {
    /**
     * Finds all the tasks that contains the keyword.
     * @param input the input from the user.
     * @return the keyword.
     * @throws DukeException handles the exception when running the Duke bot.
     */
    public static String run(String input) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("      OOPS!!! The keyword cannot be empty.");
        }
        String keyword = input.substring(5);
        return keyword;
    }
}
