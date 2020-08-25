package main.java.manager;

import main.java.tasks.Task;
import main.java.tasks.Todo;
import main.java.tasks.Deadline;
import main.java.tasks.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

    private String pathname;
    private File dataFile;

    public static Database initializeDatabase() {
        return new Database().initialize();
    }

    private Database initialize() {
        String rootPath = new File(System.getProperty("user.dir"))
                .getParentFile()
                .getPath();
        this.pathname = String.format("%s/data/database.txt", rootPath);
        try {
            this.dataFile = new File(this.pathname);
            this.dataFile.getParentFile().mkdirs();
            this.dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("File already exists.");
        }
        return this;
    }

    public List<Task> retrieveTaskList() {
        List<Task> taskList = new ArrayList<>();
        try {
            File dataFile = new File(this.pathname);
            Scanner sc = new Scanner(dataFile);
            while (sc.hasNextLine()) {
                taskList.add(createSavedTask(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file does not exist.");
        }
        listSavedTasks(taskList);
        return taskList;
    }

    public void listSavedTasks(List<Task> taskList) {
        System.out.println("Here are your saved tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
    }

    public Task createSavedTask(String string) {
        String[] words = string.split(" \\| ");
        String taskType = words[0];
        boolean isDone = words[1].equals("true");
        String description = words[2];

        switch (taskType) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                return new Deadline(description, words[3], isDone);
            case "E":
                return new Event(description, words[3], isDone);
        }
        return null;
    }

    public void updateDatabase(List<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(task.databaseString() + "\n");
        }
        try {
            FileWriter fileWriter = new FileWriter(this.pathname);
            fileWriter.write(sb.toString());
            fileWriter.close();
        } catch(IOException e) {
            System.out.println("Cannot update database file.");
        }
    }
}
