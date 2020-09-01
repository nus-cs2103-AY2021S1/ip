package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.LoadCommand;
import duke.command.SaveCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.util.DukeDateTime;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Enumeration of available commands
 * Contains generate() method to create command from parameters
 */
public enum CommandFactory {

    HELP {
        @Override
        public Command generate(List<Task> taskList, String commandParam) {
            return new HelpCommand();
        }
    },

    BYE {
        @Override
        public Command generate(List<Task> taskList, String commandParam) {
            return new ExitCommand();
        }
    },

    LIST {
        @Override
        public Command generate(List<Task> taskList, String commandParam) {
            return new ListCommand(taskList);
        }
    },

    DONE {
        @Override
        public Command generate(List<Task> taskList, String commandParam) {
            // Generate a DoneCommand which when executed, marks the task specified by the index as done
            try {
                int index = Integer.parseInt(commandParam.trim()) - 1;
                Task task = taskList.get(index);
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
        public Command generate(List<Task> taskList, String commandParam) {
            // Generate a DeleteCommand which when executed, removes the task specified by the index from the list
            try {
                int index = Integer.parseInt(commandParam.trim()) - 1;
                Task task = taskList.get(index);
                return new DeleteCommand(taskList, task);
            } catch (NumberFormatException e) {
                throw new DukeParserException("Format: delete {index}");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeParserException("Index Error: delete {index}");
            }
        }
    },

    TODO {
        @Override
        public Command generate(List<Task> taskList, String commandParam) {
            if (commandParam.isBlank()) {
                throw new DukeParserException("Format: todo {description}");
            }

            // Generate an AddCommand which when executed, adds
            // the new To-Do task to the taskList
            Task task = new ToDo(commandParam.trim());
            return new AddCommand(taskList, task);
        }
    },

    DEADLINE {
        @Override
        public Command generate(List<Task> taskList, String commandParam) {
            String usage = "deadline {description} /by {ddMMyyyy HHmm}";

            // Regex to obtain param1 and param2 in Deadline(description, deadline)
            // param1: Description (String class)
            // param2: Deadline (DukeDateTime class)
            Pattern pattern = Pattern.compile("^(.+)/by(.+)$");
            Matcher matcher = pattern.matcher(commandParam);

            // Ensure that both param1 and param2 exists
            if (!matcher.matches() || matcher.group(1).isBlank() || matcher.group(2).isBlank()) {
                throw new DukeParserException("Format: " + usage);
            }

            try {
                // Generate an AddCommand which when executed, adds the newly created Deadline to taskList
                Task task = new Deadline(
                        matcher.group(1).trim(),
                        new DukeDateTime(matcher.group(2).trim())
                );
                return new AddCommand(taskList, task);
            } catch (DateTimeParseException e) {
                throw new DukeParserException("DateTime Error: " + usage);
            }
        }
    },

    EVENT {
        @Override
        public Command generate(List<Task> taskList, String commandParam) {
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
                return new AddCommand(taskList, task);
            } catch (DateTimeParseException e) {
                throw new DukeParserException("DateTime Error: " + usage);
            }
        }
    },

    SAVE {
        @Override
        public Command generate(List<Task> taskList, String commandParam) {
            if (commandParam.isBlank()) {
                throw new DukeParserException("Format: save {filepath}");
            }

            // Generate a SaveCommand which when executed, saves the taskList into specified file path
            return new SaveCommand(taskList, commandParam.trim());
        }
    },

    LOAD {
        @Override
        public Command generate(List<Task> taskList, String commandParam) {
            if (commandParam.isBlank()) {
                throw new DukeParserException("Format: load {filepath}");
            }

            // Generate a LoadCommand which when executed, loads data from specified file path into taskList
            return new LoadCommand(taskList, commandParam.trim());
        }
    },

    FIND {
        @Override
        public Command generate(List<Task> taskList, String commandParam) {
            if (commandParam.isBlank()) {
                throw new DukeParserException("Format: find {keyword}");
            }

            // Generate a FindCommand which when executed, search for all instance of searchString in taskList
            return new FindCommand(taskList, commandParam.trim());
        }
    };

    /**
     * Generate the Command by providing command parameters (if required)
     * @param taskList The taskList which Command will execute on
     * @param commandParam Command parameters needed to construct the Command (As required)
     * @return A Command which executes on the taskList
     * @throws DukeParserException if the input cannot be parsed. Details about the error can be
     * retrieved by the Throwable.getMessage() method
     */
    public abstract Command generate(List<Task> taskList, String commandParam) throws DukeParserException;

}
