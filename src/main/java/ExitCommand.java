import javafx.application.Platform;
public class ExitCommand extends Command{

    public ExitCommand(){
        super();
    }

    public void execute(TaskList inputTasks, Storage storage, Ui ui) throws DukeException{
        ui.showGoodbye();
        Platform.exit();
    }
}
