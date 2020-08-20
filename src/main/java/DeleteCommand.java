public class DeleteCommand extends Command {

    public DeleteCommand(String input) {
        super("delete", input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeInvalidListNumberInputException, DukeLoadingErrorException {
        ui.printDeleteTaskChatWindow(tasks.deleteTask(input), tasks.getTasksSize());
        storage.save(tasks.getTasks());
    }

}
