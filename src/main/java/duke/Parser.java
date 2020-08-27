package duke;

import duke.exception.DateException;
import duke.exception.MissingInformationException;
import duke.format.DateFormat;
import java.util.Date;

/**
 * Represents a decoder to make sense of the user input.
 * It stores the user's command in the form of an array to separate
 * the different components of the command.
 */
public class Parser {
    String[] commandArr;

    /**
     * Creates an instance of Parser that splits the user
     * command into two parts, the first being the key command word
     * and the second being the description after that.
     * @param userInput The user's command
     */
    public Parser(String userInput) {
        this.commandArr = userInput.split(" ", 2);
    }

    /**
     * Extracts the key command word of the user command.
     * @return the key word of user command
     */
    public String getCommand() {
        return this.commandArr[0];
    }

    /**
     * Extracts the task number referred to in the user command.
     * @return the task number
     * @throws MissingInformationException If task number is not provided.
     * @throws NumberFormatException If the task number is not given in the form of a number.
     */
    public int getIndex() throws MissingInformationException, NumberFormatException {

        if (this.commandArr.length < 2 || commandArr[1].isBlank()) {
            throw new MissingInformationException("duke.Task number is missing!");
        }
        return Integer.parseInt(commandArr[1]);
    }

    /**
     * Extracts the description of command after the key word
     * @param taskType the type of task that we want extract description for
     * @return the description of command
     * @throws MissingInformationException If description is not provided.
     */
    public String getDescription(String taskType) throws MissingInformationException {
        if (commandArr.length < 2 || commandArr[1].isBlank()) {
            throw new MissingInformationException(
                    String.format("The description of a %s cannot be empty.", taskType));
        }

        return commandArr[1];
    }

    /**
     * Extracts the name of the task from command.
     * @param taskType the type of task that we want extract name for
     * @return the name of the task
     * @throws MissingInformationException If the name is not provided.
     */
    public String getName(String taskType) throws MissingInformationException {
        String description = getDescription(taskType);
        String splitBy = taskType.equals("deadline") ? " /by " : " /at ";
        String[] detailArr = description.split(splitBy, 2);
        return detailArr[0];
    }

    /**
     * Extracts the date of deadline from user command.
     * @return the date of deadline
     * @throws MissingInformationException If the date is not provided.
     * @throws DateException If the date provided is of the wrong format.
     */
    public Date getDeadlineBy() throws MissingInformationException, DateException {
        String details = commandArr[1];
        String[] detailArr = details.split(" /by ", 2);
        if (detailArr.length < 2 || detailArr[1].isBlank()) {
            throw new MissingInformationException("duke.task.Deadline is missing a date.");
        }
        return DateFormat.parseDate(detailArr[1]);
    }

    /**
     * Extracts the date of event from user command.
     * @return the date of event
     * @throws MissingInformationException If the date is not provided.
     * @throws DateException If the date provided is of the wrong format.
     */
    public Date getEventAt() throws MissingInformationException, DateException {
        String details = commandArr[1];
        String[] detailArr = details.split(" /at ", 2);
        if (detailArr.length < 2 || detailArr[1].isBlank()) {
            throw new MissingInformationException("duke.task.Event is missing a date.");
        }
        return DateFormat.parseDate(detailArr[1]);
    }

    /**
     * Extracts the date when the user queries for all tasks with a specified date.
     * @return the date being queried
     * @throws MissingInformationException If the date is not provided.
     */
    public String getDate() throws MissingInformationException {
        if (commandArr.length < 2 || commandArr[1].isBlank()) {
            throw new MissingInformationException("Date is missing!");
        } else {
            return commandArr[1];
        }
    }
}
