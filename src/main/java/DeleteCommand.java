public class DeleteCommand extends Command {
    DeleteCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int j = Integer.parseInt(fullCommand.substring(7));
        System.out.println(tasks.delete(j));
        storage.save(tasks);
    }
}
