package duke.command;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeInputException;
import duke.task.ToDo;

public class ToDoCommand extends ComplexCommand {

    public ToDoCommand(String params) {
        super(params);
    }

    @Override
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {

        try {
            // Attempt to create task
            ToDo newToDo = new ToDo(this.parseParams());

            // Store new task
            taskManager.storeTask(newToDo);

        } catch (DukeInputException e){
            ui.displayException(e);
        }

    }

    public String parseParams() throws DukeInputException {

        // Check if params is empty
        if (this.params.equals("")) {
            throw new DukeInputException("'todo' requires parameters.\n"
                    + "Use case: todo <name>");
        }

        return this.params;
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
