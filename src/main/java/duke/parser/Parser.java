package duke.parser;

import duke.command.CloneCommand;
import duke.command.CloseCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.StartCommand;
import duke.command.ToDoCommand;
import duke.command.UndoCommand;
import duke.command.UpdateCommand;
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
        String[] splitInput = fullCommand.trim().split("\\s+", 2);

        boolean hasNoFollowUp = splitInput.length == 1 || splitInput.equals("");

        // command: hello
        boolean hasStartSpecifier = splitInput[0].equals(StartCommand.COMMAND);
        boolean isStartCommand = hasStartSpecifier && hasNoFollowUp;
        // command: bye
        boolean hasExitSpecifier = splitInput[0].equals(ExitCommand.COMMAND);
        boolean isExitCommand = hasExitSpecifier && hasNoFollowUp;
        // command: list
        boolean hasListSpecifier = splitInput[0].equals(ListCommand.COMMAND);
        boolean isListCommand = hasListSpecifier && hasNoFollowUp;
        // command: delete [index]
        boolean isDeleteCommand = splitInput[0].equals(DeleteCommand.COMMAND);
        // command: done [index]
        boolean isDoneCommand = splitInput[0].equals(DoneCommand.COMMAND);
        //command: find [keyword(s)]
        boolean isFindCommand = splitInput[0].equals(FindCommand.COMMAND);
        // command: todo [description]
        boolean isTodoCommand = splitInput[0].equals(ToDoCommand.COMMAND);
        // command: deadline [description] /by [time]
        // or: event [description] /at [time]
        boolean isDeadlineCommand = splitInput[0].equals(DeadlineCommand.COMMAND);
        boolean isEventCommand = splitInput[0].equals(EventCommand.COMMAND);
        // command: undo
        boolean hasUndoSpecifier = splitInput[0].equals(UndoCommand.COMMAND);
        boolean isUndoCommand = hasUndoSpecifier && hasNoFollowUp;
        // command: clone [source index] or clone [source index] [destination index]
        boolean isCloneCommand = splitInput[0].equals(CloneCommand.COMMAND);
        // command: update [index] <optional>/description [description] <optional>/time [time]
        boolean isUpdateCommand = splitInput[0].equals(UpdateCommand.COMMAND);
        // command: close
        boolean hasCloseSpecifier = splitInput[0].equals(CloseCommand.COMMAND);
        boolean isCloseCommand = hasCloseSpecifier && hasNoFollowUp;

        if (isStartCommand) { // command: hello
            return createStartCommand();
        } else if (isExitCommand) { // command: bye
            return createExitCommand();
        } else if (isListCommand) { // command : list
            return createListCommand();
        } else if (isDeleteCommand) { // command: delete
            return createDeleteCommand(splitInput);
        } else if (isDoneCommand) { // command: done [index]
            return createDoneCommand(splitInput);
        } else if (isFindCommand) { //command: find [keyword(s)]
            return createFindCommand(splitInput);
        } else if (isTodoCommand) { // command: todo [description]
            return createTodoCommand(splitInput);
        } else if (isDeadlineCommand || isEventCommand) { // command: deadline [description] /by [time]
            // or: event [description] /at [time]
            return createDeadlineOrEventCommand(splitInput);
        } else if (isUndoCommand) { // command: undo
            return createUndoCommand();
        } else if (isCloneCommand) { // command: clone [source index] or clone [source index] [destination index]
            return createCloneCommand(splitInput);
        } else if (isUpdateCommand) { // command: update [index] (/description [description]) (/time [time])
            return createUpdateCommand(splitInput);
        } else if (isCloseCommand) { // command: close
            return createCloseCommand();
        } else { // unrecognised command
            return new Command();
        }
    }

    private static Command createCloseCommand() {
        return new CloseCommand();
    }

    private static Command createUpdateCommand(String[] splitInput) throws DukeException {
        assert splitInput.length >= 0 && splitInput.length <= 2 : "size of an array can be lesser than 0";
        if (splitInput.length == 1) {
            throw new DukeException("Missing index and specifier(s)");
        }
        String[] splitInfo = splitInput[1].trim().split("\\s+", 2);
        if (splitInfo.length == 1) {
            throw new DukeException("Missing specifier(s)");
        }
        try {
            int index = Integer.parseInt(splitInfo[0]) - 1;
            return new UpdateCommand(index, splitInfo[1]);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    private static Command createUndoCommand() {
        return new UndoCommand();
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

            String description = content.substring(0, timeIdx).trim();
            String time = content.substring(timeIdx + 5).trim();

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
        }
        return new FindCommand(splitInput[1].split("\\s+"));
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

    private static Command createCloneCommand(String[] splitInput) throws InvalidIndexException {
        if (splitInput.length == 1) {
            throw new InvalidIndexException();
        }
        return new CloneCommand(splitInput[1]);
    }
}
