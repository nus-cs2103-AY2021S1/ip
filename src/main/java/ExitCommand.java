import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks.getTaskList());
            ui.sayBye();
        } catch (IOException ex) {
            ui.printExceptions(ex);
        }
    }

    @Override
    public boolean isDone() {
        return true;
    }

}
