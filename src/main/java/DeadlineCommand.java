public class DeadlineCommand extends TaskCommand {
    DeadlineCommand(String description) {
        super(description);
    }

    @Override
    public String execute() throws EmptyDescriptionException {
        if (description.length() <= 9) {
            throw new EmptyDescriptionException("oops! the description of a deadline cannot be empty");
        } else {
            Deadline deadline = new Deadline(description);
            Command.tasks.add(deadline);
            Command.uncompletedTasks++;
            return super.commandResult(deadline);
        }
    }
}
