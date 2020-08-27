package duke.core;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    File saveData;
    String filePath;
    String dirPath;

    public Storage(String filePath, String dirPath) {
        this.filePath = filePath;
        this.dirPath = dirPath;
    }

    public ArrayList<Task> loadData() {
        ArrayList<Task> tasks = new ArrayList<>();
        saveData = new File(filePath);

        boolean dirExists = new File(dirPath).exists();

        if (!dirExists) new File(dirPath).mkdirs();

        // checking if the save file is already there
        try {
            boolean fileCreated = saveData.createNewFile();
            if (fileCreated) {
                System.out.println("Haven't seen a new face around 'ere for awhile, have a seat!");
            } else {
                System.out.println("A regular! The usual, I presume?\n"
                        + "I've still got your order history, care to take a look?");
                Scanner saveReader = new Scanner(saveData);
                while (saveReader.hasNextLine()) {
                    try {
                        String saveEntry = saveReader.nextLine();
                        String[] keywords = saveEntry.split(":");
                        Task savedTask = null;
                        switch (keywords[0]) {
                            case "T":
                                savedTask = new Todo(keywords[2]);
                                break;
                            case "D":
                                savedTask = new Deadline(keywords[2], LocalDate.parse(keywords[3]));
                                break;
                            case "E":
                                savedTask = new Event(keywords[2], LocalDate.parse(keywords[3]));
                                break;
                            default:
                                break;
                        }
                        if (savedTask != null) {
                            if (keywords[1].equals("y")) savedTask.markAsDone();
                            tasks.add(savedTask);
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Looks like some of your records got messed up! Sorry 'bout that!");
                    }
                }
            }
        } catch (
                IOException exception) {
            System.out.println(exception);
        }
        return tasks;
    }

    public void saveData(ArrayList<Task> taskList) {
        BufferedWriter saveWriter = null;
        try {
            saveWriter = new BufferedWriter(new FileWriter(saveData));
            StringBuffer saveContentBuffer = new StringBuffer();
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                saveContentBuffer.append(task.createSaveDataLine() + "\n");
            }

            String saveContent = saveContentBuffer.toString();

            saveWriter.write(saveContent);
        } catch (IOException exception) {
            System.out.println(exception);
        } finally {
            try {
                if (saveWriter != null) {
                    saveWriter.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
