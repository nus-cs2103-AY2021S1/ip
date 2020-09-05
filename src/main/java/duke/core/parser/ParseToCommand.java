package duke.core.parser;

import duke.core.DataStore;
import duke.core.command.AddCommand;
import duke.core.command.RedoCommand;
import duke.core.command.UndoCommand;
import duke.designpattern.command.Executable;
import duke.core.command.DeleteCommand;
import duke.core.command.DoneCommand;
import duke.core.command.ExitCommand;
import duke.core.command.FindCommand;
import duke.core.command.HelpCommand;
import duke.core.command.ListCommand;
import duke.core.command.LoadCommand;
import duke.core.command.SaveCommand;
import duke.core.task.Deadline;
import duke.core.task.Event;
import duke.core.task.Task;
import duke.core.task.ToDo;
import duke.core.util.DukeDateTime;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Enumeration of available commands
 * Contains parse() method to create command from parameters
 */
public enum ParseToCommand {

    HELP {
        @Override
        protected Executable parseParameter(DataStore dataStore, String commandParameter) {
            return new HelpCommand();
        }
    },

    BYE {
        @Override
        protected Executable parseParameter(DataStore dataStore, String commandParameter) {
            return new ExitCommand();
        }
    },

    LIST {
        @Override
        protected Executable parseParameter(DataStore dataStore, String commandParameter) {
            return new ListCommand(dataStore.getTaskList());
        }
    },

    DONE {
        @Override
        protected Executable parseParameter(DataStore dataStore, String commandParameter) {
            // Generate a DoneCommand which when executed, marks the task specified by the index as done
            try {
                int index = Integer.parseInt(commandParameter.trim()) - 1;
                Task task = dataStore.getTaskList().get(index);
                return new DoneCommand(task);
            } catch (NumberFormatException e) {
                throw new DukeParserException("Format: done {index}");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeParserException("Index Error: done {index}");
            }
        }
    },

    DELETE {
        @Override
        protected Executable parseParameter(DataStore dataStore, String commandParameter) {
            // Generate a DeleteCommand which when executed, removes the task specified by the index from the list
            try {
                int index = Integer.parseInt(commandParameter.trim()) - 1;
                Task task = dataStore.getTaskList().get(index);
                return new DeleteCommand(dataStore.getTaskList(), task);
            } catch (NumberFormatException e) {
                throw new DukeParserException("Format: delete {index}");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeParserException("Index Error: delete {index}");
            }
        }
    },

    TODO {
        @Override
        protected Executable parseParameter(DataStore dataStore, String commandParameter) {
            if (commandParameter.isBlank()) {
                throw new DukeParserException("Format: todo {description}");
            }

            // Generate an AddCommand which when executed, adds
            // the new To-Do task to the taskList
            Task task = new ToDo(commandParameter.trim());
            return new AddCommand(dataStore.getTaskList(), task);
        }
    },

    DEADLINE {
        @Override
        protected Executable parseParameter(DataStore dataStore, String commandParameter) {
            String usage = "deadline {description} /by {ddMMyyyy HHmm}";

            // Regex to obtain param1 and param2 in Deadline(description, deadline)
            // param1: Description (String class)
            // param2: Deadline (DukeDateTime class)
            Pattern pattern = Pattern.compile("^(.+)/by(.+)$");
            Matcher matcher = pattern.matcher(commandParameter);

            // Ensure that both param1 and param2 exists
            if (!matcher.matches()
                    || matcher.group(1).isBlank()
                    || matcher.group(2).isBlank()) {
                throw new DukeParserException("Format: " + usage);
            }

            try {
                // Generate an AddCommand which when executed, adds the newly created Deadline to taskList
                Task task = new Deadline(
                        matcher.group(1).trim(),
                        new DukeDateTime(matcher.group(2).trim())
                );
                return new AddCommand(dataStore.getTaskList(), task);
            } catch (DateTimeParseException e) {
                throw new DukeParserException("DateTime Error: " + usage);
            }
        }
    },

