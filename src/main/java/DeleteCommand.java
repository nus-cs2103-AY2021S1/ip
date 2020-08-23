package main.java;


public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            Task task = tasks.deleteTask(taskNumber - 1);
            storage.deleteTaskInFile(taskNumber);
            String message = ui.deleteSuccess(task, tasks.size());
            ui.sendMessage(message);
        }
    }
}
