public class DeleteCommand extends Command{
    private final int num;

    DeleteCommand(String command, int num) {
        super(command, false);
        this.num = num;
    }

    protected void execute(TaskList list, Ui ui, Storage storage) {
        try {
            if (num < 0 || num > list.size()) {
                throw new DukeException("☹ OOPS!!! there is no such task");
            } else {
                Task deleted = list.delete(num - 1);
                ui.deleteMessage(deleted.toString(), list.size());
            }
        } catch (DukeException e) {
            ui.errorEncounter(e);
        }
    }
}
