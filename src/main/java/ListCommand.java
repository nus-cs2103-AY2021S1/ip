public class ListCommand extends Command {
    final static String COMMAND = "list";
    
    ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }
}
