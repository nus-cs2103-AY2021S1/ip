public class AddCommand extends Command {

    protected AddCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws SparklesException {
        Command c = Parser.parseAddCommand(command);
        c.execute(taskList, ui, storage);
    }
}
