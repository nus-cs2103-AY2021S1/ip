public class EventCommand extends TaskCommand {
    EventCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException {
        Event event = new Event(fullCommand);
        tasks.add(event);
        storage.save(tasks);
        System.out.println(addedTaskMessage(event, tasks));
    }
}
