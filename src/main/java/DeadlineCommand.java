public class DeadlineCommand extends TaskCommand {
    DeadlineCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException {
        Deadline deadline = new Deadline(fullCommand);
        tasks.add(deadline);
        storage.save(tasks);
        System.out.println(addedTaskMessage(deadline, tasks));
    }
}
