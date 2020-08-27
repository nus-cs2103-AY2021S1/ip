public class ListCommand implements Command {
    @Override
    public void execute(TaskList ts, Ui ui, String input) {
        ui.list();
    }
}
