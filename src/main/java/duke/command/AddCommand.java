package duke.command;

import java.io.IOException;
import java.time.DateTimeException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the AddCommand when users add items to TaskList.
 */
public class AddCommand extends Command {

    private final String commandWord;
    private final String taskName;
    /**
     * Creates an AddCommand.
     *
     * @param commandWord the type of command
     * @param taskName the name of the task
     */
    public AddCommand(String commandWord, String taskName) {
        this.commandWord = commandWord;
        this.taskName = taskName;
    }
    /**
     * Adds To-Do, Deadline and Event tasks into TaskList.
     *
     * @param taskList taskList that stores Task objects
     * @param ui Ui that handles input and output to User
     * @param storage storage that handles data storage
     * @throws IOException Ioexception
     * @throws DukeException DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        try {
            switch (commandWord) {
            case "todo":
                return taskList.addTodo(taskName, storage);

            case "deadline":
                return taskList.addDeadline(taskName, storage);

            case "event":
                return taskList.addEvent(taskName, storage);

            default:
                assert false : "Task does not belong to the 3 types of tasks can be added into task list.";
                return "";

            }
        } catch (DateTimeException e) {
            throw new DukeException("Please enter dates in this format: dd/MM/yyyy HHmm"
                    + "\nE.g. 01/12/2020 2359");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid description!");
        }
    }
    /**
     * Returns true if and only if it is a command to exit the program.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
