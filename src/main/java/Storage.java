package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    static String directory = "/data";
    static String filename = "/data/taskList.txt";
    File file;

    Storage() {
        this.file = new File(filename);
    }

    List<Task> readFromFile() {
        List<Task> taskList = new ArrayList<>();
        try {
            new File(Storage.directory).mkdir();
            this.file.createNewFile();
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String[] input = scanner.nextLine().split("\\s\\|\\s");
                boolean isDone = input[1].equals("1");
                switch (input[0]) {
                case "E":
                    taskList.add(new Event(input[2], isDone, LocalDate.parse(input[3])));
                    break;
                case "T":
                    taskList.add(new ToDo(input[2], isDone));
                    break;
                case "D":
                    taskList.add(new Deadline(input[2], isDone, LocalDate.parse(input[3])));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DateTimeException e) {
            System.out.println("Problem reading file.");
        }
        return taskList;
    }

    void writeToFile(List<? extends Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter(Storage.filename);
            taskList.forEach(task -> {
                try {
                    fileWriter.write(task.print() + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void appendToFile(Task task) {
        try {
            FileWriter fileWriter = new FileWriter(Storage.filename, true);
            fileWriter.write(task.print() + System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
