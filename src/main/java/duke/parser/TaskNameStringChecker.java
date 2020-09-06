package duke.parser;

import duke.DukeException;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TaskNameStringChecker extends StringChecker {

    public TaskNameStringChecker(String[] userInput) {
        super(userInput);
    }

    private void checkTaskNamePresent(String delimiter) throws DukeException {
        if (super.checkEmptyString(getStringArray(),2) || extractTaskName(delimiter).trim().equals("") ) {
            throw new DukeException("Uhoh! Please enter a valid description/name for your task!");
        }
    }

    public String checkTaskString(String delimiter) throws DukeException {
        checkTaskNamePresent(delimiter);
        super.checkNoIllegalCharacters(extractTaskName(delimiter), "|");
        return extractTaskName(delimiter);
    }

    private String extractTaskName(String delimiter) {
        if (delimiter.equals("")) {
            return Arrays.stream(getStringArray()).skip(1).collect(Collectors.joining(" "));
        }
        return Arrays.stream(getStringArray()).takeWhile(e -> !e.equals(delimiter))
                .skip(1).collect(Collectors.joining(" "));
    }

}
