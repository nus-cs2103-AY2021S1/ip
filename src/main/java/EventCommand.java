public class EventCommand extends Command {
    EventCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException {
        System.out.println(tasks.add(new Event(fullCommand)));
        storage.save(tasks);
    }
}
