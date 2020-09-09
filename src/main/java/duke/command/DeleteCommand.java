package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyListException;
import duke.exception.NonExistentTaskException;
import duke.task.Task;

public class DeleteCommand extends Command {

    public DeleteCommand() {
        super(false);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws EmptyListException,
            NonExistentTaskException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
        ui.printDeletePrompt();
        int taskNum = Integer.parseInt(ui.readCommand());
        if (taskNum > taskList.getTaskListSize()) {
            throw new NonExistentTaskException();
        }
        Task task = taskList.getTask(taskNum - 1);
        taskList.removeTask(taskNum - 1);
        ui.printDeleteAcknowledgement(taskList, task);

        Storage.saveTaskChanges(taskList);
    }
}