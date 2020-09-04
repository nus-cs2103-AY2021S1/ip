package duke.commands;

import duke.exceptions.DukeException;

import duke.utils.Messages;

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
                throw new DukeException(Messages.INVALID_COMMAND_INPUT_MESSAGE);
            }
        }
    },
    LIST {
        @Override
        public void checkInput(String input) throws DukeException {
            String patternString = "^(?)list\\s*$";
            if (!Pattern.matches(patternString, input)) {
                throw new DukeException(Messages.INVALID_COMMAND_INPUT_MESSAGE);
            }
        }
    },
    TODO {
        @Override
        public void checkInput(String input) throws DukeException {
            String content = input.replaceFirst("^todo", "");
            if (content.isBlank()) {
                throw new DukeException(Messages.INVALID_TODO_COMMAND);
            }
        }
    },
    EVENT {
        @Override
        public void checkInput(String input) throws DukeException {
            input = input.replaceFirst("^event", "");
            String[] inputArgs = input.split("\\s*/at\\s*");
            try {
                String task = inputArgs[0];
                if (task.isBlank()) {
                    throw new DukeException(Messages.INVALID_EVENT_COMMAND);
                }
                String dateTime = inputArgs[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(Messages.MISSING_DATE_AND_TIME_FOR_EVENT);
            }
        }
    },
    DEADLINE {
        @Override
        public void checkInput(String input) throws DukeException {
            input = input.replaceFirst("^(?i)deadline", "");
            String[] inputArgs = input.split("\\s*/by\\s*");
            try {
                String task = inputArgs[0];
                if (task.isBlank()) {
                    throw new DukeException(Messages.INVALID_DEADLINE_COMMAND);
                }
                String dateTime = inputArgs[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(Messages.MISSING_DATE_AND_TIME_FOR_DEADLINE);
            }
        }
    },
    DONE {
        @Override
        public void checkInput(String input) throws DukeException {
            String patternString = "^(?i)done\\s+\\d+\\s*$";
            if (!Pattern.matches(patternString, input)) {
                throw new DukeException(Messages.INVALID_TASK_INDEX_ERROR_MESSAGE);
            }
        }
    },
    DELETE {
        @Override
        public void checkInput(String input) throws DukeException {
            String patternString = "^(?i)delete\\s+\\d+\\s*$";
            if (!Pattern.matches(patternString, input)) {
                throw new DukeException(Messages.INVALID_TASK_INDEX_ERROR_MESSAGE);
            }

        }
    },
    FIND {
        @Override
        public void checkInput(String input) throws DukeException {
            String content = input.replaceFirst("^find", "");
            if (content.isBlank()) {
                throw new DukeException(Messages.INVALID_FIND_COMMAND);
            }
        }
    },
    TODAY {
        @Override
        public void checkInput(String input) throws DukeException {
            String content = input.replaceFirst("^today", "");
            if (!content.isBlank()) {
                throw new DukeException(Messages.INVALID_TODAY_COMMAND);
            }
        }
    },
    UNCOMPLETED {
        @Override
        public void checkInput(String input) throws DukeException {
            String content = input.replaceFirst("^uncompleted", "");
            if (!content.isBlank()) {
                throw new DukeException(Messages.INVALID_UNCOMPLETED_COMMAND);
            }
        }
    },
    COMPLETED {
        @Override
        public void checkInput(String input) throws DukeException {
            String content = input.replaceFirst("^completed", "");
            if (!content.isBlank()) {
                throw new DukeException(Messages.INVALID_COMPLETED_COMMAND);
            }
        }
    },
    OVERDUE {
        @Override
        public void checkInput(String input) throws DukeException {
            String content = input.replaceFirst("^overdue", "");
            if (!content.isBlank()) {
                throw new DukeException(Messages.INVALID_OVERDUE_COMMAND);
            }
        }
    };

    public abstract void checkInput(String input) throws DukeException;
}
