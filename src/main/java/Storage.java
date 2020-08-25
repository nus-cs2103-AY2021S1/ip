package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public void saveFile(String data) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException err) {
            throw new DukeException("File Path is a directory -OR- Can't create file at location");
        }
    }

    public ArrayList<Task> loadFile() throws DukeException {
        try {
            ArrayList<Task> loadedTask = new ArrayList<>();
            Scanner scanner = new Scanner(this.file);
            String[] dataRead;
            while (scanner.hasNext()) {
                dataRead = readSavedData(scanner.nextLine());
                Task newTask = createSavedTask(dataRead);
                loadedTask.add(newTask);
            }
            return loadedTask;
        } catch (FileNotFoundException err) {
            throw new DukeException("File Not Found");
        }
    }

    private String[] readSavedData(String inputLine) {
        String[] parts = inputLine.split(" \\| ");
        ArrayList<String> result = new ArrayList<>();
        for (String part : parts) {
            result.add(part.trim());
        }
        return result.toArray(parts);
    }

    private Task createSavedTask(String[] args) throws DukeException {
        Task newTask;
        try{
            switch(args[0]) {
            case "T":
                newTask = new ToDo(args[2]);
                break;
            case "D":
                newTask = new Deadline(args[2], args[3]);
                break;
            case "E":
                newTask = new Event(args[2], args[3]);
                break;
            default:
                throw new DukeException("Error: Saved File is badly corrupted");
            }
        } catch (Exception err) {
            throw new DukeException("Error: Saved File is badly corrupted");
        }

        if (args[1].equals("1")) {
            newTask.setDone();
        }
        return newTask;
    }
}
