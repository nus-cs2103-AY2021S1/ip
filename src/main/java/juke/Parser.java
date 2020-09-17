package juke;

import juke.command.Command;
import juke.command.DeleteCommand;
import juke.command.DoneCommand;
import juke.command.ErrorCommand;
import juke.command.ExitCommand;
import juke.command.FindCommand;
import juke.command.ListCommand;
import juke.command.TaskCommand;
import juke.command.update.UpdateCommand;
import juke.command.update.UpdateDateCommand;
import juke.command.update.UpdateDescriptionAndDateCommand;
import juke.command.update.UpdateDescriptionCommand;
import juke.exception.EmptyDescriptionException;
import juke.exception.EmptyTimeException;
import juke.exception.InvalidArgumentsException;
import juke.exception.UnknownCommandException;
import juke.exception.UnknownTaskException;
import juke.exception.UnknownTimeException;
import juke.task.Deadline;
import juke.task.Event;
import juke.task.Todo;

/**
 * Represents the Parser class, that interprets user input commands.
 */
public class Parser {

    /**
     * Interprets user input text, into understandable commands.
     *
     * @param inputText Commands input by user.
     * @return Command understood by Chatbot.
     */
    protected Command commandHandler(String inputText) {
        try {
            String[] splitArr = inputText.split(" ");
            String command = splitArr[0];

            switch (command) {
            case ("find"):
                return this.handleFindCommand(splitArr);
            case ("done"):
                return this.handleDoneCommand(splitArr);
            case ("list"):
                return this.handleListCommand();
            case ("bye"):
                return this.handleExitCommand();
            case ("delete"):
                return this.handleDeleteCommand(splitArr);
            case ("todo"):
                return this.handleTodoCommand(inputText);
            case ("deadline"):
                return this.handleDeadlineCommand(inputText, splitArr);
            case ("event"):
                return this.handleEventCommand(inputText, splitArr);
            case ("update"):
                return this.handleUpdateCommand(inputText);
            default:
                throw new UnknownCommandException("Unknown command entered");
            }

        } catch (EmptyDescriptionException empty) {
            return new ErrorCommand("Mate, you've gotta let me know what you're gonna be doing.");
        } catch (UnknownCommandException com) {
            return new ErrorCommand("Um, are you sure that's not gibberish?");
        } catch (UnknownTimeException by) {
            return new ErrorCommand("You've gotta let me know the time.");
        } catch (EmptyTimeException at) {
            return new ErrorCommand("There has to be a time, surely. Don't leave it blank!");
        } catch (UnknownTaskException ex) {
            return new ErrorCommand("C'mon, I don't live in your head, you gotta tell me the task number!");
        } catch (InvalidArgumentsException ex) {
            return new ErrorCommand("You haven't provided the needed arguments, dumbo!");
        }
    }

    private FindCommand handleFindCommand(String[] splitArr) {
        String keyword = splitArr[1];
        return new FindCommand(keyword);
    }

    private ExitCommand handleExitCommand() {
        return new ExitCommand();
    }

    private ListCommand handleListCommand() {
        return new ListCommand();
    }

    private DoneCommand handleDoneCommand(String[] splitArr) throws UnknownTaskException {
        if (splitArr.length == 1) {
            throw new UnknownTaskException("No task number entered");
        }

        int taskNo = Integer.parseInt(splitArr[1]) - 1;
        return new DoneCommand(taskNo);
    }

    private DeleteCommand handleDeleteCommand(String[] splitArr) throws UnknownTaskException {
        if (splitArr.length == 1) {
            throw new UnknownTaskException("No task number entered");
        }

        int taskNo = Integer.parseInt(splitArr[1]) - 1;
        return new DeleteCommand(taskNo);
    }

    private TaskCommand handleTodoCommand(String inputText) throws EmptyDescriptionException {
        if (inputText.length() <= 5) {
            throw new EmptyDescriptionException("No Description entered");
        }

        String description = inputText.substring(5);
        return new TaskCommand(new Todo(description));
    }

    private TaskCommand handleEventCommand(String inputText, String[] splitArr) throws EmptyDescriptionException,
            UnknownTimeException, EmptyTimeException {
        if (inputText.length() <= 6) {
            throw new EmptyDescriptionException("No Description entered");
        } else if (splitArr.length == 1) {
            throw new UnknownTimeException("No at time added");
        }

        String[] newSplitArr = inputText.substring(6).split(" /at ");

        if (newSplitArr.length == 1) {
            throw new EmptyTimeException("No time entered");
        }

        String description = newSplitArr[0];
        String at = newSplitArr[1];
        return new TaskCommand(new Event(description, at));
    }

    private TaskCommand handleDeadlineCommand(String inputText, String[] splitArr) throws EmptyDescriptionException,
            UnknownTimeException, EmptyTimeException {
        if (inputText.length() <= 9) {
            throw new EmptyDescriptionException("No Description entered");
        } else if (splitArr.length == 1) {
            throw new UnknownTimeException("No by time added");
        }

        String[] newSplitArr = inputText.substring(9).split(" /by ");

        if (newSplitArr.length == 1) {
            throw new EmptyTimeException("No time entered");
        }

        String description = newSplitArr[0];
        String by = newSplitArr[1];
        return new TaskCommand(new Deadline(description, by));
    }

    private UpdateCommand handleUpdateCommand(String inputText) throws InvalidArgumentsException {

        String[] splitByIndex = inputText.split("/number ");

        if (splitByIndex.length == 1) {
            throw new InvalidArgumentsException("Index argument not provided");
        }

        String[] splitByDescription = inputText.split("/description ");
        String[] splitByDate = inputText.split("/date ");

        boolean isUpdateDate = splitByDate.length > 1;
        boolean isUpdateDescription = splitByDescription.length > 1;
        boolean isUpdateBothDateAndDescription = isUpdateDescription && isUpdateDate;

        int index = Integer.parseInt(splitByIndex[1].split(" /")[0]) - 1;

        if (isUpdateBothDateAndDescription) {
            String date = splitByDate[1].split("/")[0];
            String description = splitByDescription[1].split("/")[0];
            return new UpdateDescriptionAndDateCommand(index, description, date);
        } else if (isUpdateDate) {
            String date = splitByDate[1].split("/")[0];
            return new UpdateDateCommand(index, date);
        } else if (isUpdateDescription) {
            String description = splitByDescription[1].split("/")[0];
            return new UpdateDescriptionCommand(index, description);
        } else {
            throw new InvalidArgumentsException("Arguments not provided");
        }
    }
}
