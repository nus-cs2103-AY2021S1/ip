public class ClearCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) {
        tasks.clear();
        ui.displayThis("List is cleared");
    }
}