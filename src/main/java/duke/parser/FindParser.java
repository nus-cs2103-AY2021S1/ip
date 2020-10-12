package duke.parser;

import duke.DukeException;

public class FindParser implements IndexCommandParser {
    private final String input;

    /**
     * The constructor for the FindParser object.
     * @param input the input command.
     */
    public FindParser(String input) {
        this.input = input;
    }

    /**
     * Checks if the find command is valid.
     * @return returns -1 if the command is valid, throws a DukeException if otherwise.
     * @throws DukeException thrown if the find command is invalid.
     */
    public int checkIfValid() throws DukeException {
        if (input.length() == 5) { // Checks if the input string does not contain a keyword
            throw new DukeException("What are you trying to find?");
        } else {
            return -1;
        }
    }

    /**
     * returns the keyword for the find command if its valid.
     * @return the keyword for the find command.
     */
    public String keywordIfValid() throws DukeException {
        try {
            checkIfValid(); // The integer is not important, we just want to run checkIfValid()
            return input.substring(5);
        } catch (DukeException e) {
            throw new DukeException(e.toString());
        }
    }


}
