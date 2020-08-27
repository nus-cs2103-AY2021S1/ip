package alison.command;

import alison.exception.AlisonException;
import alison.tool.Storage;
import alison.tool.TaskList;
import alison.tool.Ui;

public class UnknownCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlisonException {
        throw AlisonException.defaultException();
    }

}
