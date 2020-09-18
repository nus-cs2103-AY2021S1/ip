import javafx.application.Platform;
public class ExitCommand extends Command{

    /**
     * ExitCommand constructor
     */
    public ExitCommand(){
        super();
    }

    /**
     * Execute the command
     * @param inputTasks the list of tasks used
     * @param storage the storage used
     * @param ui the ui of the app
     */
    public void execute(TaskList inputTasks, Storage storage, Ui ui){
        ui.showGoodbye();
        Platform.exit();
    }
}
