package luke.commands;


import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.LukeException;

public class ListCommand extends Command {

    protected TaskList tasks;

    public ListCommand() {
        super();
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws LukeException {
        return ui.showListResult(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
