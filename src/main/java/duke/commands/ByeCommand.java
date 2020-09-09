package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.IncompleteCommandException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;

/**
 * Ends the program
 */
public class ByeCommand extends Command{

    public ByeCommand(String description){
        super(description);
    }

    /**
     * Ends the program
     *
     * @param taskList
     * @param ui
     * @param storage
     * @return String of the end command
     * @throws DukeException
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        String[] inputList = description.trim().split(" ", 2);

        //asserts the command to have the proper format
        assert(inputList.length == 1);
        if (inputList.length > 1){
            throw new IncompleteCommandException();
        }

        storage.saveTasks(taskList);
        return ui.byeGreetings();
    }

    @Override
    public boolean isComplete(){
        return true;
    }
}
