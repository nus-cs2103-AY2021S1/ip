package duke.command;

import duke.Gui.Gui;
import duke.component.DukeException;
import duke.component.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class DoneCommand extends Command {
    private final String fullCommand;

    /**
     * constructor for fullCommand.
     * @param fullCommand full string from user input.
     */
    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }


    /**
     * Executes command, main logic for creating a new task.
     * @param taskList list of tasks.
     * @param gui instance of Ui to deal with user interface.
     * @param storage to read / write to storage.
     * @throws DukeException exception thrown when exception caught while running.
     */
    @Override
    public ArrayList<String> execute(TaskList taskList, Gui gui, Storage storage, ArrayList<String> responseList)
            throws DukeException {
        try {
            String numberCharacter = this.fullCommand.substring(5);
            int index = Integer.parseInt(numberCharacter) - 1;

            Task taskToChange = taskList.getItem(index);
            taskToChange.markDone();

            storage.modifyTask(taskToChange, index);

            responseList.addAll(gui.showDone());
            responseList.add(taskToChange.toString());

            return responseList;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a task number within the range of tasks");
        } catch (NumberFormatException a) {
            throw new DukeException("Please enter a valid task number for me to mark as done");
        }
    }
}
