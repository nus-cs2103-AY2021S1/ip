package command;

import java.util.Objects;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

/**
 * Class to initiate the Find Command
 */
public class FindCommand extends Command {
    private String findString;

    /**
     * Constructor for the find command
     *
     * @param findString string to find.
     */
    public FindCommand(String findString) {
        super(CommandType.Find);
        this.findString = findString;
    }

    /**
     * Runs the command to find Matching Tasks
     *
     * @param taskList ArrayList of Tasks Objects.
     * @param ui       Object of the Ui class.
     * @param storage  Object of the Storage class.
     * @throws DukeException Exception that occurs while executing the command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList filteredTaskList = taskList.find(this.findString);
        return ui.displayMatchingTasks(filteredTaskList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FindCommand that = (FindCommand) o;
        return Objects.equals(findString, that.findString);
    }
}
