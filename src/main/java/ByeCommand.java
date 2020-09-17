import javafx.application.Platform;

import java.io.IOException;

public class ByeCommand extends Command {

    ByeCommand(String str) {
        super(str);
    }

    /**
     * Says bye to the user.
     * Writes current tasks into hard disk.
     * Exits the program.
     *
     * @param list List of tasks to be saved.
     * @param ui Ui that prints out the output.
     * @param storage Storage that reads from and writes to hard disk.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String s;
        try {
            s = ui.sayBye();
            storage.writeToFile(list);
            // to get out of loop and terminate the program
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(1500);
                    Platform.exit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        } catch (IOException E) {
            s = ui.errorWithFile();
        }
        return s;
    }
}
