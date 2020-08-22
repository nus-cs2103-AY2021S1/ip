package duke.Command;

<<<<<<< HEAD
<<<<<<< HEAD
import duke.Duke;
=======
>>>>>>> 7cf06867bc1780a8ba55bc5d6537869fa9988b2d
import duke.Message;
import duke.Task;
=======
import duke.Storage;

import duke.Exception.DukeException;

import duke.Task.Task;
import duke.Task.TaskList;

import duke.Ui.Ui;
>>>>>>> master

public class CompleteCommand extends Command {

    private final int index;

    public CompleteCommand(int index) {
        this.index = index;
    }

<<<<<<< HEAD
    public String execute() {
<<<<<<< HEAD
        Task task = Duke.listArray.get(index - 1);
=======
        Task task = listArray.get(index - 1);
>>>>>>> 7cf06867bc1780a8ba55bc5d6537869fa9988b2d
=======
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(index);
>>>>>>> master
        task.markAsDone();
        storage.saveTasks(taskList);
        ui.showCompletionMessage(task);
    }
}
