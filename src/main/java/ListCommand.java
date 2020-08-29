public class ListCommand extends Command {
    @Override
    void execute(Storage storage, TaskList tasks, Ui ui) {
        if (tasks.size() <= 0) {
            ui.printStatus("    your list is empty!\n");
        } else {
            ui.printStatus("    here's your list:\n" + tasks);
        }
    }
}
