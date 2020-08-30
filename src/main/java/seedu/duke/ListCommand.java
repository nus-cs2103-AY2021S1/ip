package seedu.duke;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList ls, Ui ui) {
        ui.printResult("Here are the tasks in your list:");

        for (Task task : ls.getList()) {
            ui.printResult(((ls.indexOf(task) + 1) + ". " + task.getStatus()));
        }
    }
}
