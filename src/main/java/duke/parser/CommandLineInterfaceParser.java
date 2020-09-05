package duke.parser;

import duke.commands.*;

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
                String trimmedContent = content.trim();
                AddCommand addTask = new AddCommand(new Todo(trimmedContent));
                return addTask;
            case DEADLINE:
                userInput = userInput.replaceFirst("^(?i)deadline", "");
                return newAddTaskCommand(CommandTypes.DEADLINE, userInput);
            case EVENT:
                userInput = userInput.replaceFirst("^(?i)event", "");
                return newAddTaskCommand(CommandTypes.EVENT, userInput);
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
                throw new DukeException(Messages.INVALID_COMMAND_INPUT_MESSAGE);
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException(Messages.INVALID_COMMAND_INPUT_MESSAGE);
        }
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
}
