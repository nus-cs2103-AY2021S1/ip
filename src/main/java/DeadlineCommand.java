public class DeadlineCommand extends TaskCommand {
    DeadlineCommand(String description) {
        super(description);
    }

    @Override
    public String execute() {
        Deadline deadline = new Deadline(description);
        Command.tasks.add(deadline);
        Command.uncompletedTasks ++;
        return super.commandResult(deadline);
    }
}
