package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyListException;

public class FindCommand extends Command {

    public FindCommand() {
        super(CommandType.FIND);
    }

    @Override
    public String execute(Ui ui, TaskList taskList) throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
        return ui.printFindPrompt();
    }
}