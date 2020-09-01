package duke.commands;

import java.io.FileWriter;
import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;


/**
 * Saves all current tasks to the database and exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Sets the exit status of the command to true.
     *
     * @param command is the user input.
     */
    public ExitCommand(String command) {
        super(command);
        super.isExit = true;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        StringBuilder stringBuilder = new StringBuilder();
        taskList.forEach(task -> {
            stringBuilder.append(task.formatTaskForDatabase() + "\n");
        });

        try {
            FileWriter fileWriter = new FileWriter(storage.getDirectory());
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            return "Error while updating database file";
        }
        return "Goodbyeb";
    }

}
