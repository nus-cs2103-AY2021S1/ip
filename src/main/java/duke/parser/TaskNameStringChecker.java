package duke.parser;

import java.util.Arrays;
import java.util.stream.Collectors;

import duke.DukeException;
import duke.task.TaskType;

/**
 * Utility class that checks ensures correctness of task names.
 */
public class TaskNameStringChecker extends StringChecker {

    public TaskNameStringChecker(String[] userInput) {
        super(userInput);
    }

    private void checkTaskNamePresent(String delimiter) throws DukeException {
        if (super.checkEmptyString(getStringArray(), 2) || extractTaskName(delimiter).trim().equals("")) {
            throw new DukeException("Uh-oh! Please enter a valid description/name for your task!");
        }
    }

    /**
     * Ensures task name is specified and of the correct format.
     * No illegal delimiters used to write to file is allowed in task names.
     *
     * @param taskType Type of the task to be checked.
     * @return Extracted task name.
     * @throws DukeException If the task name is not present or illegal characters are present.
     */
    public String checkTaskString(TaskType taskType) throws DukeException {
        checkTaskNamePresent(taskType.getDelimiter());
        super.checkNoIllegalCharacters(extractTaskName(taskType.getDelimiter()));
        return extractTaskName(taskType.getDelimiter());
    }

    private String extractTaskName(String delimiter) {
        if (delimiter.equals("")) {
            return Arrays.stream(getStringArray()).skip(1).collect(Collectors.joining(" "));
        }
        return Arrays.stream(getStringArray()).takeWhile(e -> !e.equals(delimiter))
                .skip(1).collect(Collectors.joining(" "));
    }

}
