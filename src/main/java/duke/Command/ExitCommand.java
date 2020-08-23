package duke.Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class ExitCommand extends Command {

    /**
     * Constructs a ExitCommand object with the input specified
     * @param input User's input that is processed by the ExitCommand Object
     */
    public ExitCommand(String input) {
        super(input);
    }

    /**
     * Invokes the ExitCommand object which terminates Duke
     * @param tasks TaskList that contains an ArrayList of Task
     * @param ui Ui object that interacts with User
     * @param storage Storage object that reads from/write to specified filePath
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
        // saves tasks to filePath
        try {
            storage.saveToCSV(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns true as ExitCommand is a terminating Command
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}