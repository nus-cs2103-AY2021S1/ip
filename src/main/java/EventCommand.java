public class EventCommand extends TaskCommand {
    EventCommand(String description) {
        super(description);
    }

    @Override
    public String execute() {
        Event event = new Event(description);
        Command.tasks.add(event);
        Command.uncompletedTasks ++;
        return super.commandResult(event);
    }
}
