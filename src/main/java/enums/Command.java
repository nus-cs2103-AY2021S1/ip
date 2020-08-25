package enums;

import exceptions.DukeException;
import utils.ResourceHandler;

import java.text.MessageFormat;
import java.util.regex.Pattern;

/**
 * Commands that can be used in <i>Duke</i>.
 */
public enum Command {
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
    };

    /**
     * Validates whether the user input is of the correct format.
     *
     * @param input the user input.
     * @throws DukeException if the user input is invalid.
     */
    public abstract void validate(String input) throws DukeException;
}
