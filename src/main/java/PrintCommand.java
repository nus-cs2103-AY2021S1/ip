public class PrintCommand extends Command {

    public PrintCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.printTaskList(taskList);
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
