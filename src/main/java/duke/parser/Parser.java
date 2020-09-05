package duke.parser;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
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
        String[] splittedInput = fullCommand.split("\\s+", 2);

        // command: bye
        if (splittedInput[0].equals(ExitCommand.COMMAND)) {
            return new ExitCommand();
        }

        // command: list
        if (splittedInput[0].equals(ListCommand.COMMAND)) {
            return new ListCommand();
        }

        // command: delete [index]
        if (splittedInput[0].equals(DeleteCommand.COMMAND)) {
            if (splittedInput.length == 1) {
                throw new InvalidIndexException();
            }
            return new DeleteCommand(splittedInput[1]);
        }

        // command: done [index]
        if (splittedInput[0].equals(DoneCommand.COMMAND)) {
            if (splittedInput.length == 1) {
                throw new InvalidIndexException();
            }
            return new DoneCommand(splittedInput[1]);
        }

        //command: find [keyword(s)]
        if (splittedInput[0].equals(FindCommand.COMMAND)) {
            if (splittedInput.length == 1) {
                throw new DukeException("Missing keyword");
            } else {
                return new FindCommand(splittedInput[1].split("\\s+"));
            }
        }

        // command: todo [description]
        if (splittedInput[0].equals(ToDoCommand.COMMAND)) {
            if (splittedInput.length == 1 || splittedInput[1].equals("")) {
                throw new InadequateCommandException("todo", new String[] {"description"});
            }
            return new ToDoCommand(splittedInput[1]);
        }

        // command: deadline [description] /by [time]
        // or: event [description] /at [time]
        if (splittedInput[0].equals(DeadlineCommand.COMMAND) || splittedInput[0].equals(EventCommand.COMMAND)) {
            String type;
            String timeSpecifier;
            boolean isDeadline;
            if (splittedInput[0].equals(DeadlineCommand.COMMAND)) {
                type = "deadline";
                timeSpecifier = DeadlineCommand.TIME_SPECIFIER;
                isDeadline = true;
            } else {
                type = "event";
                timeSpecifier = EventCommand.TIME_SPECIFIER;
                isDeadline = false;
            }

            if (splittedInput.length == 1) {
                throw new InadequateCommandException(type, new String[] {"description", "time"});
            } else {
                String content = splittedInput[1];
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
                    throw new InadequateCommandException(type, new String[]{"description"});
                }

                if (timeIdx == -1 || timeIdx + 5 >= content.length()) {
                    throw new InadequateCommandException(type, new String[]{"time"});
                }

                String description = content.substring(0, timeIdx);
                String time = content.substring(timeIdx + 5);

                if (time.split("\\s+").length == 0) {
                    throw new InadequateCommandException(type, new String[]{"time"});
                }

                // return
                if (isDeadline) {
                    return new DeadlineCommand(description, time);
                } else {
                    return new EventCommand(description, time);
                }
            }
        }
        return new Command();
    }
}
