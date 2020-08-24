package cmd;

import command.*;
import misc.DukeDateTime;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Enum of all valid commands in CDuke
 */
public enum Parser {

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
            try {
                int index = Integer.parseInt(commandParam.trim()) - 1;
                Task task = taskList.get(index);
                return new DoneCommand(task);
            } catch (NumberFormatException e) {
                return new InvalidCommand("Format: done {index}");
            } catch (IndexOutOfBoundsException e) {
                return new InvalidCommand("Index Error: done {index}");
            }
        }
    },

    DELETE {
        @Override
        public Command generate(List<Task> taskList, String commandParam) {
            try {
                int index = Integer.parseInt(commandParam.trim()) - 1;
                Task task = taskList.get(index);
                return new DeleteCommand(taskList, task);
            } catch (NumberFormatException e) {
                return new InvalidCommand("Format: delete {index}");
            } catch (IndexOutOfBoundsException e) {
                return new InvalidCommand("Index Error: delete {index}");
            }
        }
    },

    TODO {
        @Override
        public Command generate(List<Task> taskList, String commandParam) {
            if (commandParam.isBlank()) {
                return new InvalidCommand("Format: todo {description}");
            }

            Task task = new ToDo(commandParam.trim());
            return new AddCommand(taskList, task);
        }
    },

    DEADLINE {
        @Override
        public Command generate(List<Task> taskList, String commandParam) {
            Pattern p = Pattern.compile("^(.+)/by(.+)$");
            Matcher m = p.matcher(commandParam);

            if (!m.matches() || m.group(1).isBlank() || m.group(2).isBlank()) {
                return new InvalidCommand("Format: deadline {description} /by {ddMMyyyy HHmm}");
            }

            try {
                Task task = new Deadline(
                        m.group(1).trim(),
                        new DukeDateTime(m.group(2).trim())
                );
                return new AddCommand(taskList, task);
            } catch (DateTimeParseException e) {
                return new InvalidCommand("DateTime Error: deadline {description} /by {ddMMyyyy HHmm}");
            }
        }
    },

    EVENT {
        @Override
        public Command generate(List<Task> taskList, String commandParam) {
            Pattern p = Pattern.compile("^(.+)/from(.+)/till(.+)$");
            Matcher m = p.matcher(commandParam);

            if (!m.matches() || m.group(1).isBlank() || m.group(2).isBlank() || m.group(3).isBlank()) {
                return new InvalidCommand("Format: event {description} /from {ddMMyyyy HHmm} /till {ddMMyyyy HHmm}");
            }

            try {
                Task task = new Event(
                        m.group(1).trim(),
                        new DukeDateTime(m.group(2).trim()),
                        new DukeDateTime(m.group(3).trim())
                );
                return new AddCommand(taskList, task);
            } catch (DateTimeParseException e) {
                return new InvalidCommand("DateTime Error: event {description} /from {ddMMyyyy HHmm} /till {ddMMyyyy HHmm}");
            }
        }
    },

    SAVE {
        @Override
        public Command generate(List<Task> taskList, String commandParam) {
            if (commandParam.isBlank()) {
                return new InvalidCommand("Format: save {filepath}");
            }

            return new SaveCommand(taskList, commandParam.trim());
        }
    },

    LOAD {
        @Override
        public Command generate(List<Task> taskList, String commandParam) {
            if (commandParam.isBlank()) {
                return new InvalidCommand("Format: load {filepath}");
            }

            return new LoadCommand(taskList, commandParam.trim());
        }
    };

    /**
     * Generate the command based on given secondary parameters
     * @param taskList Required for commands that makes use of an index
     * @param commandParam Command parameters (As required)
     * @return Consumer which executes the command on given taskList
     */
    public abstract Command generate(List<Task> taskList, String commandParam);

    /**
     * Translates user input to its corresponding Command
     * @param taskList The taskList which command will execute on
     * @param input The raw user input
     * @return The relevant Command
     */
    public static Command parse(List<Task> taskList, String input) {

        Pattern pattern = Pattern.compile("^\\s*(\\S+)\\s*(.*)$");
        Matcher matcher = pattern.matcher(input);

        // No input received
        if (!matcher.matches()) return new InvalidCommand("Empty input!");

        // Find the matching command
        String firstWord = matcher.group(1).toUpperCase();
        return Arrays.stream(Parser.values()) // parser is an enum of all valid commands
                .filter((parser) -> parser.toString().equals(firstWord))
                .findFirst()
                .map(parser -> parser.generate(taskList, matcher.group(2)))
                .orElse(new InvalidCommand());
    }

}
