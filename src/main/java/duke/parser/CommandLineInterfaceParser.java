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
            String dateTime;
            String[] userInputArgs;
            DukeDateTime dukeDateTime;
            switch (commandType) {
            case LIST:
                ListTasksCommand listTasks = new ListTasksCommand();
                return listTasks;
            case BYE:
                ExitCommand exitDuke = new ExitCommand();
                return exitDuke;
            case TODO:
                content = userInput.replaceFirst("^(?i)todo", "");
                String trimmedContent = content.trim();
                AddCommand addTask = new AddCommand(new Todo(trimmedContent));
                return addTask;
            case DEADLINE:
                userInput = userInput.replaceFirst("^(?i)deadline", "");
                userInputArgs = userInput.split("\\s*/by\\s*");
                content = userInputArgs[0].trim();
                dateTime = userInputArgs[1];
                dukeDateTime = DateTimeParser.parseDateTime(dateTime);
                AddCommand addDeadline = new AddCommand(new Deadline(content, dukeDateTime));
                return addDeadline;
            case EVENT:
                userInput = userInput.replaceFirst("^(?i)event", "");
                userInputArgs = userInput.split("\\s*/at\\s*");
                content = userInputArgs[0].trim();
                dateTime = userInputArgs[1];
                dukeDateTime = DateTimeParser.parseDateTime(dateTime);
                AddCommand addEvent = new AddCommand(new Event(content, dukeDateTime));
                return addEvent;
            case DONE:
                try {
                    int completedTaskIndex = Integer.parseInt(words[1]);
                    DoneCommand completedTask = new DoneCommand(completedTaskIndex);
                    return completedTask;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException(Messages.INVALID_TASK_INDEX_ERROR_MESSAGE);
                }
            case DELETE:
                try {
                    int taskIndex = Integer.parseInt(words[1]);
                    DeleteCommand deleteTask = new DeleteCommand(taskIndex);
                    return deleteTask;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException(Messages.INVALID_TASK_INDEX_ERROR_MESSAGE);
                }
            case FIND:
                content = userInput.replaceFirst("^(?i)find", "");
                trimmedContent = content.trim();
                FindCommand findTaskCommand = new FindCommand(trimmedContent);
                return findTaskCommand;
            case TODAY:
                TodayTasksCommand tasksForToday = new TodayTasksCommand();
                return tasksForToday;
            case UNCOMPLETED:
                ListAllUncompletedTasksCommand uncompletedTasksCommand = new ListAllUncompletedTasksCommand();
                return uncompletedTasksCommand;
            default:
                throw new DukeException(Messages.INVALID_COMMAND_INPUT_MESSAGE);
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException(Messages.INVALID_COMMAND_INPUT_MESSAGE);
        }
    }
}
