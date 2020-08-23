package command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.InvalidInputException;
import task.Task;

public class DeleteCommand extends Command {
    int index;

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index != 0 && index <= taskList.getSize()) {
            Task task = taskList.get(index - 1);
            taskList.removeTask(index - 1);
            ui.showDelete(task, taskList.getSize());
        } else {
            throw new InvalidInputException("Number provided is too small or too large, Please provide a valid task number");
        }
    }

    public DeleteCommand(int index) {
        super(CommandType.Delete);
        this.index = index;
    }

}
