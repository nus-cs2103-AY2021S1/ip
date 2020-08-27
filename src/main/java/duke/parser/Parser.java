package duke.parser;

import java.time.LocalDate;

import duke.DukeException;
import duke.ExceptionTypeEnum;
import duke.command.*;
import duke.task.*;

public class Parser {

    /**
     * Parses a line of user input, splits it via regex (~), creates a Command object with required information
     * and returns it.
     *
     * @param fullCommand
     * @return the Command represented by the input string
     * @throws DukeException with appropriate message if command encountered is incorrect
     */
    public static Command parse(String fullCommand) throws DukeException {
        String arr[] = fullCommand.split(" ", 2);
        String command = arr[0];
        String remainingText = arr.length == 1 ? null : arr[1].trim();
        String[] taskItems;
        String description;
        Task task;
        int index;


        switch (command) {
        case "list":
            if (remainingText != null) {
                throw new DukeException(ExceptionTypeEnum.INCORRECT_LIST);
            }
            return new ListCommand();

        case "todo":
            if (remainingText == null) {
                throw new DukeException(ExceptionTypeEnum.MISSING_TODO_DESCRIPTION);
            }
            task = new TodoTask(remainingText);
            return new AddCommand(task);

        case "deadline":
            if (remainingText == null) {
                throw new DukeException(ExceptionTypeEnum.MISSING_DEADLINE_DESCRIPTION);
            }
            taskItems = remainingText.split(" /by ");
            description = taskItems[0].trim();
            if (taskItems.length == 1) {
                throw new DukeException(ExceptionTypeEnum.MISSING_DEADLINE_DATE);
            }
            LocalDate by = LocalDate.parse(taskItems[1].trim());
            task = new DeadlineTask(description, by);
            return new AddCommand(task);

        case "event":
            if (remainingText == null) {
                throw new DukeException(ExceptionTypeEnum.MISSING_EVENT_DESCRIPTION);
            }
            taskItems = remainingText.split(" /at ");
            description = taskItems[0].trim();
            if (taskItems.length == 1) {
                throw new DukeException(ExceptionTypeEnum.MISSING_EVENT_DATE);
            }
            LocalDate at = LocalDate.parse(taskItems[1].trim());
            task = new EventTask(description, at);
            return new AddCommand(task);

        case "done":
            if (remainingText == null) {
                throw new DukeException(ExceptionTypeEnum.MISSING_DONE_ITEM);
            }
            index = Integer.parseInt(remainingText) - 1;
            return new DoneCommand(index);

        case "delete":
            if (remainingText == null) {
                throw new DukeException(ExceptionTypeEnum.MISSING_DELETE_ITEM);
            }
            index = Integer.parseInt(remainingText) - 1;
            return new DeleteCommand(index);

        case "bye":
            if (remainingText != null) {
                throw new DukeException(ExceptionTypeEnum.INCORRECT_BYE);
            }
            return new ByeCommand();

        default:
            throw new DukeException(ExceptionTypeEnum.UNKNOWN_COMMAND);
        }
    }
}
