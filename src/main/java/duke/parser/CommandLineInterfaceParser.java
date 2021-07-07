package duke.parser;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.CommandTypes;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListAllCompletedTasksCommand;
import duke.commands.ListAllUncompletedTasksCommand;
import duke.commands.ListTasksCommand;
import duke.commands.OverdueCommand;
import duke.commands.SetTagCommand;
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

    /**
     * Parses the user input and returns the respective {@code Command} object.
     *
     * @param userInput the input the user keys into the command line interface.
     * @return the {@code Command} object which represents the user input.
     * @throws DukeException If the user incorrectly inputs a wrong command or a command with incorrect format.
     */
    public static Command parse(String userInput) throws DukeException {
        try {
            String[] words = userInput.toLowerCase().split(" ");
            CommandTypes commandType = CommandTypes.valueOf(words[0].toUpperCase());
            commandType.checkInput(userInput.toLowerCase());
            switch (commandType) {
            case LIST:
                return newListTasksCommand();
            case BYE:
                return newExitCommand();
            case TODO:
                return newAddTaskCommand(CommandTypes.TODO, userInput);
            case DEADLINE:
                return newAddTaskCommand(CommandTypes.DEADLINE, userInput);
            case EVENT:
                return newAddTaskCommand(CommandTypes.EVENT, userInput);
            case DONE:
                int completedTaskIndex = Integer.parseInt(words[1]);
                return newDoneCommand(completedTaskIndex);
            case DELETE:
                int deletedTaskIndex = Integer.parseInt(words[1]);
                return newDeleteCommand(deletedTaskIndex);
            case FIND:
                return newFindCommand(userInput);
            case TODAY:
                return newTodayTasksCommand();
            case UNCOMPLETED:
                return newListAllUncompletedTasksCommand();
            case COMPLETED:
                return newListAllCompletedTasksCommand();
            case OVERDUE:
                return newOverdueCommand();
            case TAG:
                int taskToBeTaggedIndex = Integer.parseInt(words[1]);
                String tagName = words[2];
                return newSetTagCommand(taskToBeTaggedIndex, tagName);
            case HELP:
                return newHelpCommand();
            default:
                assert false : Messages.INVALID_COMMAND_ASSERTIONS;
                break;
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException(Messages.INVALID_COMMAND_INPUT_MESSAGE);
        }
        throw new DukeException(Messages.INVALID_COMMAND_INPUT_MESSAGE);
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

    private static AddCommand newAddTaskCommand(CommandTypes taskType, String userInput) throws DukeException {
        assert taskType == CommandTypes.TODO || taskType == CommandTypes.EVENT || taskType == CommandTypes.DEADLINE
                : "Command should either be a TODO, EVENT or DEADLINE command";
        switch (taskType) {
        case TODO:
            String todoContent = userInput.replaceFirst("^(?i)todo", "");
            String trimmedContent = todoContent.trim();
            return new AddCommand(new Todo(trimmedContent));
        case EVENT:
            String eventContent = userInput.replaceFirst("^(?i)event", "");
            String[] userInputEventArgs = eventContent.split("\\s*/at\\s*");
            String trimmedEventContent = userInputEventArgs[0].trim();
            String eventDateTime = userInputEventArgs[1];
            DukeDateTime eventDukeDateTime = DateTimeParser.parseDateTime(eventDateTime);
            AddCommand addEvent = new AddCommand(new Event(trimmedEventContent, eventDukeDateTime));
            return addEvent;
        case DEADLINE:
            String deadlineContent = userInput.replaceFirst("^(?i)deadline", "");
            String[] userInputDeadlineArgs = deadlineContent.split("\\s*/by\\s*");
            String trimmedDeadlineContent = userInputDeadlineArgs[0].trim();
            String deadlineDateTime = userInputDeadlineArgs[1];
            DukeDateTime deadlineDukeDateTime = DateTimeParser.parseDateTime(deadlineDateTime);
            AddCommand addDeadline = new AddCommand(new Deadline(trimmedDeadlineContent, deadlineDukeDateTime));
            return addDeadline;
        default:
            assert false : Messages.INVALID_COMMAND_ASSERTIONS;
            throw new DukeException(Messages.INVALID_COMMAND_INPUT_MESSAGE);
        }
    }

    private static FindCommand newFindCommand(String userInput) {
        String keyword = userInput.replaceFirst("^(?i)find", "");
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

    private static SetTagCommand newSetTagCommand(int taskToBeTaggedIndex, String tagName) {
        String tagNameWithoutHex = tagName.replaceAll("[^a-zA-Z0-9]", " ").trim();
        SetTagCommand newSetTagCommand = new SetTagCommand(taskToBeTaggedIndex, tagNameWithoutHex);
        return newSetTagCommand;
    }

    private static HelpCommand newHelpCommand() {
        return new HelpCommand();
    }
}
