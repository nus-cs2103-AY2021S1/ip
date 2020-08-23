import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Storage {

    private String directoryPath;
    private String fileName;

    public Storage(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
    }

    public File loadData(UI ui) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directoryPath + "/" + fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
           ui.showLoadingError(e.getMessage());
        }
        return file;
    }

    public void saveData(TaskList tasks, UI ui) {
        File file = new File(directoryPath + "/" + fileName);
        try {
            resetFileContents(directoryPath + "/" + fileName);
            for (int i = 0; i < tasks.getListSize(); i++) {
                Task task = tasks.getTaskAtIndex(i);
                appendToFile(directoryPath + "/" + fileName,
                        task.getSaveFormat() + "\n");
            }
        } catch (IOException e) {
            ui.showError("Something went wrong while trying to save your data... :/");
        }
    }

    private void resetFileContents(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write("");
        fileWriter.close();
    }

    private void appendToFile(String filePath, String lineToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(lineToAdd);
        fileWriter.close();
    }
}
