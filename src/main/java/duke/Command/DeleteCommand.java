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

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
<<<<<<< HEAD
    public String execute() {
<<<<<<< HEAD
        Task task = Duke.listArray.get(index - 1);
        Duke.listArray.remove(index - 1);
        return Message.DELETE + task.toString() + "\n" +
                "Now you have " + Duke.listArray.size() +
                (Duke.listArray.size() == 1 ? " task " : " tasks ")
=======
        Task task = listArray.get(index - 1);
        listArray.remove(index - 1);
        return Message.DELETE + task.toString() + "\n" +
                "Now you have " + listArray.size() +
                (listArray.size() == 1 ? " task " : " tasks ")
>>>>>>> 7cf06867bc1780a8ba55bc5d6537869fa9988b2d
                + "in the list";
=======
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(index);
        taskList.remove(index);
        storage.saveTasks(taskList);
        ui.showTaskDeletionMessage(task, taskList);
>>>>>>> master
    }
}
