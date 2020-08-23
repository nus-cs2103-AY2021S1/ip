package command;

/**
 * Use isExit() to check for exit command
 */
public class ExitCommand extends Command{

    @Override
    public boolean isModifying() {
        return false;
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute() {
        // Nothing to do
    }

    @Override
    public void undo() {
        // Operation unsupported
        System.out.println("Undo: ExitCommand");
    }
}
