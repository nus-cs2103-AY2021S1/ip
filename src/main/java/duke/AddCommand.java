package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * AddCommand is used when the user intends to add a task to the TaskList.
 */
public class AddCommand extends Command {
    public AddCommand(int commandType, TaskList taskList, String userInput) {
        super(commandType, taskList, userInput);
    }

    /**
     * Adds a specific task to the TaskList based on user input.
     *
     * @return Description of the specific task added.
     * @throws DukeException If this method is called on an invalid AddCommand.
     */
    public String execute() throws DukeException {
        String[] parsedInput = userInput.split(Parser.SPACE, 2);
        Task newTask;
        if (getCommandType() == Command.CREATE_TODO) {
            newTask = createToDo(parsedInput);
        } else if (getCommandType() == Command.CREATE_DEADLINE) {
            newTask = createDeadline(parsedInput);
        } else if (getCommandType() == Command.CREATE_EVENT) {
            newTask = createEvent(parsedInput);
        } else {
            throw new DukeException("Invalid Add Command!");
        }

        return addTaskAndPrint(newTask);
    }

    /**
     * Adds the given task to TaskList and prints the Description of that task.
     *
     * @param newTask The task to be added to taskList.
     * @return Description of the task.
     */
    private String addTaskAndPrint(Task newTask) {
        taskList.add(newTask);
        String userMessage = Ui.informNewTask(newTask) + Ui.informNumberOfTasksRemaining(taskList);
        return userMessage;
    }

    /**
     * Creates a ToDo task.
     *
     * @param parsedInput Input of the user parsed into tokens.
     * @return New ToDo task.
     * @throws DukeException If format of user input is incorrect.
     */
    private Task createToDo(String[] parsedInput) throws DukeException {
        if (isValidFormat(parsedInput, Command.CREATE_TODO)) {
            String taskDescription = parsedInput[1].trim();
            return new ToDo(taskDescription);
        } else {
            throw new DukeException("Invalid Todo Format!");
        }
    }

    /**
     * Creates a Deadline task.
     *
     * @param parsedInput Input of the user parsed into tokens.
     * @return New Deadline task.
     * @throws DukeException If format of user input is incorrect.
     */
    private Task createDeadline(String[] parsedInput) throws DukeException {
        if (isValidFormat(parsedInput, Command.CREATE_DEADLINE)) {
            String description = parsedInput[1];
            String[] detailsAndDate = description.split("/by");
            String details = detailsAndDate[0].trim();
            String date = detailsAndDate[1].trim();
            LocalDate formattedDate = LocalDate.parse(date, TimedTask.DATE_FORMATTER);
            return new Deadline(details, formattedDate);
        } else {
            throw new DukeException("Invalid Deadline Format! Please include Deadline details and "
                    + "Date should be in D/M/YYYY");
        }
    }

    /**
     * Creates an Event task.
     *
     * @param parsedInput Input of the user parsed into tokens.
     * @return New Event task.
     * @throws DukeException If format of the user input is incorrect
     */
    private Task createEvent(String[] parsedInput) throws DukeException {
        if (isValidFormat(parsedInput, Command.CREATE_EVENT)) {
            String description = parsedInput[1];
            String[] detailsAndDate = description.split("/at");
            String details = detailsAndDate[0].trim();
            String date = detailsAndDate[1].trim();
            LocalDate formattedDate = LocalDate.parse(date, TimedTask.DATE_FORMATTER);
            return new Event(details, formattedDate);
        } else {
            throw new DukeException("Invalid Event Format! Please include Event details and "
                    + "Date should be in D/M/YYYY");
        }
    }

    /**
     * Checks if user input is in a valid format supported by Duke.
     *
     * @param parsedInput User input parsed into tokens.
     * @param commandType Specific type of the Add Command.
     * @return True if user input is in a valid format.
     */
    private boolean isValidFormat(String[] parsedInput, int commandType) {
        boolean missingDescription = parsedInput.length < 2;
        if (missingDescription) {
            return false;
        }

        String description = parsedInput[1];

        if (description.isBlank()) {
            return false;
        }

        if (commandType == Command.CREATE_TODO) {
            return parsedInput.length == 2 && description != Parser.EMPTY_STRING;
        } else {
            String[] detailsAndDate;
            if (commandType == Command.CREATE_DEADLINE) {
                detailsAndDate = description.split("/by");
            } else if (commandType == Command.CREATE_EVENT) {
                detailsAndDate = description.split("/at");
            } else {
                return false;
            }

            if (detailsAndDate.length < 2) {
                return false;
            }

            String details = detailsAndDate[0].trim();
            String date = detailsAndDate[1].trim();
            return detailsAndDate.length == 2 && isValidTimeFormat(date) && !details.isBlank();
        }
    }

    /**
     * Checks if the date given in user input is of valid format supported by Duke.
     *
     * @param date Date given in user input for Task.
     * @return True if the date is in the correct D/M/YYYY format.
     */
    private boolean isValidTimeFormat(String date) {
        try {
            LocalDate.parse(date, TimedTask.DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
