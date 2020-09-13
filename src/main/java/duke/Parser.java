package duke;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.Commands;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.ScheduleCommand;
import duke.commands.TimeCommand;

/**
 * Parses user input to make sense of the command and find out what user wants.
 */
public class Parser {
    /**
     * Parses user input and finds out of user input is valid and if it is, which specific command is given.
     *
     * @param description User input containing command and tasks description.
     * @return Specific command that user keyed in.
     * @throws DukeException throw when error occurs.
     */
    public Command findCommand(String description) throws DukeException {
        assert description instanceof String : "Description must be a String";
        boolean noInput = description.length() == 0;
        boolean isMinimumLength = description.length() >= 3;
        boolean hasDescription = description.split(" ").length > 1;
        boolean hasDeadlineOrEventTiming =
                description.split("/by").length >= 2
                || description.split("/at").length >= 2;
        boolean invalidSearchOrDelete = description.split(" ").length > 2;

        if (noInput) {
            throw new DukeException("there's no commands");
        } else if (!isMinimumLength) {
            throw new DukeException("invalid Command");
        } else if (description.startsWith("list")) {
            return new ListCommand();
        } else if (description.startsWith("done")) {
            return doneProcess(hasDescription, invalidSearchOrDelete, description);
        } else if (description.startsWith("todo")) {
            return todoProcess(hasDescription, description);
        } else if (description.startsWith("deadline")) {
            return deadlineProcess(hasDescription, hasDeadlineOrEventTiming, description);
        } else if (description.startsWith("event")) {
            return eventProcess(hasDescription, hasDeadlineOrEventTiming, description);
        } else if (description.startsWith("delete")) {
            return deleteProcess(hasDescription, invalidSearchOrDelete, description);
        } else if (description.startsWith("schedule")) {
            return scheduleProcess(invalidSearchOrDelete, hasDescription, description);
        } else if (description.startsWith("find")) {
            return findProcess(invalidSearchOrDelete, hasDescription, description);
        } else if (description.startsWith(("time"))) {
            return timeProcess(invalidSearchOrDelete, hasDescription, description);
        } else if (description.startsWith("bye")) {
            return new ByeCommand();
        } else {
            throw new DukeException("type in 'todo', 'deadline', 'event' to start!\n"
                    + "Also, type 'schedule' and key in a date in YYYY-MM-DD format "
                    + "to view schedule on that date!\n"
                    + "Or type 'time' and key in time in HH:mm format "
                    + "to search for events/deadline happening on that time!");
        }
    }

    private Command todoProcess(boolean hasDescription, String description) throws DukeException {
        if (!hasDescription) {
            throw new DukeException(("you don't even know what you want to do."));
        }
        return new AddCommand(Commands.TODO, description.substring(5));
    }

    private Command doneProcess(boolean hasDescription,
                                boolean invalidSearchOrDelete,
                                String description) throws DukeException {
        if (!hasDescription) {
            throw new DukeException("you need to give a number.");
        } else if (invalidSearchOrDelete) {
            throw new DukeException("Check one at a time pls and only one "
                    + "space between your 'done' and the task number.");
        }
        return new DoneCommand(description.split(" ")[1]);
    }

    private Command deadlineProcess(boolean hasDescription,
                                    boolean hasDeadlineOrEventTiming,
                                    String description) throws DukeException {
        if (!hasDescription) {
            throw new DukeException("no deadline given so how you know when it is?");
        } else if (!hasDeadlineOrEventTiming) {
            throw new DukeException("for deadlines, please include a '/by' just before the deadline!");
        }
        return new AddCommand(Commands.DEADLINE, description.substring(9));
    }

    private Command eventProcess(boolean hasDescription,
                                 boolean hasDeadlineOrEventTiming,
                                 String description) throws DukeException {
        if (!hasDescription) {
            throw new DukeException("I don't see any event.");
        } else if (!hasDeadlineOrEventTiming) {
            throw new DukeException("for events, please include a '/at' just before the event!");
        }
        return new AddCommand(Commands.EVENT, description.substring(6));
    }

    private Command deleteProcess(boolean hasDescription,
                                  boolean invalidSearchOrDelete,
                                  String description) throws DukeException {
        if (!hasDescription) {
            throw new DukeException("you need to give a number.");
        } else if (invalidSearchOrDelete) {
            throw new DukeException("Delete one at a time pls and "
                    + "only have one space between 'delete' and the task number.");
        }
        return new DeleteCommand(description.split(" ")[1]);
    }

    private Command scheduleProcess(boolean invalidSearchOrDelete,
                                    boolean hasDescription,
                                    String description) throws DukeException {
        if (invalidSearchOrDelete || !hasDescription) {
            throw new DukeException(("you need to input a legit date for e.g: 29-01-19, no more and no less."));
        }
        return new ScheduleCommand(description.substring(9));
    }

    private Command findProcess(boolean invalidSearchOrDelete,
                                boolean hasDescription,
                                String description) throws DukeException {
        if (invalidSearchOrDelete || !hasDescription) {
            throw new DukeException(("you need to input something to find."));
        }
        return new FindCommand(description.substring(5));
    }

    private Command timeProcess(boolean invalidSearchOrDelete,
                                boolean hasDescription,
                                String description) throws DukeException {
        if (invalidSearchOrDelete || !hasDescription) {
            throw new DukeException(("you need to input a legit time for e.g: 18:00, no more and no less."));
        }
        return new TimeCommand(description.substring(5));
    }
}
