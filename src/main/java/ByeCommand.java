public class ByeCommand extends Command {

    @Override
    void execute(TaskList tasks, UI ui) throws DukeException {
        System.out.println("Bye! Have a nice day!");
    }

    protected boolean isExit() {
        return true;
    }
}
