package duke.command;

import duke.*;
import duke.task.TaskList;
import duke.DukeException;

public class DeleteCommand extends Command {

    private String input;

    public DeleteCommand(String input) {
        this.exit = false;
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (Parser.isValidIndex(input, tasks.getListSize())) {
            int index = Parser.getIndex(input);
            ui.deletedMessage(tasks.getList().get(index), tasks.getListSize());
            tasks.deleteTask(index);
            storage.saveListToFile(tasks.getList());
        } else {
            throw new DukeException("You don't have such task in your list...");
        }
    }
}
