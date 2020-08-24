package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.ReadFailedException;
import duke.task.Task;
import duke.task.Tasks;

public class Storage {
    private final String filePath;
    private final File file;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = createEmptyFile();
    }

    private File createEmptyFile() throws IOException {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
        file.setExecutable(true, false);
        file.setReadable(true, false);
        file.setWritable(true, false);
        return file;
    }

    public Tasks getTasks() throws ReadFailedException {
        Tasks tasks = new Tasks();
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            throw new ReadFailedException("tasks");
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] stringArr = line.split("_");
            tasks.addTask(stringArr);
        }
        scanner.close();

        return tasks;
    }

    public void addTask(Task task) throws IOException {
        this.writeData(task.getData() + "\n", true);
    }

    private void writeData(String data, boolean appendMode) throws IOException {
        FileWriter fileWriter = new FileWriter(file, appendMode);
        fileWriter.write(data);
        fileWriter.close();
    }

    public void updateTasks(Tasks newTasks) throws IOException {
        this.writeData(newTasks.getData(), false);
    }
}
