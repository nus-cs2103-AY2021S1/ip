package duke.parser;

import java.util.Arrays;
import java.util.stream.Collectors;

import duke.DukeException;

/**
 * Ensures that input strings read from data file is in the correct format.
 */
public class FileStringChecker extends StringChecker {

    public FileStringChecker(String[] userInput) {
        super(userInput);
    }

    /**
     * Ensures file input strings are of the correct length and format.
     * Formats input strings to add in respective delimiters.
     * @return Formatted string to be parsed by the string parser.
     *
     * @throws DukeException If the input command of the string is unrecognized.
     */
    public String checkFile() throws DukeException {
        String[] taskArray = super.getStringArray();
        String taskType = taskArray[0];

        switch (taskType) {
        case "todo":
            return checkFileInputLine(taskArray, 3, 3, "");

        case "deadline":
            return checkFileInputLine(taskArray, 5, 4, "/by");

        case "event":
            return checkFileInputLine(taskArray, 5, 4, "/at");

        default:
            throw new DukeException("File input command " + taskType + " is not a recognised task type!");
        }
    }

    private String checkFileInputLine(String[] stringInput, int maxLength, int minLength, String delimiter)
            throws DukeException {
        checkFileInputLength(maxLength, minLength);
        checkDoneInteger(stringInput[1]);
        return addDelimiter(removeDoneStatus(stringInput), delimiter);
    }

    private void checkFileInputLength(int maxLength, int minLength) throws DukeException {
        if (getStringArray().length > maxLength) {
            throw new DukeException("Your file string has more than the maximum " + maxLength + " inputs!");
        } else if (getStringArray().length < minLength) {
            throw new DukeException("Your file string has less than the minimum " + minLength + " inputs!");
        }
    }

    private void checkDoneInteger(String string) throws DukeException {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new DukeException("Done Status in your file input string should be 0 or 1!");
        }
    }

    private String[] removeDoneStatus(String[] string) {
        String[] tempStringArray = new String[string.length - 1];
        tempStringArray[0] = string[0];
        for (int i = 2; i < string.length; i++) {
            tempStringArray[i - 1] = string[i];
        }
        return tempStringArray;
    }

    private String addDelimiter(String[] string, String delimiter) {
        if (delimiter.equals("")) {
            return Arrays.stream(string).collect(Collectors.joining(" "));
        }
        String[] tempStringArray = new String[string.length + 1];
        tempStringArray[0] = string[0];
        tempStringArray[1] = string[1];
        tempStringArray[2] = delimiter;

        for (int i = 3; i <= string.length; i++) {
            tempStringArray[i] = string[i - 1];
        }
        return Arrays.stream(tempStringArray).collect(Collectors.joining(" "));
    }
}

