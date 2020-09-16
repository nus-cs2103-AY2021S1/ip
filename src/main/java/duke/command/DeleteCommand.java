package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyListException;
import duke.exception.NonExistentTaskException;

public class DeleteCommand extends Command {

    public DeleteCommand() {
        super(CommandType.DELETE);
    }

    @Override
    public String execute(Ui ui, TaskList taskList) throws EmptyListException,
            NonExistentTaskException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
        return ui.printDeletePrompt();
    }
}