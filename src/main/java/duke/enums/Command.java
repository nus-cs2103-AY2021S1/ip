package duke.enums;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.messages.DukeResponse;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.utils.DateTimeParser;
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
         * @param alias the name used in invoking the command; can be either the command name or an alias.
         * @param inputArgs the user inputted arguments.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String alias, String inputArgs) throws DukeException {
            String regex = "^\\s*\\S+\\s+\\S+\\s*$|^\\s*-l\\s*$|^\\s*-rm\\s+\\S+\\s*$";
            if (!Pattern.matches(regex, inputArgs)) {
                String template = String.format("%s\n%s",
                        Store.getResourceHandler().getString("exception.invalidArgs"),
                        Store.getResourceHandler().getString("command.aliasFormat"));
                String message = MessageFormat.format(template, alias);
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'alias' command.
         *
         * @param inputArgs the user inputted arguments.
         * @return the output of running the 'alias' command.
         */
        @Override
        public DukeResponse execute(String inputArgs) {
            String listRegex = "^\\s*-l\\s*$";
            String removeRegex = "^\\s*-rm\\s+\\S+\\s*$";
            String response;
            if (Pattern.matches(listRegex, inputArgs)) {
                // Display list of aliases.
                response = Store.getAliasManager().toString();
            } else if (Pattern.matches(removeRegex, inputArgs)) {
                // Remove specified alias
                String alias = inputArgs.replaceFirst("-rm", "").trim();
                response = Store.getAliasManager().removeAlias(alias);
            } else {
                // Add a new alias.
                String[] argTokens = inputArgs.trim().split("\\s+", 2);
                String command = argTokens[0].trim();
                String alias = argTokens[1].trim();
                response = Store.getAliasManager().addAlias(alias, command);
            }
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
         * @param alias the name used in invoking the command; can be either the command name or an alias.
         * @param inputArgs the user inputted arguments.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String alias, String inputArgs) throws DukeException {
            String regex = "^\\s*$";
            if (!Pattern.matches(regex, inputArgs)) {
                String template = Store.getResourceHandler().getString("exception.noArgs");
                String message = MessageFormat.format(template, alias);
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'bye' command.
         *
         * @param inputArgs the user inputted arguments.
         * @return the output of running the 'bye' command.
         */
        @Override
        public DukeResponse execute(String inputArgs) {
            String response = Store.getResourceHandler().getString("repl.farewell");
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
         * @param alias the name used in invoking the command; can be either the command name or an alias.
         * @param inputArgs the user inputted arguments.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String alias, String inputArgs) throws DukeException {
            String regex = "^\\s*.*\\S+.*\\s+/by\\s+.*\\S+.*\\s*$";
            if (!Pattern.matches(regex, inputArgs)) {
                String template = String.format("%s\n%s",
                        Store.getResourceHandler().getString("exception.invalidArgs"),
                        Store.getResourceHandler().getString("command.deadlineFormat"));
                String message = MessageFormat.format(template, alias);
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'deadline' command.
         *
         * @param inputArgs the user inputted arguments.
         * @return the output of running the 'deadline' command.
         * @throws DukeException if an error occurs while running the 'deadline' command.
         */
        @Override
        public DukeResponse execute(String inputArgs) throws DukeException {
            String[] argTokens = inputArgs.split("/by", 2);
            String deadlineName = argTokens[0].trim();
            String dueDateString = argTokens[1].trim();
            LocalDateTime dueDate = DateTimeParser.parseDateTime(dueDateString);
            String response = Store.getTaskManager().addTask(new Deadline(deadlineName, dueDate));
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
         * @param alias the name used in invoking the command; can be either the command name or an alias.
         * @param inputArgs the user inputted arguments.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String alias, String inputArgs) throws DukeException {
            String regex = "^\\s*\\d+\\s*$";
            if (!Pattern.matches(regex, inputArgs)) {
                String template = String.format("%s\n%s",
                        Store.getResourceHandler().getString("exception.invalidArgs"),
                        Store.getResourceHandler().getString("command.deleteFormat"));
                String message = MessageFormat.format(template, alias);
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'delete' command.
         *
         * @param inputArgs the user inputted arguments.
         * @return the output of running the 'delete' command.
         */
        @Override
        public DukeResponse execute(String inputArgs) {
            String listIndexString = inputArgs.trim();
            // `listIndexString` is guaranteed to be a string made up of only digit characters after validation.
            int listIndex = Integer.parseInt(listIndexString) - 1;
            String response;
            try {
                response = Store.getTaskManager().removeTask(listIndex);
            } catch (IndexOutOfBoundsException e) {
                response = Store.getResourceHandler().getString("repl.invalidTaskIndex");
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
         * @param alias the name used in invoking the command; can be either the command name or an alias.
         * @param inputArgs the user inputted arguments.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String alias, String inputArgs) throws DukeException {
            String regex = "^\\s*\\d+\\s*$";
            if (!Pattern.matches(regex, inputArgs)) {
                String template = String.format("%s\n%s",
                        Store.getResourceHandler().getString("exception.invalidArgs"),
                        Store.getResourceHandler().getString("command.doneFormat"));
                String message = MessageFormat.format(template, alias);
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'done' command.
         *
         * @param inputArgs the user inputted arguments.
         * @return the output of running the 'done' command.
         */
        @Override
        public DukeResponse execute(String inputArgs) {
            String listIndexString = inputArgs.trim();
            // `listIndexString` is guaranteed to be a string made up of only digit characters after validation.
            int listIndex = Integer.parseInt(listIndexString) - 1;
            String response;
            try {
                response = Store.getTaskManager().markAsDone(listIndex);
            } catch (IndexOutOfBoundsException e) {
                response = Store.getResourceHandler().getString("repl.invalidTaskIndex");
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
         * @param alias the name used in invoking the command; can be either the command name or an alias.
         * @param inputArgs the user inputted arguments.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String alias, String inputArgs) throws DukeException {
            String regex = "^\\s*.*\\S+.*\\s+/at\\s+.*\\S+.*\\s*$";
            if (!Pattern.matches(regex, inputArgs)) {
                String template = String.format("%s\n%s",
                        Store.getResourceHandler().getString("exception.invalidArgs"),
                        Store.getResourceHandler().getString("command.eventFormat"));
                String message = MessageFormat.format(template, alias);
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'event' command.
         *
         * @param inputArgs the user inputted arguments.
         * @return the output of running the 'event' command.
         * @throws DukeException if an error occurs while running the 'event' command.
         */
        @Override
        public DukeResponse execute(String inputArgs) throws DukeException {
            String[] argTokens = inputArgs.split("/at", 2);
            String eventName = argTokens[0].trim();
            String dateTimeString = argTokens[1].trim();
            LocalDateTime dateTime = DateTimeParser.parseDateTime(dateTimeString);
            String response = Store.getTaskManager().addTask(new Event(eventName, dateTime));
            return new DukeResponse(response);
        }
    },

    /**
     * Searches for {@code Task}s in the {@code TaskManager} which match the provided keywords.
     */
    FIND {
        /**
         * Validates whether the user input is of the correct format for the 'find' command.
         *
         * @param alias the name used in invoking the command; can be either the command name or an alias.
         * @param inputArgs the user inputted arguments.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String alias, String inputArgs) throws DukeException {
            String regex = "^\\s*.*\\S+.*\\s*$";
            if (!Pattern.matches(regex, inputArgs)) {
                String template = String.format("%s\n%s",
                        Store.getResourceHandler().getString("exception.invalidArgs"),
                        Store.getResourceHandler().getString("command.findFormat"));
                String message = MessageFormat.format(template, alias);
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'find' command.
         *
         * @param inputArgs the user inputted arguments.
         * @return the output of running the 'find' command.
         */
        @Override
        public DukeResponse execute(String inputArgs) {
            String[] searchKeywords = inputArgs.trim().split("\\s+");
            String response = Store.getTaskManager().getMatchingTasks(searchKeywords);
            return new DukeResponse(response);
        }
    },

    /**
     * Displays a list of all commands along with a brief description.
     */
    HELP {
        /**
         * Validates whether the user input is of the correct format for the 'help' command.
         *
         * @param alias the name used in invoking the command; can be either the command name or an alias.
         * @param inputArgs the user inputted arguments.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String alias, String inputArgs) throws DukeException {
            String regex = "^\\s*$";
            if (!Pattern.matches(regex, inputArgs)) {
                String template = Store.getResourceHandler().getString("exception.noArgs");
                String message = MessageFormat.format(template, alias);
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'help' command.
         *
         * @param inputArgs the user inputted arguments.
         * @return the output of running the 'help' command.
         */
        @Override
        public DukeResponse execute(String inputArgs) {
            String response = Store.getResourceHandler().getString("command.help");
            return new DukeResponse(response);
        }
    },

    /**
     * Sets the language of <i>Duke</i>.
     */
    LANGUAGE {
        /**
         * Validates whether the user input is of the correct format for the 'language' command.
         *
         * @param alias the name used in invoking the command; can be either the command name or an alias.
         * @param inputArgs the user inputted arguments.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String alias, String inputArgs) throws DukeException {
            String regex = "^\\s*(?i)(english|chinese|en|zh)\\s*$";
            if (!Pattern.matches(regex, inputArgs)) {
                String template = String.format("%s\n%s",
                        Store.getResourceHandler().getString("exception.invalidArgs"),
                        Store.getResourceHandler().getString("command.languageFormat"));
                String message = MessageFormat.format(template, alias);
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'language' command.
         *
         * @param inputArgs the user inputted arguments.
         * @return the output of running the 'language' command.
         */
        @Override
        public DukeResponse execute(String inputArgs) {
            String language = inputArgs.trim().toLowerCase();
            switch (language) {
            case "english":
            case "en":
                Store.getResourceHandler().setLocale(new Locale("en", "SG"));
                break;
            case "chinese":
            case "zh":
                Store.getResourceHandler().setLocale(new Locale("zh", "SG"));
                break;
            default:
                assert false;
            }
            String response = Store.getResourceHandler().getString("command.switchLanguage");
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
         * @param alias the name used in invoking the command; can be either the command name or an alias.
         * @param inputArgs the user inputted arguments.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String alias, String inputArgs) throws DukeException {
            String regex = "^\\s*$";
            if (!Pattern.matches(regex, inputArgs)) {
                String template = Store.getResourceHandler().getString("exception.noArgs");
                String message = MessageFormat.format(template, alias);
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'list' command.
         *
         * @param inputArgs the user inputted arguments.
         * @return the output of running the 'list' command.
         */
        @Override
        public DukeResponse execute(String inputArgs) {
            String response = Store.getTaskManager().toString();
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
         * @param alias the name used in invoking the command; can be either the command name or an alias.
         * @param inputArgs the user inputted arguments.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String alias, String inputArgs) throws DukeException {
            String regex = "^\\s*$";
            if (!Pattern.matches(regex, inputArgs)) {
                String template = Store.getResourceHandler().getString("exception.noArgs");
                String message = MessageFormat.format(template, alias);
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'overdue' command.
         *
         * @param inputArgs the user inputted arguments.
         * @return the output of running the 'overdue' command.
         */
        @Override
        public DukeResponse execute(String inputArgs) {
            String response = Store.getTaskManager().getOverdueTasks();
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
         * @param alias the name used in invoking the command; can be either the command name or an alias.
         * @param inputArgs the user inputted arguments.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String alias, String inputArgs) throws DukeException {
            String regex = "^\\s*.*\\S+.*\\s*$";
            if (!Pattern.matches(regex, inputArgs)) {
                String template = String.format("%s\n%s",
                        Store.getResourceHandler().getString("exception.invalidArgs"),
                        Store.getResourceHandler().getString("command.toDoFormat"));
                String message = MessageFormat.format(template, alias);
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'todo' command.
         *
         * @param inputArgs the user inputted arguments.
         * @return the output of running the 'todo' command.
         */
        @Override
        public DukeResponse execute(String inputArgs) {
            String toDoName = inputArgs.trim();
            String response = Store.getTaskManager().addTask(new ToDo(toDoName));
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
         * @param alias the name used in invoking the command; can be either the command name or an alias.
         * @param inputArgs the user inputted arguments.
         * @throws DukeException if the user input is invalid.
         */
        @Override
        public void validate(String alias, String inputArgs) throws DukeException {
            String regex = "^\\s*$";
            if (!Pattern.matches(regex, inputArgs)) {
                String template = Store.getResourceHandler().getString("exception.noArgs");
                String message = MessageFormat.format(template, alias);
                throw new DukeException(message);
            }
        }

        /**
         * Executes the 'upcoming' command.
         *
         * @param inputArgs the user inputted arguments.
         * @return the output of running the 'upcoming' command.
         */
        @Override
        public DukeResponse execute(String inputArgs) {
            String response = Store.getTaskManager().getUpcomingTasks();
            return new DukeResponse(response);
        }
    };

    /**
     * Validates whether the user input is of the correct format.
     *
     * @param alias the name used in invoking the command; can be either the command name or an alias.
     * @param inputArgs the user inputted arguments.
     * @throws DukeException if the user input is invalid.
     */
    public abstract void validate(String alias, String inputArgs) throws DukeException;

    /**
     * Executes the command.
     *
     * @param inputArgs the user inputted arguments.
     * @return a {@code DukeResponse}.
     * @throws DukeException if an error occurs while running the command.
     */
    public abstract DukeResponse execute(String inputArgs) throws DukeException;
}
