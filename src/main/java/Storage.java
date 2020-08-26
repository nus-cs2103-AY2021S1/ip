package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(this.filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String[] currLine = scanner.nextLine().split(" \\| ");
                String typeOfTask = currLine[0];
                switch (typeOfTask) {
                case "T":
                    Task todo = new Todo(currLine[2]);
                    if (Integer.parseInt(currLine[1]) == 1) {
                        todo.doTask();
                    }
                    list.add(todo);
                    break;
                case "D":
                    Task deadline = new Deadline(currLine[2], currLine[3]);
                    if (Integer.parseInt(currLine[1]) == 1) {
                        deadline.doTask();
                    }
                    list.add(deadline);
                    break;
                case "E":
                    Task event = new Event(currLine[2], currLine[3]);
                    if (Integer.parseInt(currLine[1]) == 1) {
                        event.doTask();
                    }
                    list.add(event);
                    break;
                default:
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to load file.. :c");
        }
    }

    public void saveTaskList(String taskToAdd) {
        try {
            FileWriter fw = new FileWriter(this.file, true);
            fw.write(taskToAdd + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editTaskList(String newTask, int taskNum, boolean delete) {
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(this.filePath)));
            if (delete) {
                fileContent.remove(taskNum - 1);
            } else {
                fileContent.set(taskNum - 1, newTask);
            }
            Files.write(Paths.get(this.filePath), fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
