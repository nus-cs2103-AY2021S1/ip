package duke.command;

import duke.Parser;
import duke.Ui;
import duke.Storage;
import duke.task.TaskList;
import duke.DukeException;

public class DeleteCommand extends Command {

    private String input;

    public DeleteCommand(String input) {
        this.exit = false;
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (Parser.isValidIndex(input, taskList.getListSize())) {
            int index = Parser.getIndex(input);
            ui.printDeletedMessage(taskList.getList().get(index), taskList.getListSize());
            taskList.deleteTask(index);
            storage.saveListToFile(taskList.getList());
        } else {
            throw new DukeException("You don't have such task in your list...");
        }
    }
}
