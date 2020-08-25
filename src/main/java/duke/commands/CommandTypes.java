package duke.commands;

import duke.exceptions.DukeException;

import duke.utils.ResourceHandler;

import java.util.regex.Pattern;

/**
 * Command types that the user can use.
 */

public enum CommandTypes {
    BYE {
        @Override
        public void checkInput(String input) throws DukeException {
            String patternString = "^(?)bye\\s*$";
            if (!Pattern.matches(patternString, input)) {
                throw new DukeException(ResourceHandler.getMessage("commandline.invalidCommandInputMessage"));
            }
        }
    },
    LIST {
        @Override
        public void checkInput(String input) throws DukeException {
            String patternString = "^(?)list\\s*$";
            if (!Pattern.matches(patternString, input)) {
                throw new DukeException(ResourceHandler.getMessage("commandline.invalidCommandInputMessage"));
            }
        }
    },
    TODO {
        @Override
        public void checkInput(String input) throws DukeException {
            String content = input.replaceFirst("^todo", "");
            if (content.isBlank()) {
                throw new DukeException(ResourceHandler.getMessage("command.invalidTodoCommand"));
            }
        }
    },
    EVENT {
        private static final String noDateTimeErrorMessage = "OOPS!!! Missing deadline date/time";

        @Override
        public void checkInput(String input) throws DukeException {
            input = input.replaceFirst("^event", "");
            String[] inputArgs = input.split("\\s*/at\\s*");
            try {
                String task = inputArgs[0];
                if (task.isBlank()) {
                    throw new DukeException(ResourceHandler.getMessage("command.invalidEventCommand"));
                }
                String dateTime = inputArgs[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(ResourceHandler.getMessage("command.missingDateAndTimeForEvent"));
            }
        }
    },
    DEADLINE {
        private static final String noDateTimeErrorMessage = "OOPS!!! Missing deadline date/time";

        @Override
        public void checkInput(String input) throws DukeException {
            input = input.replaceFirst("^(?i)deadline", "");
            String[] inputArgs = input.split("\\s*/by\\s*");
            try {
                String task = inputArgs[0];
                if (task.isBlank()) {
                    throw new DukeException(ResourceHandler.getMessage("command.invalidDeadlineCommand"));
                }
                String dateTime = inputArgs[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(ResourceHandler.getMessage("command.missingDateAndTimeForDeadline"));
            }
        }
    },
    DONE {
        private static final String invalidTaskIndexErrorMessage = "Invalid task index! Please choose another index.";

        @Override
        public void checkInput(String input) throws DukeException {
            String patternString = "^(?i)done\\s+\\d+\\s*$";
            if (!Pattern.matches(patternString, input)) {
                throw new DukeException(invalidTaskIndexErrorMessage);
            }
        }
    },
    DELETE {
        private static final String invalidTaskIndexErrorMessage = "Invalid task index! Please choose another index.";

        @Override
        public void checkInput(String input) throws DukeException {
            String patternString = "^(?i)delete\\s+\\d+\\s*$";
            if (!Pattern.matches(patternString, input)) {
                throw new DukeException(invalidTaskIndexErrorMessage);
            }

        }
    };

    public abstract void checkInput(String input) throws DukeException;
}
