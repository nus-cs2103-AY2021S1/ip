public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Command.isTerminated = true;
    }
}
