package duke.command;

/**
 * Command that has no functionality other than printing to stdout
 */
public class InvalidCommand extends Command {

    private final String message;

    public InvalidCommand() {
        this.message = "Unrecognised Command!";
    }

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public boolean hasUndo() {
        return false;
    }

    @Override
    public void execute() {
        System.out.println(this.message);
    }

    @Override
    public void undo() {
        // Operation unsupported
        System.out.println("Undo: InvalidCommand");
    }
}
