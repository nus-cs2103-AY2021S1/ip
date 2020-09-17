package bob;

import bob.command.AddCommand;
import bob.command.Command;
import bob.command.DeleteCommand;
import bob.command.DoneCommand;
import bob.command.ExitCommand;
import bob.command.FindCommand;
import bob.command.ListCommand;

import bob.command.RescheduleCommand;
import bob.command.SnoozeCommand;
import bob.exception.BobException;
import bob.exception.BobFindNoKeyWordsException;
import bob.exception.BobIncompleteDeadlineDescriptionException;
import bob.exception.BobIncompleteEventDescriptionException;
import bob.exception.BobIncorrectRescheduleFormatException;
import bob.exception.BobIncorrectSnoozeFormatException;
import bob.exception.BobNoDescriptionException;
import bob.exception.BobDeleteDoneFormatException;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

/**
 * This class consists of the parse method which parses user input and determines
 * the correct command for Bob to execute.
 */
public class Parser {

    static final int TODO_DESCRIPTION_INDEX = 5;
    static final int DEADLINE_DESCRIPTION_INDEX = 9;
    static final int EVENT_DESCRIPTION_INDEX = 6;
    static final int FIND_KEYWORD_INDEX = 5;

    /**
     * Returns the appropriate command for Bob to execute by parsing a provided String command by the user.
     *
     * @param command a String consisting of a command to be parsed for Bob to execute.
     * @return a command that corresponds to provided String command.
     * @throws BobException if a command cannot be parsed from the provided String command.
     */
    public static Command parse(String command) throws BobException {
        boolean isTask = command.contains("todo") || command.contains("deadline") || command.contains("event");
        if (command.startsWith("list")) {
            return new ListCommand();
        } else if (command.startsWith("done")) {
            return parseDone(command);
        } else if (isTask) {
            return parseTask(command);
        } else if (command.startsWith("delete")) {
            return parseDelete(command);
        } else if (command.startsWith("bye")) {
            return new ExitCommand();
        } else if (command.startsWith("find")) {
            return parseFind(command);
        } else if (command.startsWith("snooze")) {
            return parseSnooze(command);
        } else if (command.startsWith("reschedule")) {
            return parseReschedule(command);
        } else {
            throw new BobException();
        }
    }

    /**
     * Returns the appropriate AddCommand after parsing a text Command.
     *
     * @param command a string command requesting the addition of a task.
     * @return an AddCommand to fulfill the addition of a task.
     * @throws BobException if exceptions are thrown.
     */
    static Command parseTask(String command) throws BobException {
        Task task = null;
        if (command.contains("todo")) {
            try {
                String description = command.substring(TODO_DESCRIPTION_INDEX);
                if (description.isEmpty()) {
                    throw new BobNoDescriptionException();
                }
                task = new Todo(command.substring(TODO_DESCRIPTION_INDEX));
            } catch (StringIndexOutOfBoundsException e) {
                throw new BobNoDescriptionException();
            }
        } else if (command.contains("deadline")) {
            try {
                //finds the index of the char representing '/'
                int index = command.indexOf(47);
                if (command.substring(DEADLINE_DESCRIPTION_INDEX, index).isEmpty()) {
                    throw new BobIncompleteDeadlineDescriptionException();
                }
                String description = command.substring(DEADLINE_DESCRIPTION_INDEX, index - 1);
                if (description.isEmpty()) {
                    throw new BobIncompleteDeadlineDescriptionException();
                }
                String deadline = command.substring(index + 4);
                task = new Deadline(description, deadline);
            } catch (StringIndexOutOfBoundsException e) {
                throw new BobIncompleteDeadlineDescriptionException();
            }
        } else if (command.contains("event")) {
            try {
                //finds the index of the char representing '/'
                int index = command.indexOf(47);
                if (command.substring(EVENT_DESCRIPTION_INDEX, index).equals("")) {
                    throw new BobIncompleteEventDescriptionException();
                }
                String description = command.substring(EVENT_DESCRIPTION_INDEX, index - 1);
                if (description.isEmpty()) {
                    throw new BobIncompleteDeadlineDescriptionException();
                }
                String period = command.substring(index + 4);
                task = new Event(description, period);
            } catch (StringIndexOutOfBoundsException e) {
                throw new BobIncompleteEventDescriptionException();
            }
        }
        return new AddCommand(task);
    }

