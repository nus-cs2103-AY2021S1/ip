package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyListException;

public class PriorityCommand extends Command {

    public PriorityCommand() {
        super(CommandType.PRIORITY);
    }

    @Override
    public String execute(Ui ui, TaskList taskList) throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
        return ui.printPrompt("Which task do you want to assign a priority to?\n");
    }
}
