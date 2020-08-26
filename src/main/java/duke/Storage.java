package main.java.duke;

import main.java.duke.exceptions.InvalidFileException;
import main.java.duke.exceptions.InvalidInputException;
import main.java.duke.tasks.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {

    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public final String path;

    public Storage(String storagePath) {
        this.path = storagePath;
    }


    public void save(TaskList taskList) throws InvalidFileException {
        ArrayList<Task> currTaskList = taskList.getTaskList();
        File saved = new File(this.path);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(saved));
            for (Task t: currTaskList) {
                bw.write(t.getState());
                bw.newLine();
                bw.flush();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new InvalidFileException("Failed to save");
        }
    }

    public TaskList load() throws InvalidFileException {
        TaskList taskList = new TaskList();
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            File saved = new File(this.path);
            if (!saved.exists()) {
                try {
                    saved.createNewFile();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            BufferedReader br = new BufferedReader(new FileReader(saved));
            String currLine;
            while ((currLine = br.readLine()) != null) {
                String[] readLine = currLine.split("\\|");
                boolean taskDone = readLine[1].equals("1");
                Task newTask = new Task("");
                String type = readLine[0];
                switch(type) {
                    case "T":
                        newTask = new Todo(readLine[2]);
                        break;
                    case "D":
                        newTask = new Deadline(readLine[2], LocalDateTime.parse(readLine[3], formatter));
                        break;
                    case "E":
                        newTask = new Event(readLine[2], LocalDateTime.parse(readLine[3], formatter));
                        break;
                    default:
                        break;
                }
                taskList.addTask(newTask);
                if (taskDone) {
                    newTask.markAsDone();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new InvalidFileException("Failed to load");
        }
        return taskList;
    }
}
