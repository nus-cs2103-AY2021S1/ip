package duke.command;

import duke.ImageType;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyListException;

public class DeleteCommand extends Command {

    public DeleteCommand() {
        super(CommandType.DELETE, ImageType.PENDING);
    }

    @Override
    public String execute(Ui ui, TaskList taskList) throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
        return ui.printPrompt("Which task do you want to delete?\n");
    }
}
