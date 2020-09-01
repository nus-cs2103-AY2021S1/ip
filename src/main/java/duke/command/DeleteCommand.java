package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidIndexException;
import duke.task.Task;

public class DeleteCommand extends Command {
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        int taskNumber = Integer.parseInt(fullCommand.substring(7));

        Task task = tasks.delete(taskNumber);

        storage.save(tasks);
        return deletedTaskMessage(task, tasks);
    }

    public String deletedTaskMessage(Task deletedTask, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("sure thing. i have removed this task: \n    ")
                .append(deletedTask).append("\n")
                .append(tasks.numberOfTasks());
        return sb.toString();
    }
}
