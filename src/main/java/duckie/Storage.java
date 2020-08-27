package duckie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

import duckie.exception.*;
import duckie.Ui;
import duckie.task.*;

/**
 * Deals with the stored duckie file in the HardDrive
 */
public class Storage {
    private String filePath;

    /**
     * Instantiate a Storage object
     * @param filePath Path of duckie.txt
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Opens the duckie file from the harddrive
     * Creates the file and directory if cannot be found
     * @return File containing the saved tasks
     * @throws DuckieException
     */
    public File openFile() throws DuckieException {
        try {
            File duckieFile = new File(this.filePath);
            if (!duckieFile.exists()) {
                File dir = new File(duckieFile.getParent());
                if (!dir.exists()) {
                    if (dir.mkdirs() && duckieFile.createNewFile()) {
                        System.out.println("\t" + "Memory File created successfully!");
                    } else {
                        throw new DuckieException("\t" + "Quack! Unable to create file!");
                    }
                } else {
                    if (duckieFile.createNewFile()) {
                        System.out.println("\t" + "Memory File created successfully!");
                    } else {
                        throw new DuckieException("Quack! Unable to create file!");
                    }
                }
            }
            return duckieFile;
        } catch (IOException e) {
            throw new DuckieFileErrorException();
        }
    }

    /**
     * Load the duckie file and generate the ArrayList containing the tasks
     * @return ArrayList containing all the saved tasks
     * @throws DuckieException
     */
    public ArrayList<Task> load() throws DuckieException {
        File duckieFile = openFile();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(duckieFile);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] taskBreakdown = task.split("\\|");

                String type = taskBreakdown[0].strip();
                String isDone = taskBreakdown[1].strip();
                String description = taskBreakdown[2].strip();
                switch (type) {
                case "T":
                    Todo taskToDo = new Todo(description);
                    if (isDone.equals("1")) {
                        taskToDo.markDone();
                    }
                    tasks.add(taskToDo);
                    break;
                case "D":
                    String date = taskBreakdown[3].strip();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
                    LocalDate d1 = LocalDate.parse(date, formatter);
                    Deadline taskD = new Deadline(description, d1);
                    if (isDone.equals("1")) {
                        taskD.markDone();
                    }
                    tasks.add(taskD);
                    break;
                case "E":
                    String dateTime = taskBreakdown[3].strip();
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm a");
                    LocalDateTime d2 = LocalDateTime.parse(dateTime, formatter2);
                    Event taskE = new Event(description, d2);
                    if (isDone.equals("1")) {
                        taskE.markDone();
                    }
                    tasks.add(taskE);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new DuckieException("Quack! Duckie cannot find your File!");
        }

        if (tasks.size() == 0) {
            Ui.displayNoListReply();
        } else {
            Ui.displayListReply(tasks);
            Ui.showLine();
        }
        return tasks;
    }

    /**
     * Update the current tasks in the TaskList to the duckie file
     * @param tasks List containing all the current tasks
     * @throws DuckieException
     */
    public void saveToFile(ArrayList<Task> tasks) throws DuckieException {
        try {
            FileWriter fw = new FileWriter(filePath);
            String toWrite = "";
            for (Task t1 : tasks) {
                toWrite += (t1.getType() + (t1.isCompleted() ? " | 1 | " : " | 0 | ")
                        + t1.getDescription())
                        + (t1.getDate() != null ? "| " + t1.getDate() : "")
                        + System.lineSeparator();
            }
            fw.write(toWrite);
            fw.close();
        } catch (IOException e) {
            throw new DuckieFileErrorException();
        }
    }
}
