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
            return new TodoTask(isDone, taskName);
        }
        case "DEADLINE": {
            String deadline = tokens[3];
            return new DeadlineTask(taskName, deadline);
        }
        case "EVENT": {
            String timeRange = tokens[3];
            return new EventTask(taskName, timeRange);
        }
        default:
            throw new InvalidInputException("Unable to parse task");
        }
    }
}
