package duke.storage;

import duke.task.Task;
import duke.tasklist.TaskList;

import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String dataDir = System.getProperty("user.dir") + "/data";
    private String filePath = dataDir + "/tasklist.csv";
    private Ui ui;

    public Storage() {
        createFile();
        this.ui = new Ui();
    }

    private void createFile() {
        try {
            File newDirectory = new File(dataDir);
            newDirectory.mkdir();
            File newFile = new File(filePath);
            newFile.createNewFile();
        } catch (IOException e) {
            ui.fileCreationError();
        }
    }

    public List<Task> getTasks() {
        List<Task> taskList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String header = br.readLine();
            if (header != null) {
                String line = br.readLine();
                while (line != null) {
                    Task newTask = CSVConverter.parseToTask(line, ui);
                    if (newTask != null) {
                        taskList.add(newTask);
                    }
                    line = br.readLine();
                }
            }
            return taskList;
        } catch (IOException e) {
            ui.fileReadingError();
            return taskList;
        }
    }

    public void update(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            String header = "Task type  ,Description  ,Time  ,Status\n";
            StringBuilder stringBuilder = new StringBuilder(header);
            for (Task task : tasks.getTasks()) {
                stringBuilder.append(convertToCSVFormat(task));
            }
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            ui.fileUpdateError();
        }
    }

    private String convertToCSVFormat(Task task) {
        return String.format("%s  ,%s  ,%s  ,%s\n",
                task.getTaskName(), task.getDescription(), task.getTime(), task.getStatus());
    }
}
