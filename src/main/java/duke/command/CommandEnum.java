package duke.command;

import duke.DukeDateTime;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CommandEnum {

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
            Pattern pattern = Pattern.compile("^(.+)/by(.+)$");
            Matcher matcher = pattern.matcher(commandParam);

            if (!matcher.matches() || matcher.group(1).isBlank() || matcher.group(2).isBlank()) {
                return new InvalidCommand("Format: deadline {description} /by {ddMMyyyy HHmm}");
            }

            try {
                Task task = new Deadline(
                        matcher.group(1).trim(),
                        new DukeDateTime(matcher.group(2).trim())
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
            Pattern pattern = Pattern.compile("^(.+)/from(.+)/till(.+)$");
            Matcher matcher = pattern.matcher(commandParam);

            if (!matcher.matches()
                    || matcher.group(1).isBlank()
                    || matcher.group(2).isBlank()
                    || matcher.group(3).isBlank()) {
                return new InvalidCommand("Format: event {description} /from {ddMMyyyy HHmm} /till {ddMMyyyy HHmm}");
            }

            try {
                Task task = new Event(
                        matcher.group(1).trim(),
                        new DukeDateTime(matcher.group(2).trim()),
                        new DukeDateTime(matcher.group(3).trim()));
                return new AddCommand(taskList, task);
            } catch (DateTimeParseException e) {
                return new InvalidCommand("DateTime Error: "
                        + "event {description} /from {ddMMyyyy HHmm} /till {ddMMyyyy HHmm}");
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
     * Generate the Command based on given secondary parameters
     * @param taskList Required for commands that makes use of an index
     * @param commandParam Command parameters (As required)
     * @return Consumer which executes the duke.command on given taskList
     */
    public abstract Command generate(List<Task> taskList, String commandParam);
}
