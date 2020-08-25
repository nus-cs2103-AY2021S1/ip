package commands;

import data.exception.DukeInvalidUserInputException;
import data.task.TaskList;
import data.task.ToDo;
import storage.Storage;
import ui.Ui;

public class ToDoCommand extends Command {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private String user_input;

    public ToDoCommand(TaskList taskList, Storage storage, Ui ui, String user_input) {
        this.taskList = taskList;
        this.storage = storage;
        this.user_input = user_input;
        this.ui = ui;
    }

    @Override
    public void execute() throws DukeInvalidUserInputException {
        try {
            String description = user_input.substring(5).trim();
            if (description.isEmpty()) {
                throw new DukeInvalidUserInputException("I'm sorry to inform you that the "
                        + "description of a todo must not be empty.");
            }
            ToDo newTask = new ToDo(description);
            this.taskList.add(newTask);
            this.ui.showTotalTasks(this.taskList.getTotalTask());
            this.storage.saveTask(newTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidUserInputException("I'm sorry to inform you that the "
                    + "description of a todo must not be empty.");
        }
    }

    @Override
    public String toString() {
        return "ToDoCommand";
    }
}
