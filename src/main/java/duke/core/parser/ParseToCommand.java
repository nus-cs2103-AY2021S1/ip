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
        public Executable parse(DataStore dataStore, String commandParam) {
            return new HelpCommand();
        }
    },

    BYE {
        @Override
        public Executable parse(DataStore dataStore, String commandParam) {
            return new ExitCommand();
        }
    },

    LIST {
        @Override
        public Executable parse(DataStore dataStore, String commandParam) {
            return new ListCommand(dataStore.getTaskList());
        }
    },

    DONE {
        @Override
        public Executable parse(DataStore dataStore, String commandParam) {
            // Generate a DoneCommand which when executed, marks the task specified by the index as done
            try {
                int index = Integer.parseInt(commandParam.trim()) - 1;
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
        public Executable parse(DataStore dataStore, String commandParam) {
            // Generate a DeleteCommand which when executed, removes the task specified by the index from the list
            try {
                int index = Integer.parseInt(commandParam.trim()) - 1;
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
        public Executable parse(DataStore dataStore, String commandParam) {
            if (commandParam.isBlank()) {
                throw new DukeParserException("Format: todo {description}");
            }

            // Generate an AddCommand which when executed, adds
            // the new To-Do task to the taskList
            Task task = new ToDo(commandParam.trim());
            return new AddCommand(dataStore.getTaskList(), task);
        }
    },

    DEADLINE {
        @Override
        public Executable parse(DataStore dataStore, String commandParam) {
            String usage = "deadline {description} /by {ddMMyyyy HHmm}";

            // Regex to obtain param1 and param2 in Deadline(description, deadline)
            // param1: Description (String class)
            // param2: Deadline (DukeDateTime class)
            Pattern pattern = Pattern.compile("^(.+)/by(.+)$");
            Matcher matcher = pattern.matcher(commandParam);

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
        public Executable parse(DataStore dataStore, String commandParam) {
            String usage = "event {description} /from {ddMMyyyy HHmm} /till {ddMMyyyy HHmm}";

            // Regex to obtain param1, param2 and param3 in Event(description, start, end)
            // param1: Description (String class)
            // param2: start (DukeDateTime class)
            // param3: end (DukeDateTime class)
            Pattern pattern = Pattern.compile("^(.+)/from(.+)/till(.+)$");
            Matcher matcher = pattern.matcher(commandParam);

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
        public Executable parse(DataStore dataStore, String commandParam) {
            if (commandParam.isBlank()) {
                throw new DukeParserException("Format: save {filepath}");
            }

            // Generate a SaveCommand which when executed, saves the taskList into specified file path
            return new SaveCommand(dataStore.getTaskList(), commandParam.trim());
        }
    },

    LOAD {
        @Override
        public Executable parse(DataStore dataStore, String commandParam) {
            if (commandParam.isBlank()) {
                throw new DukeParserException("Format: load {filepath}");
            }

            // Generate a LoadCommand which when executed, loads data from specified file path into taskList
            return new LoadCommand(dataStore, commandParam.trim());
        }
    },

    FIND {
        @Override
        public Executable parse(DataStore dataStore, String commandParam) {
            if (commandParam.isBlank()) {
                throw new DukeParserException("Format: find {keyword}");
            }

            // Generate a FindCommand which when executed, search for all instance of searchString in taskList
            return new FindCommand(dataStore.getTaskList(), commandParam.trim());
        }
    },

    UNDO {
        @Override
        public Executable parse(DataStore dataStore, String commandParam) throws DukeParserException {
            return new UndoCommand(dataStore.getHistory());
        }
    },

    REDO {
        @Override
        public Executable parse(DataStore dataStore, String commandParam) throws DukeParserException {
            return new RedoCommand(dataStore.getHistory());
        }
    };

    /**
     * Generate the Command by providing command parameters (if required)
     * @param dataStore The dataStore which Command will execute on
     * @param commandParam Command parameters needed to construct the Command (As required)
     * @return A Command which executes on the dataStore
     * @throws DukeParserException if the input cannot be parsed. Details about the error can be
     * retrieved by the Throwable.getMessage() method
     */
    public abstract Executable parse(DataStore dataStore, String commandParam) throws DukeParserException;

}
