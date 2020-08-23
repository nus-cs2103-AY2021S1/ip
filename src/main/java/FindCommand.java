public class FindCommand extends Command {

    private String command;

    public FindCommand(String command) {
        this.command = command;
    }

    @Override
    void execute(TaskList tasks, UI ui) throws DukeException {
        
    }

    @Override
    boolean isExit() {
        return false;
    }
}
