package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyListException;

public class DoneCommand extends Command {

    public DoneCommand() {
        super(CommandType.DONE);
    }

    @Override
    public String execute(Ui ui, TaskList taskList) throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
        return ui.printDonePrompt();
    }
}