public class DoneCommand extends Command {
    DoneCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int i = Integer.parseInt(fullCommand.substring(5));
        System.out.println(tasks.done(i));
        storage.save(tasks);
    }
}
