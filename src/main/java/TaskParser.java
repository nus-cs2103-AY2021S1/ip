import java.time.LocalDate;

// TODO: 26/8/20 Consider a visitor pattern
// TODO: 26/8/20 Add try-catch clause
public class TaskParser {
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

    public static Event parseEvent(boolean isDone, String taskName, String timeRangeStr) {
        return new Event(isDone, taskName, timeRangeStr);
    }

    public static Deadline parseDeadline(boolean isDone, String taskName, String deadlineStr) {
        // TODO: 26/8/20 Handle DateTimeParseException
        LocalDate deadline = LocalDate.parse(deadlineStr);
        return new Deadline(isDone, taskName, deadline);
    }

    public static Todo parseTodo(boolean isDone, String taskName) {
        return new Todo(isDone, taskName);
    }

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
