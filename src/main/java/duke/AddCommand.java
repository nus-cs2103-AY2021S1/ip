package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    public AddCommand(int commandType, TaskList taskList, String userInput) {
        super(commandType, taskList, userInput);
    }

    public String execute() throws DukeException {
        String[] parsedInput = userInput.split(Parser.SPACE, 2);
        Task newTask;
        if (getCommandType() == Command.CREATE_TODO) {
            newTask = createToDo(parsedInput, taskList);
        } else if (getCommandType() == Command.CREATE_DEADLINE) {
            newTask = createDeadline(parsedInput, taskList);
        } else if (getCommandType() == Command.CREATE_EVENT) {
            newTask = createEvent(parsedInput, taskList);
        } else {
            throw new DukeException("Invalid Add Command!");
        }

        taskList.add(newTask);
        String userMessage = Ui.informNewTask(newTask) + Ui.informNumberOfTasksRemaining(taskList);
        return userMessage;
    }

    private Task createToDo(String[] parsedInput, TaskList taskList) throws DukeException {
        if (isValidFormat(parsedInput, Command.CREATE_TODO)) {
            String taskDescription = parsedInput[1].trim();
            return new ToDo(taskDescription);
        } else {
            throw new DukeException("Invalid Todo Format!");
        }
    }

    private Task createDeadline(String[] parsedInput, TaskList taskList) throws DukeException {
        if (isValidFormat(parsedInput, Command.CREATE_DEADLINE)) {
            String description = parsedInput[1];
            String[] detailsAndDate = description.split("/by");
            String details = detailsAndDate[0].trim();
            String date = detailsAndDate[1].trim();
            LocalDate formattedDate = LocalDate.parse(date, TimedTask.DATE_FORMATTER);
            return new Deadline(details, formattedDate);
        } else {
            throw new DukeException("Invalid Deadline Format! Please include Deadline details and " +
                    "Date should be in D/M/YYYY");
        }
    }

    private Task createEvent(String[] parsedInput, TaskList taskList) throws DukeException {
        if (isValidFormat(parsedInput, Command.CREATE_EVENT)) {
            String description = parsedInput[1];
            String[] detailsAndDate = description.split("/at");
            String details = detailsAndDate[0].trim();
            String date = detailsAndDate[1].trim();
            LocalDate formattedDate = LocalDate.parse(date, TimedTask.DATE_FORMATTER);
            return new Event(details, formattedDate);
        } else {
            throw new DukeException("Invalid Event Format! Please include Event details and " +
                    "Date should be in D/M/YYYY");
        }
    }

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

    private boolean isValidTimeFormat(String date) {
        try {
            LocalDate.parse(date, TimedTask.DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
