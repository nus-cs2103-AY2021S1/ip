package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.time.DateTimeException;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        try {
            switch (commandWord) {
            case "todo":
                taskList.addTodo(taskName, storage);
                break;
            case "deadline":
                taskList.addDeadline(taskName, storage);
                break;
            case "event":
                taskList.addEvent(taskName, storage);
                break;
            }
        } catch (DateTimeException e) {
            throw new DukeException("Please enter dates in this format: dd/MM/yyyy timeIn24Hr" +
                    "\nE.g. 01/12/2020 2359");
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
