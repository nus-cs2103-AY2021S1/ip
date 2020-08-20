public class EventCommand extends TaskCommand {
    EventCommand(String description) {
        super(description);
    }

    @Override
    public String execute() throws EmptyDescriptionException {
        if (description.length() <= 6) {
            throw new EmptyDescriptionException("oops! the description of an event cannot be empty");
        } else {
            Event event = new Event(description);
            Command.tasks.add(event);
            Command.uncompletedTasks++;
            return super.commandResult(event);
        }
    }
}
