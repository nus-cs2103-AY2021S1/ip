public class DoneCommand implements Command {

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.markDone(index);
            storage.update(tasks.getList());
            String msg = ui.getSuccessMessage("done", task);
            ui.sendMessage(msg);
        } catch (DukeException e) {
            ui.sendMessage(e.getMessage());
        }
    }
}
