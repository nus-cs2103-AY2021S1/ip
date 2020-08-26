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

    public static EventTask parseEvent(boolean isDone, String taskName, String timeRangeStr) {
        return new EventTask(isDone, taskName, timeRangeStr);
    }

    public static DeadlineTask parseDeadline(boolean isDone, String taskName, String deadlineStr) {
        // TODO: 26/8/20 Handle DateTimeParseException
        LocalDate deadline = LocalDate.parse(deadlineStr);
        return new DeadlineTask(isDone, taskName, deadline);
    }

    public static TodoTask parseTodo(boolean isDone, String taskName) {
        return new TodoTask(isDone, taskName);
    }
}
