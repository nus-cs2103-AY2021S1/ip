package duke.commands;

import java.util.regex.Pattern;

import duke.exceptions.DukeException;

import duke.utils.Messages;

/**
 * @author ianyong
 * Reused from https://github.com/ianyong/ip/blob/master/src/main/java/duke/enums/Command.java with modifications. Used
 * it to get an idea of how I could utilise enums in my code.
 *
 * Command types that the user can use.
 */
public enum CommandTypes {
    BYE {
        @Override
        public void checkInput(String input) throws DukeException {
            String patternString = "^(?i)bye\\s*$";
            if (!Pattern.matches(patternString, input)) {
                throw new DukeException(Messages.INVALID_COMMAND_INPUT_MESSAGE);
            }
        }
    },
    COMPLETED {
        @Override
        public void checkInput(String input) throws DukeException {
            String content = input.replaceFirst("^(?i)completed", "");
            if (!content.isBlank()) {
                throw new DukeException(Messages.INVALID_COMPLETED_COMMAND);
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
    DELETE {
        @Override
        public void checkInput(String input) throws DukeException {
            String patternString = "^(?i)delete\\s+\\d+\\s*$";
            if (!Pattern.matches(patternString, input)) {
                throw new DukeException(Messages.INVALID_TASK_INDEX_ERROR_MESSAGE);
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
    EVENT {
        @Override
        public void checkInput(String input) throws DukeException {
            input = input.replaceFirst("^(?i)event", "");
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
    FIND {
        @Override
        public void checkInput(String input) throws DukeException {
            String content = input.replaceFirst("^(?i)find", "");
            if (content.isBlank()) {
                throw new DukeException(Messages.INVALID_FIND_COMMAND);
            }
        }
    },
    HELP {
        @Override
        public void checkInput(String input) throws DukeException {
            String content = input.replaceFirst("^(?i)help", "");
            if (!content.isBlank()) {
                throw new DukeException(Messages.INVALID_HELP_COMMAND);
            }
        }
    },
    LIST {
        @Override
        public void checkInput(String input) throws DukeException {
            String patternString = "^(?i)list\\s*$";
            if (!Pattern.matches(patternString, input)) {
                throw new DukeException(Messages.INVALID_COMMAND_INPUT_MESSAGE);
            }
        }
    },
    OVERDUE {
        @Override
        public void checkInput(String input) throws DukeException {
            String content = input.replaceFirst("^(?i)overdue", "");
            if (!content.isBlank()) {
                throw new DukeException(Messages.INVALID_OVERDUE_COMMAND);
            }
        }
    },
    TAG {
        @Override
        public void checkInput(String input) throws DukeException {
            String userInputWithoutTagCommandTrimmed = input.replaceFirst("^(?i)tag", "").trim();
            String[] userInputArgsWithoutTagCommand = userInputWithoutTagCommandTrimmed.split(" ");
            if (userInputArgsWithoutTagCommand.length != 2) {
                throw new DukeException(Messages.INVALID_TAG_FORMAT_COMMAND);
            }
            String patternString = "#\\w*";
            if (!Pattern.matches(patternString, userInputArgsWithoutTagCommand[1])) {
                throw new DukeException(Messages.INVALID_TAG_FORMAT_COMMAND);
            }
            try {
                Integer.parseInt(userInputArgsWithoutTagCommand[0]);
            } catch (NumberFormatException e) {
                throw new DukeException(Messages.INVALID_TAG_COMMAND);
            }
        }
    },
    TODAY {
        @Override
        public void checkInput(String input) throws DukeException {
            String content = input.replaceFirst("^(?i)today", "");
            if (!content.isBlank()) {
                throw new DukeException(Messages.INVALID_TODAY_COMMAND);
            }
        }
    },
    TODO {
        @Override
        public void checkInput(String input) throws DukeException {
            String content = input.replaceFirst("^(?i)todo", "");
            if (content.isBlank()) {
                throw new DukeException(Messages.INVALID_TODO_COMMAND);
            }
        }
    },
    UNCOMPLETED {
        @Override
        public void checkInput(String input) throws DukeException {
            String content = input.replaceFirst("^(?i)uncompleted", "");
            if (!content.isBlank()) {
                throw new DukeException(Messages.INVALID_UNCOMPLETED_COMMAND);
            }
        }
    };

    public abstract void checkInput(String input) throws DukeException;
}
