import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String dataPathName;
    private String dataFilePath;

    public Storage(String dataPathName, String dataFilePath) {
        this.dataPathName = dataPathName;
        this.dataFilePath = dataFilePath;
    }

    public File getSavedData(Ui ui) {
        File folder = new File(this.dataPathName);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(this.dataFilePath);

        try {
            file.createNewFile();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }

        return file;
    }

    public void saveTasks(TaskList tasks, Ui ui) {
        File file = new File(this.dataFilePath);

        try {
            FileWriter writer = new FileWriter(file);
            writer.write("");
            writer.close();

            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                FileWriter fileWriter = new FileWriter(file, true);
                fileWriter.write(task.getSaveFormat() + "\n");
                fileWriter.close();
            }

        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }


}
