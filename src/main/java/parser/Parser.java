package parser;

import commands.Command;
import commands.ToDoCommand;
import commands.DeadlineCommand;
import commands.EventCommand;
import commands.ListCommand;
import commands.DeleteCommand;
import commands.ByeCommand;
import commands.DoneCommand;
import commands.FindCommand;
import commands.FindDateCommand;
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

    public Parser(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Parses user input into a command for execution.
     * @param user_input full user_input string to be parsed.
     * @return command based on user_input.
     * @throws DukeIllegalCommandException when an invalid command is detected.
     */
    public Command parseCommand(String user_input) throws DukeIllegalCommandException {
        String[] user_inputArr = user_input.split(" ");
        if (user_inputArr.length == 0) {
            throw new DukeIllegalCommandException("");
        }
        String keyword = user_inputArr[0];
        switch (keyword) {
        case "list":
            return new ListCommand(this.ui, this.taskList);
        case "done":
            return new DoneCommand(this.taskList, this.storage, user_input);
        case "bye":
            return new ByeCommand(this.ui);
        case "todo":
            return new ToDoCommand(this.taskList, this.storage, this.ui, user_input);
        case "event":
            return new EventCommand(this.taskList, this.storage, this.ui, user_input);
        case "deadline":
            return new DeadlineCommand(this.taskList, this.storage, this.ui, user_input);
        case "delete":
            return new DeleteCommand(this.taskList, this.storage, this.ui, user_input);
        case "find":
            return new FindCommand(this.taskList, this.ui, user_input);
        case "finddt":
            return new FindDateCommand(this.taskList, this.ui, user_input);
            default:
                throw new DukeIllegalCommandException(keyword);
        }
    }

    /**
     * Parses user_input when there are commands that follow a '/' into a command string.
     * @param user_input full follow-up user_input string to be parsed.
     * @return string command keyword based on user_input.
     */
    public static String parseFollowUpCommand(String user_input) {
        String[] user_inputArr = user_input.split(" ");
        return user_inputArr[0];
    }
}
