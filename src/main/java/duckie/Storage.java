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

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public ArrayList<Task> load() throws DuckieException {
        File duckieFile = openFile();
        ArrayList<Task> lst = new ArrayList<>();
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
                        taskToDo.checked();
                    }
                    lst.add(taskToDo);
                    break;
                case "D":
                    String date = taskBreakdown[3].strip();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
                    LocalDate d1 = LocalDate.parse(date, formatter);
                    Deadline taskD = new Deadline(description, d1);
                    if (isDone.equals("1")) {
                        taskD.checked();
                    }
                    lst.add(taskD);
                    break;
                case "E":
                    String dateTime = taskBreakdown[3].strip();
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("E, MMM dd yyyy hh:mm a");
                    LocalDateTime d2 = LocalDateTime.parse(dateTime, formatter2);
                    Event taskE = new Event(description, d2);
                    if (isDone.equals("1")) {
                        taskE.checked();
                    }
                    lst.add(taskE);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new DuckieException("Quack! Duckie cannot find your File!");
        }

        if (lst.size() == 0) {
            Ui.displayNoListReply();
        } else {
            Ui.displayListReply(lst);
            Ui.showLine();
        }
        return lst;
    }

    public void saveToFile(ArrayList<Task> lst) throws DuckieException {
        try {
            FileWriter fw = new FileWriter(filePath);
            String toWrite = "";
            for (Task t1 : lst) {
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
