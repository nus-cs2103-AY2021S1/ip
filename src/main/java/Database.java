import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    private String directory;
    private File db;

    public static Database dbInstance() {
        return new Database().init();
    }

    private Database init() {
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

    public ArrayList<Task> getTaskListFromDatabase() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File(this.directory);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                taskList.add(createTaskFromDatabase(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file does not exist in directory");
        }
        listAllItemsFromDatabase(taskList);
        return taskList;
    }

    public void listAllItemsFromDatabase(ArrayList<Task> taskList) {
        System.out.println("Your saved tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            String output = (i + 1) + "." + taskList.get(i);
            System.out.println(output);
        }
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

    public void updateDatabase(ArrayList<Task> taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        taskList.forEach(task -> stringBuilder.append(task.formatTaskForDatabase() + "\n"));


        try {
            FileWriter fileWriter = new FileWriter(this.directory);
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error while updating database file");
        }
    }

}
