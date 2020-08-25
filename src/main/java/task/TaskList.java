package task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Objects;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import exception.CorruptedDataException;
import net.harawata.appdirs.AppDirs;
import net.harawata.appdirs.AppDirsFactory;

public class TaskList extends ArrayList<Task> {
    private static final long serialVersionUID = 1L;
    private static final String DATA_STORAGE_FILE_NAME = "tasks.csv";

    public TaskList() {
        readDataStorage();
    }

    public void update() {
        try {
            File dataFile = new File(getDataStoragePath(), DATA_STORAGE_FILE_NAME);
            FileOutputStream outputStream = new FileOutputStream(dataFile);
            CSVWriter writer = new CSVWriter(new OutputStreamWriter(outputStream));
            for (Task task : this) {
                String[] line = new String[] {
                    task.getTaskType(),
                    task.isCompleted() ? "1" : "0",
                    task.getContent(),
                    Objects.toString(task.getTime()) // could be null
                };
                writer.writeNext(line);
            }
            writer.close();
        } catch (SecurityException | IOException err) {
            System.err.println("Warning: unable to write to data storage. "
                + "Your task list is not saved.");
        }
    }

    private void readDataStorage() {
        try {
            File dataFile = new File(getDataStoragePath(), DATA_STORAGE_FILE_NAME);
            dataFile.getParentFile().mkdirs(); // create the data directory if not exists
            dataFile.createNewFile(); // create the file if not exists
            FileInputStream inputStream = new FileInputStream(dataFile);
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            for (String[] parts : reader) {
                Task task;
                boolean completed = Objects.equals(parts[1], "1");
                switch (parts[0]) {
                    case "T": {
                        task = new Todo(completed, parts[2]);
                        break;
                    }
                    case "E": {
                        task = new Event(completed, parts[2], parts[3]);
                        break;
                    }
                    case "D": {
                        task = new Deadline(completed, parts[2], parts[3]);
                        break;
                    }
                    default: {
                        throw new CorruptedDataException();
                    }
                }
                add(task);
            }
        } catch (SecurityException | IOException err) {
            err.printStackTrace(System.err);
            System.err.println("Warning: unable to read data storage. "
                + "Your task list is not loaded.");
        } catch (CorruptedDataException | IndexOutOfBoundsException err) {
            err.printStackTrace(System.err);
            System.err.println("Warning: Corrupted data storage. "
                + "Your task list may not be fully loaded.");
        }
    }

    private static String getDataStoragePath() {
        AppDirs appDirs = AppDirsFactory.getInstance();
        return appDirs.getUserDataDir("tickbot", null, "HouRui");
    }
}