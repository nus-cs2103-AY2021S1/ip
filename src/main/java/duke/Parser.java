package duke;

import java.time.LocalDate;

import duke.task.TaskType;

/**
 * Encapsulates the parsing method of the Duke program.
 */
public class Parser {
    /**
     * Parses the input into a Command
     *
     * @param input the input command from the user
     * @return a command that can be executed
     */
    public static Command parse(String input) {
        String[] splitInput = input.split(" ");

        TaskType taskType = TaskType.of(splitInput[0]);

        switch (taskType) {
        case LIST:
            // Fallthrough
        case BYE:
            return new Command(taskType);
        case DELETE:
            // Fallthrough
        case DONE:
            if (splitInput.length < 2 || !splitInput[1].matches("\\d+")) {
                throw new IllegalArgumentException("The index of the task must be provided.");
            }
            int index = Integer.parseInt(splitInput[1]);
            return new Command(taskType, index);
        case FIND:
            if (input.length() <= 5 || input.split(" ").length < 2) {
                throw new IllegalArgumentException("The description of this TaskType cannot be empty.");
            }
            return new Command(taskType, input.substring(5));
        case TODO:
            int indexOfTakes = input.indexOf(" /takes ");
            if (splitInput.length < 2) {
                throw new IllegalArgumentException("The description of this kind of task cannot be empty.");
            }
            int hours = Integer.parseInt(input.substring(indexOfTakes + 8).split(" ")[0]);
            return new Command(taskType, input.substring(splitInput[0].length() + 1, indexOfTakes), hours);
        case DEADLINE:
            // Fallthrough
        case EVENT:
            int indexOfBy = input.indexOf(taskType == TaskType.EVENT ? " /at " : " /by ");
            if (splitInput.length < 2) {
                throw new IllegalArgumentException("The description of this kind of task cannot be empty.");
            }
            String dateString = input.substring(indexOfBy + 5);
            if (indexOfBy < 0 || dateString.split(" ").length <= 0) {
                throw new IllegalArgumentException(
                    "A date needs to be provided with /by or /at.\n"
                        + "Usage: deadline return book /by 2020-12-30 OR event library renewal /at 2020-12-29");
            }
            LocalDate date = LocalDate.parse(dateString);
            return new Command(taskType, input.substring(splitInput[0].length() + 1, indexOfBy), date);
        default:
            throw new IllegalArgumentException("Your input must start with one of the following:\n"
                + "\"todo\", \"deadline\", \"event\", \"list\", \"done\" or \"delete\".");
        }
    }
}
