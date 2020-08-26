public class RemoveCommand implements Command {

    private final int index;

    private final boolean clear;

    public RemoveCommand(int index) {
        this.index = index;
        clear = false;
    }

    public RemoveCommand() {
        index = -1;
        clear = true;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (clear) {
            tasks.removeAll();
            storage.update(tasks.getList());
            ui.sendMessage("Your list has been cleared!!");
            return;
        }
        try {
            Task task = tasks.remove(index);
            storage.update(tasks.getList());
            String msg = ui.getSuccessMessage("remove", task);
            msg += "\n" + ui.getTaskReportMessage(tasks.size());
            ui.sendMessage(msg);
        } catch (DukeException e) {
            ui.sendMessage(e.getMessage());
        }
    }
}
