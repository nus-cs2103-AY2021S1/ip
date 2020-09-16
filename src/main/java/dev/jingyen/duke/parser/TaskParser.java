package dev.jingyen.duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import dev.jingyen.duke.Command;
import dev.jingyen.duke.model.Deadline;
import dev.jingyen.duke.model.Event;
import dev.jingyen.duke.model.Task;
import dev.jingyen.duke.model.Todo;

// TODO: 26/8/20 Consider a visitor pattern
// TODO: 26/8/20 Add try-catch clause

/**
 * A class containing a collection of static methods that dev.jingyen.duke.Duke uses to parse Tasks from Strings.
 *
 * @author jingyenloh
 */
public class TaskParser {
    /**
     * Parses a dev.jingyen.duke.model.Task from an input string.
     *
     * @param line the input to parse
     * @return a dev.jingyen.duke.model.Task which was parsed from the String
     */
    public static Task parse(String line) throws InvalidInputException, DateTimeParseException {
        String[] tokens = line.split("\\|");
        String taskType = tokens[0];
        boolean isDone = Boolean.parseBoolean(tokens[1]);
        String taskName = tokens[2];

        switch (taskType) {
        case "TODO": {
            return TaskParser.parseTodo(isDone, taskName);
        }
        case "DEADLINE": {
            String deadlineStr = tokens[3];
            return TaskParser.parseDeadline(isDone, taskName, deadlineStr);
        }
        case "EVENT": {
            String timeRangeStr = tokens[3];
            return TaskParser.parseEvent(isDone, taskName, timeRangeStr);
        }
        default:
            throw new InvalidInputException("Unable to parse task");
        }
    }

    /**
     * Returns an Event Task, given its state, task name, and the range it occurs within
     *
     * @param isDone       whether the dev.jingyen.duke.model.Task has been completed
     * @param taskName     the name of the dev.jingyen.duke.model.Task
     * @param timeRangeStr the interval the dev.jingyen.duke.model.Event occurs in
     * @return an dev.jingyen.duke.model.Event dev.jingyen.duke.model.Task
     */
    public static Event parseEvent(boolean isDone, String taskName, String timeRangeStr) {
        assert taskName != null;
        assert timeRangeStr != null;
        return new Event(isDone, taskName, timeRangeStr);
    }

    /**
     * Returns a Deadline Task, given its state, task name, and a String representing the deadline.
     *
     * @param isDone      whether the dev.jingyen.duke.model.Task has been completed
     * @param taskName    the name of the dev.jingyen.duke.model.Task
     * @param deadlineStr a String representing the deadline
     * @return a dev.jingyen.duke.model.Deadline dev.jingyen.duke.model.Task
     * @throws DateTimeParseException if <code>deadlineStr</code> is not well formatted
     */
    public static Deadline parseDeadline(boolean isDone, String taskName, String deadlineStr)
            throws DateTimeParseException {
        // TODO: 26/8/20 Handle DateTimeParseException
        assert taskName != null;
        assert deadlineStr != null;
        LocalDate deadline = LocalDate.parse(deadlineStr);
        return new Deadline(isDone, taskName, deadline);
    }

    /**
     * Returns a dev.jingyen.duke.model.Todo dev.jingyen.duke.model.Task, given its state and task name.
     *
     * @param isDone   whether the dev.jingyen.duke.model.Task has been completed
     * @param taskName the name of the dev.jingyen.duke.model.Task
     * @return a dev.jingyen.duke.model.Todo dev.jingyen.duke.model.Task
     */
    public static Todo parseTodo(boolean isDone, String taskName) {
        assert taskName != null;
        return new Todo(isDone, taskName);
    }

    /**
     * Parses a type of Task out of an input String, given a command on the type of Task to parse into.
     *
     * @param command the type of dev.jingyen.duke.model.Task to parse into
     * @param input   the input to parse to create the dev.jingyen.duke.model.Task from
     * @return <code>dev.jingyen.duke.model.Task</code> a dev.jingyen.duke.model.Task that was parsed from the input
     * @throws InvalidTaskException if the input is malformed
     */
    // TODO: Consider some cleaner way of validating, perhaps a factory method for each dev.jingyen.duke.model.Task
    public static Task parseInput(Command command, String input) throws InvalidTaskException, DateTimeParseException {
        assert command != null;
        assert input != null && !input.isBlank();
        switch (command) {
        case TODO:
            String[] todoDetails = input.split("todo ");
            if (todoDetails.length < 2) {
                throw new InvalidTaskException("😡 I have no idea what you want to do.");
            }
            String taskName = todoDetails[1];
            return new Todo(taskName);
        case DEADLINE:
            String[] deadlineDetails = input.split("deadline | /by ");
            if (deadlineDetails.length < 2) {
                throw new InvalidTaskException("What is it you want to do?");
            }
            if (deadlineDetails.length < 3) {
                throw new InvalidTaskException("What's your deadline? You have to tell me, you know.");
            }
            return TaskParser.parseDeadline(false, deadlineDetails[1], deadlineDetails[2]);
        case EVENT:
            String[] eventDetails = input.split("event | /at ");
            if (eventDetails.length < 2) {
                throw new InvalidTaskException("What is it you want to do?");
            }
            if (eventDetails.length < 3) {
                throw new InvalidTaskException("When do you need to do this? You have to tell me, you know.");
            }
            return new Event(eventDetails[1], eventDetails[2]);
        default:
            throw new InvalidTaskException("Um, I don't get what you're saying.");
        }
    }
}
