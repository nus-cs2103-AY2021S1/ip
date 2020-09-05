package fei.command;

import fei.exception.FeiException;
import fei.tool.Storage;
import fei.tool.TaskList;
import fei.tool.Ui;

public class UnknownCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FeiException {
        throw FeiException.defaultException();
    }

}
