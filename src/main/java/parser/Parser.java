package parser;

import commands.*;
import data.exception.DukeIllegalCommandException;
import data.task.TaskList;
import storage.Storage;
import ui.Ui;

/**
 * Parses user input.
 */
public class Parser {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a parser to parse user input commands.
     * @param taskList of Duke.
     * @param storage of Duke.
     * @param ui of Duke.
     */
    public Parser(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Parses user input into a command for execution.
     * @param userInput full user_input string to be parsed.
     * @return command based on user_input.
     * @throws DukeIllegalCommandException when an invalid command is detected.
     */
    public Command parseCommand(String userInput) throws DukeIllegalCommandException {
        String[] userInputArr = userInput.split(" ");
        if (userInputArr.length == 0) {
            throw new DukeIllegalCommandException("");
        }
        String keyword = userInputArr[0];
        switch (keyword) {
        case "list":
            return new ListCommand(this.ui, this.taskList);
        case "done":
            return new DoneCommand(this.taskList, this.storage, userInput, this.ui);
        case "bye":
            return new ByeCommand(this.ui);
        case "todo":
            return new ToDoCommand(this.taskList, this.storage, this.ui, userInput);
        case "event":
            return new EventCommand(this.taskList, this.storage, this.ui, userInput);
        case "deadline":
            return new DeadlineCommand(this.taskList, this.storage, this.ui, userInput);
        case "delete":
            return new DeleteCommand(this.taskList, this.storage, this.ui, userInput);
        case "find":
            return new FindCommand(this.taskList, this.ui, userInput);
        case "finddt":
            return new FindDateCommand(this.taskList, this.ui, userInput);
        case "sort":
            return new SortCommand(this.ui, this.taskList, this.storage);
        case "sortdt":
            return new SortDateTimeCommand(this.ui, this.taskList, this.storage);
        default:
            throw new DukeIllegalCommandException(keyword);
        }
    }

    /**
     * Parses user_input when there are commands that follow a '/' into a command string.
     * @param userInput full follow-up user_input string to be parsed.
     * @return string command keyword based on user_input.
     */
    public static String parseFollowUpCommand(String userInput) {
        String[] userInputArr = userInput.split(" ");
        assert userInputArr[0] != null;
        return userInputArr[0];
    }
}
