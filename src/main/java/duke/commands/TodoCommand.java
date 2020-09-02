package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class TodoCommand extends Command {
    private final boolean HAS_FINISHED = false;
    private String todoTaskDetails;

    public TodoCommand(String todoTaskDetails) {
        this.todoTaskDetails = todoTaskDetails;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTodo("0", todoTaskDetails);
        storage.writeToFile("T", "0", todoTaskDetails, "");
        int size = taskList.getSize();
        Task targetTask = taskList.get(size - 1);
        ui.printTaskAdded(targetTask.toString(), size);
        String messageAfterExecution = addTaskToString(targetTask, size);
        return new CommandResult(messageAfterExecution);
    }

    @Override
    public boolean isExit() {
        return this.HAS_FINISHED;
    }
}
