public class ByeCommand extends Command {

    public ByeCommand(String description) throws IllegalArgumentException {
        super(description);
    }

    public void execute(TaskList taskList) {
        System.exit(0);
    }
}