    /**
     * Returns a DoneCommand after parsing a String command.
     *
     * @param command A string command requesting to mark a task as done.
     * @return a DoneCommand which marks a task as done.
     * @throws BobException if exceptions are thrown.
     */
    static Command parseDone(String command) throws BobException {
        int index = 0;
        try {
            String[] split = command.split(" ");
            if (split[1].equals("all")) {
                return new DoneCommand(-1);
            }
            index = Integer.parseInt(split[1]);
        } catch (NumberFormatException e) {
            throw new BobDeleteDoneFormatException();
        } catch (IndexOutOfBoundsException e) {
            throw new BobDeleteDoneFormatException();
        }
        return new DoneCommand(index);
    }

    /**
     * Returns a DeleteCommand after parsing a String command.
     *
     * @param command A string command requesting to delete a task.
     * @return a DeleteCommand which deletes a task.
     * @throws BobException if exceptions are thrown.
     */
    static Command parseDelete(String command) throws BobException {
        int index;
        try {
            String[] split = command.split(" ");
            if (split[1].equals("all")) {
                return new DeleteCommand(-1);
            }
            index = Integer.parseInt(split[1]);
        } catch (NumberFormatException e) {
            throw new BobDeleteDoneFormatException();
        } catch (IndexOutOfBoundsException e) {
            throw new BobDeleteDoneFormatException();
        }
        return new DeleteCommand(index);
    }

    /**
     * Returns a FindCommand after parsing a String command.
     *
     * @param command A string command requesting to find tasks consisting of keywords.
     * @return a FindCommand which finds tasks consisting of keywords.
     * @throws BobException if exceptions are thrown.
     */
    static Command parseFind(String command) throws BobException {
        try {
            String keywords = command.substring(FIND_KEYWORD_INDEX);
            if (keywords.isEmpty()) {
                throw new BobFindNoKeyWordsException();
            }
            return new FindCommand(keywords);
        } catch (StringIndexOutOfBoundsException e) {
            throw new BobFindNoKeyWordsException();
        }
    }

    /**
     * Returns a SnoozeCommand after parsing a String command.
     *
     * @param command a String command requesting to snooze a deadline.
     * @return a SnoozeCommand which snoozes a deadline.
     * @throws BobException if exceptions are thrown.
     */
    static Command parseSnooze(String command) throws BobException {
        try {
            String[] split = command.split("/");
            String deadline = split[1].substring(3);
            int index = Integer.parseInt(split[0].split(" ")[1]);
            return new SnoozeCommand(index, deadline);
        } catch (IndexOutOfBoundsException e) {
            throw new BobIncorrectSnoozeFormatException();
        } catch (NumberFormatException e) {
            throw new BobIncorrectSnoozeFormatException();
        }
    }

    /**
     * Returns a RescheduleCommand after parsing a String command.
     *
     * @param command a String command requesting to reschedule an event.
     * @return a RescheduleCommand which reschedules an event.
     * @throws BobException if exceptions are thrown.
     */
    static Command parseReschedule(String command) throws BobException {
        try {
            String[] split = command.split("/");
            String period = split[1].substring(3);
            int index = Integer.parseInt(split[0].split(" ")[1]);
            return new RescheduleCommand(index, period);
        } catch (IndexOutOfBoundsException e) {
            throw new BobIncorrectRescheduleFormatException();
        } catch (NumberFormatException e) {
            throw new BobIncorrectRescheduleFormatException();
        }
    }
}
