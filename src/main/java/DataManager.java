package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataManager {
    final String filePath = "data/data.txt";

    public void save(List<Task> tasks) throws DukeException {
        try {
            File dataFile = new File(filePath);
            dataFile.createNewFile();
            FileWriter fw = new FileWriter(dataFile);
            String stringToWrite = "";
            for (Task task : tasks) {
                switch (task.taskType) {
                case TODO:
                    ToDo todo = (ToDo) task;
                    stringToWrite = todo.taskType.name() + "|" +
                            (todo.isDone ? "true" : "false") + "|" +
                            todo.description;
                    break;
                case EVENT:
                    Event event = (Event) task;
                    stringToWrite = event.taskType.name() + "|" +
                            (event.isDone ? "true" : "false") + "|" +
                            event.description + "|" +
                            event.time;
                    break;
                case DEADLINE:
                    Deadline deadline = (Deadline) task;
                    stringToWrite = deadline.taskType.name() + "|" +
                            (deadline.isDone ? "true" : "false") + "|" +
                            deadline.description + "|" +
                            deadline.time;
                    break;
                }
                fw.write(stringToWrite + "\n");
            }
            fw.close();
        } catch (IOException exception) {
            throw new DukeException("Could not save file!\nReason: " + exception.getMessage());
        }
    }

    public List<Task> load() throws DukeException {
        File dataFile = new File(filePath);

        if(!dataFile.exists()) {
            return new ArrayList<>();
        }

        try {
            Scanner sc = new Scanner(dataFile);
            List<Task> taskList = new ArrayList<>();

            while (sc.hasNextLine()) {
                String dataLine = sc.nextLine();
                String[] data = dataLine.split("\\|");
                TaskType taskType = TaskType.enumFromString(data[0]);
                try {
                    switch (taskType) {
                    case TODO:
                        taskList.add(new ToDo(data[2], Boolean.parseBoolean(data[1])));
                        break;
                    case EVENT:
                        taskList.add(new Event(data[2], Boolean.parseBoolean(data[1]), data[3]));
                        break;
                    case DEADLINE:
                        taskList.add(new Deadline(data[2], Boolean.parseBoolean(data[1]), data[3]));
                        break;
                    default:
                        throw new DukeException("Invalid data format provided!");
                    }
                } catch (IndexOutOfBoundsException exception) {
                    throw new DukeException("Not enough data provided!");
                }

            }
            return taskList;
        } catch (FileNotFoundException exception) {
            throw new DukeException("File could not be loaded!");
        }
    }
}
