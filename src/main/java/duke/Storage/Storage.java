package duke.Storage;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Storage {
    private String directory;
    private File db;

    public static Storage dbInstance() {
        return new Storage().init();
    }

    private Storage init() {
        String projectRoot = new File(System.getProperty("user.dir"))
                .getParentFile()
                .getPath();
        this.directory = String.format("%s/data/db.txt", projectRoot);
        try {
            this.db = new File(this.directory);
            this.db.getParentFile().mkdirs();
            this.db.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file, file might already exist.");
        }
        return this;
    }

    public TaskList getTaskListFromDatabase() {
        TaskList taskList = new TaskList();
        try {
            File file = new File(this.directory);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                taskList.addTask(createTaskFromDatabase(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file does not exist in directory");
        }
        return taskList;
    }

    public Task createTaskFromDatabase(String string) {
        String[] parts = string.split("\\|");
        String typeOfTask = parts[0];
        int doneStatus = Integer.parseInt(parts[1]);
        String details = parts[2];

        if (typeOfTask.equals("T")) {
            return new Todo(doneStatus, details);
        } else if (typeOfTask.equals("D")) {
            LocalDateTime timing = LocalDateTime.parse(parts[3]);
            return new Deadline(doneStatus, details, timing);
        } else if (typeOfTask.equals("E")) {
            LocalDateTime timing = LocalDateTime.parse(parts[3]);
            return new Event(doneStatus, details, timing);
        }
        return null;
    }

    public String getDirectory() {
        return this.directory;
    }

}
