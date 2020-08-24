public class ListCommand extends Command {

    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        if (tasklist.getSize() == 0) {
            throw new DukeException("there's nothing on the list yet.");
        } else {
            for (Task i : tasklist.getList()) {
                ui.showMessage(tasklist.getList().indexOf(i) + 1 + "." + i);
            }
        }
    }
}
