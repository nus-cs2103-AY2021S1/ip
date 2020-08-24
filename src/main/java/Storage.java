package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private File dataFile;
    private final String FILE_PATH;

    public Storage(String filepath){
        this.FILE_PATH = filepath;
        dataFile = new File(filepath);
        try {
            if (dataFile.createNewFile()) {
                System.out.println("data file has been created");
            }
        } catch (IOException err) {
            System.out.println("error opening file");
        }
    }

    public void appendFile (String task) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write(task);
            fw.close();
        } catch (IOException err) {
            System.out.println("error writing " + task + " to file storage");
        }
    }

    public void overwriteFile (ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < list.size(); i++) {
                Task currentTask = list.get(i);
                String done = currentTask.isDone() ? "1" : "0";
                if (currentTask instanceof Event) {
                    fw.write("E | " + done + " | " + currentTask.getTaskName() + " | "
                            + ((Event) currentTask).getDate() + "\n");
                } else if (currentTask instanceof Deadline) {
                    fw.write("D | " + done + " | " + currentTask.getTaskName() + " | "
                            + ((Deadline) currentTask).getDate() + "\n");
                } else if (currentTask instanceof Todo) {
                    fw.write("T | " + done + " | " + currentTask.getTaskName() + "\n");
                }
            }
            fw.close();
        } catch (IOException err) {
            System.out.println("error overwriting file.");
        }
    }

    public ArrayList<Task> load() throws FileNotFoundException, DukeException {
        ArrayList<Task> toReturn = new ArrayList<>();
        Scanner reader = new Scanner(dataFile);
        while (reader.hasNext()) {
            String currentTask = reader.nextLine();
            String[] splits = currentTask.split(" \\| ");
            boolean done = Integer.parseInt(splits[1]) == 1;
            String type = splits[0];
            String task = splits[2];
            switch (type) {
                case ("T"):
                    Task todoTask = new Todo(task);
                    if (done) {
                        todoTask.checkOff();
                    }
                    toReturn.add(todoTask);
                    break;
                case ("D"):
                    String date = splits[3];
                    Deadline deadlineTask = new Deadline(task, date);
                    if (done) {
                        deadlineTask.checkOff();
                    }
                    toReturn.add(deadlineTask);
                    break;
                case ("E"):
                    String day = splits[3];
                    Event eventTask = new Event(task, day);
                    if (done) {
                        eventTask.checkOff();
                    }
                    toReturn.add(eventTask);
                    break;
            }
        }
        return toReturn;
    }
}
