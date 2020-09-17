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
        } catch (IOException E) {
            s = ui.errorWithFile();
        }
        return s;
    }
}
