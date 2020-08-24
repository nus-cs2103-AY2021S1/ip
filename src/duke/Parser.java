package duke;

import duke.command.*;

import duke.exception.CommandNotFoundException;
import duke.exception.DukeException;
import duke.exception.IncompleteCommandException;

public class Parser {
    String input;
    String[] inputSplitWhitespace;

    public Parser(String input) {
        this.input = input;
        this.inputSplitWhitespace = input.split(" ", 2);
    }

    public String getTaskCategory() {
        return this.inputSplitWhitespace[0];
    }

    public String getTaskMessage() throws IncompleteCommandException {
        try {
            return this.inputSplitWhitespace[1].trim();
        } catch (IndexOutOfBoundsException e) {
            throw new IncompleteCommandException(this.getTaskCategory());
        }
    }

    public String getTaskDescription() throws DukeException {
        String[] taskMessageArr = this.getTaskMessage().split("/");
        return taskMessageArr[0].trim();
    }

    public String getTaskTime() throws DukeException {
        String[] taskMessageArr = this.getTaskMessage().split("/");
        boolean hasTime = taskMessageArr.length > 1 &&
                taskMessageArr[1].split(" ", 2).length > 1;
        if (!hasTime) {
            throw new DukeException("Please specify the time of task e.g. event finish book /by 2019-15-10");
        } else {
            return taskMessageArr[1].split(" ", 2)[1];
        }
    }

    public int getTaskNumber() throws DukeException {
        String digitsOnlyInput = this.input.replaceAll("[^0-9]", "");
        if (digitsOnlyInput.isEmpty()) {
            throw new DukeException("Specify the task number to change e.g. done 12 / delete 4");
        } else {
            int taskNumberToMark = Integer.parseInt(digitsOnlyInput);
            return taskNumberToMark;
        }
    }

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
            try {
                return new AddCommand("event", getTaskDescription(), getTaskTime());
            } catch (DukeException e) {
                throw e;
            }
        case "deadline":
            try {
                return new AddCommand("deadline", getTaskDescription(), getTaskTime());
            } catch (DukeException e) {
                throw e;
            }
        default:
            throw new CommandNotFoundException();
        }
    }
}
