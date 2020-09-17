package cartona.command;

import cartona.DateParser;

import cartona.exception.EmptyTaskDescriptionException;
import cartona.exception.InvalidInputException;
import cartona.exception.InvalidTaskIdException;
import cartona.exception.InvalidTaskTimeException;
import cartona.exception.UnknownCommandException;

import cartona.task.Deadline;
import cartona.task.Event;
import cartona.task.Task;
import cartona.task.TaskDate;
import cartona.task.Todo;

/**
 * The Parser class is responsible for parsing various types of Strings encountered within the program.
 *
 * @author Jaya Rengam
 */
public class Parser {

    private Todo parseTodo(String name) {
        return new Todo(name, false);
    }

    private Deadline parseDeadline(String nameAndDate) throws InvalidTaskTimeException, EmptyTaskDescriptionException {
        String deadlineName;
        String time;

        try {
            String[] nameAndTime = nameAndDate.split(" /by ");
            deadlineName = nameAndTime[0];
            time = nameAndTime[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskTimeException("Error: Missing '/by' keyword");
        }

        if (deadlineName.equals("")) {
            throw new EmptyTaskDescriptionException("Error: Empty task name not allowed");
        }
        if (time.equals("")) {
            throw new InvalidTaskTimeException("Error: Please enter a valid time for the deadline");
        }

        TaskDate dateTime = DateParser.parseTaskDate(time);
        return new Deadline(deadlineName, false, dateTime);
    }

    private Event parseEvent(String nameAndDate) throws InvalidTaskTimeException, EmptyTaskDescriptionException {
        String eventName;
        String timeRange;

        try {
            String[] nameAndTime = nameAndDate.split(" /at ");
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
    }

    private Task parseTaskToAdd(String consoleArg) throws InvalidInputException {

        // checks if there is any text after "add "
        if (consoleArg.length() <= 4) {
            throw new UnknownCommandException("Error: Missing task type keyword");
        }

        String keyword = consoleArg.substring(4).split(" ")[0];

        switch (keyword) {
        case("todo"):
            if (consoleArg.length() <= 9) {
                throw new EmptyTaskDescriptionException("Error: Empty task name not allowed");
            }

            return parseTodo(consoleArg.substring(9));

        case("deadline"):
            return parseDeadline(consoleArg.substring(13));

        case("event"):
            return parseEvent(consoleArg.substring(10));

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

                if (taskIdToDelete < 1) {
                    throw new InvalidInputException("Error: Task numbers start from 1");
                }

                return new DeleteCommand(taskIdToDelete);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Error: Invalid number argument after 'delete'");
            }
        case ("done"):
            try {
                if (words.length < 2) {
                    throw new InvalidInputException("Error: No number argument after 'delete'");
                }

                Integer taskIdToComplete;

                try {
                    taskIdToComplete = Integer.parseInt(words[1]);
                } catch (NumberFormatException e) {
                    throw new InvalidTaskIdException("Error: Task ID is invalid");
                }

                if (taskIdToComplete < 1) {
                    throw new InvalidInputException("Error: Task numbers start from 1");
                }

                return new DoneCommand(taskIdToComplete);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Error: Invalid number argument after 'delete'");
            }
        case ("undone"):
            try {
                if (words.length < 2) {
                    throw new InvalidInputException("Error: No number argument after 'delete'");
                }

                Integer taskIdToUncomplete;

                try {
                    taskIdToUncomplete = Integer.parseInt(words[1]);
                } catch (NumberFormatException e) {
                    throw new InvalidTaskIdException("Error: Task ID is invalid");
                }

                if (taskIdToUncomplete < 1) {
                    throw new InvalidInputException("Error: Task numbers start from 1");
                }

                return new UndoneCommand(taskIdToUncomplete);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Error: Invalid number argument after 'delete'");
            }
        case ("find"):
            if (userInput.length() <= 5) {
                throw new InvalidInputException("Error: Please specify search keyword");
            }

            String keyword = userInput.substring(5);

            return new FindTaskCommand(keyword);
        case ("edit"):
            if (words.length < 4) {
                throw new InvalidInputException("Error: Edit parameters missing");
            }

            Integer taskIdToEdit;
            try {
                taskIdToEdit = Integer.parseInt(words[1]);
            } catch (NumberFormatException e) {
                throw new InvalidTaskIdException("Error: Task ID is invalid");
            }

            // Pass on edit string to Command
            return new EditCommand(taskIdToEdit, userInput);
        default:
            throw new UnknownCommandException("Error: Invalid command keyword!");
        }

    }

    /**
     * Parses the storage string representation of a Task and returns the Task.
     *
     * @param storageLine A storage string representation of a Task.
     * @return The task represented by storageLine.
     */
    public Task parseFromStorage(String storageLine) {
        String[] taskData = storageLine.split(" \\| ");
        assert taskData.length >= 2
                : "Task storage line is missing fields";


        String taskType = taskData[0];
        assert taskType.equals("T") || taskType.equals("D") || taskType.equals("E")
                : "Task type in storage does not match expected characters";

        boolean taskIsDone = Integer.parseInt(taskData[1]) == 1;
        String taskName = taskData[2];

        if (taskType.equals("T")) {
            return new Todo(taskName, taskIsDone);

        } else {
            String taskTime = taskData[3];

            if (taskType.equals("D")) {
                TaskDate dueTime = DateParser.parseDateFromStorage(taskTime);
                return new Deadline(taskName, taskIsDone, dueTime);

            } else {
                TaskDate startDate = DateParser.parseRangeFromStorage(taskTime, true);
                TaskDate endDate = DateParser.parseRangeFromStorage(taskTime, false);

                return new Event(taskName, taskIsDone, startDate, endDate);
            }
        }
    }
}
