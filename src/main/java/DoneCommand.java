public class DoneCommand extends Command {

    public DoneCommand(String command){
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        tasks = tasks.done(this.command);
        ui.showDoneTask(tasks.getAddedOrDeletedTask());
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
