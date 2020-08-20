public class ByeCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.show("\t Bye. Hope to see you again soon!");
    }
}
