public class DeleteCommand extends Command {
    private final int entryDelete;


    public DeleteCommand(int entryDelete) {
        this.entryDelete = entryDelete;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException {
        try {
            ui.displayThis("OKay, I've remove this task: \n        " + tasks.delete(entryDelete) +
                    "\n    Now you have " + tasks.size() + " tasks in the list");

        } catch (Exception ex) {
            throw new DukeException("This task does not exist");
        }
    }
}