package duke.main;

import duke.exception.InvalidCommandException;

/**
 * Represents different available command to Duke.
 */
public enum Keyword {
    TODO("todo"),
    DEADLINE("deadline"),
    LIST("list"),
    EVENT("event"),
    DELETE("delete"),
    BYE("bye"),
    DONE("done"),
    FIND("find"),
    SORT("sort");

    private String keyword;

    /**
     * Initializes a value of keyword.
     *
     * @param keyword The keyword given.
     */
    Keyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Check whether a given command is a valid command.
     *
     * @param inputKeyword Command input from the user.
     * @return True if it is a valid command.
     * @throws InvalidCommandException If the command is invalid.
     */
    public static boolean isValid(String inputKeyword) throws InvalidCommandException {
        Keyword[] array = Keyword.values();
        for (Keyword keyword : array) {
            if (keyword.keyword.equals(inputKeyword)) {
                return true;
            }
        }
        throw new InvalidCommandException();
    }
}
