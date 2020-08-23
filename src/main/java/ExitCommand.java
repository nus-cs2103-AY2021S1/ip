import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand(String input) {
        super(input);
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("Bye. Hope to see you again soon!");
        // saves tasks to filePath
        try {
            storage.saveToCSV(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    boolean isExit() {
        return true;
    }
}
