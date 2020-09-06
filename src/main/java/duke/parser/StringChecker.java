package duke.parser;

import duke.DukeException;

/**
 * Abstract class to represent utility checker classes that ensure string quality and correctness.
 */
public abstract class StringChecker {

    /** Input string array from the user. */
    private String[] inputStringArray;

    StringChecker(String[] userInput) {
        inputStringArray = userInput;
    }

    protected boolean checkEmptyString(String[] stringArray, int length) {
        //Makes sure string array specified contains minimal number of elements
        return (stringArray.length < length || stringArray[length - 1].trim().equals(""));
    }

    protected void checkNoIllegalCharacters(String string, String delimiter) throws DukeException {
        //Makes sure string does not contain the illegal delimiter specified
        if (string.contains(delimiter)) {
            throw new DukeException("Sorry, task descriptions cannot contain '" + delimiter + " '");
        }
    }

    public String[] getStringArray() {
        return inputStringArray;
    }
}
