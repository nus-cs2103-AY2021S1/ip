import java.io.IOException;

public class DoneCommand extends Command {
    private int i;

    public DoneCommand(int i) {
        this.i = i;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.printDone(taskList.setDone(i));
            storage.refresh(taskList);
        } catch(IOException e) {
            System.out.println("Sorry something went wrong!");
        }
    }
}
