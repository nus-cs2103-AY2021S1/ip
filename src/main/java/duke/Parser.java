package duke;

import com.joestelmach.natty.DateGroup;
import duke.command.*;

import duke.exception.CommandNotFoundException;
import duke.exception.DukeException;
import duke.exception.IncompleteCommandException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Parser {
    /** the string input by the user to be parsed. */
    String input;
    /** the string input but separated by delimiter whitespace. */
    String[] inputSplitWhitespace;

    public Parser(String input) {
        this.input = input;
        this.inputSplitWhitespace = input.split(" ", 2);
    }

    /**
     * Returns the task category of the user input.
     *
     * @return the task category of the user input.
     */
    public String getTaskCategory() {
        return this.inputSplitWhitespace[0];
    }

    /**
     * Returns the task message of the user input.
     *
     * @return the task message of the user input.
     * @throws IncompleteCommandException if there is no task message.
     */
    public String getTaskMessage() throws IncompleteCommandException {
        try {
            return this.inputSplitWhitespace[1].trim();
        } catch (IndexOutOfBoundsException e) {
            throw new IncompleteCommandException(this.getTaskCategory());
        }
    }

    /**
     * Returns the task description of the user input.
     *
     * @return the task description of the user input.
     * @throws DukeException if there is no task message.
     */
    public String getTaskDescription() throws DukeException {
        String[] taskMessageArr = this.getTaskMessage().split("/");
        return taskMessageArr[0].trim();
    }

    /**
     * Returns the task time specified by the user input.
     *
     * @return the task time specified by the user input.
     * @throws DukeException if there is no task message or the task message is missing "/".
     */
    public String getTaskTime() throws DukeException {
        String[] taskMessageArr = this.getTaskMessage().split("/");
        boolean hasTime = taskMessageArr.length > 1 &&
                taskMessageArr[1].split(" ", 2).length > 1;
        if (!hasTime) {
            throw new DukeException("Please specify the time of task e.g. event finish book /by 2019-15-10");
        } else {
            String originalTime = taskMessageArr[1].split(" ", 2)[1];
            com.joestelmach.natty.Parser parser = new com.joestelmach.natty.Parser();
            List<DateGroup> groups = parser.parse(originalTime);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            for (DateGroup dates:groups) {
//                System.out.println(dates.getDates());
//            }
            return format.format(groups.get(0).getDates().get(0));
        }
    }

    /**
     * Returns the task number specified by the user input.
     *
     * @return the task number specified by the user input.
     * @throws DukeException if the task number is not specified.
     */
    public int getTaskNumber() throws DukeException {
        String digitsOnlyInput = this.input.replaceAll("[^0-9]", "");
        if (digitsOnlyInput.isEmpty()) {
            throw new DukeException("Specify the task number to change e.g. done 12 / delete 4");
        } else {
            int taskNumberToMark = Integer.parseInt(digitsOnlyInput);
            return taskNumberToMark;
        }
    }

    /**
     * Parses and makes sense of every user input before creating the respective commands.
     *
     * @return respective commands requested by the user input.
     * @throws DukeException if the tasktime cannot be parsed into datetime.
     */
    public Command parse() throws DukeException {
        switch (getTaskCategory()) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(getTaskNumber());
        case "delete":
            return new DeleteCommand(getTaskNumber());
        case "find":
            return new FindCommand(getTaskDescription());
        case "todo":
            return new AddCommand("todo", getTaskMessage());
        case "event":
        case "deadline":
            try {
                return new AddCommand(getTaskCategory(), getTaskDescription(), getTaskTime());
            } catch (DukeException e) {
                throw new DukeException("Datetime could not be recognised. Use yyyy-mm-dd format e.g. 2019-10-15");
            }
        default:
            throw new CommandNotFoundException();
        }
    }
}
