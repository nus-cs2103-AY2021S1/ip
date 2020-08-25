public class endCommand extends Command {

    public endCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public void execute() {
        this.isExit = true;
    }
}
