package duke.enums;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.messages.DukeResponse;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskManager;
import duke.tasks.ToDo;
import duke.utils.AliasManager;
import duke.utils.DateTimeParser;
import duke.utils.ResourceHandler;
import duke.utils.Store;

/**
 * Commands that can be used in <i>Duke</i>.
 */
public enum Command {
    /**
     * Defines an alias for a command.
     */
    ALIAS {
        /**
         * Validates whether the user input is of the correct format for the 'alias' command.
         *
         * @param input the user input.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String input) throws DukeException {
            String regex = "^(?i)alias\\s+\\S+\\s+\\S+\\s*$|^(?i)alias\\s+-l$";
            if (!Pattern.matches(regex, input)) {
                String template = String.format("%s\n%s", ResourceHandler.getString("exception.invalidArgs"),
                        ResourceHandler.getString("command.aliasFormat"));
                String message = MessageFormat.format(template, "alias");
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'alias' command.
         *
         * @param input the user input.
         * @return the output of running the 'alias' command.
         */
        @Override
        public DukeResponse execute(String input) {
            String regex = "^(?i)alias\\s+-l$";
            if (Pattern.matches(regex, input)) {
                // Display list of aliases.
                return new DukeResponse(aliasManager.toString());
            }

            // Add a new alias.
            String lineWithoutCommand = input.replaceFirst("^alias", "");
            String[] args = lineWithoutCommand.trim().split("\\s+", 2);
            String command = args[0].trim();
            String alias = args[1].trim();
            String response = aliasManager.addAlias(alias, command);
            return new DukeResponse(response);
        }
    },

    /**
     * Terminates the running of <i>Duke</i>.
     */
    BYE {
        /**
         * Validates whether the user input is of the correct format for the 'bye' command.
         *
         * @param input the user input.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String input) throws DukeException {
            String regex = "^(?i)bye\\s*$";
            if (!Pattern.matches(regex, input)) {
                String template = ResourceHandler.getString("exception.noArgs");
                String message = MessageFormat.format(template, "bye");
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'bye' command.
         *
         * @param input the user input.
         * @return the output of running the 'bye' command.
         */
        @Override
        public DukeResponse execute(String input) {
            String response = ResourceHandler.getString("repl.farewell");
            // Return a `DukeResponse` with the exit flag enabled.
            return new DukeResponse(response, true);
        }
    },

    /**
     * Adds a {@code Deadline} task to the {@code TaskManager}.
     */
    DEADLINE {
        /**
         * Validates whether the user input is of the correct format for the 'deadline' command.
         *
         * @param input the user input.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String input) throws DukeException {
            String regex = "^(?i)deadline\\s+.*\\S+.*\\s+/by\\s+.*\\S+.*\\s*$";
            if (!Pattern.matches(regex, input)) {
                String template = String.format("%s\n%s", ResourceHandler.getString("exception.invalidArgs"),
                        ResourceHandler.getString("command.deadlineFormat"));
                String message = MessageFormat.format(template, "deadline");
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'deadline' command.
         *
         * @param input the user input.
         * @return the output of running the 'deadline' command.
         * @throws DukeException if an error occurs while running the 'deadline' command.
         */
        @Override
        public DukeResponse execute(String input) throws DukeException {
            String lineWithoutCommand = input.replaceFirst("^deadline", "");
            String[] args = lineWithoutCommand.split("/by", 2);
            String deadlineName = args[0].trim();
            String dueDateString = args[1].trim();
            LocalDateTime dueDate = DateTimeParser.parseDateTime(dueDateString);
            String response = taskManager.addTask(new Deadline(deadlineName, dueDate));
            return new DukeResponse(response);
        }
    },

