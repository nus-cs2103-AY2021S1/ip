package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.InadequateCommandException;
import duke.exception.InvalidIndexException;

/**
 * Parses the input from the user.
 */
public class Parser {
    /**
     * Parses the input from the user
     * @param fullCommand The input from the user
     * @return A <code>Command</code> object that can later be executed
     * @throws DukeException If the parsing process faces any problem
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] splitInput = fullCommand.split("\\s+", 2);

        // command: hello
        boolean isStartCommand = splitInput[0].equals(StartCommand.COMMAND);
        if (isStartCommand) {
            return createStartCommand();
        }

        // command: bye
        boolean isExitCommand = splitInput[0].equals(ExitCommand.COMMAND);
        if (isExitCommand) {
            return createExitCommand();
        }

        // command: list
        boolean isListCommand = splitInput[0].equals(ListCommand.COMMAND);
        if (isListCommand) {
            return createListCommand();
        }

        // command: delete [index]
        boolean isDeleteCommand = splitInput[0].equals(DeleteCommand.COMMAND);
        if (isDeleteCommand) {
            return createDeleteCommand(splitInput);
        }

        // command: done [index]
        boolean isDoneCommand = splitInput[0].equals(DoneCommand.COMMAND);
        if (isDoneCommand) {
            return createDoneCommand(splitInput);
        }

        //command: find [keyword(s)]
        boolean isFindCommand = splitInput[0].equals(FindCommand.COMMAND);
        if (isFindCommand) {
            return createFindCommand(splitInput);
        }

        // command: todo [description]
        boolean isTodoCommand = splitInput[0].equals(ToDoCommand.COMMAND);
        if (isTodoCommand) {
            return createTodoCommand(splitInput);
        }

        // command: deadline [description] /by [time]
        // or: event [description] /at [time]
        boolean isDeadlineCommand = splitInput[0].equals(DeadlineCommand.COMMAND);
        boolean isEventCommand = splitInput[0].equals(EventCommand.COMMAND);
        if (isDeadlineCommand || isEventCommand) {
            return createDeadlineOrEventCommand(splitInput);
        }
        return new Command();
    }

    public static Command createStartCommand() {
        return new StartCommand();
    }

    private static Command createDeadlineOrEventCommand(String[] splitInput) throws InadequateCommandException {
        String type;
        String timeSpecifier;
        boolean isDeadline;
        if (splitInput[0].equals(DeadlineCommand.COMMAND)) {
            type = "deadline";
            timeSpecifier = DeadlineCommand.TIME_SPECIFIER;
            isDeadline = true;
        } else {
            type = "event";
            timeSpecifier = EventCommand.TIME_SPECIFIER;
            isDeadline = false;
        }

        if (splitInput.length == 1) {
            throw new InadequateCommandException(type, "description", "time");
        } else {
            String content = splitInput[1];
            String[] split2Test = content.split("\\s+");
            int timeIdx = content.indexOf(" " + timeSpecifier);
            if (split2Test.length == 0 || (
                    split2Test.length == 1
                            && (split2Test[0].equals(timeSpecifier) || split2Test[0].equals(""))
                    )
            ) {
                String[] missing = {"description", "time"};
                throw new InadequateCommandException(type, missing);
            }

            if (timeIdx == 0 || content.indexOf(timeSpecifier) == 0) {
                throw new InadequateCommandException(type, "description");
            }

            if (timeIdx == -1 || timeIdx + 5 >= content.length()) {
                throw new InadequateCommandException(type, "time");
            }

            String description = content.substring(0, timeIdx);
            String time = content.substring(timeIdx + 5);

            if (time.split("\\s+").length == 0) {
                throw new InadequateCommandException(type, "time");
            }

            // return
            if (isDeadline) {
                return new DeadlineCommand(description, time);
            } else {
                return new EventCommand(description, time);
            }
        }
    }

    private static Command createTodoCommand(String[] splitInput) throws InadequateCommandException {
        if (splitInput.length == 1 || splitInput[1].equals("")) {
            throw new InadequateCommandException("todo", "description");
        }
        return new ToDoCommand(splitInput[1]);
    }

    private static Command createFindCommand(String[] splitInput) throws DukeException {
        if (splitInput.length == 1) {
            throw new DukeException("Missing keyword");
        } else {
            return new FindCommand(splitInput[1].split("\\s+"));
        }
    }

    private static Command createDoneCommand(String[] splitInput) throws InvalidIndexException {
        if (splitInput.length == 1) {
            throw new InvalidIndexException();
        }
        return new DoneCommand(splitInput[1]);
    }

    private static Command createDeleteCommand(String[] splitInput) throws InvalidIndexException {
        if (splitInput.length == 1) {
            throw new InvalidIndexException();
        }
        return new DeleteCommand(splitInput[1]);
    }

    private static Command createListCommand() {
        return new ListCommand();
    }

    private static Command createExitCommand() {
        return new ExitCommand();
    }
}
