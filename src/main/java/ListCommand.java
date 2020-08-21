public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        if (tasks.isNull()) {
            throw new DukeException("There's nothing in the list :-(");
        } else {
            ui.displayList(tasks.getList());
        }
    }
}