    /**
     * Deletes a {@code Task} from the {@code TaskManager}.
     */
    DELETE {
        /**
         * Validates whether the user input is of the correct format for the 'delete' command.
         *
         * @param input the user input.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String input) throws DukeException {
            String regex = "^(?i)delete\\s+\\d+\\s*$";
            if (!Pattern.matches(regex, input)) {
                String template = String.format("%s\n%s", ResourceHandler.getString("exception.invalidArgs"),
                        ResourceHandler.getString("command.deleteFormat"));
                String message = MessageFormat.format(template, "delete");
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'delete' command.
         *
         * @param input the user input.
         * @return the output of running the 'delete' command.
         */
        @Override
        public DukeResponse execute(String input) {
            String lineWithoutCommand = input.replaceFirst("^delete", "");
            String listIndexString = lineWithoutCommand.trim();
            // `listIndexString` is guaranteed to be a string made up of only digit characters after validation.
            int listIndex = Integer.parseInt(listIndexString) - 1;
            String response;
            try {
                response = taskManager.removeTask(listIndex);
            } catch (IndexOutOfBoundsException e) {
                response = ResourceHandler.getString("repl.invalidTaskIndex");
            }
            assert response != null;
            return new DukeResponse(response);
        }
    },

    /**
     * Marks a {@code Task} as done.
     */
    DONE {
        /**
         * Validates whether the user input is of the correct format for the 'done' command.
         *
         * @param input the user input.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String input) throws DukeException {
            String regex = "^(?i)done\\s+\\d+\\s*$";
            if (!Pattern.matches(regex, input)) {
                String template = String.format("%s\n%s", ResourceHandler.getString("exception.invalidArgs"),
                        ResourceHandler.getString("command.doneFormat"));
                String message = MessageFormat.format(template, "done");
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'done' command.
         *
         * @param input the user input.
         * @return the output of running the 'done' command.
         */
        @Override
        public DukeResponse execute(String input) {
            String lineWithoutCommand = input.replaceFirst("^done", "");
            String listIndexString = lineWithoutCommand.trim();
            // `listIndexString` is guaranteed to be a string made up of only digit characters after validation.
            int listIndex = Integer.parseInt(listIndexString) - 1;
            String response;
            try {
                response = taskManager.markAsDone(listIndex);
            } catch (IndexOutOfBoundsException e) {
                response = ResourceHandler.getString("repl.invalidTaskIndex");
            }
            assert response != null;
            return new DukeResponse(response);
        }
    },

    /**
     * Adds an {@code Event} task to the {@code TaskManager}.
     */
    EVENT {
        /**
         * Validates whether the user input is of the correct format for the 'event' command.
         *
         * @param input the user input.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String input) throws DukeException {
            String regex = "^(?i)event\\s+.*\\S+.*\\s+/at\\s+.*\\S+.*\\s*$";
            if (!Pattern.matches(regex, input)) {
                String template = String.format("%s\n%s", ResourceHandler.getString("exception.invalidArgs"),
                        ResourceHandler.getString("command.eventFormat"));
                String message = MessageFormat.format(template, "event");
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'event' command.
         *
         * @param input the user input.
         * @return the output of running the 'event' command.
         * @throws DukeException if an error occurs while running the 'event' command.
         */
        @Override
        public DukeResponse execute(String input) throws DukeException {
            String lineWithoutCommand = input.replaceFirst("^event", "");
            String[] args = lineWithoutCommand.split("/at", 2);
            String eventName = args[0].trim();
            String dateTimeString = args[1].trim();
            LocalDateTime dateTime = DateTimeParser.parseDateTime(dateTimeString);
            String response = taskManager.addTask(new Event(eventName, dateTime));
            return new DukeResponse(response);
        }
    },

    /**
     * Searches for {@code Task}s in the {@code TaskManager} which match the keyword.
     */
    FIND {
        /**
         * Validates whether the user input is of the correct format for the 'find' command.
         *
         * @param input the user input.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String input) throws DukeException {
            String regex = "^(?i)find\\s+.*\\S+.*\\s*$";
            if (!Pattern.matches(regex, input)) {
                String template = String.format("%s\n%s", ResourceHandler.getString("exception.invalidArgs"),
                        ResourceHandler.getString("command.findFormat"));
                String message = MessageFormat.format(template, "find");
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'find' command.
         *
         * @param input the user input.
         * @return the output of running the 'find' command.
         */
        @Override
        public DukeResponse execute(String input) {
            String lineWithoutCommand = input.replaceFirst("^find", "");
            String[] searchKeywords = lineWithoutCommand.trim().split("\\s+");
            String response = taskManager.getMatchingTasks(searchKeywords);
            return new DukeResponse(response);
        }
    },

