import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// TODO: 26/8/20 Consider a visitor pattern
// TODO: 26/8/20 Add try-catch clause

/**
 * A class containing a collection of static methods that Duke uses to parse Tasks from Strings.
 *
 * @author jingyenloh
 */
public class TaskParser {
    /**
     * Parses a Task from an input string.
     *
     * @param line the input to parse
     * @return a Task which was parsed from the String
     */
    public static Task parse(String line) {
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
     * @param isDone       whether the Task has been completed
     * @param taskName     the name of the Task
     * @param timeRangeStr the interval the Event occurs in
     * @return an Event Task
     */
    public static Event parseEvent(boolean isDone, String taskName, String timeRangeStr) {
        return new Event(isDone, taskName, timeRangeStr);
    }

    /**
     * Returns a Deadline Task, given its state, task name, and a String representing the deadline.
     *
     * @param isDone      whether the Task has been completed
     * @param taskName    the name of the Task
     * @param deadlineStr a String representing the deadline
     * @return a Deadline Task
     * @throws DateTimeParseException if <code>deadlineStr</code> is not well formatted
     */
    public static Deadline parseDeadline(boolean isDone, String taskName, String deadlineStr)
            throws DateTimeParseException {
        // TODO: 26/8/20 Handle DateTimeParseException
        LocalDate deadline = LocalDate.parse(deadlineStr);
        return new Deadline(isDone, taskName, deadline);
    }

    /**
     * Returns a Todo Task, given its state and task name.
     *
     * @param isDone   whether the Task has been completed
     * @param taskName the name of the Task
     * @return a Todo Task
     */
    public static Todo parseTodo(boolean isDone, String taskName) {
        return new Todo(isDone, taskName);
    }

    /**
     * Parses a type of Task out of an input String, given a command on the type of Task to parse into.
     *
     * @param command the type of Task to parse into
     * @param input   the input to parse to create the Task from
     * @return <code>Task</code> a Task that was parsed from the input
     * @throws InvalidTaskException if the input is malformed
     */
    // TODO: Consider some cleaner way of validating, perhaps a factory method for each Task
    public static Task parseInput(String command, String input) throws InvalidTaskException {
        switch (command) {
        case "todo":
            String[] todoDetails = input.split("todo ");
            if (todoDetails.length < 2) {
                throw new InvalidTaskException("😡 I have no idea what you want to do.");
            }
            String taskName = todoDetails[1];
            return new Todo(taskName);
        case "deadline":
            String[] deadlineDetails = input.split("deadline | /by ");
            if (deadlineDetails.length < 2) {
                throw new InvalidTaskException("What is it you want to do?");
            }
            if (deadlineDetails.length < 3) {
                throw new InvalidTaskException("What's your deadline? You have to tell me, you know.");
            }
            return TaskParser.parseDeadline(false, deadlineDetails[1], deadlineDetails[2]);
        case "event":
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
