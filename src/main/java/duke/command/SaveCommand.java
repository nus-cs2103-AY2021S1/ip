package duke.command;

import duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Save taskList to file
 */
public class SaveCommand implements Command {

    private final List<Task> taskList;
    private final String filePath;

    public SaveCommand(List<Task> taskList, String filePath) {
        this.taskList = taskList;
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        try {
            // Open file for write/overwrite
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : taskList) {
                fileWriter.write(task.toCsv() + "\n");
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaveCommand)) return false;
        SaveCommand that = (SaveCommand) o;
        return taskList.equals(that.taskList) &&
                filePath.equals(that.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList, filePath);
    }
}
