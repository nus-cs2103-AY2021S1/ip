package duke;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.Commands;
import duke.commands.DateCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
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
            if (!hasDescription) {
                throw new DukeException("you need to give a number.");
            } else if (invalidSearchOrDelete) {
                throw new DukeException("Check one at a time pls and only one "
                        + "space between your 'done' and the task number.");
            }
            return new DoneCommand(description.split(" ")[1]);
        } else if (description.startsWith("todo")) {
            if (!hasDescription) {
                throw new DukeException(("you don't even know what you want to do."));
            }
            return new AddCommand(Commands.TODO, description.substring(5));
        } else if (description.startsWith("deadline")) {
            if (!hasDescription) {
                throw new DukeException("no deadline given so how you know when it is?");
            } else if (!hasDeadlineOrEventTiming) {
                throw new DukeException("for deadlines, please include a '/by' just before the deadline!");
            }
            return new AddCommand(Commands.DEADLINE, description.substring(9));
        } else if (description.startsWith("event")) {
            if (!hasDescription) {
                throw new DukeException("I don't see any event.");
            } else if (!hasDeadlineOrEventTiming) {
                throw new DukeException("for events, please include a '/at' just before the event!");
            }
            return new AddCommand(Commands.EVENT, description.substring(6));
        } else if (description.startsWith("delete")) {
            if (!hasDescription) {
                throw new DukeException("you need to give a number.");
            } else if (invalidSearchOrDelete) {
                throw new DukeException("Delete one at a time pls and "
                        + "only have one space between 'delete' and the task number.");
            }
            return new DeleteCommand(description.split(" ")[1]);
        } else if (description.startsWith("date")) {
            if (invalidSearchOrDelete || !hasDescription) {
                throw new DukeException(("you need to input a legit date for e.g: 29-01-19, no more and no less."));
            }
            return new DateCommand(description.substring(5));
        } else if (description.startsWith("find")) {
            if (invalidSearchOrDelete || !hasDescription) {
                throw new DukeException(("you need to input something to find."));
            }
            return new FindCommand(description.substring(5));
        } else if (description.startsWith(("time"))) {
            if (invalidSearchOrDelete || !hasDescription) {
                throw new DukeException(("you need to input a legit time for e.g: 18:00, no more and no less."));
            }
            return new TimeCommand(description.substring(5));
        } else if (description.startsWith("bye")) {
            return new ByeCommand();
        } else {
            throw new DukeException("type in 'todo', 'deadline', 'event' to start!\n"
                    + "Also, type 'date' and key in a date in YYYY-MM-DD format "
                    + "to search for events/deadlines happening on that date!\n"
                    + " Or type 'time' and key in time in HH:mm format "
                    + "to search for events/deadline happening on that time!");
        }
    }
}
