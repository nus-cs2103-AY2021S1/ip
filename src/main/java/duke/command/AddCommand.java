package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.TaskType;
import duke.Ui;
import duke.exception.DukeException;

/***
 * Adds a task to Tasklist.
 */
public class AddCommand extends Command {

    /**
     * Message to be printed when AddCommand is executed successfully.
     */
    protected static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n%s\n%s";

    protected String description;
    protected String date;
    protected TaskType type;

    /**
     * Constructs a new instance of an AddCommand.
     * @param description Description of task to be added.
     * @param date Date of deadline or event task
     * @param type Type of task to be added.
     */
    public AddCommand(String description, String date, TaskType type) {
        this.description = description;
        this.date = date;
        this.type = type;
    }

    /**
     * Executes the add command.
     * @param taskList TaskList associated with command.
     * @param ui Ui associated with command.
     * @param storage Storage associated with command.
     * @throws DukeException If there is error during execution of command.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(description, date, type);
        storage.writeToFile(taskList);
        return ui.printReply(String.format(MESSAGE_SUCCESS, taskList.get(taskList.size() - 1), taskList));
    }

    /**
     * Indicates whether Duke chatbot is still running.
     * @return false.
     */
    public boolean isExit() {
        return false;
    }

}
