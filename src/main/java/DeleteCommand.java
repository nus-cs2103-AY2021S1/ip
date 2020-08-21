public class DeleteCommand extends Command {
    private int taskNo;

    DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeLoadingErrorException {
        Task deletedTask = taskList.deleteTask(taskNo);
        storage.save(taskList);
        String uiMessage = String.format(
                "Noted. I've removed this task:\n%s\n%s",
                deletedTask.toString(),
                taskList.taskSizeMessage());
        ui.print(uiMessage);
    }
}