    /**
     * Lists all {@code Task}s in the {@code TaskManager}.
     */
    LIST {
        /**
         * Validates whether the user input is of the correct format for the 'list' command.
         *
         * @param input the user input.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String input) throws DukeException {
            String regex = "^(?i)list\\s*$";
            if (!Pattern.matches(regex, input)) {
                String template = ResourceHandler.getString("exception.noArgs");
                String message = MessageFormat.format(template, "list");
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'list' command.
         *
         * @param input the user input.
         * @return the output of running the 'list' command.
         */
        @Override
        public DukeResponse execute(String input) {
            String response = taskManager.toString();
            return new DukeResponse(response);
        }
    },

    /**
     * Lists all overdue {@code Task}s in the {@code TaskManager}.
     */
    OVERDUE {
        /**
         * Validates whether the user input is of the correct format for the 'overdue' command.
         *
         * @param input the user input.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String input) throws DukeException {
            String regex = "^(?i)overdue\\s*$";
            if (!Pattern.matches(regex, input)) {
                String template = ResourceHandler.getString("exception.noArgs");
                String message = MessageFormat.format(template, "overdue");
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'overdue' command.
         *
         * @param input the user input.
         * @return the output of running the 'overdue' command.
         */
        @Override
        public DukeResponse execute(String input) {
            String response = taskManager.getOverdueTasks();
            return new DukeResponse(response);
        }
    },

    /**
     * Adds a {@code ToDo} task to the {@code TaskManager}.
     */
    TODO {
        /**
         * Validates whether the user input is of the correct format for the 'todo' command.
         *
         * @param input the user input.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String input) throws DukeException {
            String regex = "^(?i)todo\\s+.*\\S+.*\\s*$";
            if (!Pattern.matches(regex, input)) {
                String template = String.format("%s\n%s", ResourceHandler.getString("exception.invalidArgs"),
                        ResourceHandler.getString("command.toDoFormat"));
                String message = MessageFormat.format(template, "todo");
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'todo' command.
         *
         * @param input the user input.
         * @return the output of running the 'todo' command.
         */
        @Override
        public DukeResponse execute(String input) {
            String lineWithoutCommand = input.replaceFirst("^todo", "");
            String toDoName = lineWithoutCommand.trim();
            String response = taskManager.addTask(new ToDo(toDoName));
            return new DukeResponse(response);
        }
    },

    /**
     * Lists all upcoming {@code Task}s in the {@code TaskManager}.
     */
    UPCOMING {
        /**
         * Validates whether the user input is of the correct format for the 'upcoming' command.
         *
         * @param input the user input.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String input) throws DukeException {
            String regex = "^(?i)upcoming\\s*$";
            if (!Pattern.matches(regex, input)) {
                String template = ResourceHandler.getString("exception.noArgs");
                String message = MessageFormat.format(template, "upcoming");
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'upcoming' command.
         *
         * @param input the user input.
         * @return the output of running the 'upcoming' command.
         */
        @Override
        public DukeResponse execute(String input) {
            String response = taskManager.getUpcomingTasks();
            return new DukeResponse(response);
        }
    };

    /** {@code TaskManager} object that is keeping track of tasks. */
    private static final TaskManager taskManager = Store.getTaskManager();
    /** {@code AliasManager} object that is keeping track of aliases. */
    private static final AliasManager aliasManager = Store.getAliasManager();

    /**
     * Validates whether the user input is of the correct format.
     *
     * @param input the user input.
     * @throws DukeException if the user input is invalid.
     */
    public abstract void validate(String input) throws DukeException;

    /**
     * Executes the command.
     *
     * @param input the user input.
     * @return a {@code DukeResponse}.
     * @throws DukeException if an error occurs while running the command.
     */
    public abstract DukeResponse execute(String input) throws DukeException;
}
