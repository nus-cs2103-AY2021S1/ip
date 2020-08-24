package duke.command;

import duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Save taskList to file
 */
public class SaveCommand extends Command {

    private final List<Task> taskList;
    private final String filePath;

    public SaveCommand(List<Task> taskList, String filePath) {
        this.taskList = taskList;
        this.filePath = filePath;
    }

    @Override
    public boolean hasUndo() {
        return false;
    }

    @Override
    public void execute() {
        try {
            // Open file for write/overwrite
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : taskList) {
                fileWriter.write(task.toCSV() + "\n");
            }

            // Close file
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Save: " + taskList.size() + " entries");

        } catch (IOException e) {
            System.out.println("Error: Could not save to file. Ensure directory exists and file is not in use");
        }
    }

    @Override
    public void undo() {
        // Operation unsupported
        System.out.println("Undo: SaveCommand");
    }
}
