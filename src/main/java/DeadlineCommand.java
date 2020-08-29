public class DeadlineCommand extends Command {
    DeadlineCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException {
        System.out.println(tasks.add(new Deadline(fullCommand)));
        storage.save(tasks);
    }
}
