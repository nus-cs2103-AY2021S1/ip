public class ExitCommand extends Command{
    public void execute(TaskList inputTasks, Storage storage, Ui ui) throws DukeException{
        ui.showGoodbye();
    }
}
