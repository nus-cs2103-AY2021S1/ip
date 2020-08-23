import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class FileManager {

    private final String dataDir = System.getProperty("user.dir") + "/data";
    private final String filePath = dataDir + "/duke.csv";

    protected FileManager() {
        createFile();
    }

    private void createFile() {
        try {
            File newDirectory = new File(dataDir);
            newDirectory.mkdir();
            File newFile = new File(filePath);
            newFile.createNewFile();
        } catch (IOException e) {
            Ui.fileCreationError();
        }
    }

    protected List<Task> getTasks() {
        List<Task> taskList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String header = br.readLine();
            if (header != null) {
                String line = br.readLine();
                while (line != null) {
                    Task newTask = CSVParser.parseToTask(line);
                    if (newTask != null) {
                        taskList.add(newTask);
                    }
                    line = br.readLine();
                }
            }
            return taskList;
        } catch (IOException e) {
            Ui.fileReadingError();
            return taskList;
        }
    }

    protected void update(List<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            String header = "Task type  ,Description  ,Time  ,Status\n";
            StringBuilder stringBuilder = new StringBuilder(header);
            for (Task task : tasks) {
                stringBuilder.append(convertToCSVFormat(task));
            }
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            Ui.fileUpdateError();
        }
    }

    private String convertToCSVFormat(Task task) {
        return String.format("%s  ,%s  ,%s  ,%s\n",
                task.getTaskName(),task.getDescription(), task.getTime(), task.getStatus());
    }
}