    EVENT {
        @Override
        protected Executable parseParameter(DataStore dataStore, String commandParameter) {
            String usage = "event {description} /from {ddMMyyyy HHmm} /till {ddMMyyyy HHmm}";

            // Regex to obtain param1, param2 and param3 in Event(description, start, end)
            // param1: Description (String class)
            // param2: start (DukeDateTime class)
            // param3: end (DukeDateTime class)
            Pattern pattern = Pattern.compile("^(.+)/from(.+)/till(.+)$");
            Matcher matcher = pattern.matcher(commandParameter);

            // Ensure that both param1, param2 and param3 exists
            if (!matcher.matches()
                    || matcher.group(1).isBlank()
                    || matcher.group(2).isBlank()
                    || matcher.group(3).isBlank()) {
                throw new DukeParserException("Format: " + usage);
            }

            try {
                // Generate an AddCommand which when executed, adds the newly created Event to taskList
                Task task = new Event(
                        matcher.group(1).trim(),
                        new DukeDateTime(matcher.group(2).trim()),
                        new DukeDateTime(matcher.group(3).trim()));
                return new AddCommand(dataStore.getTaskList(), task);
            } catch (DateTimeParseException e) {
                throw new DukeParserException("DateTime Error: " + usage);
            }
        }
    },

    SAVE {
        @Override
        protected Executable parseParameter(DataStore dataStore, String commandParameter) {
            if (commandParameter.isBlank()) {
                throw new DukeParserException("Format: save {filepath}");
            }

            // Generate a SaveCommand which when executed, saves the taskList into specified file path
            return new SaveCommand(dataStore.getTaskList(), commandParameter.trim());
        }
    },

    LOAD {
        @Override
        protected Executable parseParameter(DataStore dataStore, String commandParameter) {
            if (commandParameter.isBlank()) {
                throw new DukeParserException("Format: load {filepath}");
            }

            // Generate a LoadCommand which when executed, loads data from specified file path into taskList
            return new LoadCommand(dataStore, commandParameter.trim());
        }
    },

    FIND {
        @Override
        protected Executable parseParameter(DataStore dataStore, String commandParameter) {
            if (commandParameter.isBlank()) {
                throw new DukeParserException("Format: find {keyword}");
            }

            // Generate a FindCommand which when executed, search for all instance of searchString in taskList
            return new FindCommand(dataStore.getTaskList(), commandParameter.trim());
        }
    },

    UNDO {
        @Override
        protected Executable parseParameter(DataStore dataStore, String commandParameter) throws DukeParserException {
            return new UndoCommand(dataStore.getHistory());
        }
    },

    REDO {
        @Override
        protected Executable parseParameter(DataStore dataStore, String commandParameter) throws DukeParserException {
            return new RedoCommand(dataStore.getHistory());
        }
    };

    /**
     * Parse the commandParameter based on the commandType.
     * @param dataStore The data which the Duke Command will execute on
     * @param commandParameter Command parameters needed to construct the Command (As required)
     * @return A Command which executes on the dataStore
     * @throws DukeParserException if the input cannot be parsed. Details about the error can be
     * retrieved by the Throwable.getMessage() method
     */
    protected abstract Executable parseParameter(DataStore dataStore, String commandParameter)
            throws DukeParserException;

    /**
     * Parse user input and convert it into an executable command.
     * The resulting command, when will execute on the data in DataStore
     * @param dataStore The data which the Duke Command will execute on
     * @param input The raw user input
     * @return A Command which executes on the dataStore
     * @throws DukeParserException if the input cannot be parsed. Details about the error can be
     * retrieved by the Throwable.getMessage() method
     */
    public static Executable parse(DataStore dataStore, String input) throws DukeParserException {

        // Match the input pattern
        Pattern pattern = Pattern.compile("^\\s*(\\S+)\\s*(.*)$");
        Matcher matcher = pattern.matcher(input);

        // No input received
        if (!matcher.matches()) {
            throw new DukeParserException("Empty input!");
        }

        // Extract commandType and commandParameter
        String commandType = matcher.group(1).toUpperCase();
        String commandParam = matcher.group(2);

        try {
            // Generate duke.core.command with commandType and commandParameter
            return ParseToCommand.valueOf(commandType).parseParameter(dataStore, commandParam);
        } catch (IllegalArgumentException e) {
            throw new DukeParserException("Unrecognised Command!");
        }
    }
}
