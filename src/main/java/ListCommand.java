public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList ls, Ui ui) {
        ui.printResult("Here are the tasks in your list:");

        for (Task t : ls.getList()) {
            ui.printResult(((ls.indexOf(t) + 1) + ". " + t.getStatus()));
        }
    }
}
