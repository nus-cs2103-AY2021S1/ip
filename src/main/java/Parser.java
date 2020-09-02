/**
 * The Parser class is responsible for parsing various types of Strings encountered within the program.
 *
 * @auther Jaya Rengam
 */
public class Parser {

    private Task parseTaskToAdd(String consoleArg) throws EmptyTaskDescriptionException, InvalidTaskTimeException,
            UnknownCommandException, InvalidInputException {

        if (consoleArg.length() <= 4) {
            throw new UnknownCommandException("Error: Missing task type keyword");
        }

        String keyword = consoleArg.substring(4).split(" ")[0];
        switch (keyword) {
        case("todo"):
            if (consoleArg.length() <= 9) {
                throw new EmptyTaskDescriptionException("Error: Empty task name not allowed");
            }

            String name = consoleArg.substring(9);

            return new Todo(name, false);
        case("deadline"):
            String deadlineName = "";
            String time = "";

            try {
                String[] nameAndTime = consoleArg.substring(13).split(" /by ");
                deadlineName = nameAndTime[0];
                time = nameAndTime[1];
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidTaskTimeException("Error: Missing '/by' keyword or time range");
            }

            if (deadlineName.equals("")) {
                throw new EmptyTaskDescriptionException("Error: Empty task name not allowed");
            }
            if (time.equals("")) {
                throw new InvalidTaskTimeException("Error: Empty task name not allowed");
            }

            TaskDate dateTime = DateParser.parseDate(time);
            return new Deadline(deadlineName, false, dateTime);

        case("event"):
            String eventName = "";
            String timeRange = "";

            try {
                String[] nameAndTime = consoleArg.substring(10).split(" /at ");
                eventName = nameAndTime[0];
                timeRange = nameAndTime[1];
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidTaskTimeException("Error: Missing '/at' keyword or time range");
            }

            if (eventName.equals("")) {
                throw new EmptyTaskDescriptionException("Error: Empty task name not allowed");
            }
            if (timeRange.equals("")) {
                throw new InvalidTaskTimeException("Error: Please input a time range");
            }

            TaskDate startDateTime = DateParser.getRange(timeRange, true);
            TaskDate endDateTime = DateParser.getRange(timeRange, false);
            return new Event(eventName, false, startDateTime, endDateTime);
        default:
            throw new UnknownCommandException("Error: Invalid Event Type, please try again.");
        }
    }

    /**
     * Parses one line of user input and returns a Command describing what type of action to execute.
     *
     * @throws InvalidInputException if there is a formatting error in the input after the first keyword.
     * @throws UnknownCommandException if the keyword is not recognised.
     */
    public Command parseCommand(String userInput) throws InvalidInputException, UnknownCommandException {
        String[] words = userInput.split(" ");

        switch (words[0]) {

        case ("bye"):
            return new ExitCommand();
        case ("list"):
            return new PrintListCommand();
        case ("add"):
            Task taskToAdd = parseTaskToAdd(userInput);
            return new AddCommand(taskToAdd);
        case ("delete"):
            try {
                if (words.length < 2) {
                    throw new InvalidInputException("Error: No number argument after 'delete'");
                }
                int taskIdToDelete = Integer.parseInt(words[1]);
                return new DeleteCommand(taskIdToDelete);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Error: Invalid number argument after 'delete'");
            }
        case ("done"):
            try {
                if (words.length < 2) {
                    throw new InvalidInputException("Error: No number argument after 'delete'");
                }
                int taskIdToComplete = Integer.parseInt(words[1]);
                return new DoneCommand(taskIdToComplete);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Error: Invalid number argument after 'delete'");
            }
        case ("find"):
            if (userInput.length() <= 5) {
                throw new InvalidInputException("Error: Please specify search keyword");
            }

            String keyword = userInput.substring(5);

            return new FindTaskCommand(keyword);
        }

        throw new UnknownCommandException("Error: Invalid command keyword!");
    }

    /**
     * Parses the storage string representation of a Task and returns the Task.
     *
     * @param storageLine A storage string representation of a Task.
     * @return The task represented by storageLine.
     */
    public Task parseFromStorage(String storageLine) {
        String[] taskData = storageLine.split(" \\| ");
        String taskType = taskData[0];
        boolean taskIsDone = Integer.parseInt(taskData[1]) == 1;
        String taskName = taskData[2];

        if (!taskType.equals("T")) {
            String taskTime = taskData[3];
            if (taskType.equals("D")) {
                TaskDate dueTime = DateParser.parseDateFromStorage(taskTime);
                return new Deadline(taskName, taskIsDone, dueTime);
            } else {
                TaskDate startDate = DateParser.parseRangeFromStorage(taskTime, true);
                TaskDate endDate = DateParser.parseRangeFromStorage(taskTime, false);

                return new Event(taskName, taskIsDone, startDate, endDate);
            }
        } else {
            return new Todo(taskName, taskIsDone);
        }
    }
}