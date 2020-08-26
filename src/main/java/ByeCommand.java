import java.io.FileWriter;
import java.io.IOException;

public class ByeCommand extends Command{

    ByeCommand(String str) {
        super(str);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            ui.sayBye();
            storage.writeToFile(list);
            // to get out of loop and terminate the program
            isExit = true;
        } catch (IOException E) {
            ui.errorWithFile();
        }
    }
}
