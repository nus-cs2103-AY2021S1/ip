package duke.parser;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.CommandTypes;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListAllCompletedTasksCommand;
import duke.commands.ListAllUncompletedTasksCommand;
import duke.commands.ListTasksCommand;
import duke.commands.OverdueCommand;
import duke.commands.TodayTasksCommand;

import duke.exceptions.DukeException;

import duke.task.Deadline;
import duke.task.DukeDateTime;
import duke.task.Event;
import duke.task.Todo;

import duke.utils.Messages;

/**
 * Represents a parser which will parse user input into its respective commands.
 */

public class CommandLineInterfaceParser {

    public static Command parse(String userInput) throws DukeException {
        try {
            String[] words = userInput.toLowerCase().split(" ");
            CommandTypes commandType = CommandTypes.valueOf(words[0].toUpperCase());
            commandType.checkInput(userInput.toLowerCase());
            String content;
            switch (commandType) {
            case LIST:
                return newListTasksCommand();
            case BYE:
                return newExitCommand();
            case TODO:
                content = userInput.replaceFirst("^(?i)todo", "");
                return newAddTaskCommand(CommandTypes.TODO, content);
            case DEADLINE:
                content = userInput.replaceFirst("^(?i)deadline", "");
                return newAddTaskCommand(CommandTypes.DEADLINE, content);
            case EVENT:
                content = userInput.replaceFirst("^(?i)event", "");
                return newAddTaskCommand(CommandTypes.EVENT, content);
            case DONE:
                int completedTaskIndex = Integer.parseInt(words[1]);
                return newDoneCommand(completedTaskIndex);
            case DELETE:
                int deletedTaskIndex = Integer.parseInt(words[1]);
                return newDeleteCommand(deletedTaskIndex);
            case FIND:
                content = userInput.replaceFirst("^(?i)find", "");
                return newFindCommand(content);
            case TODAY:
                return newTodayTasksCommand();
            case UNCOMPLETED:
                return newListAllUncompletedTasksCommand();
            case COMPLETED:
                return newListAllCompletedTasksCommand();
            case OVERDUE:
                return newOverdueCommand();
            default:
                assert false : Messages.INVALID_COMMAND_ASSERTIONS;
                break;
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException(Messages.INVALID_COMMAND_INPUT_MESSAGE);
        }
        return newInvalidCommand(Messages.INVALID_COMMAND_INPUT_MESSAGE);
    }

    private static ListTasksCommand newListTasksCommand() {
        return new ListTasksCommand();
    }

    private static ExitCommand newExitCommand() {
        return new ExitCommand();
    }

    private static DoneCommand newDoneCommand(int completedTaskIndex) throws DukeException {
        try {
            DoneCommand doneCommand = new DoneCommand(completedTaskIndex);
            return doneCommand;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Messages.INVALID_TASK_INDEX_ERROR_MESSAGE);
        }
    }

    private static DeleteCommand newDeleteCommand(int deletedTaskIndex) throws DukeException {
        try {
            DeleteCommand deleteCommand = new DeleteCommand(deletedTaskIndex);
            return deleteCommand;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Messages.INVALID_TASK_INDEX_ERROR_MESSAGE);
        }
    }

    private static AddCommand newAddTaskCommand(CommandTypes taskType, String userInputContent) throws DukeException {
        assert taskType == CommandTypes.TODO || taskType == CommandTypes.EVENT || taskType == CommandTypes.DEADLINE
                : "Command should either be a TODO, EVENT or DEADLINE command";
        switch (taskType) {
        case TODO:
            String trimmedContent = userInputContent.trim();
            return new AddCommand(new Todo(trimmedContent));
        case EVENT:
            String[] userInputEventArgs = userInputContent.split("\\s*/at\\s*");
            String content = userInputEventArgs[0].trim();
            String eventDateTime = userInputEventArgs[1];
            DukeDateTime eventDukeDateTime = DateTimeParser.parseDateTime(eventDateTime);
            AddCommand addEvent = new AddCommand(new Event(content, eventDukeDateTime));
            return addEvent;
        case DEADLINE:
            String[] userInputDeadlineArgs = userInputContent.split("\\s*/by\\s*");
            content = userInputDeadlineArgs[0].trim();
            String deadlineDateTime = userInputDeadlineArgs[1];
            DukeDateTime deadlineDukeDateTime = DateTimeParser.parseDateTime(deadlineDateTime);
            AddCommand addDeadline = new AddCommand(new Deadline(content, deadlineDukeDateTime));
            return addDeadline;
        default:
            assert false : Messages.INVALID_COMMAND_ASSERTIONS;
            throw new DukeException(Messages.INVALID_COMMAND_INPUT_MESSAGE);
        }
    }

    private static FindCommand newFindCommand(String keyword) {
        String trimmedKeyword = keyword.trim();
        FindCommand newFindCommand = new FindCommand(trimmedKeyword);
        return newFindCommand;
    }

    private static TodayTasksCommand newTodayTasksCommand() {
        return new TodayTasksCommand();
    }

    private static ListAllUncompletedTasksCommand newListAllUncompletedTasksCommand() {
        return new ListAllUncompletedTasksCommand();
    }

    private static ListAllCompletedTasksCommand newListAllCompletedTasksCommand() {
        return new ListAllCompletedTasksCommand();
    }

    private static OverdueCommand newOverdueCommand() {
        return new OverdueCommand();
    }

    private static InvalidCommand newInvalidCommand(String errorMessage) {
        return new InvalidCommand(errorMessage);
    }
}
